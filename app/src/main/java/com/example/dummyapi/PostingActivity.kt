package com.example.dummyapi
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dummyapi.adapter.PostAdapter
import com.example.dummyapi.data.PostData
import com.example.dummyapi.repository.PostRepository
import kotlinx.android.synthetic.main.activity_posting.*

class PostingActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private val postAdapter by lazy { PostAdapter() }
    lateinit var layoutManager: LinearLayoutManager
    var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posting)
        incomingIntent
    }

    private val incomingIntent: Unit
        private get() {
            if (intent.hasExtra("post_id")) {
                val postId = intent.getStringExtra("post_id")

                // setting up recyclerView
                setupRecyclerview()
                layoutManager = LinearLayoutManager(this)
                postRecyclerView.layoutManager = layoutManager
                if (postId != null) {
                    // forward
                    forward(postId, 0)
                }
            }
        }

    private fun forward(postId: String, delay: Long) {
        isLoading = true
        val repository = PostRepository()
        val viewModelFactory = MainViewModelFactory(repository)

        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        viewModel.getPost(postId)
        viewModel.PostResponse.observe(this, Observer { response ->
            if (response.isSuccessful) {
                @Suppress("DEPRECATION")

                Handler().postDelayed({
                    isLoading = false
                }, delay)
                val post: List<PostData> = listOf(response.body()) as List<PostData>
                postAdapter.setPostData(this,R.layout.post, post)
            } else {
                Toast.makeText(this, response.code(), Toast.LENGTH_SHORT).show()
            }
        })
    }
    private fun setupRecyclerview() {
        postRecyclerView.adapter = postAdapter
        postRecyclerView.layoutManager = LinearLayoutManager(this)
    }
}