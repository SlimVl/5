package com.vlasov.ochercity.repository.category;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.vlasov.ochercity.AppExecutors;
import com.vlasov.ochercity.Config;
import com.vlasov.ochercity.api.ApiResponse;
import com.vlasov.ochercity.api.PSApiService;
import com.vlasov.ochercity.db.CategoryDao;
import com.vlasov.ochercity.db.PSCoreDb;
import com.vlasov.ochercity.repository.common.NetworkBoundResource;
import com.vlasov.ochercity.repository.common.PSRepository;
import com.vlasov.ochercity.utils.Utils;
import com.vlasov.ochercity.viewobject.Category;
import com.vlasov.ochercity.viewobject.CategoryMap;
import com.vlasov.ochercity.viewobject.common.Resource;
import com.vlasov.ochercity.viewobject.holder.CategoryParameterHolder;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CategoryRepository extends PSRepository {


    //region Variables

    private final CategoryDao categoryDao;

    //endregion


    //region Constructor

    @Inject
    CategoryRepository(PSApiService psApiService, AppExecutors appExecutors, PSCoreDb db, CategoryDao categoryDao) {
        super(psApiService, appExecutors, db);

        Utils.psLog("Inside CategoryRepository");

        this.categoryDao = categoryDao;
    }

    //endregion


    //region Category Repository Functions for ViewModel

    /**
     * Load Category List
     */

    public LiveData<Resource<List<Category>>> getAllSearchCategory(CategoryParameterHolder categoryParameterHolder, String limit, String offset) {

        return new NetworkBoundResource<List<Category>, List<Category>>(appExecutors) {

            @Override
            protected void saveCallResult(@NonNull List<Category> item) {
                Utils.psLog("SaveCallResult of getAllCategoriesWithUserId");


                try {
                    db.beginTransaction();

                    String mapKey = categoryParameterHolder.changeToMapValue();
                    db.categoryMapDao().deleteByMapKey(mapKey);
                    categoryDao.insertAll(item);
                    String dateTime = Utils.getDateTime();

                    for (int i = 0; i < item.size(); i++) {
                        db.categoryMapDao().insert(new CategoryMap(mapKey + item.get(i).id, mapKey, item.get(i).id, i + 1, dateTime));
                    }

                    db.setTransactionSuccessful();

                } catch (Exception e) {
                    Utils.psErrorLog("Error in doing transaction of getAllCategoriesWithUserId", e);
                } finally {
                    db.endTransaction();
                }
            }


            @Override
            protected boolean shouldFetch(@Nullable List<Category> data) {

                return connectivity.isConnected();
            }

            @NonNull
            @Override
            protected LiveData<List<Category>> loadFromDb() {

                Utils.psLog("Load From Db (All Categories)");
                String mapKey = categoryParameterHolder.changeToMapValue();

                if(!limit.equals(String.valueOf(Config.LOAD_FROM_DB))){
                    return categoryDao.getCategoryByKeyTest(mapKey);
                }else {
                    return categoryDao.getCategoryByKeyTestByLimit(mapKey,limit);
                }


            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<List<Category>>> createCall() {
                Utils.psLog("Call Get All Categories webservice.");

                return psApiService.getSearchCategory(Config.API_KEY, limit, offset, categoryParameterHolder.order_by);
            }

            @Override
            protected void onFetchFailed(String message) {
                Utils.psLog("Fetch Failed of About Us");
            }

        }.asLiveData();
    }

    public LiveData<Resource<Boolean>> getNextSearchCategory(String limit, String offset, CategoryParameterHolder categoryParameterHolder) {
        final MediatorLiveData<Resource<Boolean>> statusLiveData = new MediatorLiveData<>();
        LiveData<ApiResponse<List<Category>>> apiResponse = psApiService.getSearchCategory(Config.API_KEY, limit, offset, categoryParameterHolder.order_by);

        statusLiveData.addSource(apiResponse, response -> {

            statusLiveData.removeSource(apiResponse);

            //noinspection Constant Conditions
            if (response.isSuccessful()) {

                appExecutors.diskIO().execute(() -> {


                    try {
                        db.beginTransaction();

                        if (response.body != null) {

                            db.categoryMapDao().insertAll(response.body);

                            int finalIndex = db.categoryMapDao().getMaxSortingByValue(categoryParameterHolder.changeToMapValue());

                            int startIndex = finalIndex + 1;

                            String mapKey = categoryParameterHolder.changeToMapValue();
                            String dateTime = Utils.getDateTime();

                            for (int i = 0; i < response.body.size(); i++) {
                                db.categoryMapDao().insert(new CategoryMap(mapKey + response.body.get(i).id, mapKey, response.body.get(i).id, startIndex + i, dateTime));
                            }

                            //db.trendingCategoryDao().insertAll(new TrendingCategory(apiResponse.body.));
                        }

                        db.setTransactionSuccessful();
                    } catch (NullPointerException ne) {
                        Utils.psErrorLog("Null Pointer Exception : ", ne);
                    } catch (Exception e) {
                        Utils.psErrorLog("Exception : ", e);
                    } finally {
                        db.endTransaction();
                    }

                    statusLiveData.postValue(Resource.success(true));
                });

            } else {
                statusLiveData.postValue(Resource.error(response.errorMessage, null));
            }

        });

        return statusLiveData;
    }
}