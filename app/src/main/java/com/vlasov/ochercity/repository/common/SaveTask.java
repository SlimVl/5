package com.vlasov.ochercity.repository.common;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.vlasov.ochercity.api.PSApiService;
import com.vlasov.ochercity.db.PSCoreDb;
import com.vlasov.ochercity.viewobject.Category;
import com.vlasov.ochercity.viewobject.common.Resource;

public class SaveTask implements Runnable {


    //region Variables

    private final MutableLiveData<Resource<Boolean>> statusLiveData = new MutableLiveData<>();

    public final PSApiService service;
    private final PSCoreDb db;
    private final Object obj;

    //endregion


    //region Constructor

    /**
     * Constructor of SaveTask.
     * @param service Panacea-Soft API Service Instance
     * @param db Panacea-Soft DB Instance
     * @param obj Object to Save
     *
     */
    SaveTask(PSApiService service, PSCoreDb db, Object obj) {
        this.service = service;
        this.db = db;
        this.obj = obj;
    }

    //endregion


    //region Override Methods

    @Override
    public void run() {
        try {
            try{
                db.beginTransaction();

                if(obj instanceof Category) {
                    //db.categoryDao().insert((Category) obj);
                    db.setTransactionSuccessful();
                }

            }finally {
                db.endTransaction();
            }
            statusLiveData.postValue(Resource.success(true));
        } catch (Exception e) {
            statusLiveData.postValue(Resource.error(e.getMessage(), true));
        }
    }

    //endregion


    //region public SyncCategory Methods

    /**
     * This function will return Status of Process
     * @return statusLiveData
     */
    public LiveData<Resource<Boolean>> getStatusLiveData() { return statusLiveData; }

    //endregion

}
