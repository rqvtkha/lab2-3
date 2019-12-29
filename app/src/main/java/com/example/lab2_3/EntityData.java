package com.example.lab2_3;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class EntityData {
    @PrimaryKey
    int id;
    String name;
    String userName;
    String email;
}
