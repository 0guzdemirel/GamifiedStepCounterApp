package com.mustafaoguzdemirel.gamifiedstepcounterapp.view.adapters.post

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.makeramen.roundedimageview.RoundedImageView
import com.mustafaoguzdemirel.gamifiedstepcounterapp.R
import com.mustafaoguzdemirel.gamifiedstepcounterapp.helper.UIHelper
import com.mustafaoguzdemirel.gamifiedstepcounterapp.model.post.PostModel

class PostAdapter(
    private val postClickListener: PostClickListener,
) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    private val postList: MutableList<PostModel?> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.child_item_post, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = postList[position]

        holder.profilePhotoIV.setImageResource(UIHelper.getAvatar(post?.userModel?.avatarId?.toInt()))

        holder.nameTV.text = "" + post?.userModel?.name
        holder.dateTV.text = "" + post?.createdDate
        holder.contentTV.text = "" + post?.description
        if (post?.likeCount!! > 0) {
            holder.likeCountTV.text = "" + post?.likeCount
        } else {
            holder.likeCountTV.text = "0"
        }
        holder.commentCountTV.text = "" + post?.commentCount

        holder.likeIconIV.setImageResource(
            if (post?.isLiked == true)
                R.drawable.like_filled_icon
            else
                R.drawable.like_icon
        )

        holder.mainLL.setOnClickListener {
            postClickListener.onClickPost(post)
        }

        holder.likeLL.setOnClickListener {
            postClickListener.onClickPostLike(post, position)
        }

        holder.userStatsRL.setOnClickListener {
            postClickListener.onClickShowStats(post)
        }
    }

    fun setList(list: List<PostModel?>?) {
        postList.clear()
        postList.addAll(list!!)
        notifyDataSetChanged()
    }

    fun getList(): List<PostModel?> {
        return postList
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var mainLL: LinearLayout
        var likeLL: LinearLayout
        var commentLL: LinearLayout
        var userStatsRL: RelativeLayout
        var profilePhotoIV: RoundedImageView
        var likeIconIV: ImageView
        var nameTV: TextView
        var dateTV: TextView
        var likeCountTV: TextView
        var commentCountTV: TextView
        var contentTV: TextView

        init {
            mainLL = view.findViewById(R.id.mainLL)
            likeLL = view.findViewById(R.id.likeLL)
            commentLL = view.findViewById(R.id.commentLL)
            userStatsRL = view.findViewById(R.id.userStatsRL)
            likeIconIV = view.findViewById(R.id.likeIconIV)
            profilePhotoIV = view.findViewById(R.id.profilePhotoIV)
            nameTV = view.findViewById(R.id.nameTV)
            dateTV = view.findViewById(R.id.dateTV)
            likeCountTV = view.findViewById(R.id.likeCountTV)
            commentCountTV = view.findViewById(R.id.commentCountTV)
            contentTV = view.findViewById(R.id.contentTV)
        }
    }
}
