package com.stu74524.final_project_mobileapp.Screens.Product

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.stu74524.final_project_mobileapp.BottomNavigationBar
import com.stu74524.final_project_mobileapp.MainActivity
import com.stu74524.final_project_mobileapp.R
import com.stu74524.final_project_mobileapp.Routes
import com.stu74524.final_project_mobileapp.TopAppBar2

@Composable
fun ProductListScreen(navController: NavController, category: String) {
    val context = LocalContext.current
    val activity = context as? MainActivity
    val productquejaiget by activity?.getProductListByCategory(category)!!.collectAsState(initial = emptyList())
    Scaffold(
        topBar = {
            TopAppBar2(navController = navController,"Product List")
        },
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                IconButton(onClick = { navController.navigate(Routes.ProductCategoriesScreen.route) }) {
                    Icon(painter = painterResource(id = R.drawable.ic_backarrow), contentDescription = "BackArrow")
                }
                LazyColumn(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item {
                        if (productquejaiget.isEmpty()) {
                            CircularProgressIndicator(modifier = Modifier.fillMaxSize(), color = Color.Blue)
                        }
                    }
                    items(productquejaiget) { elt ->
                        val imageResourceId = elt["imageResourceId"] as? String
                        val productName = elt.data["name"].toString()
                        ProductItem(productName, imageResourceId) {
                            navController.navigate("${Routes.ProductScreen.route}/${elt.id}")
                        }
                        Spacer(modifier = Modifier.height(24.dp))
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
fun ProductItem(productName: String, imageResourceId: String?, onClick: () -> Unit) {
    val context = LocalContext.current

    Button(
        onClick = onClick,
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .height(75.dp)
            .width(375.dp)
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        colors = ButtonDefaults.buttonColors(contentColor = Color.White)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically, // Aligns contents vertically
            modifier = Modifier.fillMaxSize()
        ) {
            if (imageResourceId != null) {
                val painter: Painter = painterResource(id = context.resources.getIdentifier(imageResourceId, "drawable", context.packageName))
                Image(
                    painter = painter,
                    contentDescription = null,
                    modifier = Modifier
                        .size(75.dp)
                        .padding(end = 8.dp)
                )
            }
            Text(
                text = productName,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp) // Add padding to separate icon and text
            )
        }
    }
}
