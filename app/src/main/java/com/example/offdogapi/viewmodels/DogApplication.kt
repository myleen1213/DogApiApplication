package com.example.offdogapi.viewmodels

import android.app.Application
import com.example.offdogapi.database.AppDatabase

class DogApplication : Application() {
    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }
}