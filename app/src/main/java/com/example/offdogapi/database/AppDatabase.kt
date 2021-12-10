package com.example.offdogapi.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Define a database and specifies data tables that will be used.
 * Version is incremented as new tables/columns are added/removed/changed.
 * You can optionally use this class for one-time setup, such as pre-populating a database.
 */


//Create a database of dog image entities
@Database(entities = [DogImageEntity::class], version = 1)

//Create database to be accessed by all parts of the app
abstract class AppDatabase: RoomDatabase() {

    //Need object to access functions
    abstract fun dogImageDao(): DogImageDao


    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

    public fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance

                return instance
            }
        }
    }
}
