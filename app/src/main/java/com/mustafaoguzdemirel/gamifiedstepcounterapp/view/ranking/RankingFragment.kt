package com.mustafaoguzdemirel.gamifiedstepcounterapp.view.ranking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.mustafaoguzdemirel.gamifiedstepcounterapp.R
import com.mustafaoguzdemirel.gamifiedstepcounterapp.databinding.FragmentRankingBinding
import com.mustafaoguzdemirel.gamifiedstepcounterapp.helper.Dataholder
import com.mustafaoguzdemirel.gamifiedstepcounterapp.helper.NavigationHelper
import com.mustafaoguzdemirel.gamifiedstepcounterapp.helper.UIHelper
import com.mustafaoguzdemirel.gamifiedstepcounterapp.model.user.UserModel
import com.mustafaoguzdemirel.gamifiedstepcounterapp.view.adapters.ranking.RankingAdapter
import com.mustafaoguzdemirel.gamifiedstepcounterapp.view.main.MainCallback
import com.mustafaoguzdemirel.gamifiedstepcounterapp.view.start.LoginActivity
import com.sothree.slidinguppanel.SlidingUpPanelLayout

class RankingFragment(private val mainCallback: MainCallback) : Fragment() {
    private var binding: FragmentRankingBinding? = null
    private var isStreakScreenON = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRankingBinding.inflate(inflater, container, false)
        bindData()
        setListeners()
        return binding?.root
    }

    private fun bindData() {
        binding!!.streakCountTV.text = "" + Dataholder.instance.currentUserModel?.streakCount
        binding!!.avgStepCountTV.text = "" + Dataholder.instance.currentUserModel?.avgStepCount
        binding!!.coinTV.text = "" + Dataholder.instance.currentUserModel?.coin

        binding!!.profilePhotoIV.setImageResource(UIHelper.getAvatar(Dataholder.instance.currentUserModel?.avatarId?.toInt()))
        binding!!.photoIV.setImageResource(UIHelper.getAvatar(Dataholder.instance.currentUserModel?.avatarId?.toInt()))
        binding!!.profilePhotoMainIV.setImageResource(UIHelper.getAvatar(Dataholder.instance.currentUserModel?.avatarId?.toInt()))

        binding!!.todayStepCountTV.text =
            "" + Dataholder.instance.currentUserModel?.todaysStepCount + "/10000"
        binding!!.todayStepsProgressBar.progress =
            Dataholder.instance.currentUserModel?.todaysStepCount!!

        bindMyRank()
    }

    private fun setListeners() {
        binding!!.streakTV.setOnClickListener {
            isStreakScreenON = true
            binding!!.avgStepsTV.background = null
            binding!!.streakTV.background =
                resources.getDrawable(R.drawable.rounded_shape_transparent_bordered_color_brown_900_rad_5)
            bindMyRank()
        }

        binding!!.avgStepsTV.setOnClickListener {
            isStreakScreenON = false
            binding!!.streakTV.background = null
            binding!!.avgStepsTV.background =
                resources.getDrawable(R.drawable.rounded_shape_transparent_bordered_color_brown_900_rad_5)
            bindMyRank()
        }

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

        binding!!.walkRL.setOnClickListener {
            binding!!.slidingUpPanelLayout.panelState = SlidingUpPanelLayout.PanelState.EXPANDED
        }

        binding!!.slidingUpPanelLayout.setFadeOnClickListener {
            binding!!.slidingUpPanelLayout.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED
        }
    }

    private fun bindMyRank() {
        if (isStreakScreenON) {
            binding!!.valueTV.text =
                "" + Dataholder.instance.currentUserModel?.streakCount + " " + resources.getString(R.string.days)
            binding!!.rankTV.text = "" + Dataholder.instance.currentUserModel?.streakRanking

            binding!!.streakRvList.visibility = View.VISIBLE
            binding!!.avgStepRvList.visibility = View.GONE

        } else {
            binding!!.valueTV.text =
                "" + Dataholder.instance.currentUserModel?.avgStepCount + " " + resources.getString(
                    R.string.steps
                )
            binding!!.rankTV.text = "" + Dataholder.instance.currentUserModel?.avgStepRanking

            binding!!.streakRvList.visibility = View.GONE
            binding!!.avgStepRvList.visibility = View.VISIBLE
        }
    }

    private lateinit var streakRankingAdapter: RankingAdapter
    private lateinit var avgStepRankingAdapter: RankingAdapter
    private var streakRankList: MutableList<UserModel> = ArrayList()
    private var avgStepsRankList: MutableList<UserModel> = ArrayList()

    private fun initStreakAdapter() {
        streakRankingAdapter = RankingAdapter(isForStreak = true)
        streakRankingAdapter.setList(streakRankList)
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding?.streakRvList?.layoutManager = layoutManager
        binding?.streakRvList?.setHasFixedSize(true)
        binding?.streakRvList?.adapter = streakRankingAdapter
        binding?.streakRvList?.itemAnimator = DefaultItemAnimator()
    }

    private fun initAvgStepsAdapter() {
        avgStepRankingAdapter = RankingAdapter(isForStreak = false)
        avgStepRankingAdapter.setList(avgStepsRankList)
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding?.avgStepRvList?.layoutManager = layoutManager
        binding?.avgStepRvList?.setHasFixedSize(true)
        binding?.avgStepRvList?.adapter = avgStepRankingAdapter
        binding?.avgStepRvList?.itemAnimator = DefaultItemAnimator()
    }



    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}