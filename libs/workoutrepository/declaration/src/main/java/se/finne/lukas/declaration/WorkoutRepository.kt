package se.finne.lukas.declaration

import se.finne.lukas.declaration.entities.Lift

interface WorkoutRepository{
    fun increaseWeight(lift: Lift): Float
}