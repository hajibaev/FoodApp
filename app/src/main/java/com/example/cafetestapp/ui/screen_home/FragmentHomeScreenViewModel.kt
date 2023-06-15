package com.example.cafetestapp.ui.screen_home

import androidx.lifecycle.viewModelScope
import com.example.cafetestapp.models.CategoryUiModel
import com.example.cafetestapp.models.adapter_models.CategoryAdapterModel
import com.example.cafetestapp.ui.screen_home.router.HomeScreenRouter
import com.example.common_api.BaseResourceProvider
import com.example.common_api.DispatchersProvider
import com.example.common_api.Mapper
import com.example.common_api.base.BaseViewModel
import com.example.domain.models.CategoryDomain
import com.example.domain.use_case.CategoryUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.shareIn

class FragmentHomeScreenViewModel(
    categoryUseCase: CategoryUseCase,
    dispatchersProvider: DispatchersProvider,
    private val router: HomeScreenRouter,
    private val resourceProvider: BaseResourceProvider,
    private val mapFromCategoryDomainToUi: Mapper<CategoryDomain, CategoryUiModel>,
    private val mapFromCategoryUiToAdapterModel: Mapper<CategoryUiModel, CategoryAdapterModel>
) : BaseViewModel() {

    val getCategory = categoryUseCase.invoke()
        .flowOn(dispatchersProvider.default())
        .map { categoryDomain -> categoryDomain.map(mapFromCategoryDomainToUi::map) }
        .map { categoryUi -> categoryUi.map(mapFromCategoryUiToAdapterModel::map) }
        .catch { exception: Throwable ->
            emitToErrorMessageFlow(resourceProvider.fetchIdErrorMessage(exception))
        }.shareIn(viewModelScope, SharingStarted.Lazily, 1)


    fun categoryClick(categoryTitle: String) {
        navigate(router.navigateToDishesFragment(categoryTitle))
    }


}