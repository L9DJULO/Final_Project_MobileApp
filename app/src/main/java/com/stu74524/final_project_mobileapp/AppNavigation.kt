package com.stu74524.final_project_mobileapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.stu74524.final_project_mobileapp.Screens.Cart.CartScreen
import com.stu74524.final_project_mobileapp.Screens.HomeScreen
import com.stu74524.final_project_mobileapp.Screens.Authentification.LoginScreen
import com.stu74524.final_project_mobileapp.Screens.Cart.OrderHistoryScreen
import com.stu74524.final_project_mobileapp.Screens.Product.ProductCategoriesScreen
import com.stu74524.final_project_mobileapp.Screens.Product.ProductListScreen
import com.stu74524.final_project_mobileapp.Screens.Product.ProductScreen
import com.stu74524.final_project_mobileapp.Screens.Authentification.SignUpScreen
import com.stu74524.final_project_mobileapp.Screens.User.AboutScreen
import com.stu74524.final_project_mobileapp.Screens.User.UserProfileScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.LoginScreen.route,
    ) {
        composable(route = Routes.SignUpScreen.route)
        {
            SignUpScreen(navController = navController)
        }
        composable(route = Routes.LoginScreen.route)
        {
            LoginScreen(navController = navController)
        }
        composable(route = Routes.HomeScreen.route)
        {
            HomeScreen(navController = navController)
        }
        composable(route = Routes.CartScreen.route)
        {
            CartScreen(navController = navController)
        }
        composable(route = Routes.ProductCategoriesScreen.route)
        {
            ProductCategoriesScreen(navController = navController)
        }
        composable(route = Routes.UserProfileScreen.route)
        {
            UserProfileScreen(navController = navController)
        }
        composable(route = Routes.OrderHistoryScreen.route)
        {
            OrderHistoryScreen(navController = navController)
        }
        composable(route = Routes.AboutScreen.route)
        {
            AboutScreen(navController = navController)
        }


        composable(route = Routes.ProductListScreen.route + "/{category}",
            arguments = listOf(
                navArgument("category") {
                    type = NavType.StringType
                }
            )) { entry ->
            val category = entry.arguments?.getString("category")
            if (category != null) {
                ProductListScreen(navController = navController, category = category)
            }
        }

        composable(
            route = Routes.ProductScreen.route + "/{productid}",
            arguments = listOf(
                navArgument("productid") {type= NavType.StringType},
            )
        ) { entry ->
            val productid = entry.arguments?.getString("productid")
            if (productid != null) {
                ProductScreen(
                    navController = navController,
                    productid = productid
                )
            }
        }
    }
}
