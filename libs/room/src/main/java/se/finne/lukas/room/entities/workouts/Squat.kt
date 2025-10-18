package se.finne.lukas.room.entities.workouts

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Squat(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val weight: Int,
    val reps: Int,
    val set: Int,
    val userId: Int,
)

