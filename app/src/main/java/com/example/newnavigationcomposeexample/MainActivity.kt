package com.example.newnavigationcomposeexample

import android.os.Bundle
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
import androidx.navigation.toRoute
import com.example.newnavigationcomposeexample.ui.Item
import com.example.newnavigationcomposeexample.ui.screens.DetailsScreen
import com.example.newnavigationcomposeexample.ui.screens.HomeScreen
import com.example.newnavigationcomposeexample.ui.screens.ItemNavType
import com.example.newnavigationcomposeexample.ui.theme.NewNavigationComposeExampleTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf

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
                navController.navigate(Detail(item))
            })

        }
        composable<Detail>(typeMap = mapOf(typeOf<Item>() to ItemNavType)) { backStackEntry ->
            // Details screen content
            val item = backStackEntry.toRoute<Detail>()
            DetailsScreen(item.item)
        }
    }
}

@Serializable
data object Home

@Serializable
data class Detail (val item: Item)