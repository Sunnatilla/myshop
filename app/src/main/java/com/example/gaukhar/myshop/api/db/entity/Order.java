package com.example.gaukhar.myshop.api.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

import static android.arch.persistence.room.ForeignKey.SET_NULL;

@Entity(foreignKeys = @ForeignKey(entity = Item.class, parentColumns = "id_item", childColumns = "item_id", onDelete = SET_NULL))
public class Order {

    @PrimaryKey
    @ColumnInfo(name = "id_order")
    public long idOrder;

    @ColumnInfo(name = "item_id")
    public String itemId;

    @ColumnInfo(name = "uuid", index = true)
    public String uuid;

    @ColumnInfo(name = "order_price")
    public double orderPrice;

    @ColumnInfo(name = "order_date")
    public Date orderDate;
}
