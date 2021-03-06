package com.example.dummyapi.adapter

import com.example.dummyapi.data.PostData
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dummyapi.R
import com.example.dummyapi.TagActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row.view.*

class PostAdapter: RecyclerView.Adapter<PostAdapter.MyViewHolder>(){

    private var Context: Context? = null
    private var Row = R.layout.row
    private var mPost= emptyList<PostData>()

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(Row, parent, false))
    }

    override fun getItemCount(): Int {
        return mPost.size
    }



    override fun onBindViewHolder(holder: MyViewHolder, i: Int) {
        // title
        holder.itemView.authName.text = String.format("%s. %s %s", mPost[i].owner.title, mPost[i].owner.firstName, mPost[i].owner.lastName )
        // Date
        holder.itemView.Date.text = mPost[i].publishDate
        // avatar
        Picasso.get().load(mPost[i].owner.picture).into(holder.itemView.avatar);
        // image
        Picasso.get().load(mPost[i].image).into(holder.itemView.mainImage);
        // content
        holder.itemView.content.text = mPost[i].text
        // tags
        holder.itemView.tag1.text = mPost[i].tags[0]
        holder.itemView.tag2.text = mPost[i].tags[1]
        holder.itemView.tag3.text = mPost[i].tags[2]
        // there is no on post click listener
        // go to posts by tag
        holder.itemView.tag1.setOnClickListener {
            val tag = it.tag1.text
            val intent = Intent(Context, TagActivity::class.java)
            intent.putExtra("tag", tag)
            Context?.startActivity(intent)
        }
        holder.itemView.tag2.setOnClickListener {
            val tag = it.tag2.text
            val intent = Intent(Context, TagActivity::class.java)
            intent.putExtra("tag", tag)
            Context?.startActivity(intent)
        }
        holder.itemView.tag3.setOnClickListener {
            val tag = it.tag3.text
            val intent = Intent(Context, TagActivity::class.java)
            intent.putExtra("tag", tag)
            Context?.startActivity(intent)
        }
    }

    // update / set data
    fun setPostData(context: Context,layout: Int, Post: List<PostData>){
        mPost = Post
        Context = context
        Row = layout
        notifyDataSetChanged()
    }


}