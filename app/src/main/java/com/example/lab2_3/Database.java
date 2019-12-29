package com.example.lab2_3;

import androidx.room.RoomDatabase;
@androidx.room.Database(entities = {EntityData.class},version = 1)
public abstract class Database extends RoomDatabase {
    public abstract EntityDao entityDao();
}
