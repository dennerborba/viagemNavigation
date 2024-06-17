package com.example.viagemnavigation.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.viagemnavigation.model.Trip
import com.example.viagemnavigation.model.TripType
import com.example.viagemnavigation.model.TripViewModel
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun TripScreen(navController: NavController, tripviewModel: TripViewModel = viewModel()) {
    val list = listOf(
        Trip("Bahamas", TripType.LAZER, Date(), Date(), 5888.0 ),
        Trip("Nova York", TripType.NEGOCIOS, Date(), Date(), 20000.0),
        Trip("Blumenau", TripType.LAZER, Date(), Date(), 200.0)
    )
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(route = "cadviagem") },
                containerColor = Color.DarkGray,
                contentColor = Color(0xFFd4262c)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Adicionar Viagem")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Text(
                text = "Minhas Viagens",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(list) { trip ->
                    TripCard(trip, navController)
                }
            }
        }
    }
}




@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TripCard(trip: Trip, navController: NavController) {
    val ctx = LocalContext.current
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        border = BorderStroke(1.dp, Color.Black),
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .combinedClickable(
                onClick = {
                    Toast
                        .makeText(ctx, "Destino: ${trip.destination}", Toast.LENGTH_SHORT)
                        .show()
                },
                onLongClick = {
                    navController.navigate("cadviagem")
                }
            )
    ) {
        Column(modifier = Modifier.padding(4.dp)) {
            Text(text = trip.destination, style = MaterialTheme.typography.titleLarge)
            Text(text = "Tipo: ${trip.type}", style = MaterialTheme.typography.bodySmall)
            Text(text = "Data de Início: ${trip.startDate}", style = MaterialTheme.typography.bodySmall)
            Text(text = "Data Final: ${trip.endDate}", style = MaterialTheme.typography.bodySmall)
            Text(text = "Valor: R$ ${trip.value}", style = MaterialTheme.typography.bodySmall)
        }
    }
}