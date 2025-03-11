package com.example.myrecipes3app.data.models


import com.google.gson.annotations.SerializedName

data class AllRecipesModel(
    @SerializedName("limit")
    val limit: Int?,
    @SerializedName("recipes")
    val recipes: List<RecipeModel?>?,
    @SerializedName("skip")
    val skip: Int?,
    @SerializedName("total")
    val total: Int?
)