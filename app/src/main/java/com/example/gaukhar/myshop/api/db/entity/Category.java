package com.example.gaukhar.myshop.api.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.SET_NULL;

@Entity(foreignKeys = @ForeignKey(entity = Category.class, parentColumns = "id_category", childColumns = "parent_id", onDelete = SET_NULL))
public class Category {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_category")
    public int idCategory;

    public String name;

    public String description;

    @ColumnInfo(name = "parent_id")
    public int parentId;
}
