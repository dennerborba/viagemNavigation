package com.example.viagemnavigation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun AboutPage(navController: NavController){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Text(text = "Sobre o dev: \n" +
                "Denner de Borba \n" +
                "20 anos \n" +
                "https://github.com/dennerborba/viagemNavigation", fontSize = 24.sp,
            textAlign = TextAlign.Center

        )
    }
}