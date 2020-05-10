package com.vlasov.ochercity.viewobject;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class FavouriteProduct {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    public final int id = 0;

    @NonNull
    @SerializedName("id")
    public final String productId;

    public int sorting;

    public FavouriteProduct(@NonNull String productId, int sorting) {
        this.productId = productId;
        this.sorting = sorting;
    }
}
