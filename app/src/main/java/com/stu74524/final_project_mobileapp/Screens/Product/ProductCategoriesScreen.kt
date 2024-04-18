package com.stu74524.final_project_mobileapp.Screens.Product

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MonotonicFrameClock
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.stu74524.final_project_mobileapp.BottomNavigationBar
import com.stu74524.final_project_mobileapp.R
import com.stu74524.final_project_mobileapp.Routes
import com.stu74524.final_project_mobileapp.TopAppBar2

@Composable
fun ProductCategoriesScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar2(navController = navController, name = "Product Category")
        },
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                IconButton(
                    onClick = { navController.navigate(Routes.HomeScreen.route) },
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(16.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_backarrow),
                        contentDescription = "BackArrow"
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.TopStart)
                        .padding(start = 16.dp, top = 72.dp),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val productCategories = listOf<Pair<String, Int>>(
                        "Street Wear" to R.drawable.ic_streetwear,
                        "Formal Wear" to R.drawable.ic_formalwear,
                        "Casual Wear" to R.drawable.ic_tshirt,
                        "Accessoires" to R.drawable.ic_accessoires,
                    )
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        productCategories.forEach { pair ->
                            CategoryButton(category = pair.first , pair.second ) {
                                navController.navigate("${Routes.ProductListScreen.route}/${pair.first}")
                            }
                            Spacer(modifier = Modifier.height(16.dp))
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

@Composable
fun CategoryButton(category: String, image: Int, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .height(75.dp)
            .width(375.dp)
            .padding(horizontal = 16.dp),
        colors = ButtonDefaults.buttonColors(contentColor = Color.White)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier.size(75.dp)
            )
            Text(
                text = category,
                modifier = Modifier.weight(1f)
            )
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
}


