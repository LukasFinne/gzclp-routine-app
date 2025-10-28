package se.finne.lukas.implementation

import se.finne.lukas.declaration.WorkoutRepository
import se.finne.lukas.declaration.entities.Lift
import se.finne.lukas.declaration.entities.LiftType

class WorkoutRepositoryImpl : WorkoutRepository {
    override fun increaseWeight(lift: Lift): Float {
        return when (lift.type) {
            LiftType.Squat -> 5f
            LiftType.Bench -> 2.5f
            LiftType.Deadlift -> 5f
            LiftType.OHP -> 2.5f
            LiftType.DumbbellRow -> 2.5f
            LiftType.LatPullDown -> 2.5f
        }
    }

}