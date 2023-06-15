package com.example.cafetestapp.ui.screen_dishes

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import com.example.cafetestapp.R
import com.example.cafetestapp.databinding.FragmentDishesBinding
import com.example.cafetestapp.ui.screen_dishes.adapter.DishesFingerprint
import com.example.cafetestapp.ui.screen_dishes.adapter.TagItemClick
import com.example.cafetestapp.ui.screen_dishes.adapter.TagAdapter
import com.example.cafetestapp.ui.screen_food_info.FoodInfoDialogFragment
import com.example.common_api.base.BaseFragment
import com.example.common_api.base.adapter.FingerprintAdapter
import com.example.ui_core.extensions.hideView
import com.example.ui_core.extensions.launchWhenViewStarted
import com.example.ui_core.extensions.setOnDownEffectClickListener
import com.example.ui_core.extensions.showOnlyOne
import kotlinx.coroutines.flow.filterNotNull
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentDishes :
    BaseFragment<FragmentDishesBinding, FragmentDishesViewModel>(FragmentDishesBinding::inflate),
    TagItemClick {

    override val viewModel: FragmentDishesViewModel by viewModel()

    private val categoryTitle by lazy { FragmentDishesArgs.fromBundle(requireArguments()).title }

    private val adapter = FingerprintAdapter(
        listOf(DishesFingerprint())
    )

    private val tagAdapter by lazy {
        TagAdapter(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setOnClickListener()
        observeRecourse()
    }

    private fun setOnClickListener() = with(binding()) {
        userToolbar.back.setOnDownEffectClickListener { viewModel.navigateBack() }
    }

    private fun setupViews() = with(binding()) {
        tagsRv.adapter = tagAdapter
        recyclerView.adapter = adapter
        userToolbar.title.text = categoryTitle
    }


    private fun observeRecourse() = with(viewModel) {
        launchWhenViewStarted {
            dishes.observe { adapter.submitList(it) }
            all_tags.filterNotNull().observe { tagAdapter.setData(it) }
            tagsFlow.filterNotNull().observe { tagAdapter.setSelected(0) }
            showDialog.observe { showFoodInfoDialog(it.toString()) }
        }

    }


    private fun showFoodInfoDialog(id: String) {
        FoodInfoDialogFragment.getInstance(id = id).showOnlyOne(parentFragmentManager)
    }

    override fun itemClick(tag: String, previousItem: Int, selectedItem: Int) {
        viewModel.tagsClick(tag)
        tagAdapter.setSelected(selectedItem)
        tagAdapter.notifyItemChanged(previousItem)
        tagAdapter.notifyItemChanged(selectedItem)
    }

    override fun onStart() {
        super.onStart()
        requireActivity().findViewById<LinearLayout>(R.id.user_toolbar_container).hideView()
    }
}

