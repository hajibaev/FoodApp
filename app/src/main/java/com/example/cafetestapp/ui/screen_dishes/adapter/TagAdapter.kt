package com.example.cafetestapp.ui.screen_dishes.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.cafetestapp.R
import com.example.ui_core.extensions.getAttrColor
import com.example.ui_core.extensions.setOnDownEffectClickListener

class TagAdapter(private val listener: TagItemClick) :
    RecyclerView.Adapter<TagAdapter.RvViewHolder>() {

    private var tagsList: HashSet<String> = HashSet()

    private var selectedTag: Int = 0


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_tegs, parent, false)
        return RvViewHolder(view)
    }

    override fun onBindViewHolder(holder: RvViewHolder, position: Int) {
        val tagItem = tagsList.elementAt(position)
        holder.bind(tagItem)
        holder.itemView.setOnDownEffectClickListener {
            listener.itemClick(tag = tagItem, previousItem = selectedTag, selectedItem = position)
        }
    }

    override fun getItemCount() = tagsList.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: HashSet<String>?) {
        data?.let {
            this.tagsList = it
            notifyDataSetChanged()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
        fun setSelected(position: Int) {
        selectedTag = position
    }

    inner class RvViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        private val castName = view.findViewById<TextView>(R.id.title)
        private val container = view.findViewById<CardView>(R.id.container)


        val backgroundBlueColor =
            itemView.context.getAttrColor(R.attr.card_blue_background)

        val backgroundGrayColor =
            itemView.context.getAttrColor(R.attr.card_background)

        fun bind(tag: String) {
            castName.text = tag
            if (selectedTag == bindingAdapterPosition) {
                container.setCardBackgroundColor(backgroundBlueColor)
            } else {
                container.setCardBackgroundColor(backgroundGrayColor)
            }
        }
    }
}

interface TagItemClick {
    fun itemClick(tag: String, previousItem: Int, selectedItem: Int)
}

