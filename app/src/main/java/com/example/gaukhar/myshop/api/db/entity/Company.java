package com.example.gaukhar.myshop.api.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Company {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_company")
    public int idCompany;

    public String name;

    public String description;
}
