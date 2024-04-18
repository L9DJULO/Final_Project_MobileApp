package com.stu74524.final_project_mobileapp.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.stu74524.final_project_mobileapp.Routes
import com.stu74524.final_project_mobileapp.ToCartButton
import com.stu74524.final_project_mobileapp.ToProductCategoriesButton
import com.stu74524.final_project_mobileapp.ToUserProfileButton
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavController
import com.stu74524.final_project_mobileapp.BottomNavigationBar
import com.stu74524.final_project_mobileapp.R
import com.stu74524.final_project_mobileapp.TopAppBar2

@Composable
fun HomeScreen(navController: NavController)
{
    Scaffold(
        topBar = {
            TopAppBar2(navController = navController, "Home Page")
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Welcome to Our App!",
                    color = Color.Black,
                    modifier = Modifier.padding(vertical = 16.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        navController.navigate(Routes.ProductCategoriesScreen.route)
                    },
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .height(75.dp)
                        .width(375.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),

                ) {
                    Text(text = "Explore Product Categories")
                }
            }
        },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    )
}



