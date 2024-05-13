package com.example.viagemnavigation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun User(navController: NavController){
    Box(modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center) {
        Button(
            onClick = {
                navController.navigate("login")
            }, colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary, contentColor = Color.White
            ), modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
        ) {
          Text(
              text = "Voltar para Login",
              fontSize = 18.sp)
        }
    }
}
