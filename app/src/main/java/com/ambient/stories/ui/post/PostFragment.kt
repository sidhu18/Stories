package com.ambient.stories.ui.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ambient.stories.R
import com.ambient.stories.data.entities.PostData
import com.ambient.stories.databinding.FragmentPostBinding

class PostFragment : Fragment() {

    private lateinit var postViewModel: PostViewModel
    private lateinit var binding: FragmentPostBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        postViewModel = ViewModelProvider(this).get(PostViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_post, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = postViewModel

        binding.postButton.setOnClickListener{
            postViewModel.addPost(getPostData())
            clearTextFields()
        }

        return binding.root
    }
    private fun getPostData() : PostData{
        return PostData(0,1,binding.postHeading.text.toString(),binding.postBody.text.toString(),"",0,"06 June 2020")
    }

    private fun clearTextFields() {
        binding.postHeading.text.clear()
        binding.postBody.text.clear()
    }
}
