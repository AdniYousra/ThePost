package com.example.dummyapi

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.dummyapi.repository.PostRepository


class DeletingActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deleting)
        incomingIntent
    }
    private val incomingIntent: Unit
        private get() {
            if (intent.hasExtra("post_id")) {

                val postId = intent.getStringExtra("post_id")

                val warning: TextView = findViewById<View>(R.id.successText) as TextView
                val warningText = " the deleting process of the Post with id : $postId was successful"

                warning.setText(warningText)

                if (postId != null) {
                    delete(postId)
                }
            }
        }



    private fun delete(postId: String) {
        val repository = PostRepository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.deletePost(postId)
    }
}