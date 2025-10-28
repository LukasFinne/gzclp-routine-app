package se.finne.lukas.declaration.entities

data class WorkoutUI (
    val name: String,
    val lifts: Map<WorkOutTier,Lift>
)

data class Lift(
    val id: Int,
    val workoutTier: WorkOutTier,
    val type: LiftType,
    val name: String,
    val sets: Int,
    val reps: Int,
    val restTime: Int,
    val weight: Int,
    val onNext: WorkOutTier,
)

enum class LiftType{
    Squat,
    Bench,
    Deadlift,
    OHP,
    DumbbellRow,
    LatPullDown,
}

enum class WorkOutTier{
    T1, T2, T3,Finished
}
enum class T1Lifts(val set: Int, val rep: Int) {
    FiveThree(5, 3), SixTwo(6,2), TenOne(10,1)
}

fun calculateNextT1Lift(lift: Lift): T1Lifts {
    return when(T1Lifts.entries.first { it.set == lift.sets && it.rep == lift.reps }) {
        T1Lifts.FiveThree -> T1Lifts.SixTwo
        T1Lifts.SixTwo -> T1Lifts.TenOne
        T1Lifts.TenOne -> T1Lifts.FiveThree
    }
}

enum class T2Lifts(val set: Int, val rep: Int) {
    ThreeTen(3,10), ThreeEight(3,8), ThreeSix(3,6)
}

fun calculateNextT2Lift(lift: Lift): T2Lifts {
    return when(T2Lifts.entries.first { it.set == lift.sets && it.rep == lift.reps }) {
        T2Lifts.ThreeTen -> T2Lifts.ThreeEight
        T2Lifts.ThreeEight -> T2Lifts.ThreeSix
        T2Lifts.ThreeSix -> T2Lifts.ThreeTen
    }
}
enum class T3Lifts(val set: Int, val rep: Int) {
    ThreeFifteen(3,15)
}

enum class Workouts(val id: String){
    A1("A1"), A2("A2"), B1("B1"),B2("B2")
}
