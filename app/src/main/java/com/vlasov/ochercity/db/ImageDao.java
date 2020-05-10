package com.vlasov.ochercity.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.vlasov.ochercity.viewobject.Image;

import java.util.List;

@Dao
public interface ImageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Image image);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Image> imageList);

    @Query("SELECT * FROM Image")
    LiveData<List<Image>> getAll();

    @Query("SELECT * FROM Image WHERE imgParentId = :imgParentId AND imgType= :imagetype order by imgId")
    LiveData<List<Image>> getByImageIdAndType(String imgParentId, String imagetype);

    @Query("DELETE FROM Image WHERE imgParentId = :imgParentId AND imgType= :imagetype")
    void deleteByImageIdAndType(String imgParentId, String imagetype);

    @Query("DELETE FROM Image")
    void deleteTable();

}
