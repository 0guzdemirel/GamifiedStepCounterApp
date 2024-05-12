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
        initData()
        return binding?.root
    }

    private fun bindData() {
        binding!!.streakCountTV.text = "" + Dataholder.instance.currentUserModel?.streakCount
        binding!!.avgStepCountTV.text = "" + Dataholder.instance.currentUserModel?.avgStepCount
        binding!!.coinTV.text = "" + Dataholder.instance.currentUserModel?.coin

        binding!!.profilePhotoIV.setImageResource(UIHelper.getAvatar(Dataholder.instance.currentUserModel?.avatarId))
        binding!!.photoIV.setImageResource(UIHelper.getAvatar(Dataholder.instance.currentUserModel?.avatarId))
        binding!!.profilePhotoMainIV.setImageResource(UIHelper.getAvatar(Dataholder.instance.currentUserModel?.avatarId))

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

    private fun initData() {
        streakRankList = mutableListOf(
            UserModel(
                id = "1",
                email = "test@gmail.com",
                password = "123456",
                name = "Elif Demirel",
                avatarId = 12,
                streakCount = 92,
                avgStepCount = 12348,
                streakRanking = 1,
                avgStepRanking = 2,
                todaysStepCount = 23484,
                coin = 0
            ),
            UserModel(
                id = "2",
                email = "test2@gmail.com",
                password = "123456",
                name = "Dilek Akay",
                avatarId = 10,
                streakCount = 83,
                avgStepCount = 12598,
                streakRanking = 2,
                avgStepRanking = 1,
                todaysStepCount = 1200,
                coin = 0
            ),
            UserModel(
                id = "3",
                email = "test2@gmail.com",
                password = "123456",
                name = "Ece Celik",
                avatarId = 6,
                streakCount = 70,
                avgStepCount = 11223,
                streakRanking = 3,
                avgStepRanking = 3,
                todaysStepCount = 5003,
                coin = 0
            ),
            UserModel(
                id = "5",
                email = "test2@gmail.com",
                password = "123456",
                name = "Altan Kahraman",
                avatarId = 11,
                streakCount = 65,
                avgStepCount = 10349,
                streakRanking = 4,
                avgStepRanking = 5,
                todaysStepCount = 2000,
                coin = 0
            ),
            UserModel(
                id = "6",
                email = "test2@gmail.com",
                password = "123456",
                name = "Beyza Akkaya",
                avatarId = 2,
                streakCount = 60,
                avgStepCount = 10200,
                streakRanking = 5,
                avgStepRanking = 6,
                todaysStepCount = 1242,
                coin = 0
            ),
            UserModel(
                id = "7",
                email = "test2@gmail.com",
                password = "123456",
                name = "Haluk Akman",
                avatarId = 5,
                streakCount = 52,
                avgStepCount = 10132,
                streakRanking = 6,
                avgStepRanking = 7,
                todaysStepCount = 4356,
                coin = 0
            ),
            UserModel(
                id = "8",
                email = "test2@gmail.com",
                password = "123456",
                name = "Doruk Kahraman",
                avatarId = 5,
                streakCount = 45,
                avgStepCount = 10003,
                streakRanking = 7,
                avgStepRanking = 8,
                todaysStepCount = 4332,
                coin = 0
            ),
            UserModel(
                id = "9",
                email = "test2@gmail.com",
                password = "123456",
                name = "Tuncay Akkaya",
                avatarId = 5,
                streakCount = 38,
                avgStepCount = 9822,
                streakRanking = 8,
                avgStepRanking = 9,
                todaysStepCount = 4356,
                coin = 0
            ),
            UserModel(
                id = "4",
                email = "test2@gmail.com",
                password = "123456",
                name = "Burak Uzunlar",
                avatarId = 6,
                streakCount = 12,
                avgStepCount = 10550,
                streakRanking = 9,
                avgStepRanking = 4,
                todaysStepCount = 4200,
                coin = 0
            ),
            UserModel(
                id = "10",
                email = "test2@gmail.com",
                password = "123456",
                name = "Zehra Kiraz",
                avatarId = 5,
                streakCount = 3,
                avgStepCount = 8500,
                streakRanking = 10,
                avgStepRanking = 10,
                todaysStepCount = 4356,
                coin = 0
            )
        )

        avgStepsRankList = mutableListOf(
            UserModel(
                id = "2",
                email = "test2@gmail.com",
                password = "123456",
                name = "Dilek Akay",
                avatarId = 10,
                streakCount = 83,
                avgStepCount = 12598,
                streakRanking = 2,
                avgStepRanking = 1,
                todaysStepCount = 1200,
                coin = 0
            ),
            UserModel(
                id = "1",
                email = "test@gmail.com",
                password = "123456",
                name = "Elif Demirel",
                avatarId = 12,
                streakCount = 92,
                avgStepCount = 12348,
                streakRanking = 1,
                avgStepRanking = 2,
                todaysStepCount = 23484,
                coin = 0
            ),
            UserModel(
                id = "3",
                email = "test2@gmail.com",
                password = "123456",
                name = "Ece Celik",
                avatarId = 6,
                streakCount = 70,
                avgStepCount = 11223,
                streakRanking = 3,
                avgStepRanking = 3,
                todaysStepCount = 5003,
                coin = 0
            ),
            UserModel(
                id = "4",
                email = "test2@gmail.com",
                password = "123456",
                name = "Burak Uzunlar",
                avatarId = 6,
                streakCount = 12,
                avgStepCount = 10550,
                streakRanking = 9,
                avgStepRanking = 4,
                todaysStepCount = 4200,
                coin = 0
            ),
            UserModel(
                id = "5",
                email = "test2@gmail.com",
                password = "123456",
                name = "Altan Kahraman",
                avatarId = 11,
                streakCount = 65,
                avgStepCount = 10349,
                streakRanking = 4,
                avgStepRanking = 5,
                todaysStepCount = 2000,
                coin = 0
            ),
            UserModel(
                id = "6",
                email = "test2@gmail.com",
                password = "123456",
                name = "Beyza Akkaya",
                avatarId = 2,
                streakCount = 60,
                avgStepCount = 10200,
                streakRanking = 5,
                avgStepRanking = 6,
                todaysStepCount = 1242,
                coin = 0
            ),
            UserModel(
                id = "7",
                email = "test2@gmail.com",
                password = "123456",
                name = "Haluk Akman",
                avatarId = 5,
                streakCount = 52,
                avgStepCount = 10132,
                streakRanking = 6,
                avgStepRanking = 7,
                todaysStepCount = 4356,
                coin = 0
            ),
            UserModel(
                id = "8",
                email = "test2@gmail.com",
                password = "123456",
                name = "Doruk Kahraman",
                avatarId = 5,
                streakCount = 45,
                avgStepCount = 10003,
                streakRanking = 7,
                avgStepRanking = 8,
                todaysStepCount = 4332,
                coin = 0
            ),
            UserModel(
                id = "9",
                email = "test2@gmail.com",
                password = "123456",
                name = "Tuncay Akkaya",
                avatarId = 5,
                streakCount = 38,
                avgStepCount = 9822,
                streakRanking = 8,
                avgStepRanking = 9,
                todaysStepCount = 4356,
                coin = 0
            ),
            UserModel(
                id = "10",
                email = "test2@gmail.com",
                password = "123456",
                name = "Zehra Kiraz",
                avatarId = 5,
                streakCount = 3,
                avgStepCount = 8500,
                streakRanking = 10,
                avgStepRanking = 10,
                todaysStepCount = 4356,
                coin = 0
            )
        )

        initStreakAdapter()
        initAvgStepsAdapter()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}