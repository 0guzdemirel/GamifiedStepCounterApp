package com.mustafaoguzdemirel.gamifiedstepcounterapp.view.adapters.avatar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import com.makeramen.roundedimageview.RoundedImageView
import com.mustafaoguzdemirel.gamifiedstepcounterapp.R
import com.mustafaoguzdemirel.gamifiedstepcounterapp.model.avatar.AvatarModel

class AvatarAdapter(
    private val avatarClickListener: AvatarClickListener,
) : RecyclerView.Adapter<AvatarAdapter.ViewHolder>() {

    private val avatarList: MutableList<AvatarModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.child_item_avatar, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val avatar = avatarList[position]

        holder.avatarIV.setImageResource(avatar.drawableId)

        holder.selectedRL.visibility =
            if (avatar.isSelected)
                View.VISIBLE
            else
                View.GONE

        holder.mainRL.setOnClickListener {
            avatarClickListener.onClickAvatar(avatar)
        }
    }

    fun setList(list: List<AvatarModel>?) {
        avatarList.clear()
        avatarList.addAll(list!!)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return avatarList.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var mainRL: RelativeLayout
        var selectedRL: RelativeLayout
        var avatarIV: RoundedImageView

        init {
            mainRL = view.findViewById(R.id.mainRL)
            selectedRL = view.findViewById(R.id.selectedRL)
            avatarIV = view.findViewById(R.id.avatarIV)
        }
    }
}
