package com.mustafaoguzdemirel.gamifiedstepcounterapp.view.social.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mustafaoguzdemirel.gamifiedstepcounterapp.databinding.FragmentCreatePostBinding
import com.mustafaoguzdemirel.gamifiedstepcounterapp.helper.Dataholder
import com.mustafaoguzdemirel.gamifiedstepcounterapp.model.post.PostModel

class CreatePostFragment(
    private val postCallback: PostCallback
) : Fragment() {
    private var binding: FragmentCreatePostBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreatePostBinding.inflate(inflater, container, false)
        setListeners()
        return binding?.root
    }

    private fun setListeners() {
        binding!!.shareRL.setOnClickListener {
            binding!!.postContentET.setText("")
            //TODO: addPost api
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}