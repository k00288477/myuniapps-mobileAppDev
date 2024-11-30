package com.example.myuniapps_cs.ui.auth

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
// user login
fun loginUser(
    email: String,
    password: String,
    onSuccess: () -> Unit,
    onError: (String) -> Unit
) {
    val auth = Firebase.auth
    auth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                onSuccess()
            } else {
                onError(task.exception?.message ?: "An error occurred")
            }
        }
}
// Delete user from Authentication
fun deleteUser(
    onSuccess: () -> Unit,
    onError: (String) -> Unit
) {
    val user = Firebase.auth.currentUser
    if (user != null) {
        user.delete()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onSuccess()
                } else {
                    onError(task.exception?.message ?: "An error occurred while deleting the user")
                }
            }
    } else {
        onError("No user is currently signed in")
    }
}


