package com.vlasov.ochercity.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.vlasov.ochercity.viewobject.Blog;

import java.util.List;

@Dao
public interface BlogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Blog> blogs);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Blog blog);

    @Query("SELECT * FROM Blog WHERE id = :id")
    LiveData<Blog> getBlogById(String id);

    @Query("SELECT * FROM Blog ORDER BY addedDate desc")
    LiveData<List<Blog>> getAllNewsFeed();

    @Query("DELETE FROM Blog")
    void deleteAll();

}