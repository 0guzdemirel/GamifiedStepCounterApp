package com.mustafaoguzdemirel.gamifiedstepcounterapp.view.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import com.mustafaoguzdemirel.gamifiedstepcounterapp.R
import com.mustafaoguzdemirel.gamifiedstepcounterapp.databinding.FragmentBottomMenuBinding
import com.mustafaoguzdemirel.gamifiedstepcounterapp.model.eventbus.EventbusModel
import com.nef.app.view.main.BottomMenuCallback
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

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

    //**************** EventBus ****************
    @Subscribe
    fun onBottomMenuItemChanged(itemChangeEvent: EventbusModel.SelectBottomMenuItemEvent) {
        selectedIndex = itemChangeEvent.selectedItemIndex
        selectItem()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this)
    }

    override fun onDetach() {
        super.onDetach()
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this)
    }
    //**************** EventBus ****************
}