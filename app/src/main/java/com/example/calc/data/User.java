package com.example.calc.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "user_table")
public class User {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    public String login;
    public String email;
    public String password;

}
