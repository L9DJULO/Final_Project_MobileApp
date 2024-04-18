package com.stu74524.final_project_mobileapp.Screens.Product

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.firestore.DocumentSnapshot
import com.stu74524.final_project_mobileapp.BottomNavigationBar
import com.stu74524.final_project_mobileapp.MainActivity
import com.stu74524.final_project_mobileapp.R
import com.stu74524.final_project_mobileapp.Routes
import com.stu74524.final_project_mobileapp.TopAppBar2
import kotlinx.coroutines.flow.firstOrNull

@Composable
fun ProductScreen(navController: NavController, productid: String) {
    val productState = remember { mutableStateOf<DocumentSnapshot?>(null) }
    val context = LocalContext.current
    val activity = context as? MainActivity
    val productSelected = remember { mutableStateOf(0) }

    LaunchedEffect(productid) {
        val productData = activity?.getProductById(productid)?.firstOrNull()
        productState.value = productData
    }

    val productData = productState.value?.data
    if (productData != null) {
        Scaffold(
            topBar = {
                TopAppBar2(navController = navController, productData["name"].toString())
            },
            content = { innerPadding ->
                Box(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                ) {
                    IconButton(
                        onClick = { navController.navigate("${Routes.ProductListScreen.route}/${productData["category"].toString()}") },
                        modifier = Modifier.padding(start = 16.dp)
                    ) {
                        Icon(painter = painterResource(id = R.drawable.ic_backarrow), contentDescription = "BackArrow")
                    }
                    Column(
                        modifier = Modifier
                            .padding(innerPadding),
                        verticalArrangement = Arrangement.SpaceAround,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Spacer(modifier = Modifier.height(8.dp))
                            val imageResourceId = productData["imagepresid"] as? String
                            val painter: Painter = painterResource(id = context.resources.getIdentifier(imageResourceId, "drawable", context.packageName))
                            val quantityString: String = productData["quantity"].toString()
                            val quantityofproduct = remember {
                                mutableStateOf(quantityString.toInt())
                            }
                            Image(
                                painter = painter,
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth(1f)
                                    .height(200.dp)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(text = "Name: ${productData["name"] ?: ""}")
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(text = "Price: ${productData["price"] ?: ""}")
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(text = "Quantity: ${productData["quantity"] ?: ""}")
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(text = "Description: ${productData["description"] ?: ""}")
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(text = "Category: ${productData["category"] ?: ""}")
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "Select product",
                                    color = Color.Black,
                                    textAlign = TextAlign.Start,
                                    fontWeight = FontWeight.Bold
                                )
                                IconButton(
                                    onClick = {
                                        if (productSelected.value > 0) {
                                            productSelected.value--
                                            quantityofproduct.value++
                                        }
                                    },
                                    enabled = productSelected.value > 0
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Delete,
                                        contentDescription = "Delete",
                                        tint = if (productSelected.value > 0) Color.Black else Color.Gray // Change tint based on availability
                                    )
                                }
                                Text(
                                    text = "${productSelected.value}",
                                    color = Color.Black,
                                )
                                IconButton(
                                    onClick = {
                                        if (quantityofproduct.value > 0) {
                                            quantityofproduct.value--
                                            productSelected.value++
                                        }
                                    },
                                    enabled = quantityofproduct.value > 0
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Add,
                                        contentDescription = "Add Seat",
                                        tint = if (quantityofproduct.value > 0) Color.Black else Color.Gray
                                    )
                                }
                            }
                            Button(
                                onClick = {
                                },
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .height(200.dp)
                                    .fillMaxWidth(),
                                colors = ButtonDefaults.buttonColors(contentColor = Color.White)
                            ) {
                                Text(
                                    text = "Add to Cart"
                                )
                            }
                        }
                    }
                }
            },
            bottomBar = {
                BottomNavigationBar(navController = navController)
            }
        )
    } else {
        CircularProgressIndicator(modifier = Modifier.fillMaxSize(), color = Color.Blue)
    }
}
