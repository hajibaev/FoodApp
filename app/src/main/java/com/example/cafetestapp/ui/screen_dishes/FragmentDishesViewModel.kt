package com.example.cafetestapp.ui.screen_dishes

import com.example.cafetestapp.ui.screen_dishes.listeners.DishesItemClickListener
import com.example.cafetestapp.ui.screen_dishes.listeners.TagsItemClickListener
import com.example.cafetestapp.ui.screen_dishes.mappers.AllDishesItemFilteredMapper
import com.example.common_api.BaseResourceProvider
import com.example.common_api.DispatchersProvider
import com.example.common_api.base.BaseViewModel
import com.example.domain.models.DishesScreenItems
import com.example.domain.use_case.FetchAllDishesScreenItemsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class FragmentDishesViewModel(
    fetchAllDishesScreenItemsUseCase: FetchAllDishesScreenItemsUseCase,
    private val allDishesItemFilteredMapper: AllDishesItemFilteredMapper,
    dispatchersProvider: DispatchersProvider,
    private val resourceProvider: BaseResourceProvider,
) : BaseViewModel(), DishesItemClickListener, TagsItemClickListener {

    private var tagsList = HashSet<String>()

    private val _tagsFlow = MutableStateFlow("")
    val tagsFlow get() = _tagsFlow.asStateFlow()

    private val _showDialog = createMutableSharedFlowAsSingleLiveEvent<Int>()
    val showDialog get() = _showDialog.asSharedFlow()

    private val _all_tags = createMutableSharedFlowAsSingleLiveEvent<HashSet<String>>()
    val all_tags get() = _all_tags.asSharedFlow()


    val dishes = _tagsFlow.flatMapLatest { fetchAllDishesScreenItemsUseCase.invoke() }
        .flowOn(dispatchersProvider.default())
        .map { items -> mapToAdapterModel(items, _tagsFlow.value) }
        .onEach { it.forEach { it.dishes.tags.forEach { setAllTags(it) } } }
        .catch { exception: Throwable ->
            emitToErrorMessageFlow(resourceProvider.fetchIdErrorMessage(exception))
        }


    private fun mapToAdapterModel(items: DishesScreenItems, tegs: String) =
        allDishesItemFilteredMapper.map(
            items = items,
            dishesItemClickListener = this,
            filterTags = tegs,
        )

    fun setAllTags(value: String) {
        tagsList.add(value)
        _all_tags.tryEmit(tagsList)
    }


    override fun dishesItemOnClick(id: Int) {
        _showDialog.tryEmit(id)
    }

    fun tagsClick(tag: String) {
        _tagsFlow.value = tag
    }

    override fun itemClickListener(tags: String) {
        _tagsFlow.value = tags
    }
}