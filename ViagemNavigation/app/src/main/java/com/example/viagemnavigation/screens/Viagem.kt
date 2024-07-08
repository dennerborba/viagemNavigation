package com.example.viagemnavigation.components

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.viagemnavigation.database.AppDataBase
import com.example.viagemnavigation.model.Trip
import com.example.viagemnavigation.model.TripType
import com.example.viagemnavigation.model.TripViewModel
import com.example.viagemnavigation.model.TripViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date

@Composable
fun CadastroViagem(navController: NavController, tripId: Long? = null){
    val snackbarHostState = remember { SnackbarHostState()}
    val trip = remember {
        mutableStateOf(
            Trip(
                destination = "",
                type = TripType.LAZER,
                startDate = Date(),
                endDate = Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000),
                value = 0.0
            )
        )
    }

    val ctx = LocalContext.current
    val db = AppDataBase.getDataBase(ctx)
    val tripViewModel: TripViewModel = viewModel (
        factory = TripViewModelFactory(db)
    )
    val state = tripViewModel.uiState.collectAsState()
    LaunchedEffect(tripId) {
        tripId?.let {
            tripViewModel.editTrip(it)
        }
    }
    Scaffold (
        snackbarHost = { SnackbarHost(hostState = snackbarHostState)},
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.Gray.copy(alpha = 0.1f))
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Cadastrar Nova Viagem",
                            modifier = Modifier.weight(0.1f),
                            textAlign = TextAlign.Center
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    DestinationInput(
                        destination = state.value.destination,
                        onDestinationChanged = { tripViewModel.updateDestination(it) }
                    )
                    TripTypeInput(
                        tripType = state.value.type,
                        onTripTypeChanged = { tripViewModel.updateTripType(it) }
                    )
                    ValueInput(
                        value = formatValue(state.value.value),
                        onValueChanged = {tripViewModel.updateValue(it.toDouble() )
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    DatePickerComponent(
                        label = "Data Início",
                        date = state.value.startDate,
                        onDateSelected = { tripViewModel.updateStartDate(it) }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    DatePickerComponent(
                        label = "Data Final",
                        date = state.value.endDate,
                        onDateSelected = { tripViewModel.updateEndDate(it) }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            if (state.value.destination == ""){
                                Toast.makeText(ctx, "Por favor, insira um destino.", Toast.LENGTH_SHORT).show()
                            } else {
                                tripViewModel.save()
                                Toast.makeText(ctx, "Viagem salva!", Toast.LENGTH_SHORT).show()
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text("Salvar Viagem")
                    }
                }
            }
        }
    )
}

@Composable
fun formatValue(value: Double): String {
    val intValue = value.toInt()
    return if (value == intValue.toDouble()) {
        intValue.toString()
    } else {
        value.toString()
    }
}