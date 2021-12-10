package com.example.offdogapi.viewmodels

import android.app.Application
import com.example.offdogapi.database.AppDatabase

//This is what we need to access our database

class DogApplication : Application() {
    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }
}