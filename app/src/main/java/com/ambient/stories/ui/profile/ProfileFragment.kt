package com.ambient.stories.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.ambient.stories.R
import com.ambient.stories.databinding.FragmentProfileBinding
import com.ambient.stories.databinding.FragmentProfileBindingImpl
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ProfileFragment : Fragment() {

    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_profile,
            container,
            false)

        profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        binding.viewmodel = profileViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        Glide.with(binding.root)
            .load(profileViewModel.user.value?.profileImageUri)
            .apply(RequestOptions().circleCrop())
            .placeholder(R.drawable.ic_placeholder)
            .into(binding.userProfileImage)

        return binding.root
    }
}
