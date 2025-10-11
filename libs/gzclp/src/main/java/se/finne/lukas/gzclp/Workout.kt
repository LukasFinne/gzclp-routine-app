package se.finne.lukas.gzclp

data class Workout (
    val name: String,
    val lifts: Map<String,Lift>
    )

data class Lift(
    val name: String,
    val sets: Int,
    val reps: Int,
    val restTime: Int,
    val weight: Int,
    val nextWorkout: String,
)

