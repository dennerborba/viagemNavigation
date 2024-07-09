package com.example.viagemnavigation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.viagemnavigation.FormLogin
import com.example.viagemnavigation.components.CadastroViagem
import com.example.viagemnavigation.model.Trip


@Composable
fun NavigationBarS(navController: NavController, user: String?, trip: Trip?){
    val navController = rememberNavController()
    Scaffold (
        bottomBar = { NavigationBottom(navController, user) }
    ) { innerPadding ->
        NavHost(navController,
            startDestination = "home/$user",
            modifier = Modifier.padding(innerPadding)
        ){
            composable (route = "home/{user}", arguments = listOf(navArgument("user") { type = NavType.StringType })
            ) { backStackEntry ->
                val userArg = backStackEntry.arguments?.getString("user")
                Home(navController = navController, user = user?:userArg)
            }
            composable("triplist") { TripScreen(navController = navController) }
            composable("about") { AboutPage(navController = navController) }
            composable("cadviagem") { CadastroViagem(navController = navController)}
            composable(route = "cadviagem/{tripId}",
                arguments = listOf(navArgument("tripId") { type = NavType.LongType })
            ) { backStackEntry ->
                val tripId = backStackEntry.arguments?.getLong("tripId")
                CadastroViagem(navController = navController, tripId = tripId)
            }
        }
    }
}

@Composable
fun NavigationBottom(navController: NavController, user: String?){
    BottomAppBar (
        modifier = Modifier,
        containerColor = Color.DarkGray,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        tonalElevation = 8.dp,
        windowInsets = NavigationBarDefaults.windowInsets
    ){
        var selectedItem by remember { mutableStateOf(0) }

        BottomNavigationItem(
            selected = selectedItem == 0,
            onClick = {
                selectedItem = 0
                navController.navigate("home/$user")
                      },
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
            label = { Text(text = "Home")},
        )

        BottomNavigationItem(
            selected = selectedItem == 1,
            onClick = {
                      selectedItem = 1
                      navController.navigate("triplist")
            },
            icon = { Icon(Icons.Filled.LocationOn, contentDescription = "Viagens") },
            label = { Text(text = "Viagens")}
        )

        BottomNavigationItem(
            selected = selectedItem == 2,
            onClick = {
                selectedItem = 2
                navController.navigate("about")
            },
            icon = { Icon(Icons.Filled.Info, contentDescription = "Sobre") },
            label = { Text(text = "Sobre") }
        )

    }
}
