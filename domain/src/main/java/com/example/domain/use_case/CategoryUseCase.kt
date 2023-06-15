package com.example.domain.use_case

import com.example.domain.models.CategoryDomain
import com.example.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow

interface CategoryUseCase {

    fun invoke(): Flow<List<CategoryDomain>>

}

class CategoryUseCaseImpl(private val repository: CategoryRepository) : CategoryUseCase {
    override fun invoke(): Flow<List<CategoryDomain>> = repository.fetchCategories()
}