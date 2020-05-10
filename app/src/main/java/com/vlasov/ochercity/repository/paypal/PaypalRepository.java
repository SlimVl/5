package com.vlasov.ochercity.repository.paypal;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.vlasov.ochercity.AppExecutors;
import com.vlasov.ochercity.Config;
import com.vlasov.ochercity.api.ApiResponse;
import com.vlasov.ochercity.api.PSApiService;
import com.vlasov.ochercity.db.PSCoreDb;
import com.vlasov.ochercity.repository.common.PSRepository;
import com.vlasov.ochercity.utils.Utils;
import com.vlasov.ochercity.viewobject.ApiStatus;
import com.vlasov.ochercity.viewobject.common.Resource;

import java.io.IOException;

import javax.inject.Inject;

import retrofit2.Response;

public class PaypalRepository extends PSRepository {


    @Inject
    PaypalRepository(PSApiService psApiService, AppExecutors appExecutors, PSCoreDb db) {
        super(psApiService, appExecutors, db);

        Utils.psLog("Inside PaypalRepository");
    }

    public LiveData<Resource<Boolean>> getPaypalToekn() {

        final MutableLiveData<Resource<Boolean>> statusLiveData = new MutableLiveData<>();

        appExecutors.networkIO().execute(() -> {

            Response<ApiStatus> response;

            try {
                response = psApiService.getPaypalToken(Config.API_KEY).execute();

                ApiResponse<ApiStatus> apiResponse = new ApiResponse<>(response);

                if (apiResponse.isSuccessful()) {
                    if(apiResponse.body != null) {
                        statusLiveData.postValue(Resource.successWithMsg(apiResponse.body.message, true));
                    }else {
                        statusLiveData.postValue(Resource.error(apiResponse.errorMessage, false));
                    }
                } else {
                    statusLiveData.postValue(Resource.error(apiResponse.errorMessage, false));
                }

            } catch (IOException e) {
                statusLiveData.postValue(Resource.error(e.getMessage(), false));
            }

        });

        return statusLiveData;
    }
}
