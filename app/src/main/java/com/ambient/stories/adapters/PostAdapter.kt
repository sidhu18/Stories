package com.ambient.stories.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ambient.stories.R
import com.ambient.stories.data.entities.PostData

class PostAdapter : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    var data = listOf<PostData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item : PostData = data[position]
        holder.bind(item)
    }


    class ViewHolder private constructor(itemView: View) :RecyclerView.ViewHolder(itemView) {

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.item_recycler_post, parent, false)
                return ViewHolder(view)
            }
        }

        fun bind(item: PostData) {
            postUserName.text = item.userId.toString()
            postHeading.text = item.postHeading
            postBody.text = item.postBody
        }

        val postUserName : TextView = itemView.findViewById(R.id.postUserName)
        val postUserImage : ImageView = itemView.findViewById(R.id.postUserImage)
        val postDate : TextView = itemView.findViewById(R.id.postDate)
        val postHeading : TextView = itemView.findViewById(R.id.postHeading)
        val postBody : TextView = itemView.findViewById(R.id.postBody)
    }


}