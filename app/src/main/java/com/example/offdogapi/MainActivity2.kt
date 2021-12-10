package com.example.offdogapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import androidx.activity.viewModels
import com.example.offdogapi.network.DogImage
import com.example.offdogapi.network.DogImageApiService
import com.example.offdogapi.viewmodels.DogApplication
import com.example.offdogapi.viewmodels.MainViewModel
import com.example.offdogapi.viewmodels.MainViewModelFactory
import com.squareup.picasso.Picasso



        class MainActivity2 : AppCompatActivity() {
            private val viewModel: MainViewModel by viewModels {
                MainViewModelFactory((application as DogApplication).database.dogImageDao())
            }

            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_main2)


                // Get Count - 1 from viewmodel to go to previous dog image

                viewModel.getAllDogs().observe(this) {
                          val count = it.size
                    val imageView = findViewById<ImageView>(R.id.imageView2)
                            Picasso.with(this).load(it[count-1].imageUrl).into(imageView)
                }
                            val button: ImageButton = findViewById(R.id.backButton)
                            button.setOnClickListener {
                                finish()
                            }
            }}

//Picasso.with(this).load(it.get(0).imageUrl).into(imageView)


