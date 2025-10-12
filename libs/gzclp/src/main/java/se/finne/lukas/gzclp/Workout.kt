package se.finne.lukas.gzclp

data class Workout (
    val name: String,
    val lifts: Map<WorkOutTier,Lift>
    )

data class Lift(
    val name: String,
    val sets: Int,
    val reps: Int,
    val restTime: Int,
    val weight: Int,
    val nextWorkout: WorkOutTier,
)

enum class WorkOutTier{
    T1, T2, T3
}
enum class T1Lifts(val set: Int, val rep: Int) {
    FiveThree(5, 3), SixTwo(6,2), TenOne(10,1)
}

enum class T2Lifts(val set: Int, val rep: Int) {
    ThreeTen(3,10), ThreeEight(3,8), ThreeSix(3,6)
}

enum class T3Lifts(val set: Int, val rep: Int) {
    ThreeFifteen(3,15)
}

enum class Workouts(val id: String){
    A1("A1"), A2("A2"), B1("B1"),B2("B2")
}
