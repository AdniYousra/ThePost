package com.example.dummyapi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.dummyapi.repository.PostRepository
class CreatingActivity : AppCompatActivity() {
    private lateinit var rSubmitButton: Button
    private lateinit var rImageUrl: EditText
    private lateinit var rText: EditText
    private lateinit var rTags: EditText
    private lateinit var rLikes: EditText
    private lateinit var rOwner: EditText
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creating)

        rSubmitButton = findViewById(R.id.submitButton) as Button

        rImageUrl = findViewById(R.id.imageUrl) as EditText
        rLikes = findViewById(R.id.likes) as EditText
        rText = findViewById(R.id.text) as EditText
        rTags = findViewById(R.id.tags) as EditText
        rOwner = findViewById(R.id.owner) as EditText

        rSubmitButton.setOnClickListener {
            submitData()
        }
    }

    private fun submitData() {
        val image = rImageUrl.text.toString()
        val text = rText.text.toString()
        val likes = rLikes.text.toString().toInt()
        val tags = rTags.text.toString().split(", ") as ArrayList<String>
        val owner = rOwner.text.toString()

        val repository = PostRepository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.createPost(text, image, likes, tags, owner)
        val intent = Intent(this, CreatedActivity::class.java)
        this.startActivity(intent)
    }

}