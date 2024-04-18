package com.stu74524.final_project_mobileapp.Screens.User

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.stu74524.final_project_mobileapp.BottomNavigationBar
import com.stu74524.final_project_mobileapp.R
import com.stu74524.final_project_mobileapp.Routes
import com.stu74524.final_project_mobileapp.ToOrderHistoryButton
import com.stu74524.final_project_mobileapp.TopAppBar2

@Composable
fun AboutScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar2(navController = navController,"About me ")
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.Start
            ) {
                IconButton(onClick = { navController.navigate(Routes.UserProfileScreen.route) })
                { Icon(painter = painterResource(id = R.drawable.ic_backarrow), contentDescription = "BackArrow") }
                Spacer(modifier = Modifier.height(8.dp))
                Image(
                    painter = painterResource(id = R.drawable.senju),
                    contentDescription = "Movie preview",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.6f),
                )
                Text(text = "Name: Jules Lange")
                Text(text = "Age: 20")
                Text(text = "Project: Mobile app project for my exchange semester abroad in Ireland")
                Text(text = "Credit: All icons where found on the GoogleFont website and all cloth image were provided by : Pexels, Unplash and ShutterStock")
            }
        },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    )
}