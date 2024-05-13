package com.mustafaoguzdemirel.gamifiedstepcounterapp.view.social.post

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.hukukkarar.app.common.helper.FragmentHelper
import com.mustafaoguzdemirel.gamifiedstepcounterapp.R
import com.mustafaoguzdemirel.gamifiedstepcounterapp.databinding.ActivityPostDetailBinding
import com.mustafaoguzdemirel.gamifiedstepcounterapp.helper.Dataholder
import com.mustafaoguzdemirel.gamifiedstepcounterapp.helper.UIHelper
import com.mustafaoguzdemirel.gamifiedstepcounterapp.model.comment.CommentModel
import com.mustafaoguzdemirel.gamifiedstepcounterapp.model.comment.CommentResponse
import com.mustafaoguzdemirel.gamifiedstepcounterapp.model.post.PostResponse
import com.mustafaoguzdemirel.gamifiedstepcounterapp.presenter.user.UserPresenter
import com.mustafaoguzdemirel.gamifiedstepcounterapp.presenter.user.UserView
import com.mustafaoguzdemirel.gamifiedstepcounterapp.view.adapters.comment.CommentAdapter
import com.mustafaoguzdemirel.gamifiedstepcounterapp.view.adapters.comment.CommentClickListener
import com.sothree.slidinguppanel.SlidingUpPanelLayout

class PostDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPostDetailBinding
    private lateinit var userPresenter: UserPresenter

    private val addCommentFragment = AddCommentFragment(
        postCallback = object : PostCallback {
            override fun onCommentAdded() {
                loadComments()
                binding.slidingUpPanelLayout.panelState =
                    SlidingUpPanelLayout.PanelState.COLLAPSED
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initPresenters()
        replaceFragment()
        bindData()
        initAdapter()
        setListeners()
        loadComments()
    }

    private fun initPresenters() {
        userPresenter = UserPresenter()
    }

    private fun bindData() {
        binding.profilePhotoIV.setImageResource(UIHelper.getAvatar(Dataholder.instance.selectedPost?.userModel?.avatarId?.toInt()))
        binding.nameTV.text = "" + Dataholder.instance.selectedPost?.userModel?.name
        binding.dateTV.text = "" + Dataholder.instance.selectedPost?.createdDate
        binding.contentTV.text = "" + Dataholder.instance.selectedPost?.description
        binding.likeCountTV.text = "" + Dataholder.instance.selectedPost?.likeCount
        binding.commentCountTV.text = "" + Dataholder.instance.selectedPost?.commentCount

        binding.likeIconIV.setImageResource(
            if (Dataholder.instance.selectedPost?.isLiked == true)
                R.drawable.like_filled_icon
            else
                R.drawable.like_icon
        )
    }

    private fun setListeners() {
        binding.backRL.setOnClickListener {
            finish()
        }

        binding.userStatsRL.setOnClickListener {
            finish()
        }

        binding.likeLL.setOnClickListener {
            likePost(!Dataholder.instance.selectedPost?.isLiked!!)
        }

        binding.addCommentRL.setOnClickListener {
            binding.slidingUpPanelLayout.panelState = SlidingUpPanelLayout.PanelState.EXPANDED
        }

        binding.slidingUpPanelLayout.setFadeOnClickListener {
            binding.slidingUpPanelLayout.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED
        }
    }

    private fun replaceFragment() {
        FragmentHelper.instance?.replaceFragment(
            fragmentManager = supportFragmentManager,
            fragment = addCommentFragment,
            containerId = binding.fragmentContainerFL.id
        )
    }

    private lateinit var commentAdapter: CommentAdapter
    private var commentList: MutableList<CommentModel?>? = ArrayList()

    private fun initAdapter() {
        commentAdapter = CommentAdapter(commentClickListener = object : CommentClickListener {
            override fun onClickShowStats(commentModel: CommentModel?) {
                //TODO
            }
        })
        commentAdapter.setList(commentList)
        val layoutManager = LinearLayoutManager(this@PostDetailActivity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.rvList.layoutManager = layoutManager
        binding.rvList.setHasFixedSize(true)
        binding.rvList.adapter = commentAdapter
        binding.rvList.itemAnimator = DefaultItemAnimator()
    }

    private fun loadComments() {
        userPresenter.getComments(
            postId = Dataholder.instance.selectedPost?.id,
            userView = object : UserView {
                override fun onGetCommentsSuccessful(commentResponse: CommentResponse?) {
                    commentList = commentResponse?.commentData?.commentList
                    initAdapter()
                }

                override fun onError() {

                }
            }
        )
    }

    private fun likePost(isLike: Boolean) {
        userPresenter.likePost(
            postId = Dataholder.instance.selectedPost?.id,
            isLiked = isLike,
            userView = object : UserView {
                override fun onGetPostsSuccessful(postResponse: PostResponse?) {
                    bindData()
                }

                override fun onError() {

                }
            }
        )
    }
}