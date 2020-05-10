package com.vlasov.ochercity.repository.shippingmethod;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.vlasov.ochercity.AppExecutors;
import com.vlasov.ochercity.Config;
import com.vlasov.ochercity.api.ApiResponse;
import com.vlasov.ochercity.api.PSApiService;
import com.vlasov.ochercity.db.PSCoreDb;
import com.vlasov.ochercity.db.ShippingMethodDao;
import com.vlasov.ochercity.repository.common.NetworkBoundResource;
import com.vlasov.ochercity.repository.common.PSRepository;
import com.vlasov.ochercity.utils.Utils;
import com.vlasov.ochercity.viewobject.ShippingCost;
import com.vlasov.ochercity.viewobject.ShippingCostContainer;
import com.vlasov.ochercity.viewobject.ShippingMethod;
import com.vlasov.ochercity.viewobject.common.Resource;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Response;

public class ShippingMethodRepository extends PSRepository {

    private ShippingMethodDao shippingMethodDao;

    /**
     * Constructor of PSRepository
     *
     * @param psApiService Panacea-Soft API Service Instance
     * @param appExecutors Executors Instance
     * @param db           Panacea-Soft DB
     */
    @Inject
    ShippingMethodRepository(PSApiService psApiService, AppExecutors appExecutors, PSCoreDb db, ShippingMethodDao shippingMethodDao) {
        super(psApiService, appExecutors, db);

        Utils.psLog("Inside ProductRepository");

        this.shippingMethodDao = shippingMethodDao;
    }

    public LiveData<Resource<List<ShippingMethod>>> getAllShippingMethods() {
        return new NetworkBoundResource<List<ShippingMethod>, List<ShippingMethod>>(appExecutors) {

            @Override
            protected void saveCallResult(@NonNull List<ShippingMethod> itemList) {
                Utils.psLog("SaveCallResult of getAllShippingMethods.");

                db.beginTransaction();

                try {

                    db.shippingMethodDao().insertAll(itemList);

                    db.setTransactionSuccessful();

                } catch (Exception e) {
                    Utils.psErrorLog("Error in doing transaction of getAllShippingMethods.", e);
                } finally {
                    db.endTransaction();
                }
            }

            @Override
            protected boolean shouldFetch(@Nullable List<ShippingMethod> data) {

                // Recent news always load from server
                return connectivity.isConnected();

            }

            @NonNull
            @Override
            protected LiveData<List<ShippingMethod>> loadFromDb() {
                Utils.psLog("Load getAllShippingMethods From Db");

                return shippingMethodDao.getShippingMethods();

            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<List<ShippingMethod>>> createCall() {
                Utils.psLog("Call API Service to getAllShippingMethods.");

                return psApiService.getShipping(Config.API_KEY);

            }

            @Override
            protected void onFetchFailed(String message) {
                Utils.psLog("Fetch Failed (getAllShippingMethods) : " + message);
            }

        }.asLiveData();

    }

    public LiveData<Resource<ShippingCost>> postShippingByCountryAndCity(ShippingCostContainer shippingCostContainer) {

        final MutableLiveData<Resource<ShippingCost>> statusLiveData = new MutableLiveData<>();

        appExecutors.networkIO().execute(() -> {

            Response<ShippingCost> response;

            try {
                response = psApiService.postShippingByCountryAndCity( Config.API_KEY, shippingCostContainer).execute();

                ApiResponse<ShippingCost> apiResponse = new ApiResponse<>(response);

                if (apiResponse.isSuccessful()) {
                    statusLiveData.postValue(Resource.success(apiResponse.body));
                } else {
                    statusLiveData.postValue(Resource.error(apiResponse.errorMessage, null));
                }

            } catch (IOException e) {
                statusLiveData.postValue(Resource.error(e.getMessage(), null));
            }

        });

        return statusLiveData;

    }
}
