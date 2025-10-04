package se.finne.lukas.gzclp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update


// Beöver komma ihåg nurvarande
// A1 -> B1 -> A2 -> B2

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

//sealed class T11{
//    data class OHP(val scheme: T1Lifts = T1Lifts.FiveThree): T1()
//    data class DeadLift(val scheme: T1Lifts = T1Lifts.FiveThree): T1()
//    data class Bench(val scheme: T1Lifts = T1Lifts.FiveThree): T1()
//}
//
//sealed class T22{
//    data class Squat(val scheme: T2Lifts = T2Lifts.ThreeTen): T2()
//    data class OHP(val scheme: T2Lifts = T2Lifts.ThreeTen): T2()
//    data class DeadLift(val scheme: T2Lifts = T2Lifts.ThreeTen): T2()
//}
//
//sealed class T33{
//    data class DumbbellRow(val scheme: T3Lifts = T3Lifts.ThreeFifteen): T3()
//}
//
//sealed class A1{
//    data class Squat(val scheme: T1Lifts = T1Lifts.FiveThree, val kg: Int = 8, val lb: Int = 9): T1()
//    data class Bench(val scheme: T2Lifts = T2Lifts.ThreeTen): T2()
//    data class LatPullDown(val scheme: T3Lifts = T3Lifts.ThreeFifteen): T3()
//}


sealed class GzClpState{
    data object Loading: GzClpState()
    data class Loaded(val state: Workout): GzClpState()
}

sealed class State{
    data class A1State(val scheme: T1Lifts = T1Lifts.FiveThree, val kg: Int = 8, val lb: Int = 9): State()
}

sealed class WorkOutState{

}


@HiltViewModel
class GzclpViewModel @Inject constructor(): ViewModel() {

    val uiState: StateFlow<GzClpState> = watchWorkout()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = GzClpState.Loading
        )

    private fun watchWorkout(): Flow<GzClpState> =
        getWorkOut(Workouts.A1.id)



    fun updateWorkOutState(workOutState: WorkOutState){
    }

}


fun getWorkOut(id : String): Flow<GzClpState> = flowOf(
   when(Workouts.valueOf(id)){
        Workouts.A1 -> GzClpState.Loaded(
            Workout(
                name ="Squat day",
                tierOneLift = Lift(
                    name = "Squat",
                    sets = T1Lifts.FiveThree.set,
                    reps = T1Lifts.FiveThree.rep,
                ),
                tierTwoLift = Lift(
                    name = "Bench",
                    sets = T2Lifts.ThreeTen.set,
                    reps = T2Lifts.ThreeTen.rep,
                ),
                tierThreeLift = Lift(
                    name = "LatPullDown",
                    sets = T3Lifts.ThreeFifteen.set,
                    reps = T3Lifts.ThreeFifteen.rep,
                )

            )
        )
       else -> GzClpState.Loading
    }
)
