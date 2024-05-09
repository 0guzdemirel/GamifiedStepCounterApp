package com.mustafaoguzdemirel.gamifiedstepcounterapp.view.social

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mustafaoguzdemirel.gamifiedstepcounterapp.databinding.FragmentMainBinding
import com.mustafaoguzdemirel.gamifiedstepcounterapp.databinding.FragmentSocialBinding
import com.mustafaoguzdemirel.gamifiedstepcounterapp.helper.NavigationHelper
import com.mustafaoguzdemirel.gamifiedstepcounterapp.view.social.post.CreatePostActivity

class SocialFragment : Fragment() {
    private var binding: FragmentSocialBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSocialBinding.inflate(inflater, container, false)
        setListeners()
        return binding?.root
    }

    private fun setListeners() {
        binding?.infoRL?.setOnClickListener {
            //TODO
        }

        binding?.coinRL?.setOnClickListener {
            //TODO
        }

        binding?.logoutRL?.setOnClickListener {
            //TODO
        }

        binding?.createPostRL?.setOnClickListener {
            NavigationHelper.instance?.navigateToActivity(
                context = context,
                navigateActivity = CreatePostActivity::class.java
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}