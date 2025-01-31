package com.example.newnavigationcomposeexample

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import androidx.navigation.toRoute
import com.example.newnavigationcomposeexample.common.getItemById
import com.example.newnavigationcomposeexample.ui.screens.DetailsScreen
import com.example.newnavigationcomposeexample.ui.screens.HomeScreen
import com.example.newnavigationcomposeexample.ui.theme.NewNavigationComposeExampleTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewNavigationComposeExampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavHost(innerPadding)
                }
            }
        }
    }
}
@Composable
fun AppNavHost(innerPadding: PaddingValues) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Home, modifier = Modifier.padding(innerPadding)) {
        composable<Home>{
            HomeScreen(onItemSelected = { item ->
                navController.navigate(Detail(item.id))
            })

        }
        composable<Detail>(deepLinks = listOf(navDeepLink <Detail>(basePath = "https://newnav.com/detail") )
        ) { backStackEntry ->
            // Details screen content
            val item = backStackEntry.toRoute<Detail>()
            Log.v("item", "Item: $item")

            val fetchedItem = getItemById(item.item)
            Log.v("item", "Item: $fetchedItem")
            if (fetchedItem != null) {
                DetailsScreen(fetchedItem)
            }
        }
    }
}

@Serializable
data object Home

@Serializable
data class Detail(val item: Int)