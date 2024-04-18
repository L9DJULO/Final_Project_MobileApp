package com.stu74524.final_project_mobileapp.Screens.Cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.stu74524.final_project_mobileapp.BottomNavigationBar
import com.stu74524.final_project_mobileapp.R
import com.stu74524.final_project_mobileapp.Routes
import com.stu74524.final_project_mobileapp.ToOrderHistoryButton
import com.stu74524.final_project_mobileapp.ToProductButton
import com.stu74524.final_project_mobileapp.TopAppBar2

@Composable
fun OrderHistoryScreen(navController: NavController)
{

    Scaffold(
        topBar = {
            TopAppBar2(navController = navController,"Your history")
        },
        content = { innerPadding ->
            IconButton(onClick = { navController.navigate(Routes.CartScreen.route) })
            { Icon(painter = painterResource(id = R.drawable.ic_backarrow), contentDescription = "BackArrow") }
            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.Start
            ) {
                item {
                    IconButton(onClick = { navController.navigate(Routes.CartScreen.route) }) {
                        Icon(painter = painterResource(id = R.drawable.ic_backarrow), contentDescription = "BackArrow")
                    }
                }
                item {
                    Text(text = "Your precedent orders :")
                }
                items(1) {
                    Box(
                        modifier = Modifier
                            .size(150.dp)
                            .background(Color.Gray)
                            .padding(8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column {
                            Text(text = "Order 1")
                            Text(text = "Order Date")
                            Text(text = "Order Total Price")
                            Text(text = "Number of Items")
                        }
                    }
                }
            }
        },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    )
}