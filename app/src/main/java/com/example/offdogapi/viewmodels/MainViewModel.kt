package com.example.offdogapi.viewmodels

import androidx.lifecycle.*
import com.example.offdogapi.database.DogImageDao
import com.example.offdogapi.database.DogImageEntity
import com.example.offdogapi.network.DogImage
import com.example.offdogapi.network.DogImageApi
import kotlinx.coroutines.launch


class MainViewModel(private val dogImageDao: DogImageDao) : ViewModel() {

    private val _currentlyDisplayedImage = MutableLiveData<DogImage>()
    val currentlyDisplayedImage: LiveData<DogImage> = _currentlyDisplayedImage

    //Gets new dog
    init {
        getNewDog()
    }

    // Getting our random image from the internet
    fun getNewDog() {
        viewModelScope.launch {
            _currentlyDisplayedImage.value = DogImageApi.retrofitService.getRandomDogImage()
        }

    }

    //using coroutine launch to pass entity to database
    fun addDog(dogImageEntity: DogImageEntity) {
        viewModelScope.launch {
            dogImageDao.addDogImage(dogImageEntity)
        }
    }

    //Gets all dog images, returns flow list of dogs as live data
    fun getAllDogs(): LiveData<List<DogImageEntity>> {
        return dogImageDao.getAllDogImages().asLiveData()
    }

}
    /*fun deleteMostRecentDog(){
        viewModelScope.launch {
            dogImageDao.deleteDog()
        }
    }*/

//convert dogimagedao so we can access our database
class MainViewModelFactory(
    private val dogImageDao: DogImageDao
    ) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(dogImageDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}