package com.mustafaoguzdemirel.gamifiedstepcounterapp.view.social.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mustafaoguzdemirel.gamifiedstepcounterapp.databinding.FragmentCreatePostBinding
import com.mustafaoguzdemirel.gamifiedstepcounterapp.helper.UIHelper
import com.mustafaoguzdemirel.gamifiedstepcounterapp.presenter.user.UserPresenter
import com.mustafaoguzdemirel.gamifiedstepcounterapp.presenter.user.UserView

class CreatePostFragment(
    private val postCallback: PostCallback
) : Fragment() {
    private var binding: FragmentCreatePostBinding? = null
    private lateinit var userPresenter: UserPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreatePostBinding.inflate(inflater, container, false)
        initPresenters()
        setListeners()
        return binding?.root
    }

    private fun initPresenters() {
        userPresenter = UserPresenter()
    }

    private fun setListeners() {
        binding!!.shareRL.setOnClickListener {
            addPost()
        }
    }

    private fun addPost() {
        UIHelper.setButtonLoading(
            buttonL = binding!!.shareRL,
            buttonText = binding!!.shareBtnText,
            progressBar = binding!!.shareBtnPB
        )

        userPresenter.addPost(
            postContent = binding?.postContentET?.text.toString(),
            object : UserView {
                override fun onAddPostSuccessful() {
                    UIHelper.setButtonLoaded(
                        buttonL = binding!!.shareRL,
                        buttonText = binding!!.shareBtnText,
                        progressBar = binding!!.shareBtnPB
                    )

                    postCallback.onPostCreated()
                    binding!!.postContentET.setText("")
                }

                override fun onError() {
                    UIHelper.setButtonLoaded(
                        buttonL = binding!!.shareRL,
                        buttonText = binding!!.shareBtnText,
                        progressBar = binding!!.shareBtnPB
                    )
                }
            }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}