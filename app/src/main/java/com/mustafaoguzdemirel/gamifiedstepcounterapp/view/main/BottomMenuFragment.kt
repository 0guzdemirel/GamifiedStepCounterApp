package com.mustafaoguzdemirel.gamifiedstepcounterapp.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import com.mustafaoguzdemirel.gamifiedstepcounterapp.R
import com.mustafaoguzdemirel.gamifiedstepcounterapp.databinding.FragmentBottomMenuBinding
import com.nef.app.view.main.BottomMenuCallback

class BottomMenuFragment(private val callback: BottomMenuCallback) : Fragment() {
    private var binding: FragmentBottomMenuBinding? = null
    private lateinit var navButtonsArray: Array<RelativeLayout>
    private var selectedIndex = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBottomMenuBinding.inflate(inflater, container, false)
        initArrays()
        setListeners()
        return binding?.root
    }

    private fun setListeners() {
        binding?.socialRL?.setOnClickListener {
            selectedIndex = 0
            selectItem()
            callback.onItemSelected(selectedIndex)
        }

        binding?.rankingRL?.setOnClickListener {
            selectedIndex = 1
            selectItem()
            callback.onItemSelected(selectedIndex)
        }

        binding?.contentRL?.setOnClickListener {
            selectedIndex = 2
            selectItem()
            callback.onItemSelected(selectedIndex)
        }
    }

    private fun initArrays() {
        if (binding != null) {
            navButtonsArray = arrayOf(
                binding!!.socialBtnRL,
                binding!!.rankingBtnRL,
                binding!!.contentBtnRL
            )
        }
    }

    private fun selectItem() {
        resetSelectedItems()
        navButtonsArray[selectedIndex].background =
            context?.getDrawable(R.drawable.rounded_shape_transparent_boredered_color_white_rad_8)
    }

    private fun resetSelectedItems() {
        binding?.socialBtnRL?.background = null
        binding?.rankingBtnRL?.background = null
        binding?.contentBtnRL?.background = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}