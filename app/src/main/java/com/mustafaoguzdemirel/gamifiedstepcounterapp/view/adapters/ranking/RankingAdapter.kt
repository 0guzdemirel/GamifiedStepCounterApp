package com.mustafaoguzdemirel.gamifiedstepcounterapp.view.adapters.ranking

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.makeramen.roundedimageview.RoundedImageView
import com.mustafaoguzdemirel.gamifiedstepcounterapp.R
import com.mustafaoguzdemirel.gamifiedstepcounterapp.helper.UIHelper
import com.mustafaoguzdemirel.gamifiedstepcounterapp.model.user.UserModel

class RankingAdapter(
    private val isForStreak: Boolean
) : RecyclerView.Adapter<RankingAdapter.ViewHolder>() {

    private val userList: MutableList<UserModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.child_item_ranking, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = userList[position]

        holder.photoIV.setImageResource(UIHelper.getAvatar(user.avatarId?.toInt()))
        holder.nameTV.text = "" + user.name

        if (isForStreak) {
            holder.rankTV.text = "" + user.streakRanking
            holder.valueTV.text = "" + user.streakCount + " days"
        } else {
            holder.rankTV.text = "" + user.avgStepRanking
            holder.valueTV.text = "" + user.avgStepCount + " steps"
        }

        if (position == userList.size - 1) {
            holder.dividerView.visibility = View.GONE
        } else {
            holder.dividerView.visibility = View.VISIBLE
        }
    }

    fun setList(list: List<UserModel>?) {
        userList.clear()
        userList.addAll(list!!)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var rankTV: TextView
        var nameTV: TextView
        var valueTV: TextView
        var photoIV: RoundedImageView
        var dividerView: View

        init {
            rankTV = view.findViewById(R.id.rankTV)
            nameTV = view.findViewById(R.id.nameTV)
            valueTV = view.findViewById(R.id.valueTV)
            photoIV = view.findViewById(R.id.photoIV)
            dividerView = view.findViewById(R.id.dividerView)
        }
    }
}
