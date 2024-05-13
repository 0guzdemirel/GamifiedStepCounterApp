package com.mustafaoguzdemirel.gamifiedstepcounterapp.view.adapters.comment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.makeramen.roundedimageview.RoundedImageView
import com.mustafaoguzdemirel.gamifiedstepcounterapp.R
import com.mustafaoguzdemirel.gamifiedstepcounterapp.helper.UIHelper
import com.mustafaoguzdemirel.gamifiedstepcounterapp.model.comment.CommentModel

class CommentAdapter(
    private val commentClickListener: CommentClickListener,
) : RecyclerView.Adapter<CommentAdapter.ViewHolder>() {

    private val commentList: MutableList<CommentModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.child_item_comment, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comment = commentList[position]

        holder.profilePhotoIV.setImageResource(UIHelper.getAvatar(comment.userModel?.avatarId?.toInt()))

        holder.nameTV.text = "" + comment.userModel?.name
        holder.dateTV.text = "" + comment.createdDate
        holder.contentTV.text = "" + comment.description

        holder.userStatsRL.setOnClickListener {
            commentClickListener.onClickShowStats(comment)
        }
    }

    fun setList(list: List<CommentModel>?) {
        commentList.clear()
        commentList.addAll(list!!)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return commentList.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var userStatsRL: RelativeLayout
        var profilePhotoIV: RoundedImageView
        var nameTV: TextView
        var dateTV: TextView
        var contentTV: TextView

        init {
            userStatsRL = view.findViewById(R.id.userStatsRL)
            profilePhotoIV = view.findViewById(R.id.profilePhotoIV)
            nameTV = view.findViewById(R.id.nameTV)
            dateTV = view.findViewById(R.id.dateTV)
            contentTV = view.findViewById(R.id.contentTV)
        }
    }
}
