package com.example.cafetestapp.ui.screen_basket

import androidx.lifecycle.viewModelScope
import com.example.cafetestapp.models.DishesUi
import com.example.cafetestapp.models.adapter_models.BasketAdapterModel
import com.example.common_api.BaseResourceProvider
import com.example.common_api.DispatchersProvider
import com.example.common_api.Mapper
import com.example.common_api.base.BaseViewModel
import com.example.domain.models.DishesDomain
import com.example.domain.use_case.FetchAllBasketScreenItemsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FragmentBasketViewModel(
    private val fetchAllBasketScreenItemsUseCase: FetchAllBasketScreenItemsUseCase,
    dispatchersProvider: DispatchersProvider,
    private val mapFromDishesDomainToUi: Mapper<DishesDomain, DishesUi>,
    private val mapFromDishesUiToAdapterModel: Mapper<DishesUi, BasketAdapterModel>,
    private val resourceProvider: BaseResourceProvider
) : BaseViewModel() {

    private val _totalPrice = MutableStateFlow(0)
    val totalPrice get() = _totalPrice.asStateFlow()

    val getAllDishes = fetchAllBasketScreenItemsUseCase.invoke()
        .flowOn(dispatchersProvider.io())
        .map { list -> list.map(mapFromDishesDomainToUi::map) }
        .map { dishes -> dishes.map(mapFromDishesUiToAdapterModel::map) }
        .catch { exception: Throwable ->
            emitToErrorMessageFlow(resourceProvider.fetchIdErrorMessage(exception))
        }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())


    fun getAllPriceFromBasket() {
        _totalPrice.value = 0
        getAllDishes.value.forEach {
            _totalPrice.value += it.price
        }
    }

    fun observeTotalPrice(total: Int, isPlus: Boolean) {
        if (isPlus) _totalPrice.value += total
        else if (!isPlus) _totalPrice.value -= total
    }

    fun deleteFoodFromStorage(id: Int) = viewModelScope.launch {
        fetchAllBasketScreenItemsUseCase.invoke(id)
    }

}