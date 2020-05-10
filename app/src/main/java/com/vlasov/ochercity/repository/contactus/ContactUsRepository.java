package com.vlasov.ochercity.repository.contactus;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.vlasov.ochercity.AppExecutors;
import com.vlasov.ochercity.api.ApiResponse;
import com.vlasov.ochercity.api.PSApiService;
import com.vlasov.ochercity.db.PSCoreDb;
import com.vlasov.ochercity.repository.common.PSRepository;
import com.vlasov.ochercity.utils.Utils;
import com.vlasov.ochercity.viewobject.ApiStatus;
import com.vlasov.ochercity.viewobject.common.Resource;

import javax.inject.Inject;

import retrofit2.Response;

public class ContactUsRepository extends PSRepository {

    @Inject
    ContactUsRepository(PSApiService psApiService, AppExecutors appExecutors, PSCoreDb db) {
        super(psApiService, appExecutors, db);

    }

    /**
     * Post Contact Us
     * @param apiKey APIKey to access Web Service
     * @param contactName Name
     * @param contactEmail Email
     * @param contactDesc Desc
     * @return Status of Post
     */
    public LiveData<Resource<Boolean>> postContactUs(String apiKey, String contactName, String contactEmail, String contactDesc, String contactPhone) {

        final MutableLiveData<Resource<Boolean>> statusLiveData = new MutableLiveData<>();
        appExecutors.networkIO().execute(() -> {
            try {

                // Call the API Service
                Response<ApiStatus> response = psApiService.rawPostContact(apiKey, contactName, contactEmail, contactDesc, contactPhone).execute();

                // Wrap with APIResponse Class
                ApiResponse<ApiStatus> apiResponse = new ApiResponse<>(response);

                Utils.psLog("apiResponse " + apiResponse);
                // If response is successful
                if (apiResponse.isSuccessful()) {

                    statusLiveData.postValue(Resource.success(true));
                } else {

                    statusLiveData.postValue(Resource.error(apiResponse.errorMessage, true));
                }
            } catch (Exception e) {
                statusLiveData.postValue(Resource.error(e.getMessage(), true));
            }

        });

        return statusLiveData;

    }

}
