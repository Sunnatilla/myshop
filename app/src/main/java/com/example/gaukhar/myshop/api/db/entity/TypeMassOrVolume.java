package com.example.gaukhar.myshop.api.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "type_mass_or_volume")
public class TypeMassOrVolume {

    @PrimaryKey
    @ColumnInfo(name = "id_type_mass_or_volume")
    public int id;

    public String name;

    public String description;
}
