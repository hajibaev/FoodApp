package com.example.cafetestapp.ui.screen_basket.listener

interface BasketItemClickListener {

    fun observePrice(totalPrice: Int, isPlus: Boolean)

    fun deleteFood(id: Int)
}