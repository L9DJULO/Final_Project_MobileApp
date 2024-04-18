package com.stu74524.final_project_mobileapp

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ToSignUp(onClick: () -> Unit) {
    ElevatedButton(onClick = { onClick() }) {
        Text("Sign Up")
    }
}

@Composable
fun ToLoginButton(onClick: () -> Unit) {
    ElevatedButton(onClick = { onClick() }) {
        Text("Login")
    }
}

@Composable
fun ToHomeButton(onClick: () -> Unit) {
    ElevatedButton(onClick = { onClick() }) {
        Text("Home")
    }
}

@Composable
fun ToCartButton(onClick: () -> Unit) {
    ElevatedButton(onClick = { onClick() }) {
        Text("Cart")
    }
}

@Composable
fun ToProductCategoriesButton(onClick: () -> Unit) {
    ElevatedButton(onClick = { onClick() }) {
        Text("Product Categories")
    }
}

@Composable
fun ToProductListButton(onClick: () -> Unit) {
    ElevatedButton(onClick = { onClick() }) {
        Text("Product List")
    }
}

@Composable
fun ToUserProfileButton(onClick: () -> Unit) {
    ElevatedButton(onClick = { onClick() }) {
        Text("User")
    }
}

@Composable
fun ToProductButton(onClick: () -> Unit) {
    ElevatedButton(onClick = { onClick() }) {
        Text("Product")
    }
}

@Composable
fun ToOrderHistoryButton(onClick: () -> Unit) {
    ElevatedButton(shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .height(65.dp)
            .width(250.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White
        ),
        onClick = { onClick() }) {
        Text("Your Past Order")
    }
}

@Composable
fun ToAboutButton(onClick: () -> Unit) {
    ElevatedButton(shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .height(50.dp)
            .width(250.dp)
            .padding(horizontal = 16.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White
        ),onClick = { onClick() }) {
        Text("About This app")
    }
}