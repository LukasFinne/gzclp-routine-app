package se.finne.lukas.implementation

import org.junit.Assert.assertEquals
import org.junit.Test
import se.finne.lukas.declaration.entities.Lift
import se.finne.lukas.declaration.entities.LiftType
import se.finne.lukas.declaration.entities.WorkOutTier

class WorkoutRepositoryImplTest {

    private val repository = WorkoutRepositoryImpl()

    @Test
    fun `increaseWeight for Squat returns 5f`() {
        val lift = Lift(
            id = 0,
            name = "Squat",
            type = LiftType.Squat,
            workoutTier = WorkOutTier.Finished,
            sets = 5,
            reps = 5,
            restTime = 180,
            weight = 100.0,
            onNext = WorkOutTier.T1
        )
        assertEquals(5.0, repository.increaseWeight(lift).weight, 0.0)
    }

    @Test
    fun `increaseWeight for Bench Press returns 2,5f`() {
        val lift = Lift(
            id = 0,
            name = "Squat",
            type = LiftType.Bench,
            workoutTier = WorkOutTier.Finished,
            sets = 5,
            reps = 5,
            restTime = 180,
            weight = 100.0,
            onNext = WorkOutTier.T1
        )
        assertEquals(2.5, repository.increaseWeight(lift).weight, 0.0)
    }

    @Test
    fun `increaseWeight for Deadlift returns 5f`() {
        val lift = Lift(
            id = 0,
            name = "Squat",
            type = LiftType.Deadlift,
            workoutTier = WorkOutTier.Finished,
            sets = 5,
            reps = 5,
            restTime = 180,
            weight = 100.0,
            onNext = WorkOutTier.T1
        )
        assertEquals(5.0, repository.increaseWeight(lift).weight, 0.0)
    }

    @Test
    fun `increaseWeight for Overhead Press returns 2,5f`() {
        val lift = Lift(
            id = 0,
            name = "Squat",
            type = LiftType.OHP,
            workoutTier = WorkOutTier.Finished,
            sets = 5,
            reps = 5,
            restTime = 180,
            weight = 100.0,
            onNext = WorkOutTier.T1
        )
        assertEquals(2.5, repository.increaseWeight(lift).weight, 0.0)
    }

    @Test
    fun `updateSetReps for T1 lift cycles correctly`() {
        val lift = Lift(
            id = 0,
            name = "Squat",
            type = LiftType.Squat,
            workoutTier = WorkOutTier.T1,
            sets = 5,
            reps = 3,
            restTime = 180,
            weight = 100.0,
            onNext = WorkOutTier.T2
        )
        val updatedLift = repository.updateSetReps(lift)
        assertEquals(6, updatedLift.sets)
        assertEquals(2, updatedLift.reps)
    }

    @Test
    fun `updateSetReps and weight when failing 10x1`() {
        val lift = Lift(
            id = 0,
            name = "Lat Pull Down",
            type = LiftType.LatPullDown,
            workoutTier = WorkOutTier.T1,
            sets = 10,
            reps = 1,
            restTime = 90,
            weight = 100.0,
            onNext = WorkOutTier.Finished
        )
        val updatedLift = repository.updateSetReps(lift)
        assertEquals(5, updatedLift.sets)
        assertEquals(3, updatedLift.reps)
        assertEquals(85.0, updatedLift.weight, 0.0)
    }

    @Test
    fun `updateSetReps for T2 lift cycles correctly`() {
        val lift = Lift(
            id = 0,
            name = "Bench Press",
            type = LiftType.Bench,
            workoutTier = WorkOutTier.T2,
            sets = 3,
            reps = 10,
            restTime = 120,
            weight = 80.0,
            onNext = WorkOutTier.T3
        )
        val updatedLift = repository.updateSetReps(lift)
        assertEquals(3, updatedLift.sets)
        assertEquals(8, updatedLift.reps)
    }

    @Test
    fun `updateSetReps and weight when failing 3x6`() {
        val lift = Lift(
            id = 0,
            name = "Lat Pull Down",
            type = LiftType.LatPullDown,
            workoutTier = WorkOutTier.T2,
            sets = 3,
            reps = 6,
            restTime = 90,
            weight = 100.0,
            onNext = WorkOutTier.Finished
        )
        val updatedLift = repository.updateSetReps(lift)
        assertEquals(3, updatedLift.sets)
        assertEquals(10, updatedLift.reps)
        assertEquals(90.0, updatedLift.weight, 0.0)
    }

    @Test
    fun `updateSetReps and weight when failing 3x6 and weight is lower than 10 it should return 0`() {
        val lift = Lift(
            id = 0,
            name = "Bench",
            type = LiftType.LatPullDown,
            workoutTier = WorkOutTier.T2,
            sets = 3,
            reps = 6,
            restTime = 90,
            weight = 9.0,
            onNext = WorkOutTier.Finished
        )
        val updatedLift = repository.updateSetReps(lift)
        assertEquals(3, updatedLift.sets)
        assertEquals(10, updatedLift.reps)
        assertEquals(0.0, updatedLift.weight, 0.0)
    }



}