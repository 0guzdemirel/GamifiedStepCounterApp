package com.mustafaoguzdemirel.gamifiedstepcounterapp.view.purchasableContent.article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.mustafaoguzdemirel.gamifiedstepcounterapp.databinding.FragmentArticleBinding
import com.mustafaoguzdemirel.gamifiedstepcounterapp.model.purchasableContent.PurchasableContentModel

class ArticleFragment(
    private val purchasableContentModel: PurchasableContentModel
) : Fragment() {
    private var binding: FragmentArticleBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArticleBinding.inflate(inflater, container, false)
        bindData()
        return binding?.root
    }

    private fun bindData() {
        Glide.with(binding!!.articlePhotoIV).load(purchasableContentModel)
            .into(binding!!.articlePhotoIV)
        binding!!.articleTitleTV.text = purchasableContentModel.title
        binding!!.articleTitleTV.text = purchasableContentModel.description
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}