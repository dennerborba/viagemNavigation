package com.example.viagemnavigation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.viagemnavigation.FormLogin
import com.example.viagemnavigation.model.TripViewModel
import com.example.viagemnavigation.screens.AboutPage
import com.example.viagemnavigation.screens.Home
import com.example.viagemnavigation.screens.NavigationBarS
import com.example.viagemnavigation.screens.Register
import com.example.viagemnavigation.screens.TripScreen

@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") { FormLogin(context = LocalContext.current, navController = navController) }
        composable("register") { Register(navController = navController) }
        composable("about") { AboutPage(navController = navController) }
        composable("cadviagem") { CadastroViagem(navController = navController, TripViewModel()) }
        composable(route= "menu/{user}",
            arguments = listOf(navArgument("user"){
            type = NavType.StringType })
        ) { backStackEntry ->
            val user = backStackEntry.arguments?.getString("user")
            NavigationBarS(navController = navController, user = user)
        }
        composable("triplist"){ TripScreen(navController = navController, TripViewModel()) }
    }
}