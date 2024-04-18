package com.stu74524.final_project_mobileapp

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.Navigation.findNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.userProfileChangeRequest
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mod72.simplenavigation.ui.theme.SimpleNavigationTheme
import com.stu74524.final_project_mobileapp.Screens.Authentification.SignUpScreen
import com.stu74524.final_project_mobileapp.Screens.Cart.CartScreen
import com.stu74524.final_project_mobileapp.Screens.Cart.OrderHistoryScreen
import com.stu74524.final_project_mobileapp.Screens.HomeScreen
import com.stu74524.final_project_mobileapp.Screens.Product.ProductCategoriesScreen
import com.stu74524.final_project_mobileapp.Screens.Product.ProductListScreen
import com.stu74524.final_project_mobileapp.Screens.Product.ProductScreen
import com.stu74524.final_project_mobileapp.Screens.User.UserProfileScreen
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.auth.User
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class MainActivity : ComponentActivity() {

    // Firebase Authentication instance
    public lateinit var auth: FirebaseAuth

    // SharedPreferences instance
    public lateinit var sharedPreferences: SharedPreferences

    // Tag for logging
    public val TAG = "EmailPassword"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Firebase Auth
        auth = Firebase.auth

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("user_data", Context.MODE_PRIVATE)

        // Set the content view using Jetpack Compose
        setContent {
            SimpleNavigationTheme {
                AppNavigation()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            reload()
        }
    }


    public fun SignUp(username: String, name: String, email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser

                    // Update user profile
                    val profileUpdates = userProfileChangeRequest {
                        displayName = name
                    }

                    user?.updateProfile(profileUpdates)
                        ?.addOnCompleteListener { profileUpdateTask ->
                            if (profileUpdateTask.isSuccessful) {
                                // Profile updated successfully
                                Log.d(TAG, "User profile updated.")
                            } else {
                                Log.w(TAG, "Failed to update user profile.", profileUpdateTask.exception)
                            }
                        }

                    // Save user data to Firestore with authID
                    val userData = hashMapOf(
                        "username" to username,
                        "name" to name,
                        "email" to email,
                        "authID" to user?.uid // Include the authentication UID in the document
                    )

                    user?.uid?.let { userId ->
                        db.collection("users")
                            .document(userId)
                            .set(userData)
                            .addOnSuccessListener {
                                Log.d(TAG, "User data successfully written to Firestore.")
                            }
                            .addOnFailureListener { e ->
                                Log.w(TAG, "Error writing user data to Firestore", e)
                            }
                    }

                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                    updateUI(null)
                }
            }
    }

    public fun Login(email: String, password: String, onComplete: () -> Unit) {
        // [START sign_in_with_email]
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                    onComplete()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
                    updateUI(null)
                }
            }
        // [END sign_in_with_email]
    }

    public fun saveUserIdLocally(userId: String) {
        // Save user ID to SharedPreferences
        val editor = sharedPreferences.edit()
        editor.putString("user_id", userId)
        editor.apply()
    }

    public fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            // User is signed in, save user ID to SharedPreferences
            val userId = user.uid
            saveUserIdLocally(userId)
            // Proceed with your UI update
        } else {
            //logout()
        }
    }

    public fun reload() {
        // Your existing code for reloading data or UI
    }

    public fun logout() {
        auth.signOut()
        clearUserIdLocally()
    }

    public fun clearUserIdLocally() {
        // Clear locally stored user ID from SharedPreferences
        val editor = sharedPreferences.edit()
        editor.remove("user_id")
        editor.apply()
    }

    public val db = Firebase.firestore


    val collection = db.collection("Product")

    fun getProductListByCategory(category: String): Flow<List<QueryDocumentSnapshot>> {
        return callbackFlow {
            val collection = db.collection("Product")
            val listener = collection.whereEqualTo("category", category).addSnapshotListener { snapshot, exception ->
                if (exception != null) {
                    // Handle error
                    close(exception)
                    return@addSnapshotListener
                }

                // Convert DocumentSnapshots to QueryDocumentSnapshots
                val queryDocumentSnapshots = snapshot?.documents?.mapNotNull { it as? QueryDocumentSnapshot }

                // Try sending the converted list to the flow
                trySend(queryDocumentSnapshots ?: emptyList())
                    .isSuccess // Check if sending was successful
            }

            // Cancel the listener when the flow is cancelled
            awaitClose { listener.remove() }
        }
    }
    fun getProductById(productid: String): Flow<DocumentSnapshot?> {
        return callbackFlow {
            val collection = db.collection("Product")
            val listener = collection.document(productid)
                .addSnapshotListener { snapshot, exception ->
                    if (exception != null) {
                        // Handle error
                        close(exception)
                        return@addSnapshotListener
                    }

                    // Send the snapshot to the flow
                    trySend(snapshot)
                        .isSuccess // Check if sending was successful
                }

            // Cancel the listener when the flow is cancelled
            awaitClose { listener.remove() }
        }
    }

    fun getUserbyAuthid(authid: String): Flow<List<QueryDocumentSnapshot>> {
        return callbackFlow {
            val collection = db.collection("users")
            val uid = authid
            val listener = collection.whereEqualTo("authID", authid).addSnapshotListener { snapshot, exception ->
                if (exception != null) {
                    // Handle error
                    close(exception)
                    return@addSnapshotListener
                }

                // Convert DocumentSnapshots to QueryDocumentSnapshots
                val queryDocumentSnapshots = snapshot?.documents?.mapNotNull { it as? QueryDocumentSnapshot }

                // Try sending the converted list to the flow
                trySend(queryDocumentSnapshots ?: emptyList())
                    .isSuccess // Check if sending was successful
            }

            // Cancel the listener when the flow is cancelled
            awaitClose { listener.remove() }
        }
    }


    class CartManager(private val userId: String) {
        private val db = FirebaseFirestore.getInstance()
        private val cartRef = db.collection("Carts").document(userId)

        suspend fun addItemToCart(productId: String, quantity: Int) {
            val cart = getCart()
            if (cart != null) {
                if (cart.items.containsKey(productId)) {
                    // If the product already exists in the cart, update its quantity
                    val updatedQuantity = cart.items[productId]!! + quantity
                    cart.items[productId] = updatedQuantity
                } else {
                    // If the product is not in the cart, add it
                    cart.items[productId] = quantity
                }
                cartRef.set(cart).await()
            } else {
                // If the cart doesn't exist, create a new one and add the product
                val newCart = Cart(mutableMapOf(productId to quantity))
                cartRef.set(newCart).await()
            }
        }

        suspend fun removeItemFromCart(productId: String) {
            val cart = getCart()
            if (cart != null && cart.items.containsKey(productId)) {
                cart.items.remove(productId)
                cartRef.set(cart).await()
            }
        }

        private suspend fun getCart(): Cart? {
            return try {
                val document = cartRef.get().await()
                document.toObject(Cart::class.java)
            } catch (e: Exception) {
                null
            }
        }
    }

    data class Cart(val items: MutableMap<String, Int>)
}

