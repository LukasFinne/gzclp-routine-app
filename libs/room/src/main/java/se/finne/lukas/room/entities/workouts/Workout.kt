package se.finne.lukas.room.entities.workouts

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Workout(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val workoutName: String,
    val weight: Double,
    val tierOneSet: Int,
    val tierOneRep: Int,
    val tierTwoSet: Int,
    val tierTwoRep: Int,
    val userCreatorId: Int,
)
