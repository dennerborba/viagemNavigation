package com.example.viagemnavigation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.viagemnavigation.R

@Composable
fun Register(navController: NavController) {
    var user = remember {
        mutableStateOf(value = "")
    }
    var password = remember {
        mutableStateOf(value = "")
    }
    var email = remember {
        mutableStateOf(value = "")
    }
    var stringPassword = remember {
        mutableStateOf(false)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp), content = {
            Text(
                text = "Usuário",
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                modifier = Modifier
                    .padding(bottom = 8.dp)
            )
            OutlinedTextField(
                value = user.value,
                onValueChange = { user.value = it },
                label = {
                    Text(
                        text = "Usuário",
                        fontSize = 24.sp,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
            )

            Text(
                text = "E-mail",
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                modifier = Modifier
                    .padding(bottom = 8.dp)
            )
            OutlinedTextField(
                value = email.value,
                onValueChange = { email.value = it },
                label = {
                    Text(
                        text = "E-mail",
                        fontSize = 24.sp,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
            )

            Text(
                text = "Senha",
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                modifier = Modifier
                    .padding(bottom = 8.dp)
            )

            OutlinedTextField(
                value = password.value,
                onValueChange = { password.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                label = {
                    Text(
                        text = "Senha",
                        fontSize = 24.sp,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                visualTransformation =
                if (stringPassword.value)
                    VisualTransformation.None
                else
                    PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(
                        onClick = {
                            stringPassword.value = !stringPassword.value
                        }
                    ) {
                        if (stringPassword.value)
                            Icon(
                                painterResource(id = R.drawable.open_eye), ""
                            )
                        else
                            Icon(
                                painterResource(id = R.drawable.closed_eye), ""
                            )
                    }
                }
            )

            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Button(
                    onClick = {
                        navController.navigate("login")
                    }, colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        contentColor = Color.White
                    ), modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                ) {
                    Text(
                        text = "Cadastrar",
                        fontSize = 18.sp
                    )
                }
            }
        }
    )
}