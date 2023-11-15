package com.bolla.helloworldkmm.android

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.bolla.helloworldkmm.Greeting
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Box(
                        contentAlignment = Alignment.Center
                    ) {
                        Column {
                            GreetingView(Greeting().greet())
                            Button(
                                onClick = {
                                    testLogUsers()
                                }
                            ) {
                                Text(text = "Log users to logcat")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun GreetingView(text: String) {
    Text(text = text)
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        GreetingView("Hello, Android!")
    }
}

fun testLogUsers() {
    try {
        val db = Firebase.firestore
        db.collection("users")
            .get()
            .addOnSuccessListener { result ->
                result.documents.forEach { user ->
                    Log.d("AndroidUserRepository", "${user.id} => ${user.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w("AndroidUserRepository", "Error getting documents.", exception)
            }
    } catch (exception: Exception) {
        Log.e("AndroidUserRepository", "Could not get users from firestore", exception)
    }
}
