package com.vlasov.ochercity.viewobject;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ProductCollection {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    public final int id = 0;

    @NonNull
    public final String collectionId;

    public final String productId;

    public ProductCollection(@NonNull String collectionId, String productId) {
        this.collectionId = collectionId;
        this.productId = productId;
    }
}
