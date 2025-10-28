package se.finne.lukas.declaration

interface WorkoutRepository{
    fun increaseWeight(exercise: String): Float
}