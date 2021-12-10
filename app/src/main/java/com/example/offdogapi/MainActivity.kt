package com.example.offdogapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import androidx.activity.viewModels
import com.example.offdogapi.database.DogImageEntity
import com.example.offdogapi.viewmodels.DogApplication
import com.example.offdogapi.viewmodels.MainViewModel
import com.example.offdogapi.viewmodels.MainViewModelFactory
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels{
        MainViewModelFactory((application as DogApplication).database.dogImageDao())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ADD NEXT AND PREVIOUS BUTTON

        //viewModel.getNewDog()
       val changeDogButton: ImageButton = findViewById(R.id.nextBtn)
        val prevDogButton: ImageButton = findViewById(R.id.prevBtn)



        //DISPLAYS IN IMAGE VIEW1 FROM API
        viewModel.currentlyDisplayedImage.observe(this,
            {
                val mainImage : ImageView = findViewById(R.id.imageView1)
                Picasso.with(this).load(it.imgSrcUrl).into(mainImage)
            })


        //BUTTON SHOULD DISPLAY THE NEXT DOG
        changeDogButton.setOnClickListener {
            val currentImgUrl = viewModel.currentlyDisplayedImage.value?.imgSrcUrl
            viewModel.getNewDog()

             val newDogImage = currentImgUrl?.let { it1 -> DogImageEntity(imageUrl = it1) }
            //viewModel.addDog()
            if(newDogImage != null ) {
                viewModel.addDog(newDogImage)
        }
        }

        prevDogButton.setOnClickListener {
            openActivity2()

        }
    }

    private fun openActivity2() {
        val intent = Intent(this, MainActivity2::class.java)
        startActivity(intent)

    }



}
