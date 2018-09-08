package com.example.gaukhar.myshop.api.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.gaukhar.myshop.api.db.entity.TypeMassOrVolume;

import java.util.List;

import io.reactivex.Maybe;

@Dao
public interface TypeMassOrVolumeDao {

    @Query("SELECT * FROM type_mass_or_volume")
    Maybe<List<TypeMassOrVolume>> getAll();

    @Query("SELECT * FROM type_mass_or_volume WHERE id_type_mass_or_volume=:id")
    Maybe<TypeMassOrVolume> getById(String id);

    @Insert
    void insert(TypeMassOrVolume typeMassOrVolume);

    @Insert
    void insertAll(List<TypeMassOrVolume> typeMassOrVolumes);

    @Update
    void update(TypeMassOrVolume typeMassOrVolume);

    @Update
    void updateAll(List<TypeMassOrVolume> typeMassOrVolumes);

    @Delete
    void delete(TypeMassOrVolume typeMassOrVolume);
}
