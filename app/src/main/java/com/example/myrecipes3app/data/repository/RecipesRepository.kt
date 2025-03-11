package com.example.myrecipes3app.data.repository

import com.example.myrecipes3app.data.models.AllRecipesModel
import com.example.myrecipes3app.data.models.RecipeModel
import com.example.myrecipes3app.data.remote.ApiResponse

interface RecipesRepository {

    suspend fun getALlRecipes() : ApiResponse<AllRecipesModel>

    suspend fun getRecipeById(id:String) : ApiResponse<RecipeModel>
}