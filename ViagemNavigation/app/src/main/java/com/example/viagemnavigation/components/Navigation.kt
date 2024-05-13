package com.example.viagemnavigation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.viagemnavigation.FormLogin
import com.example.viagemnavigation.screens.Register

@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") { FormLogin(context = LocalContext.current, navController = navController) }
        composable("register") { Register(navController = navController) }
        composable("home") { Viagem(navController = navController) }
    }
}