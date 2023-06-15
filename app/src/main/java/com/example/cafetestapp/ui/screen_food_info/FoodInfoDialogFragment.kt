package com.example.cafetestapp.ui.screen_food_info

import android.os.Bundle
import android.provider.MediaStore.Audio.AudioColumns.TITLE_KEY
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.cafetestapp.databinding.FoodInfoDialogBinding
import com.example.cafetestapp.models.DishesUi
import com.example.ui_core.extensions.bindingLifecycleError
import com.example.ui_core.extensions.launchWhenViewStarted
import com.example.ui_core.extensions.makeToast
import com.example.ui_core.extensions.runWithArgumentsOrSkip
import com.example.ui_core.extensions.setOnDownEffectClickListener
import com.example.ui_core.extensions.showRoundedImage
import com.example.ui_core.extensions.tuneLyricsDialog
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class FoodInfoDialogFragment : DialogFragment() {

    private var _binding: FoodInfoDialogBinding? = null
    private val binding get() = _binding ?: bindingLifecycleError()

    private val dishesId: String by lazy(LazyThreadSafetyMode.NONE) {
        requireArguments().getString(DISHES_KEY, String()) ?: String()
    }

    private val viewModel: FoodInfoDialogViewModel by viewModel {
        parametersOf(dishesId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FoodInfoDialogBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        tuneLyricsDialog()
        initAndApplyArguments()
    }

    private fun observeData() = with(viewModel) {
        launchWhenViewStarted {
            dishes.observe { dishes ->
                observeResource(dishes = dishes)
                setOnClickListeners(dishes = dishes)
            }
        }
    }

    private fun setOnClickListeners(dishes: DishesUi) = with(binding) {
        saveFoodBtn.setOnDownEffectClickListener {
            viewModel.saveFoodForBasket(dishes)
            requireContext().makeToast("Блюдо добавлено в корзину")
            dismiss()
        }
        closed.setOnDownEffectClickListener {
            dismiss()
        }
    }

    private fun initAndApplyArguments() = runWithArgumentsOrSkip { arguments ->
        val title = arguments.getString(TITLE_KEY)
        if (title.isNullOrEmpty()) return@runWithArgumentsOrSkip
    }

    private fun observeResource(dishes: DishesUi) = with(binding) {
        requireContext().showRoundedImage(imageUrl = dishes.image_url, imageView = poster)
        price.text = "${dishes.price} ₽"
        weight.text = "${dishes.weight} г"
        title.text = dishes.dishes_name
        description.text = dishes.description
    }

    companion object {
        private const val DISHES_KEY = "DISHES_KEY"

        fun getInstance(
            id: String
        ): FoodInfoDialogFragment {
            val dialog = FoodInfoDialogFragment()
            val bundle = Bundle()
            bundle.putString(DISHES_KEY, id)
            dialog.arguments = bundle
            return dialog
        }
    }


}