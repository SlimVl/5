package com.vlasov.ochercity.viewmodel.subcategory;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.vlasov.ochercity.Config;
import com.vlasov.ochercity.repository.subcategory.SubCategoryRepository;
import com.vlasov.ochercity.utils.AbsentLiveData;
import com.vlasov.ochercity.utils.Utils;
import com.vlasov.ochercity.viewmodel.common.PSViewModel;
import com.vlasov.ochercity.viewobject.SubCategory;
import com.vlasov.ochercity.viewobject.common.Resource;

import java.util.List;

import javax.inject.Inject;

public class SubCategoryViewModel extends PSViewModel {

    private LiveData<Resource<List<SubCategory>>> allSubCategoryListData;
    private MutableLiveData<TmpDataHolder> allSubCategoryListObj = new MutableLiveData<>();

//    private LiveData<Resource<List<SubCategory>>> subCategoryListData;
//    private MutableLiveData<TmpDataHolder> subCategoryListObj = new MutableLiveData<>();

//    private LiveData<Resource<Boolean>> nextPageLoadingStateData;
//    private MutableLiveData<TmpDataHolder> nextPageLoadingStateObj = new MutableLiveData<>();

    private LiveData<Resource<List<SubCategory>>> subCategoryListWithCatIdData;
    private MutableLiveData<TmpDataHolder> subCategoryListWithCatIdObj = new MutableLiveData<>();

    private LiveData<Resource<Boolean>> nextPageLoadingStateWithCatIdData;
    private MutableLiveData<TmpDataHolder> nextPageLoadingStateWithCatIdObj = new MutableLiveData<>();


    @Inject
    SubCategoryViewModel(SubCategoryRepository repository) {
        Utils.psLog("Inside SubCategoryViewModel");

        allSubCategoryListData = Transformations.switchMap(allSubCategoryListObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }
            Utils.psLog("allSubCategoryListData");
            return repository.getAllSubCategoryList(Config.API_KEY);
        });

//        subCategoryListData = Transformations.switchMap(subCategoryListObj, obj -> {
//            if (obj == null) {
//                return AbsentLiveData.create();
//            }
//            Utils.psLog("subCategoryListData");
//            return repository.getSubCategoryList(Config.API_KEY, obj.limit, obj.offset);
//        });

//        nextPageLoadingStateData = Transformations.switchMap(nextPageLoadingStateObj, obj -> {
//            if (obj == null) {
//                return AbsentLiveData.create();
//            }
//            Utils.psLog("nextPageLoadingStateData");
//            return repository.getNextPageSubCategory( obj.limit, obj.offset);
//        });

        subCategoryListWithCatIdData = Transformations.switchMap(subCategoryListWithCatIdObj, obj -> {

            if (obj == null) {
                return AbsentLiveData.create();
            }

            return repository.getSubCategoriesWithCatId(obj.loginUserId, obj.offset, obj.catId);
        });

        nextPageLoadingStateWithCatIdData = Transformations.switchMap(nextPageLoadingStateWithCatIdObj, new Function<TmpDataHolder, LiveData<Resource<Boolean>>>() {
            @Override
            public LiveData<Resource<Boolean>> apply(TmpDataHolder obj) {

                if (obj == null) {
                    return AbsentLiveData.create();
                }

                return repository.getNextPageSubCategoriesWithCatId(obj.loginUserId, obj.limit, obj.offset, obj.catId);
            }
        });
    }

    public void setSubCategoryListWithCatIdObj(String loginUserId, String offset, String catId) {
        TmpDataHolder tmpDataHolder = new TmpDataHolder();
        tmpDataHolder.loginUserId = loginUserId;
        tmpDataHolder.offset = offset;
        tmpDataHolder.catId = catId;

        subCategoryListWithCatIdObj.setValue(tmpDataHolder);
    }

    public LiveData<Resource<List<SubCategory>>> getsubCategoryListWithCatIdData() {

        return subCategoryListWithCatIdData;
    }

    public LiveData<Resource<Boolean>> getnextPageLoadingStateWithCatIdData() {

        return nextPageLoadingStateWithCatIdData;
    }


    public void setNextPageLoadingStateWithCatIdObj(String loginUserId, String limit, String offset, String catId) {
        if (!isLoading) {
            TmpDataHolder tmpDataHolder = new TmpDataHolder();
            tmpDataHolder.loginUserId = loginUserId;
            tmpDataHolder.limit = limit;
            tmpDataHolder.offset = offset;
            tmpDataHolder.catId = catId;

            nextPageLoadingStateWithCatIdObj.setValue(tmpDataHolder);

            setLoadingState(true);
        }
    }


    public void setAllSubCategoryListObj() {
        if (!isLoading) {
            TmpDataHolder tmpDataHolder = new TmpDataHolder();
            allSubCategoryListObj.setValue(tmpDataHolder);

            // start loading
            setLoadingState(true);
        }
    }

    public LiveData<Resource<List<SubCategory>>> getAllSubCategoryListData() {
        return allSubCategoryListData;
    }


    class TmpDataHolder {
        public String loginUserId = "";
        public String shopId = "";
        public String offset = "";
        public String limit = "";
        public String catId = "";
        public Boolean isConnected = false;


    }
}
