package com.example.offdogapi.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


//create entity table name and structure
@Entity(tableName = "DogImages")
data class DogImageEntity(
    @PrimaryKey(autoGenerate = true)
    @NonNull
   @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "image_url")
    val imageUrl: String)