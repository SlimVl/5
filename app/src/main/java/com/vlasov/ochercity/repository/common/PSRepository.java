package com.vlasov.ochercity.repository.common;

import androidx.lifecycle.LiveData;

import com.vlasov.ochercity.AppExecutors;
import com.vlasov.ochercity.Config;
import com.vlasov.ochercity.api.PSApiService;
import com.vlasov.ochercity.db.PSCoreDb;
import com.vlasov.ochercity.utils.AbsentLiveData;
import com.vlasov.ochercity.utils.Connectivity;
import com.vlasov.ochercity.utils.RateLimiter;
import com.vlasov.ochercity.utils.Utils;
import com.vlasov.ochercity.viewobject.common.Resource;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

public abstract class PSRepository {


    //region Variables

    protected final PSApiService psApiService;
    protected final AppExecutors appExecutors;
    protected final PSCoreDb db;
    @Inject
    protected Connectivity connectivity;
    protected RateLimiter<String> rateLimiter = new RateLimiter<>( Config.API_SERVICE_CACHE_LIMIT, TimeUnit.MINUTES);

    //endregion


    //region Constructor

    /**
     * Constructor of PSRepository
     * @param psApiService Panacea-Soft API Service Instance
     * @param appExecutors Executors Instance
     * @param db Panacea-Soft DB
     */
    protected PSRepository(PSApiService psApiService, AppExecutors appExecutors, PSCoreDb db) {
        Utils.psLog("Inside NewsRepository");
        this.psApiService = psApiService;
        this.appExecutors = appExecutors;
        this.db = db;
    }

    //endregion


    //region public Methods

    public LiveData<Resource<Boolean>> save(Object obj) {

        if(obj == null) {
            return AbsentLiveData.create();
        }

        SaveTask saveTask = new SaveTask(psApiService, db, obj);
        appExecutors.diskIO().execute(saveTask);
        return saveTask.getStatusLiveData();
    }


    public LiveData<Resource<Boolean>> delete(Object obj) {

        if(obj == null) {
            return AbsentLiveData.create();
        }

        DeleteTask deleteTask = new DeleteTask(psApiService, db, obj);
        appExecutors.diskIO().execute(deleteTask);
        return deleteTask.getStatusLiveData();
    }
    //endregion

}
