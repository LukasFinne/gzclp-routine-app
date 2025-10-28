package se.finne.lukas.implementation

import se.finne.lukas.declaration.WorkoutRepository

class WorkoutRepositoryImpl : WorkoutRepository {
    override fun increaseWeight(exercise: String): Float {
        return when (exercise) {
            "Squat" -> 5f
            "Bench Press" -> 2.5f
            "Deadlift" -> 5f
            "Overhead Press" -> 2.5f
            else -> 0f
        }
    }
}