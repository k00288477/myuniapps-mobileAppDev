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
    suspend fun addUser(email: String){
        val userId = auth.currentUser?.uid
        try{
            if (userId != null) {
                val user = User(userId, displayName = "Edit to add name", email, courseTitle = "Edit to add title")

                usersRef.child(userId).setValue(user).await()
                Log.d("FirebaseService", "User added successfully")
            }

        } catch (e: Exception) {
            Log.d("FirebaseService", "Error adding user: ${e.message}")
        }
    }

    // Update user details
    suspend fun updateUser(user:User){
        val userId = auth.currentUser?.uid
        try {
            if (userId != null) {
                usersRef.child(userId).setValue(user).await()
                Log.d("FirebaseService", "User: ${user.email}, details updated successfully")
            }
        } catch (e: Exception){
            Log.d("FirebaseService", "Error updating user: ${user.email}, ${e.message}")
        }
    }

    // get all clubs
    suspend fun getAllClubs(): List<Club>? {
        return try {
            val snapshot = clubsRef.get().await()
            val clubsMap = snapshot.value as? Map<*, *> ?: return null

            clubsMap.mapNotNull { entry ->
                val club = (entry.value as? Map<*, *>)?.let {
                    Club(
                        clubId = it["clubId"] as? String ?: "",
                        clubName = it["clubName"] as? String ?: "",
                        description = it["description"] as? String ?: "",
                        thumbnail = it["thumbnail"] as? String ?: "",
                        adminId = it["adminId"] as? String ?: ""
                    )
                }
                club
            }
        } catch (e: Exception) {
            Log.e("FirebaseService", "Failed to fetch clubs: ${e.message}")
            null
        }
    }


}

data class User(
    val userId: String = "",
    var displayName: String = "",
    val email: String = "",
    var courseTitle: String = ""
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
