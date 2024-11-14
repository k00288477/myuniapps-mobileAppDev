package com.example.myuniapps_cs.ui.database

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await

class FirebaseService {

    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()

    // Reference db entities
    private val usersRef: DatabaseReference = database.getReference("users")
    private val clubsRef: DatabaseReference = database.getReference("clubs")
    private val eventsRef: DatabaseReference = database.getReference("clubEvents")

    // Auth instance
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    // Methods

    // Get User
    suspend fun getUser(userId: String): User?{
        return try {
            val snapshot = usersRef.child(userId).get().await()
            snapshot.getValue(User::class.java)
        } catch (e: Exception) {
            Log.e("FirebaseService", "Filed to fetch user: ${e.message}")
            null
        }
    }

    // Add user to the database
    suspend fun addUser(userId: String, displayName: String, email: String, courseTitle: String){
        val user = User(userId, displayName, email, courseTitle)
        try{
            usersRef.child(userId).setValue(user).await()
            Log.d("FirebaseService", "User added successfully")
        } catch (e: Exception) {
            Log.d("FirebaseService", "Error adding user: ${e.message}")
        }
    }


}

data class User(
    val userId: String = "",
    val displayName: String = "",
    val email: String = "",
    val courseTitle: String = ""
)

data class Club(
    val clubId: String = "",
    val clubName: String = "",
    val description: String = "",
    val thumbnail: String = "",
    val adminId: String = ""
)

data class ClubEvent(
    val eventId: String = "",
    val name: String = "",
    val description: String = "",
    val timedate: String = "",
    val location: String = ""
)
