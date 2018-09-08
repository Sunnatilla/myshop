package com.example.gaukhar.myshop.api.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;

import static android.arch.persistence.room.ForeignKey.SET_NULL;

@Entity(foreignKeys = {
                        @ForeignKey(entity = Category.class, parentColumns = "id_category", childColumns = "category_id", onDelete = SET_NULL),
                        @ForeignKey(entity = Company.class, parentColumns = "id_company", childColumns = "company_id", onDelete = SET_NULL),
                        @ForeignKey(entity = TypeMassOrVolume.class,  parentColumns = "id_type_mass_or_volume", childColumns = "type_mass_or_volume_id", onDelete = SET_NULL)
                      },
        indices = {@Index(value = "name", unique = true)}
       )
public class Item {

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id_item")
    @NonNull
    public String idItem;

    public String name;

    public String description;

    public double price;

    @ColumnInfo(name = "image_url")
    public String imageUrl;

    @ColumnInfo(name = "category_id")
    public int categoryId;

    @ColumnInfo(name = "company_id")
    public int companyId;

    @ColumnInfo(name = "mass_or_volume")
    public double massOrVolume;

    @ColumnInfo(name = "type_mass_or_volume_id")
    public int typeMassOrVolumeId;

    @ColumnInfo(name = "expire_date")
    public Date expireDate;
}
