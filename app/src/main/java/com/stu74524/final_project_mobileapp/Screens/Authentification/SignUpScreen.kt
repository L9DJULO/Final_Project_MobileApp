package com.stu74524.final_project_mobileapp.Screens.Authentification

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.stu74524.final_project_mobileapp.R
import com.stu74524.final_project_mobileapp.Routes
import com.stu74524.final_project_mobileapp.ToLoginButton
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import com.stu74524.final_project_mobileapp.MainActivity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(navController: NavController) {
    val context = LocalContext.current
    val activity = context as? MainActivity
    val varEmail = remember { mutableStateOf(TextFieldValue()) }
    val varPassword = remember { mutableStateOf(TextFieldValue()) }
    val varUsername = remember { mutableStateOf(TextFieldValue()) }
    val varName= remember { mutableStateOf(TextFieldValue()) }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),
        color = Color.Transparent
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            Image(
                painter = painterResource(id = R.drawable.padlock),
                contentDescription = "opened lock",
                modifier = Modifier.size(75.dp)
            )
            Text(
                text = "Let's create an account for you",
                fontSize = 17.sp,
                color = Color.Gray
            )

            TextField(
                modifier = Modifier
                    .height(64.dp)
                    .background(Color.White, RoundedCornerShape(16.dp))
                    .width(350.dp)
                    .fillMaxWidth(),
                value = varUsername.value,
                onValueChange = { varUsername.value = it },
                label = { Text(text = "Username", color = Color.Gray) },
                shape = RoundedCornerShape(16.dp),
                singleLine = true,
            )

            TextField(
                modifier = Modifier
                    .height(64.dp)
                    .background(Color.White, RoundedCornerShape(16.dp))
                    .width(350.dp)
                    .fillMaxWidth(),
                value = varName.value,
                onValueChange = { varName.value = it },
                label = { Text(text = "Name", color = Color.Gray) },
                shape = RoundedCornerShape(16.dp),
                singleLine = true,
            )
            TextField(
                modifier = Modifier
                    .height(64.dp)
                    .background(Color.White, shape = RoundedCornerShape(16.dp))
                    .width(350.dp),
                value = varEmail.value,
                onValueChange = { varEmail.value = it },
                label = { Text(text = "Email", color = Color.Gray,) },
                shape = RoundedCornerShape(16.dp),
                singleLine = true
            )

            TextField(
                modifier = Modifier
                    .height(64.dp)
                    .background(Color.White, RoundedCornerShape(16.dp))
                    .width(350.dp)
                    .fillMaxWidth(),
                value = varPassword.value,
                onValueChange = { varPassword.value = it },
                label = { Text(text = "Password", color = Color.Gray) },
                shape = RoundedCornerShape(16.dp),
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
            )

            Button(
                modifier = Modifier
                    .height(64.dp)
                    .width(350.dp),
                onClick = {
                        activity?.SignUp(varUsername.value.text,varName.value.text,varEmail.value.text, varPassword.value.text)
                        val userId = activity?.auth?.currentUser?.uid
                        if (userId != null) {
                            activity.saveUserIdLocally(userId)
                        }
                        navController.navigate(Routes.HomeScreen.route)
                },
                shape = MaterialTheme.shapes.medium.copy(
                    topStart = CornerSize(8.dp),
                    topEnd = CornerSize(8.dp),
                    bottomStart = CornerSize(8.dp),
                    bottomEnd = CornerSize(8.dp)
                ),
                colors = ButtonDefaults.buttonColors(Color.White),
            ) {
                Text(text = "Sign up", color = Color.Black, fontWeight = FontWeight.ExtraBold, fontSize = 17.sp)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                Text(
                    text = "Already a member ? ",
                    fontSize = 15.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.width(8.dp))
                ToLoginButton(onClick = {
                    navController.navigate(Routes.LoginScreen.route)
                })
            }
        }
    }
}