package com.example.pam;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

@Dao
public interface MyDao {

    @Insert
    public void addValue(User wynik);

    @Query("SELECT COUNT(*) FROM wynik")
    int getCount();

    @Query("SELECT wynik FROM wynik WHERE id=1")
    int savedvalue();

    @Query("UPDATE wynik SET wynik=:value WHERE id=1")
    void updateValue(int value);

}
