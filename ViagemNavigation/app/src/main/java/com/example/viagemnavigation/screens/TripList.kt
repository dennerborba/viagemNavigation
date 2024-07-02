package com.example.viagemnavigation.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberBasicTooltipState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.viagemnavigation.model.Trip
import com.example.viagemnavigation.model.TripType
import com.example.viagemnavigation.R
import java.util.Date

@Composable
fun TripScreen(navController: NavController) {
    val list = listOf(
        Trip(1, "Bahamas", TripType.LAZER, Date(), Date(), 5888.0 ),
        Trip(2, "Nova York", TripType.NEGOCIOS, Date(), Date(), 20000.0),
        Trip(3, "Blumenau", TripType.LAZER, Date(), Date(), 200.0)
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
    val tripImage = if (trip.type == TripType.LAZER) {
        painterResource(id = R.drawable.viagem)
    } else {
        painterResource(id = R.drawable.negocios)
    }
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
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(16.dp))
                ) {
                    Image(
                        painter = tripImage,
                        contentDescription = "Tipo Viagem",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = trip.destination,
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 5.dp)
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            Column(modifier = Modifier.padding(4.dp)) {
                Text(
                    text = "Data de Início: ${trip.startDate}",
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = "Data Final: ${trip.endDate}",
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = "Valor: R$ ${trip.value}",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}
