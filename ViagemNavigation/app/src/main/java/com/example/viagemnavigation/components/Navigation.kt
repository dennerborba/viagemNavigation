package com.example.viagemnavigation.components

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.viagemnavigation.FormLogin
import com.example.viagemnavigation.model.Trip

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
        composable("register") { Register(context = LocalContext.current, navController = navController) }
        composable("about") { AboutPage(navController = navController) }
        composable("cadviagem") { CadastroViagem(navController = navController) }
        composable(route= "menu/{user}", arguments = listOf(navArgument("user"){
            type = NavType.StringType })
        ) { backStackEntry ->
            val user = backStackEntry.arguments?.getString("user")
            NavigationBarS(navController = navController, user = user, trip = Trip())
        }
        composable("triplist"){ TripScreen(navController = navController) }
        composable(route = "cadviagem/{tripId}",
            arguments = listOf(navArgument("tripId") { type = NavType.LongType })
        ) { backStackEntry ->
            val tripId = backStackEntry.arguments?.getLong("tripId")
            CadastroViagem(navController = navController, tripId = tripId)
        }
    }
}