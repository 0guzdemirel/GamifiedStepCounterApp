package com.mustafaoguzdemirel.gamifiedstepcounterapp.view.social.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mustafaoguzdemirel.gamifiedstepcounterapp.databinding.FragmentAddCommentBinding
import com.mustafaoguzdemirel.gamifiedstepcounterapp.helper.Dataholder
import com.mustafaoguzdemirel.gamifiedstepcounterapp.model.comment.CommentModel

class AddCommentFragment(private val postCallback: PostCallback) : Fragment() {
    private var binding: FragmentAddCommentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddCommentBinding.inflate(inflater, container, false)
        setListeners()
        return binding?.root
    }

    private fun setListeners() {
        binding!!.sendRL.setOnClickListener {
            postCallback.onCommentAdded(
                CommentModel(
                    id = "1",
                    description = binding!!.commentET.text.toString(),
                    createdDate = "12.05.2024",
                    userModel = Dataholder.instance.currentUserModel,
                    parentPostId = Dataholder.instance.selectedPost!!.id
                )
            )
            binding!!.commentET.setText("")
            //TODO: addPost api
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}