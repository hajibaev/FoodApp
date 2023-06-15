package com.example.cafetestapp.ui.screen_home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.cafetestapp.R
import com.example.cafetestapp.databinding.ItemCategotiesBinding
import com.example.cafetestapp.models.adapter_models.CategoryAdapterModel
import com.example.cafetestapp.ui.screen_home.listeners.CategoryItemOnClickListeners
import com.example.common_api.base.adapter.BaseViewHolder
import com.example.common_api.base.adapter.Item
import com.example.common_api.base.adapter.ItemFingerprint
import com.example.ui_core.extensions.setOnDownEffectClickListener
import com.example.ui_core.extensions.showRoundedImage

class CategoryFingerprint(private val listener: CategoryItemOnClickListeners) :
    ItemFingerprint<ItemCategotiesBinding, CategoryAdapterModel> {

    override fun isRelativeItem(item: Item) = item is CategoryAdapterModel

    override fun getLayoutId() = R.layout.item_categoties

    override fun getViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<ItemCategotiesBinding, CategoryAdapterModel> {
        val binding = ItemCategotiesBinding.inflate(layoutInflater, parent, false)
        return CategoryViewHolder(binding, listener = listener)
    }

    override fun getDiffUtil() = diffUtil

    private val diffUtil = object : DiffUtil.ItemCallback<CategoryAdapterModel>() {

        override fun areItemsTheSame(
            oldItem: CategoryAdapterModel,
            newItem: CategoryAdapterModel
        ) = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: CategoryAdapterModel,
            newItem: CategoryAdapterModel
        ) = oldItem == newItem
    }
}

class CategoryViewHolder(
    binding: ItemCategotiesBinding,
    private val listener: CategoryItemOnClickListeners
) : BaseViewHolder<ItemCategotiesBinding, CategoryAdapterModel>(binding) {

    override fun onBind(item: CategoryAdapterModel) {
        super.onBind(item)
        setupViews()
        setOnClickListeners()
    }

    private fun setupViews() = with(binding) {
        itemView.context.showRoundedImage(imageUrl = item.posterUrl, imageView = posterCategory)
        title.text = item.title
    }

    private fun setOnClickListeners() = with(binding) {
        root.setOnDownEffectClickListener {
            listener.categoryItemOnClickListeners(item.title)
        }
    }

}

