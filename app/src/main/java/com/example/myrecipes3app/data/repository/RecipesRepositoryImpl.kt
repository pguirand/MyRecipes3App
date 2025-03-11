package com.example.myrecipes3app.data.repository

import com.example.myrecipes3app.data.models.AllRecipesModel
import com.example.myrecipes3app.data.models.RecipeModel
import com.example.myrecipes3app.data.remote.ApiResponse
import com.example.myrecipes3app.data.remote.ApiService
import javax.inject.Inject

class RecipesRepositoryImpl @Inject constructor(
    private val apiService: ApiService
): RecipesRepository{
    override suspend fun getALlRecipes(): ApiResponse<AllRecipesModel> {
        return try {
            val response = apiService.getAllRecipes()
            if (response.isSuccessful) {
                response.body()?.let {
                    ApiResponse.Success(it)
                } ?: ApiResponse.Error("No data available")
            } else {
                ApiResponse.Error("Error ${response.code()} : ${response.message()}")
            }
        } catch (e: Exception) {
            ApiResponse.Error("Network error : ${e.message}")
        }
    }

    override suspend fun getRecipeById(id: String): ApiResponse<RecipeModel> {
        TODO("Not yet implemented")
    }

}