package com.example.gaukhar.myshop.api.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.gaukhar.myshop.api.db.entity.Company;

import java.util.List;

import io.reactivex.Maybe;

@Dao
public interface CompanyDao {

    @Query("SELECT * FROM company")
    Maybe<List<Company>> getAll();

    @Query("SELECT * FROM company WHERE id_company=:id")
    Maybe<Company> getById(String id);

    @Insert
    void insert(Company company);

    @Insert
    void insertAll(List<Company> companies);

    @Update
    void update(Company company);

    @Update
    void updateAll(List<Company> companies);

    @Delete
    void delete(Company company);
}
