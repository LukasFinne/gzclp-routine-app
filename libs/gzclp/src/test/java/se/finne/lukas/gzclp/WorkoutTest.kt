package se.finne.lukas.gzclp

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class WorkoutTest {

    @Test
    fun `calculateNextT1Lift should return SixTwo for FiveThree`() {
        val lift = Lift(0, "", 5, 3, 0, 0, WorkOutTier.T1)
        val nextLift = calculateNextT1Lift(lift)
        assert(nextLift == T1Lifts.SixTwo)
    }

    @Test
    fun `calculateNextT1Lift should return TenOne for SixTwo`() {
        val lift = Lift(0, "", 6, 2, 0, 0, WorkOutTier.T1)
        val nextLift = calculateNextT1Lift(lift)
        assert(nextLift == T1Lifts.TenOne)
    }

    @Test
    fun `calculateNextT1Lift should return FiveThree for TenOne`() {
        val lift = Lift(0, "", 10, 1, 0, 0, WorkOutTier.T1)
        val nextLift = calculateNextT1Lift(lift)
        assert(nextLift == T1Lifts.FiveThree)
    }

    @Test
    fun `calculateNextT2Lift should return ThreeEight for ThreeTen`() {
        val lift = Lift(0, "", 3, 10, 0, 0, WorkOutTier.T2)
        val nextLift = calculateNextT2Lift(lift)
        assert(nextLift == T2Lifts.ThreeEight)
    }

    @Test
    fun `calculateNextT2Lift should return ThreeSix for ThreeEight`() {
        val lift = Lift(0, "", 3, 8, 0, 0, WorkOutTier.T2)
        val nextLift = calculateNextT2Lift(lift)
        assert(nextLift == T2Lifts.ThreeSix)
    }

    @Test
    fun `calculateNextT2Lift should return ThreeTen for ThreeSix`() {
        val lift = Lift(0, "", 3, 6, 0, 0, WorkOutTier.T2)
        val nextLift = calculateNextT2Lift(lift)
        assert(nextLift == T2Lifts.ThreeTen)
    }
}