package se.finne.lukas.room.entities.workouts

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Workout(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val weight: Int,
    val tierOneSet: Int,
    val tierOneRep: Int,
    val tierTwoSet: Int,
    val tierTwoRep: Int,
    val userId: Int
)
