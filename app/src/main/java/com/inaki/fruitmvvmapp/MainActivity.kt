package com.inaki.fruitmvvmapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import com.inaki.fruitmvvmapp.utils.ResponseState
import com.inaki.fruitmvvmapp.viewmodel.FruitViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: FruitViewModel by viewModels {
        FruitApp.fruitComponent.viewModelsFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FruitApp.fruitComponent.inject(this)

        viewModel.allFruits.observe(this) {
            when(it.state) {
                ResponseState.LOADING -> {}
                ResponseState.SUCCESS -> {
                    findViewById<TextView>(R.id.my_text).text =
                        it.data?.get(0)?.name ?: "No data found"
                }
                ResponseState.ERROR -> {
                    findViewById<TextView>(R.id.my_text).text =
                        it.error?.localizedMessage.toString()
                }
            }
        }

        viewModel.getAllFruits()
    }
}