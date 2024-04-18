package com.stu74524.final_project_mobileapp.Screens.User

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.stu74524.final_project_mobileapp.MainActivity
import com.stu74524.final_project_mobileapp.R
import com.stu74524.final_project_mobileapp.Routes
import com.stu74524.final_project_mobileapp.ToAboutButton
import com.google.firebase.auth.FirebaseUser
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.stu74524.final_project_mobileapp.BottomNavigationBar
import com.stu74524.final_project_mobileapp.ToAboutButton
import com.stu74524.final_project_mobileapp.ToOrderHistoryButton
import com.stu74524.final_project_mobileapp.TopAppBar2


@SuppressLint("RestrictedApi")
@Composable
fun UserProfileScreen(navController: NavController) {
    val context = LocalContext.current
    val activity = context as? MainActivity
    val currentUser = activity?.auth?.currentUser
    val userdatatable by currentUser?.let { activity.getUserbyAuthid(it.uid) }!!.collectAsState(initial = emptyList())

    if (userdatatable.isNotEmpty()) {
        val userdata = userdatatable[0]

        Scaffold(
            topBar = {
                TopAppBar2(navController = navController, "User Details")
            },
            content = { innerPadding ->
                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceAround,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.ic_profile),
                        contentDescription = "Profile"
                    )
                    Text(text = "Username: ${userdata.data["username"] ?: "N/A"}")
                    Text(text = "Name: ${userdata.data["name"] ?: "N/A"}")
                    Text(text = "Email: ${userdata.data["email"] ?: "N/A"}")
                    Button(
                        onClick = {
                            activity?.logout() // Logout the user
                            navController.navigate(Routes.LoginScreen.route) // Navigate to login screen
                        },
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .height(65.dp)
                            .width(400.dp)
                            .padding(horizontal = 16.dp),
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color.White
                        )
                    ) {
                        Text(
                            text = "Log Out",
                            color = Color.White,
                        )
                    }
                    ToAboutButton {
                        navController.navigate(Routes.AboutScreen.route)
                    }
                }
            },
            bottomBar = {
                BottomNavigationBar(navController = navController)
            }
        )
    } else {
        Text("User data not available")
    }
}
