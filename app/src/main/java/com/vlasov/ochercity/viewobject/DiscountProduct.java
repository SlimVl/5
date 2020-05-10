package com.vlasov.ochercity.viewobject;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class DiscountProduct {

    @NonNull
    @PrimaryKey
    @SerializedName("id")
    public final String id;

    public DiscountProduct(@NonNull String id) {
        this.id = id;
    }
}
