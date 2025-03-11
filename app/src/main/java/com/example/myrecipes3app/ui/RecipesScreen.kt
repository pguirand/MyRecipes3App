package com.example.myrecipes3app.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.myrecipes3app.data.models.RecipeModel
import com.example.myrecipes3app.data.remote.ApiResponse
import com.example.myrecipes3app.viewmodels.RecipesViewModel

@Composable
fun RecipesScreen(
    modifier: Modifier = Modifier,
    viewModel: RecipesViewModel = hiltViewModel()
) {
    val recipesState by viewModel.recipesState.collectAsState()

    when(recipesState) {
        is ApiResponse.Error -> {
            val errorMessage = (recipesState as ApiResponse.Error).errorMessage
            Text(errorMessage)
        }
        ApiResponse.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.LightGray)
            ) {
                CircularProgressIndicator(
                    modifier
                        .background(Color.LightGray)
                        .align(Alignment.Center),
                    color = Color.Blue
                )
            }
        }
        is ApiResponse.Success -> {
            val recipesModel = (recipesState as ApiResponse.Success).data
            val recipesList = recipesModel.recipes?.filterNotNull()?: emptyList()

            val users = generateUsers()
            Surface(
                modifier = modifier.padding(16.dp).fillMaxSize()
            ) {
                LazyColumn(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    item {
                        Text(
                            text = "Recipes",
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                    items(recipesList) { recipe ->
                        RecipeItem(recipe)
                    }
                    item {
                        Text(
                            text = "Users",
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                    items(users) {user ->
                        UserItem(user)
                    }
                }
            }
        }
    }
}

@Composable
fun UserItem(user: Users) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(Color.Cyan),
        horizontalArrangement = Arrangement.SpaceEvenly
    ){
        Text(user.id.toString())
        Text(user.firstName)
        Text(user.age.toString())
        Text(user.adult.toString())
    }
}

@Composable
fun RecipeItem(recipe: RecipeModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp)
            .background(Color.Cyan),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            modifier = Modifier
                .size(50.dp)
                .padding(2.dp),
            painter = rememberAsyncImagePainter(recipe.image),
            contentDescription = recipe.name
        )
        Text(
            text = recipe.name.toString()
        )
        Text(recipe.cuisine.toString())
    }
}


data class Users(
    val id:Int,
    val age:Int,
    val firstName:String,
    val adult: Boolean? = true
)

fun generateUsers() : List<Users> {
    return List(50) {
        val age = (15 .. 30).random()
        Users(
            id = it,
            age = age,
            firstName = "userFirstName $it",
            adult = age > 21
        )
    }
}
