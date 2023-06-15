package com.example.cafetestapp.ui.screen_search

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import com.example.cafetestapp.R
import com.example.cafetestapp.databinding.FragmentSearchBinding
import com.example.cafetestapp.ui.screen_home.FragmentHomeScreenViewModel
import com.example.common_api.base.BaseFragment
import com.example.ui_core.extensions.hideView
import com.example.ui_core.extensions.showView
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentSearch :
    BaseFragment<FragmentSearchBinding, FragmentHomeScreenViewModel>(FragmentSearchBinding::inflate) {

    override val viewModel: FragmentHomeScreenViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        requireActivity().findViewById<LinearLayout>(R.id.user_toolbar_container).showView()
    }
}