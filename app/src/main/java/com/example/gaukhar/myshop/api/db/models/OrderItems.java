package com.example.gaukhar.myshop.api.db.models;

import android.arch.persistence.room.Embedded;

import com.example.gaukhar.myshop.api.db.entity.Item;
import com.example.gaukhar.myshop.api.db.entity.Order;

public class OrderItems {

    @Embedded
    public Order order;

    @Embedded
    public Item item;
}
