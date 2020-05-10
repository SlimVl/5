package com.vlasov.ochercity.viewobject;


import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(primaryKeys = "id")
public class PSAppInfo {

    @NonNull
    public String id;

    @Embedded(prefix = "version_")
    @SerializedName("version")
    public PSAppVersion psAppVersion;

    @SerializedName("delete_history")
    @Ignore
    public List<DeletedObject> deletedObjects;

    public PSAppInfo(@NonNull String id, PSAppVersion psAppVersion) {
        this.id = id;
        this.psAppVersion = psAppVersion;
    }
}
