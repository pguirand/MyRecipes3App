package com.example.myrecipes3app.data.remote

sealed class ApiResponse<out T:Any> {
    data class Success<out T:Any>(val data:T) : ApiResponse<T>()
    data class Error(val errorMessage:String) : ApiResponse<Nothing>()
    data object Loading : ApiResponse<Nothing>()
}