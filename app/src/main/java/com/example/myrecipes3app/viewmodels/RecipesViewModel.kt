package com.example.myrecipes3app.viewmodels

import androidx.lifecycle.ViewModel
import com.example.myrecipes3app.data.models.AllRecipesModel
import com.example.myrecipes3app.data.models.RecipeModel
import com.example.myrecipes3app.data.remote.ApiResponse
import com.example.myrecipes3app.data.repository.RecipesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch


@HiltViewModel
class RecipesViewModel @Inject constructor(private val repository: RecipesRepository) : ViewModel() {

    private val _allRecipesState = MutableStateFlow<ApiResponse<AllRecipesModel>>(ApiResponse.Loading)
    val recipesState:StateFlow<ApiResponse<AllRecipesModel>> = _allRecipesState

    private val _singleRecipeState = MutableStateFlow<ApiResponse<RecipeModel>>(ApiResponse.Loading)
    val singleRecipeState:StateFlow<ApiResponse<RecipeModel>> = _singleRecipeState

    init {
        getAllRecipes()
    }

    private fun getAllRecipes() {
        viewModelScope.launch {
            _allRecipesState.value = ApiResponse.Loading
            _allRecipesState.value = repository.getALlRecipes()
        }

    }
}