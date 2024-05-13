package com.mustafaoguzdemirel.gamifiedstepcounterapp.view.start.register

import androidx.recyclerview.widget.RecyclerView
import com.mustafaoguzdemirel.gamifiedstepcounterapp.model.avatar.AvatarModel

interface AvatarCallback {
    fun onAvatarSelected(avatarModel: AvatarModel)
    fun onRvListCreated(recyclerView: RecyclerView)
}