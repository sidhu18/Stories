package com.ambient.stories.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ambient.stories.R
import com.ambient.stories.adapters.PostAdapter
import com.ambient.stories.databinding.FragmentHomeBinding
import com.ambient.stories.helpers.AppPreferences

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding
    val adapter = PostAdapter()
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home, container, false)
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = homeViewModel

        if(AppPreferences.loggedInUserId == -1L){
            findNavController().navigate(R.id.action_navigation_home_to_loginFragment)
        }else {
            binding.postRecycler.adapter = adapter

            homeViewModel.allPost.observe(viewLifecycleOwner, Observer { it ->
                it?.let { adapter.submitList(it) }
            })
        }

        return binding.root
    }
}
