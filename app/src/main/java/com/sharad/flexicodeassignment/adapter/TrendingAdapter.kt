package com.sharad.flexicodeassignment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sharad.flexicodeassignment.R
import com.sharad.flexicodeassignment.model.Item

class TrendingAdapter(
    val context: Context,
    val itemList: ArrayList<Item>,
    var listner: OnItemClickListener
) :
    RecyclerView.Adapter<TrendingAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return MyViewHolder(inflater)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // holder.bind(itemList[position])

        var data = itemList[position]


       // holder.bind(data, position,listner)

        holder.name.text = data?.fullName
        holder.description.text = data?.description
        holder.subject.text = data?.license?.name

        Glide.with(holder.profileImage)
            .load(data?.owner?.avatarUrl)
            .centerCrop()
            .into(holder.profileImage)

        holder.detail_layout.visibility = if (data.expand) View.VISIBLE else View.GONE

        holder.itemView.setOnClickListener {
            data.expand= !data.expand
            notifyDataSetChanged()
        }
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val subject = view.findViewById<TextView>(R.id.tv_subject)
        val description = view.findViewById<TextView>(R.id.tv_description)
        val detail_layout = view.findViewById<ConstraintLayout>(R.id.detail_layout)
        val name = view.findViewById<TextView>(R.id.tv_name)
        val profileImage = view.findViewById<ImageView>(R.id.iv_profile_image)


        fun bind(data: Item, position: Int, listner: OnItemClickListener) {

            name.text = data?.fullName
            description.text = data?.description

            Glide.with(profileImage)
                .load(data?.owner?.avatarUrl)
                .centerCrop()
                .into(profileImage)

            detail_layout.visibility = if (data.expand) View.VISIBLE else View.GONE


        }
    }

    interface OnItemClickListener {
        fun showDetails(description:String)


    }

}