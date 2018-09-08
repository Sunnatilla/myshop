package com.example.gaukhar.myshop.api.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;


import com.example.gaukhar.myshop.api.db.entity.Category;

import java.util.List;

import io.reactivex.Maybe;

@Dao
public interface CategoryDao {

    @Query("SELECT * FROM category")
    Maybe<List<Category>> getAll();

    @Query("SELECT * FROM category WHERE id_category=:id")
    Maybe<Category> getById(String id);

    @Insert
    void insert(Category category);

    @Insert
    void insertAll(List<Category> categories);

    @Update
    void update(Category category);

    @Update
    void updateAll(List<Category> categories);

    @Delete
    void delete(Category category);
}
