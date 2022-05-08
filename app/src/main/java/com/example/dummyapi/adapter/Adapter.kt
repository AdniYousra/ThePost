package com.example.dummyapi.adapter

import com.example.dummyapi.data.Data


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dummyapi.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row.view.*


class Adapter: RecyclerView.Adapter<Adapter.MyViewHolder>(){
    private var Context: Context? = null
    private var Layout = R.layout.row
    private var List = emptyList<Data>()

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(Layout, parent, false))
    }

    override fun getItemCount(): Int {
        return List.size
    }
    override fun onBindViewHolder(holder: MyViewHolder, i: Int) {
        holder.itemView.authName.text = String.format("%s. %s %s", List[i].owner.title, List[i].owner.firstName, List[i].owner.lastName )
        holder.itemView.Date.text = List[i].publishDate
        Picasso.get().load(List[i].owner.picture).into(holder.itemView.avatar);
        Picasso.get().load(List[i].image).into(holder.itemView.mainImage);
        holder.itemView.content.text = List[i].text
        holder.itemView.tag1.text = List[i].tags[0]
        holder.itemView.tag2.text = List[i].tags[1]
        holder.itemView.tag3.text = List[i].tags[2]
        holder.itemView.setOnClickListener {
            val postId = List[i].id
            val intent = Intent(Context, PostingActivity::class.java)
            intent.putExtra("post_id", postId)
            Context?.startActivity(intent)
        }
        holder.itemView.setOnLongClickListener{
            val postId = List[i].id
            val intent = Intent(Context, DeletingActivity::class.java)
            intent.putExtra("post_id", postId)
            Context?.startActivity(intent)
            return@setOnLongClickListener true
        }
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

    fun setData(context: Context,layout: Int, newList: List<Data>){
        List = newList
        Context = context
        Layout = layout
        notifyDataSetChanged()
    }


}