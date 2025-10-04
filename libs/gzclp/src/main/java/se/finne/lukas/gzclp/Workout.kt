package se.finne.lukas.gzclp

data class Workout (
    val name: String,
    val tierOneLift: Lift,
    val tierTwoLift: Lift,
    val tierThreeLift: Lift
    )

data class Lift(
    val name: String,
    val sets: Int,
    val reps: Int,
)

