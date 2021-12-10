package com.example.offdogapi.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


//Create entity table name for database
@Entity(tableName = "DogImages")

//Create entity structure
data class DogImageEntity(
    @PrimaryKey(autoGenerate = true)      //Automatically generates primary key
    @NonNull                              //Makes sure we always have a key for each row ( for access )
    @ColumnInfo(name = "id")              //Defines column name ID
    val id: Int = 0,
    @ColumnInfo(name = "image_url")       //Adds Another Url column
    val imageUrl: String)                 //Takes in a String data