package com.example.cafetestapp.ui.screen_basket.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.cafetestapp.R
import com.example.cafetestapp.databinding.ItemBasketBinding
import com.example.cafetestapp.models.adapter_models.BasketAdapterModel
import com.example.cafetestapp.ui.screen_basket.listener.BasketItemClickListener
import com.example.common_api.base.adapter.BaseViewHolder
import com.example.common_api.base.adapter.Item
import com.example.common_api.base.adapter.ItemFingerprint
import com.example.ui_core.extensions.setOnDownEffectClickListener
import com.example.ui_core.extensions.showRoundedImage

class BasketFingerprint(private val listener: BasketItemClickListener) :
    ItemFingerprint<ItemBasketBinding, BasketAdapterModel> {

    override fun isRelativeItem(item: Item) = item is BasketAdapterModel

    override fun getLayoutId() = R.layout.item_basket

    override fun getViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<ItemBasketBinding, BasketAdapterModel> {
        val binding = ItemBasketBinding.inflate(layoutInflater, parent, false)
        return DishesViewHolder(binding, listener = listener)
    }

    override fun getDiffUtil() = diffUtil

    private val diffUtil = object : DiffUtil.ItemCallback<BasketAdapterModel>() {

        override fun areItemsTheSame(
            oldItem: BasketAdapterModel,
            newItem: BasketAdapterModel
        ) = oldItem.dishes_id == newItem.dishes_id

        override fun areContentsTheSame(
            oldItem: BasketAdapterModel,
            newItem: BasketAdapterModel
        ) = oldItem == newItem
    }
}

class DishesViewHolder(
    binding: ItemBasketBinding,
    private val listener: BasketItemClickListener
) : BaseViewHolder<ItemBasketBinding, BasketAdapterModel>(binding) {

    var foodCount: Int = 1

    var totalPrice: Int = 0

    override fun onBind(item: BasketAdapterModel) {
        super.onBind(item)
        setupViews()
        setOnClickListeners()
    }

    @SuppressLint("SetTextI18n")
    private fun setupViews() = with(binding) {
        itemView.context.showRoundedImage(imageUrl = item.image_url!!, imageView = foodPoster)
        title.text = item.dishes_name
        count.text = foodCount.toString()
        foodPrice.text = "${item.price} ₽"
        foodWeight.text = "${item.weight}г"
    }

    private fun setOnClickListeners() = with(binding) {
        plusCount.setOnDownEffectClickListener {
            foodCount += 1
            observePrice(item.price)
        }

        minusCount.setOnDownEffectClickListener {
            foodCount--
            totalPrice = foodCount * item.price
            observePrice(item.price, isPlus = false)
            if (foodCount == 0) listener.deleteFood(item.dishes_id)
        }
    }

    private fun observePrice(price: Int, isPlus: Boolean = true) = with(binding) {
        count.text = foodCount.toString()
        listener.observePrice(price, isPlus)
    }
}