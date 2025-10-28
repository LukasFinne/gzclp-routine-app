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
            weight = 100,
            onNext = WorkOutTier.T1
        )
        assertEquals(5f, repository.increaseWeight(lift))
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
            weight = 100,
            onNext = WorkOutTier.T1
        )
        assertEquals(2.5f, repository.increaseWeight(lift))
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
            weight = 100,
            onNext = WorkOutTier.T1
        )
        assertEquals(5f, repository.increaseWeight(lift))
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
            weight = 100,
            onNext = WorkOutTier.T1
        )
        assertEquals(2.5f, repository.increaseWeight(lift))
    }
}