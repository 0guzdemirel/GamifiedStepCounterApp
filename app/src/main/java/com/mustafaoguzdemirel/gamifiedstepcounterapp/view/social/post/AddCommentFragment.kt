package com.mustafaoguzdemirel.gamifiedstepcounterapp.view.social.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mustafaoguzdemirel.gamifiedstepcounterapp.databinding.FragmentAddCommentBinding
import com.mustafaoguzdemirel.gamifiedstepcounterapp.helper.Dataholder
import com.mustafaoguzdemirel.gamifiedstepcounterapp.helper.UIHelper
import com.mustafaoguzdemirel.gamifiedstepcounterapp.presenter.user.UserPresenter
import com.mustafaoguzdemirel.gamifiedstepcounterapp.presenter.user.UserView

class AddCommentFragment(private val postCallback: PostCallback) : Fragment() {
    private var binding: FragmentAddCommentBinding? = null
    private lateinit var userPresenter: UserPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddCommentBinding.inflate(inflater, container, false)
        initPresenters()
        setListeners()
        return binding?.root
    }

    private fun initPresenters() {
        userPresenter = UserPresenter()
    }


    private fun setListeners() {
        binding!!.sendRL.setOnClickListener {
            addComment()
        }
    }

    private fun addComment() {
        UIHelper.setButtonLoading(
            buttonL = binding!!.sendRL,
            buttonText = binding!!.sendBtnText,
            progressBar = binding!!.sendBtnPB
        )

        userPresenter.addComment(
            postId = Dataholder.instance.selectedPost?.id,
            commentContent = binding?.commentET?.text.toString(),
            object : UserView {
                override fun onAddCommentSuccessful() {
                    UIHelper.setButtonLoaded(
                        buttonL = binding!!.sendRL,
                        buttonText = binding!!.sendBtnText,
                        progressBar = binding!!.sendBtnPB
                    )

                    postCallback.onCommentAdded()
                    binding!!.commentET.setText("")
                }

                override fun onError() {
                    UIHelper.setButtonLoaded(
                        buttonL = binding!!.sendRL,
                        buttonText = binding!!.sendBtnText,
                        progressBar = binding!!.sendBtnPB
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