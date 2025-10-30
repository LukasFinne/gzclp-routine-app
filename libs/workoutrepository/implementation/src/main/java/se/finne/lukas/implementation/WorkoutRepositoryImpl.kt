package se.finne.lukas.implementation

import se.finne.lukas.declaration.WorkoutRepository
import se.finne.lukas.declaration.entities.Lift
import se.finne.lukas.declaration.entities.LiftType
import se.finne.lukas.declaration.entities.T1Lifts
import se.finne.lukas.declaration.entities.T2Lifts
import se.finne.lukas.declaration.entities.T3Lifts
import se.finne.lukas.declaration.entities.WorkOutTier

class WorkoutRepositoryImpl : WorkoutRepository {
    override fun increaseWeight(lift: Lift): Lift{
        return when (lift.type) {
            LiftType.Squat -> lift.copy(
                weight = lift.weight + 5.0
            )
            LiftType.Bench -> lift.copy(weight = lift.weight + 2.5)
            LiftType.Deadlift -> lift.copy(weight = lift.weight + 5.0)
            LiftType.OHP -> lift.copy(weight = lift.weight + 2.5)
            LiftType.DumbbellRow -> lift.copy(weight = lift.weight + 2.5)
            LiftType.LatPullDown -> lift.copy(weight = lift.weight + 2.5)
        }
    }

    override fun updateSetReps(
        lift: Lift
    ): Lift {
        return when (lift.workoutTier){
            WorkOutTier.T1 ->{
                T1Lifts.entries.first { it.set == lift.sets && it.rep == lift.reps }.let {
                    val lift = when(it) {
                        T1Lifts.FiveThree ->{
                            lift.copy(
                                sets = T1Lifts.SixTwo.set,
                                reps = T1Lifts.SixTwo.rep
                            )
                        }
                        T1Lifts.SixTwo ->{
                            lift.copy(
                                sets = T1Lifts.TenOne.set,
                                reps = T1Lifts.TenOne.rep
                            )
                        }
                        T1Lifts.TenOne -> lift.copy(
                            sets  = T1Lifts.FiveThree.set,
                            reps = T1Lifts.FiveThree.rep,
                            weight = 0.85 * lift.weight
                        )
                    }
                    return lift
                }
            }
            WorkOutTier.T2 ->{
                T2Lifts.entries.first { it.set == lift.sets && it.rep == lift.reps }.let {
                    val lift = when(it) {
                        T2Lifts.ThreeTen -> lift.copy(
                            sets = T2Lifts.ThreeEight.set,
                            reps = T2Lifts.ThreeEight.rep
                        )
                        T2Lifts.ThreeEight -> lift.copy(
                            sets = T2Lifts.ThreeSix.set,
                            reps = T2Lifts.ThreeSix.rep
                        )
                        T2Lifts.ThreeSix -> lift.copy(
                            sets  = T2Lifts.ThreeTen.set,
                            reps = T2Lifts.ThreeTen.rep,
                            weight = if(lift.weight - 10 < 0.0) 0.0 else lift.weight - 10
                        )
                    }
                    return lift
                }
            }
            WorkOutTier.T3 -> {
                T3Lifts.entries.first { it.set == lift.sets && it.rep == lift.reps }.let {
                    val next = when(it) {
                    T3Lifts.ThreeFifteen -> T3Lifts.ThreeFifteen

                    }
                    return lift.copy(
                        sets = next.set,
                        reps = next.rep
                    )
                }
            }
            WorkOutTier.Finished -> lift
        }
    }

}