package com.vlasov.ochercity.viewobject;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Panacea-Soft on 9/18/18.
 * Contact Email : teamps.is.cool@gmail.com
 */

@Entity(primaryKeys = "id")
public class LatestProduct {

    @SerializedName("id")
    @NonNull
    public final String id;

    public LatestProduct(@NonNull String id) {
        this.id = id;
    }
}
