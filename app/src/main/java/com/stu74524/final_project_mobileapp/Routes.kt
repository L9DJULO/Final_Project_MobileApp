package com.stu74524.final_project_mobileapp

sealed class Routes(val route: String) {
    object SignUpScreen : Routes("SignUp_screen")
    object LoginScreen : Routes("Login_screen")
    object HomeScreen : Routes("Home_screen")
    object CartScreen: Routes("Cart_screen")
    object ProductCategoriesScreen : Routes("ProductCategories_screen")
    object ProductListScreen : Routes("ProductList_screen")
    object UserProfileScreen : Routes("UserProfile_screen")
    object ProductScreen : Routes("Product_screen")
    object OrderHistoryScreen : Routes("OrderHistory_screen")
    object AboutScreen : Routes("About_screen")
}