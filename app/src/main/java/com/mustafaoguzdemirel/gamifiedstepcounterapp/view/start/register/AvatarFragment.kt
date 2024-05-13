package com.mustafaoguzdemirel.gamifiedstepcounterapp.view.start.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.mustafaoguzdemirel.gamifiedstepcounterapp.R
import com.mustafaoguzdemirel.gamifiedstepcounterapp.databinding.FragmentAvatarBinding
import com.mustafaoguzdemirel.gamifiedstepcounterapp.model.avatar.AvatarModel
import com.mustafaoguzdemirel.gamifiedstepcounterapp.view.adapters.avatar.AvatarAdapter
import com.mustafaoguzdemirel.gamifiedstepcounterapp.view.adapters.avatar.AvatarClickListener

class AvatarFragment(private val avatarCallback: AvatarCallback) : Fragment() {
    private var binding: FragmentAvatarBinding? = null

    private lateinit var avatarList: MutableList<AvatarModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAvatarBinding.inflate(inflater, container, false)
        initData()
        initAdapter()
        return binding?.root
    }

    private fun initData() {
        avatarList = mutableListOf(
            AvatarModel(id = 0, drawableId = R.drawable.avatar_1, isSelected = false),
            AvatarModel(id = 1, drawableId = R.drawable.avatar_2, isSelected = false),
            AvatarModel(id = 2, drawableId = R.drawable.avatar_3, isSelected = false),
            AvatarModel(id = 3, drawableId = R.drawable.avatar_4, isSelected = false),
            AvatarModel(id = 4, drawableId = R.drawable.avatar_5, isSelected = false),
            AvatarModel(id = 5, drawableId = R.drawable.avatar_6, isSelected = false),
            AvatarModel(id = 6, drawableId = R.drawable.avatar_7, isSelected = false),
            AvatarModel(id = 7, drawableId = R.drawable.avatar_8, isSelected = false),
            AvatarModel(id = 8, drawableId = R.drawable.avatar_9, isSelected = false),
            AvatarModel(id = 9, drawableId = R.drawable.avatar_10, isSelected = false),
            AvatarModel(id = 10, drawableId = R.drawable.avatar_11, isSelected = false),
            AvatarModel(id = 11, drawableId = R.drawable.avatar_12, isSelected = false),
            AvatarModel(id = 12, drawableId = R.drawable.avatar_13, isSelected = false),
            AvatarModel(id = 13, drawableId = R.drawable.avatar_14, isSelected = false),
            AvatarModel(id = 14, drawableId = R.drawable.avatar_15, isSelected = false),
            AvatarModel(id = 15, drawableId = R.drawable.avatar_16, isSelected = false),
            AvatarModel(id = 16, drawableId = R.drawable.avatar_17, isSelected = false),
            AvatarModel(id = 17, drawableId = R.drawable.avatar_18, isSelected = false),
        )
    }

    private lateinit var avatarAdapter: AvatarAdapter

    private fun initAdapter() {
        avatarAdapter = AvatarAdapter(avatarClickListener = object : AvatarClickListener {
            override fun onClickAvatar(avatarModel: AvatarModel) {
                for (a in avatarList) {
                    a.isSelected = false
                }
                avatarModel.isSelected = true
                avatarAdapter.notifyDataSetChanged()
                avatarCallback.onAvatarSelected(avatarModel)
            }
        })
        avatarAdapter.setList(avatarList)
        val layoutManager = GridLayoutManager(context, 3)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding?.rvList?.layoutManager = layoutManager
        binding?.rvList?.setHasFixedSize(true)
        binding?.rvList?.adapter = avatarAdapter
        binding?.rvList?.itemAnimator = DefaultItemAnimator()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onResume() {
        super.onResume()
        avatarCallback.onRvListCreated(binding!!.rvList)
    }
}