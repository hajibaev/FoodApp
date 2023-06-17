package com.example.cafetestapp.ui.screen_food_info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cafetestapp.models.DishesUi
import com.example.common_api.Mapper
import com.example.domain.models.DishesDomain
import com.example.domain.use_case.FetchAllDishesScreenItemsUseCase
import com.example.domain.use_case.SaveFoodForBasketUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class FoodInfoDialogViewModel(
    id: String,
    private val saveFoodForBasketUseCase: SaveFoodForBasketUseCase,
    private val fetchAllDishesScreenItemsUseCase: FetchAllDishesScreenItemsUseCase,
    private val saveMapper: Mapper<DishesUi, DishesDomain>,
    private val mapFromDishesDomainToUi: Mapper<DishesDomain, DishesUi>
) : ViewModel() {

    private val dishesIdFlow = MutableStateFlow(id)

    val dishes =
        dishesIdFlow.flatMapLatest(fetchAllDishesScreenItemsUseCase::invoke)
            .map(mapFromDishesDomainToUi::map)


    fun saveFoodForBasket(dishes: DishesUi) = viewModelScope.launch {
        saveFoodForBasketUseCase.invoke(saveMapper.map(dishes))
    }

}