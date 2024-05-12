package com.mustafaoguzdemirel.gamifiedstepcounterapp.view.purchasableContent.article

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.mustafaoguzdemirel.gamifiedstepcounterapp.databinding.ActivityArticleBinding
import com.mustafaoguzdemirel.gamifiedstepcounterapp.helper.Dataholder

class ArticleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityArticleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bindData()
        setListeners()
    }

    private fun bindData() {
        Glide.with(binding.articlePhotoIV).load(Dataholder.instance.selectedContent?.url)
            .into(binding.articlePhotoIV)
        binding.articleTitleTV.text = Dataholder.instance.selectedContent?.title
        binding.articleDesTV.text = Dataholder.instance.selectedContent?.description
    }

    private fun setListeners() {
        binding.backRL.setOnClickListener {
            finish()
        }
    }
}