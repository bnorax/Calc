package com.example.calc.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addUser(User... users);

    @Delete
    void deleteUser(User user);

    @Query("SELECT * FROM user_table")
    List<User> getAll();

    @Query("SELECT * FROM user_table WHERE uid IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM user_table WHERE login = (:pLogin)")
    User getUserByLogin(String pLogin);

    @Query("SELECT * FROM user_table WHERE email = (:pEmail)")
    User getUserByEmail(String pEmail);
}
