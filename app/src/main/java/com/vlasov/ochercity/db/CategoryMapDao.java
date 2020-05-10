package com.vlasov.ochercity.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.vlasov.ochercity.viewobject.Category;
import com.vlasov.ochercity.viewobject.CategoryMap;

import java.util.List;

@Dao
public interface CategoryMapDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CategoryMap categoryMap);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Category> categoryMapList);

    @Query("DELETE FROM CategoryMap WHERE mapKey = :key")
    void deleteByMapKey(String key);

    @Query("SELECT max(sorting) from CategoryMap WHERE mapKey = :value ")
    int getMaxSortingByValue(String value);

    @Query("SELECT * FROM CategoryMap")
    List<CategoryMap> getAll();

    @Query("DELETE FROM CategoryMap")
    void deleteAll();
}
