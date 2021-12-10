package com.example.offdogapi.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

import kotlinx.coroutines.flow.Flow


//DELETE THIS TO FIND OUT WHY WE UE FLOW^^

/**
     * Provides access to read/write operations on the schedule table.
     * Used by the view models to format the query results for use in the UI.
     */

    @Dao
    interface DogImageDao {

    @Query("SELECT * FROM DogImages")
    fun getAllDogImages(): Flow<List<DogImageEntity>>

    @Query("SELECT * FROM DogImages ORDER BY id DESC LIMIT 1")
    fun getMostRecentlyAddDog(): DogImageEntity

   /* @Query("DELETE from DogImages where id=(select max(id)-1 from DogImages)")
    suspend fun deleteDog()*/

    @Insert
    //suspend fun insert(dogImageEntity: DogImageEntity)
      suspend fun addDogImage(dogImageEntity: DogImageEntity)


}