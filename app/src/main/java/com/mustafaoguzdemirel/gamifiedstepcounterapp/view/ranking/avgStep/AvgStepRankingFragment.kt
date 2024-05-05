package com.mustafaoguzdemirel.gamifiedstepcounterapp.view.ranking.avgStep

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mustafaoguzdemirel.gamifiedstepcounterapp.databinding.FragmentMainBinding

class AvgStepRankingFragment : Fragment() {
    private var binding: FragmentMainBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}