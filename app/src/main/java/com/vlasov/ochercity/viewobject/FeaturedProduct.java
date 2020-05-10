package com.vlasov.ochercity.viewobject;


import androidx.annotation.NonNull;
import androidx.room.Entity;

import com.google.gson.annotations.SerializedName;

@Entity(primaryKeys = "id")
public class FeaturedProduct {

    @SerializedName("id")
    @NonNull
    public final String id;

    public FeaturedProduct(@NonNull String id) {
        this.id = id;
    }
}
