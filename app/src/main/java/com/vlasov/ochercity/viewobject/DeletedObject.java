package com.vlasov.ochercity.viewobject;


import androidx.annotation.NonNull;
import androidx.room.Entity;

import com.google.gson.annotations.SerializedName;

@Entity(primaryKeys = "id")
public class DeletedObject {

    @NonNull
    @SerializedName("id")
    public String id;

    @SerializedName("type_id")
    public String typeId;

    @SerializedName("type_name")
    public String typeName;

    @SerializedName("deleted_date")
    public String deletedDate;

    public DeletedObject(@NonNull String id, String typeId, String typeName, String deletedDate) {
        this.id = id;
        this.typeId = typeId;
        this.typeName = typeName;
        this.deletedDate = deletedDate;
    }
}
