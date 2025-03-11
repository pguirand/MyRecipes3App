package com.example.myrecipes3app.data.remote

import com.example.myrecipes3app.common.ApiConsttants
import com.example.myrecipes3app.data.models.AllRecipesModel
import com.example.myrecipes3app.data.models.RecipeModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET(ApiConsttants.ALL_RECIPES_ENDPOINT)
    suspend fun getAllRecipes() : Response<AllRecipesModel>

    @GET(ApiConsttants.SINGLE_RECIPE_ENDPOINT)
    suspend fun getRecipeById(@Path("id") id:String) : Response<RecipeModel>
}