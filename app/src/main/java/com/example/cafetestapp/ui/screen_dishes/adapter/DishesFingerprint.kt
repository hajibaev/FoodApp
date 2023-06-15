package com.example.cafetestapp.ui.screen_dishes.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.cafetestapp.R
import com.example.cafetestapp.databinding.ItemFoodBinding
import com.example.cafetestapp.models.adapter_models.DishesAdapterModel
import com.example.common_api.base.adapter.BaseViewHolder
import com.example.common_api.base.adapter.Item
import com.example.common_api.base.adapter.ItemFingerprint
import com.example.ui_core.extensions.setOnDownEffectClickListener
import com.example.ui_core.extensions.showRoundedImage

class DishesFingerprint : ItemFingerprint<ItemFoodBinding, DishesAdapterModel> {

    override fun isRelativeItem(item: Item) = item is DishesAdapterModel

    override fun getLayoutId() = R.layout.item_food

    override fun getViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<ItemFoodBinding, DishesAdapterModel> {
        val binding = ItemFoodBinding.inflate(layoutInflater, parent, false)
        return DishesViewHolder(binding)
    }

    override fun getDiffUtil() = diffUtil

    private val diffUtil = object : DiffUtil.ItemCallback<DishesAdapterModel>() {

        override fun areItemsTheSame(
            oldItem: DishesAdapterModel,
            newItem: DishesAdapterModel
        ) = oldItem.dishes.dishes_id == newItem.dishes.dishes_id

        override fun areContentsTheSame(
            oldItem: DishesAdapterModel,
            newItem: DishesAdapterModel
        ) = oldItem == newItem
    }
}

class DishesViewHolder(binding: ItemFoodBinding) :
    BaseViewHolder<ItemFoodBinding, DishesAdapterModel>(binding) {

    override fun onBind(item: DishesAdapterModel) {
        super.onBind(item)
        setupViews()
        setOnClickListeners()
    }

    private fun setupViews() = with(binding) {
        itemView.context.showRoundedImage(imageUrl = item.dishes.image_url!!, imageView = poster)
        title.text = item.dishes.dishes_name
    }

    private fun setOnClickListeners() = with(binding) {
        root.setOnDownEffectClickListener {
            item.listener.dishesItemOnClick(item.dishes.dishes_id)
            Log.i("hajibaev", "dishesItemOnClick id =${item.dishes.dishes_id}")
        }
    }

}

