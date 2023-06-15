package com.example.cafetestapp.ui.screen_basket

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import com.example.cafetestapp.R
import com.example.cafetestapp.databinding.FragmentBasketBinding
import com.example.cafetestapp.models.adapter_models.BasketAdapterModel
import com.example.cafetestapp.ui.screen_basket.adapter.BasketFingerprint
import com.example.cafetestapp.ui.screen_basket.listener.BasketItemClickListener
import com.example.common_api.base.BaseFragment
import com.example.common_api.base.adapter.FingerprintAdapter
import com.example.ui_core.extensions.hideView
import com.example.ui_core.extensions.launchWhenViewStarted
import com.example.ui_core.extensions.showView
import kotlinx.coroutines.flow.filterNotNull
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentBasket :
    BaseFragment<FragmentBasketBinding, FragmentBasketViewModel>(FragmentBasketBinding::inflate),
    BasketItemClickListener {

    override val viewModel: FragmentBasketViewModel by viewModel()


    private val adapter = FingerprintAdapter(
        listOf(BasketFingerprint(this))
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        observeRecourse()
    }

    private fun setupViews() = with(binding()) {
        recyclerView.adapter = adapter
    }

    private fun observeRecourse() = with(viewModel) {
        launchWhenViewStarted {
            getAllDishes.filterNotNull().observe(::filterAdapter)
            totalPrice.observe { binding().button.text = "Оплатить $it ₽" }
        }
    }

    private fun filterAdapter(basket: List<BasketAdapterModel>) = with(binding()) {
        adapter.submitList(basket)
        if (basket.isNotEmpty()) {
            lottieAnimationView.hideView()
            storage.showView()
        } else {
            lottieAnimationView.showView()
            storage.hideView()
        }
    }


    override fun observePrice(totalPrice: Int, isPlus: Boolean) {
        viewModel.observeTotalPrice(totalPrice, isPlus = isPlus)
    }

    override fun deleteFood(id: Int) {
        viewModel.deleteFoodFromStorage(id)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllPriceFromBasket()
        requireActivity().findViewById<LinearLayout>(R.id.user_toolbar_container).showView()
    }

}