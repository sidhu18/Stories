package com.ambient.stories.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ambient.stories.R
import com.ambient.stories.data.entities.PostData
import com.ambient.stories.data.entities.PostWithUserData
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import de.hdodenhof.circleimageview.CircleImageView

class PostAdapter : ListAdapter<PostWithUserData, PostAdapter.ViewHolder>(PostDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
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

        fun bind(item: PostWithUserData) {
            postUserName.text = item.userId.toString()
            postHeading.text = item.postHeading
            postBody.text = item.postBody
            postUserName.text = item.userName
            postDate.text = item.postDate

            Glide.with(itemView)
                .load(item.profileImageUri)
                .apply(RequestOptions().circleCrop())
                .placeholder(R.drawable.ic_placeholder)
                .into(postUserImage)
        }

        val postUserName : TextView = itemView.findViewById(R.id.postUserName)
        val postUserImage : ImageView = itemView.findViewById(R.id.postUserImage)
        val postDate : TextView = itemView.findViewById(R.id.postDate)
        val postHeading : TextView = itemView.findViewById(R.id.postHeading)
        val postBody : TextView = itemView.findViewById(R.id.postBody)
    }

    class PostDiffCallBack : DiffUtil.ItemCallback<PostWithUserData>(){
        override fun areItemsTheSame(oldItem: PostWithUserData, newItem: PostWithUserData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PostWithUserData, newItem: PostWithUserData): Boolean {
            return oldItem == newItem
        }

    }

}