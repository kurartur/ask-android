package com.ask.android.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.ask.android.data.entity.User;

import io.reactivex.Flowable;

@Dao
public interface UserDao {

    @Query("SELECT * FROM users WHERE id=:id")
    Flowable<User> getUser(Long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveUser(User user);

}
