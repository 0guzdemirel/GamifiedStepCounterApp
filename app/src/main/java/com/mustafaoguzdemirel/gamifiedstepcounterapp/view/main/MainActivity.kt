package com.mustafaoguzdemirel.gamifiedstepcounterapp.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hukukkarar.app.common.helper.FragmentHelper
import com.mustafaoguzdemirel.gamifiedstepcounterapp.databinding.ActivityMainBinding
import com.mustafaoguzdemirel.gamifiedstepcounterapp.model.eventbus.EventbusModel
import com.mustafaoguzdemirel.gamifiedstepcounterapp.view.purchasableContent.PurchasableContentFragment
import com.mustafaoguzdemirel.gamifiedstepcounterapp.view.ranking.RankingFragment
import com.mustafaoguzdemirel.gamifiedstepcounterapp.view.social.SocialFragment
import com.nef.app.view.main.BottomMenuCallback
import org.greenrobot.eventbus.EventBus

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val SOCIAL_FRAGMENT_TAG = "socialFragmentTag"
    private val RANKING_FRAGMENT_TAG = "rankingFragmentTag"
    private val CONTENT_FRAGMENT_TAG = "contentFragmentTag"

    private val socialFragment = SocialFragment(object : MainCallback {
        override fun onRanking() {
            EventBus.getDefault()
                .post(EventbusModel.SelectBottomMenuItemEvent(selectedItemIndex = 1))
            openRankingFragment()
        }

        override fun onContent() {
            EventBus.getDefault()
                .post(EventbusModel.SelectBottomMenuItemEvent(selectedItemIndex = 2))
            openContentFragment()
        }
    })
    private val rankingFragment = RankingFragment()
    private val purchasableContentFragment = PurchasableContentFragment()
    private val bottomMenuFragment = BottomMenuFragment(
        callback = object : BottomMenuCallback {
            override fun onItemSelected(selectedItemIndex: Int) {
                when (selectedItemIndex) {
                    0 -> openSocialFragment()
                    1 -> openRankingFragment()
                    2 -> openContentFragment()
                    else -> openSocialFragment()
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        FragmentHelper.instance?.replaceFragment(
            fragmentManager = supportFragmentManager,
            fragment = bottomMenuFragment,
            containerId = binding.bottomMenuContainerFL.id
        )

        openSocialFragment()
    }

    private fun openSocialFragment() {
        if (!socialFragment.isAdded)
            FragmentHelper.instance?.addFragment(
                fragmentManager = supportFragmentManager,
                fragment = socialFragment,
                fragmentTag = SOCIAL_FRAGMENT_TAG,
                containerId = binding.mainFragmentContainerFL.id
            )

        FragmentHelper.instance?.showFragment(
            fragmentManager = supportFragmentManager,
            fragmentTag = SOCIAL_FRAGMENT_TAG
        )

        FragmentHelper.instance?.hideFragment(
            fragmentManager = supportFragmentManager,
            fragmentTag = RANKING_FRAGMENT_TAG
        )

        FragmentHelper.instance?.hideFragment(
            fragmentManager = supportFragmentManager,
            fragmentTag = CONTENT_FRAGMENT_TAG
        )
    }

    private fun openRankingFragment() {
        if (!rankingFragment.isAdded)
            FragmentHelper.instance?.addFragment(
                fragmentManager = supportFragmentManager,
                fragment = rankingFragment,
                fragmentTag = RANKING_FRAGMENT_TAG,
                containerId = binding.mainFragmentContainerFL.id
            )

        FragmentHelper.instance?.hideFragment(
            fragmentManager = supportFragmentManager,
            fragmentTag = SOCIAL_FRAGMENT_TAG
        )

        FragmentHelper.instance?.showFragment(
            fragmentManager = supportFragmentManager,
            fragmentTag = RANKING_FRAGMENT_TAG
        )

        FragmentHelper.instance?.hideFragment(
            fragmentManager = supportFragmentManager,
            fragmentTag = CONTENT_FRAGMENT_TAG
        )
    }

    private fun openContentFragment() {
        if (!purchasableContentFragment.isAdded)
            FragmentHelper.instance?.addFragment(
                fragmentManager = supportFragmentManager,
                fragment = purchasableContentFragment,
                fragmentTag = CONTENT_FRAGMENT_TAG,
                containerId = binding.mainFragmentContainerFL.id
            )

        FragmentHelper.instance?.hideFragment(
            fragmentManager = supportFragmentManager,
            fragmentTag = SOCIAL_FRAGMENT_TAG
        )

        FragmentHelper.instance?.hideFragment(
            fragmentManager = supportFragmentManager,
            fragmentTag = RANKING_FRAGMENT_TAG
        )

        FragmentHelper.instance?.showFragment(
            fragmentManager = supportFragmentManager,
            fragmentTag = CONTENT_FRAGMENT_TAG
        )
    }
}