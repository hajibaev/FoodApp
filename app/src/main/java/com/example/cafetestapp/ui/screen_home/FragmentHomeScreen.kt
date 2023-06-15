package com.example.cafetestapp.ui.screen_home

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import com.example.cafetestapp.R
import com.example.cafetestapp.databinding.FragmentHomeScreenBinding
import com.example.cafetestapp.ui.screen_home.adapter.CategoryFingerprint
import com.example.cafetestapp.ui.screen_home.listeners.CategoryItemOnClickListeners
import com.example.common_api.base.BaseFragment
import com.example.common_api.base.adapter.FingerprintAdapter
import com.example.ui_core.extensions.hideView
import com.example.ui_core.extensions.launchWhenViewStarted
import com.example.ui_core.extensions.showView
import kotlinx.coroutines.flow.filterNotNull
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentHomeScreen :
    BaseFragment<FragmentHomeScreenBinding, FragmentHomeScreenViewModel>(FragmentHomeScreenBinding::inflate),
    CategoryItemOnClickListeners {

    override val viewModel: FragmentHomeScreenViewModel by viewModel()

    private val adapter = FingerprintAdapter(
        listOf(CategoryFingerprint(this))
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
            getCategory.filterNotNull().observe {
                adapter.submitList(it)
                uiVisible()
            }
        }
    }

    private fun uiVisible() = with(binding()) {
        homeShimmer.stopShimmer()
        homeShimmer.hideView()
        homeScreen.showView()
    }


    override fun categoryItemOnClickListeners(categoryTitle: String) {
        viewModel.categoryClick(categoryTitle = categoryTitle)
    }


    override fun onStart() {
        super.onStart()
        requireActivity().findViewById<LinearLayout>(R.id.user_toolbar_container).showView()
    }
}