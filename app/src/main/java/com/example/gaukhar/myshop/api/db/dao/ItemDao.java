package com.example.gaukhar.myshop.api.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.gaukhar.myshop.api.db.entity.Item;

import java.util.List;

import io.reactivex.Maybe;

@Dao
public interface ItemDao {

    @Query("SELECT * FROM item")
    Maybe<List<Item>> getAll();

    @Query("SELECT * FROM item WHERE id_item=:id")
    Maybe<Item> getById(String id);

    @Insert
    void insert(Item item);

    @Insert
    void insertAll(List<Item> items);

    @Update
    void update(Item item);

    @Update
    void updateAll(List<Item> items);

    @Delete
    void delete(Item item);
}
