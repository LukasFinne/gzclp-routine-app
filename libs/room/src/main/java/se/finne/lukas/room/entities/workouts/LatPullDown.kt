package se.finne.lukas.room.entities.workouts

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LatPullDown(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val weight: Int,
    val reps: Int,
    val set: Int,
)
