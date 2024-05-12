package com.mustafaoguzdemirel.gamifiedstepcounterapp.view.social

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.hukukkarar.app.common.helper.FragmentHelper
import com.mustafaoguzdemirel.gamifiedstepcounterapp.databinding.FragmentSocialBinding
import com.mustafaoguzdemirel.gamifiedstepcounterapp.helper.Dataholder
import com.mustafaoguzdemirel.gamifiedstepcounterapp.helper.NavigationHelper
import com.mustafaoguzdemirel.gamifiedstepcounterapp.helper.UIHelper
import com.mustafaoguzdemirel.gamifiedstepcounterapp.model.post.PostModel
import com.mustafaoguzdemirel.gamifiedstepcounterapp.model.user.UserModel
import com.mustafaoguzdemirel.gamifiedstepcounterapp.view.adapters.post.PostAdapter
import com.mustafaoguzdemirel.gamifiedstepcounterapp.view.adapters.post.PostClickListener
import com.mustafaoguzdemirel.gamifiedstepcounterapp.view.main.MainCallback
import com.mustafaoguzdemirel.gamifiedstepcounterapp.view.social.post.CreatePostFragment
import com.mustafaoguzdemirel.gamifiedstepcounterapp.view.social.post.PostCallback
import com.mustafaoguzdemirel.gamifiedstepcounterapp.view.social.post.PostDetailActivity
import com.mustafaoguzdemirel.gamifiedstepcounterapp.view.start.LoginActivity
import com.sothree.slidinguppanel.SlidingUpPanelLayout

class SocialFragment(private val mainCallback: MainCallback) : Fragment() {
    private var binding: FragmentSocialBinding? = null

    private val createPostFragment = CreatePostFragment(
        postCallback = object : PostCallback {
            override fun onPostCreated(postModel: PostModel) {
                postList.add(postModel)
                initAdapter()
                binding!!.slidingUpPanelLayout.panelState =
                    SlidingUpPanelLayout.PanelState.COLLAPSED
            }
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSocialBinding.inflate(inflater, container, false)
        replaceFragment()
        bindData()
        initData()
        setListeners()
        return binding?.root
    }

    private fun bindData() {
        binding!!.streakCountTV.text = "" + Dataholder.instance.currentUserModel?.streakCount
        binding!!.avgStepCountTV.text = "" + Dataholder.instance.currentUserModel?.avgStepCount
        binding!!.coinTV.text = "" + Dataholder.instance.currentUserModel?.coin
        binding!!.profilePhotoIV.setImageResource(UIHelper.getAvatar(Dataholder.instance.currentUserModel?.avatarId))
    }

    private fun setListeners() {
        binding!!.infoRL.setOnClickListener {
            mainCallback.onRanking()
        }

        binding!!.coinRL.setOnClickListener {
            mainCallback.onContent()
        }

        binding!!.logoutRL.setOnClickListener {
            activity?.finishAffinity()
            NavigationHelper.instance?.navigateToActivity(
                context = context,
                navigateActivity = LoginActivity::class.java
            )
        }

        binding!!.createPostRL.setOnClickListener {
            binding!!.slidingUpPanelLayout.panelState = SlidingUpPanelLayout.PanelState.EXPANDED
        }

        binding!!.slidingUpPanelLayout.setFadeOnClickListener {
            binding!!.slidingUpPanelLayout.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED
        }
    }

    private fun replaceFragment() {
        FragmentHelper.instance?.replaceFragment(
            fragmentManager = requireActivity().supportFragmentManager,
            fragment = createPostFragment,
            containerId = binding!!.fragmentContainerFL.id
        )
    }

    private lateinit var postAdapter: PostAdapter
    private var postList: MutableList<PostModel> = ArrayList()

    private fun initAdapter() {
        postAdapter = PostAdapter(postClickListener = object : PostClickListener {
            override fun onClickPost(postModel: PostModel) {
                Dataholder.instance.selectedPost = postModel
                NavigationHelper.instance?.navigateToActivity(
                    context = context,
                    navigateActivity = PostDetailActivity::class.java
                )
            }

            override fun onClickPostLike(postModel: PostModel, position: Int) {
                postModel.isLiked = !postModel.isLiked
                postAdapter.notifyItemChanged(position)
            }

            override fun onClickShowStats(postModel: PostModel) {
                //TODO
            }
        })
        postAdapter.setList(postList)
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding?.rvList?.layoutManager = layoutManager
        binding?.rvList?.setHasFixedSize(true)
        binding?.rvList?.adapter = postAdapter
        binding?.rvList?.itemAnimator = DefaultItemAnimator()
    }

    private fun initData() {
        postList = mutableListOf(
            PostModel(
                id = "1",
                description = "Günün güzelliğine inanamıyorum! Hava mükemmel, doğa harika ve ben de 10.000 adımlık hedefime bir adım daha yaklaştım! Siz de bugün doğayla buluşup sağlık dolu bir yürüyüş yapın, ne dersiniz? \uD83C\uDF1E\uD83C\uDF3F",
                createdDate = "10.05.2024",
                isLiked = false,
                likeCount = 0,
                commentCount = 0,
                userModel = Dataholder.instance.currentUserModel
            ),
            PostModel(
                id = "2",
                description = "Kendime sağlıklı bir kahvaltı hazırladım ve güne enerjik başladım! Sağlıklı beslenme, aktif yaşamın olmazsa olmazı! Siz de bugün hangi sağlıklı lezzetleri tercih ettiniz? Paylaşın, birbirimize ilham olalım! \uD83C\uDF4F\uD83E\uDD51",
                createdDate = "09.05.2024",
                isLiked = false,
                likeCount = 0,
                commentCount = 0,
                userModel = Dataholder.instance.currentUserModel
            )
        )
        initAdapter()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}