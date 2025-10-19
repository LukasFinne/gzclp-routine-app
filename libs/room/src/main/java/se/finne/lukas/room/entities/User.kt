package se.finne.lukas.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val username: String,
    val currentWorkout: String,
    val squatId: Int,
    val benchId: Int,
    val latPullDownId: Int,
)