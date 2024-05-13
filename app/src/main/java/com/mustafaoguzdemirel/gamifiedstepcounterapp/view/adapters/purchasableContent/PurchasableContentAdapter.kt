package com.mustafaoguzdemirel.gamifiedstepcounterapp.view.adapters.purchasableContent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.makeramen.roundedimageview.RoundedImageView
import com.mustafaoguzdemirel.gamifiedstepcounterapp.R
import com.mustafaoguzdemirel.gamifiedstepcounterapp.model.purchasableContent.PurchasableContentModel

class PurchasableContentAdapter(
    private val purchasableContentClickListener: PurchasableContentClickListener,
) : RecyclerView.Adapter<PurchasableContentAdapter.ViewHolder>() {

    private val purchasableList: MutableList<PurchasableContentModel?> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.child_item_content, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val purchasable = purchasableList[position]

        if (purchasable?.typeId == "1") {
            holder.typeIconIV.setImageResource(R.drawable.spoti_icon)
        } else if (purchasable?.typeId == "2") {
            holder.typeIconIV.setImageResource(R.drawable.podcast_icon)
        } else {
            holder.typeIconIV.setImageResource(R.drawable.article_icon)
        }

        if (purchasable?.isOwned == true) {
            holder.ownedIconIV.visibility = View.VISIBLE
            holder.notOwnedLL.visibility = View.GONE
        } else {
            holder.ownedIconIV.visibility = View.GONE
            holder.notOwnedLL.visibility = View.VISIBLE
        }

        holder.nameTV.text = "" + purchasable?.title
        holder.priceTV.text = "" + purchasable?.price

        Glide.with(holder.contentPhotoIV).load(purchasable?.url).into(holder.contentPhotoIV)

        holder.mainRL.setOnClickListener {
            purchasableContentClickListener.onClickContent(purchasable)
        }
    }

    fun setList(list: List<PurchasableContentModel?>?) {
        purchasableList.clear()
        purchasableList.addAll(list!!)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return purchasableList.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var mainRL: RelativeLayout
        var notOwnedLL: LinearLayout
        var contentPhotoIV: RoundedImageView
        var typeIconIV: ImageView
        var ownedIconIV: ImageView
        var priceTV: TextView
        var nameTV: TextView

        init {
            mainRL = view.findViewById(R.id.mainRL)
            contentPhotoIV = view.findViewById(R.id.contentPhotoIV)
            typeIconIV = view.findViewById(R.id.typeIconIV)
            priceTV = view.findViewById(R.id.priceTV)
            nameTV = view.findViewById(R.id.nameTV)
            notOwnedLL = view.findViewById(R.id.notOwnedLL)
            ownedIconIV = view.findViewById(R.id.ownedIconIV)
        }
    }
}
