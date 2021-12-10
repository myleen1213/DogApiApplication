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

    init {
        getNewDog()
    }

    // Getting the first item in the list from the response.
    fun getNewDog() {
        viewModelScope.launch {
            _currentlyDisplayedImage.value = DogImageApi.retrofitService.getRandomDogImage()
        }

    }

    fun addDog(dogImageEntity: DogImageEntity) {
        viewModelScope.launch {
            dogImageDao.addDogImage(dogImageEntity)
        }
    }

    fun getAllDogs(): LiveData<List<DogImageEntity>> {
        return dogImageDao.getAllDogImages().asLiveData()
    }

}
    /*fun deleteMostRecentDog(){
        viewModelScope.launch {
            dogImageDao.deleteDog()
        }
    }*/

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