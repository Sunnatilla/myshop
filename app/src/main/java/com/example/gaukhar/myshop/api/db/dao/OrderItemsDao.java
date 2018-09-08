package com.example.gaukhar.myshop.api.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.gaukhar.myshop.api.db.entity.Order;
import com.example.gaukhar.myshop.api.db.models.OrderItems;

import java.util.List;

import io.reactivex.Maybe;

@Dao
public interface OrderItemsDao {

    @Query("SELECT * FROM item i, `order` r where i.id_item = r.item_id")
    Maybe<List<OrderItems>> getOrderItemsAll();

    @Query("SELECT * FROM item i, `order` r where i.id_item = r.item_id and r.id_order = :id")
    Maybe<OrderItems> getOrderItemsByOrderId(long id);

    @Query("SELECT * FROM item i, `order` r where i.id_item = r.item_id LIMIT :limit OFFSET :offset")
    Maybe<List<OrderItems>> getOrderItems(long limit, long offset);

    @Insert
    void insert(Order order);

    @Insert
    void insertAll(List<Order> orders);

    @Update
    void update(Order order);

    @Update
    void updateAll(List<Order> orders);

    @Delete
    void delete(Order order);
}
