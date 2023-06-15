package com.example.cafetestapp.ui.screen_profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.example.cafetestapp.R
import com.example.cafetestapp.databinding.FragmentProfileBinding
import com.example.common_api.base.BaseFragment
import com.example.ui_core.extensions.hideView
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentProfile : Fragment() {

    private val binding by lazy {
        FragmentProfileBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        requireActivity().findViewById<LinearLayout>(R.id.user_toolbar_container).hideView()
    }
}