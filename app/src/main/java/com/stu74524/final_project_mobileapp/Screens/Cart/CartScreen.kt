package com.stu74524.final_project_mobileapp.Screens.Cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.stu74524.final_project_mobileapp.BottomNavigationBar
import com.stu74524.final_project_mobileapp.Routes
import com.stu74524.final_project_mobileapp.ToOrderHistoryButton
import com.stu74524.final_project_mobileapp.TopAppBar2
import com.stu74524.final_project_mobileapp.ToProductButton

@Composable
fun CartScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar2(navController = navController, "Your Cart")
        },
        content = { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Text(text = "Your cart : ")
                }
                item {
                    Box(
                        modifier = Modifier
                            .size(150.dp)
                            .background(Color.Gray)
                            .padding(8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column {
                            Text("Item 1 Icon")
                            Text("Item 1")
                            Text("Item Price : 100")
                            Text("Item Quantity : 1")
                            Button(onClick = {}) {
                                Text("Go to Item")
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Box(
                        modifier = Modifier
                            .size(150.dp)
                            .background(Color.Gray)
                            .padding(8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column {
                            Text("Item 2 Icon")
                            Text("Item 2")
                            Text("Item Price : 75")
                            Text("Item Quantity : 2")
                            Button(onClick = {}) {
                                Text("Go to Item")
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Box(
                        modifier = Modifier
                            .size(150.dp)
                            .background(Color.Gray)
                            .padding(8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column {
                            Text("Item 3 Icon")
                            Text("Item 3")
                            Text("Item Price : 10")
                            Text("Item Quantity : 5")
                            Button(onClick = {}) {
                                Text("Go to Item")
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Total price :  300")
                }
                item {

                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = {},
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .height(65.dp)
                            .width(250.dp),
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color.White
                        ))
                    {
                        Text(text = "Proceed to payment")
                    }
                }
                item {
                    ToOrderHistoryButton { navController.navigate(Routes.OrderHistoryScreen.route) }
                }
            }
        },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    )
}
