package com.example.gaukhar.myshop.api.db;

import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.TypeConverters;

import com.example.gaukhar.myshop.api.db.dao.CategoryDao;
import com.example.gaukhar.myshop.api.db.dao.CompanyDao;
import com.example.gaukhar.myshop.api.db.dao.ItemDao;
import com.example.gaukhar.myshop.api.db.dao.OrderItemsDao;
import com.example.gaukhar.myshop.api.db.dao.TypeMassOrVolumeDao;
import com.example.gaukhar.myshop.api.db.entity.Category;
import com.example.gaukhar.myshop.api.db.entity.Company;
import com.example.gaukhar.myshop.api.db.entity.Item;
import com.example.gaukhar.myshop.api.db.entity.Order;
import com.example.gaukhar.myshop.api.db.entity.TypeMassOrVolume;
import com.example.gaukhar.myshop.api.db.utils.Converter;

@Database(entities = {Item.class, Category.class, Company.class, TypeMassOrVolume.class, Order.class}, version = 1)
@TypeConverters(Converter.class)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ItemDao itemDao();

    public abstract CategoryDao categoryDao();

    public abstract CompanyDao companyDao();

    public abstract TypeMassOrVolumeDao typeMassOrVolumeDao();

    public abstract OrderItemsDao orderItemsDao();
}
