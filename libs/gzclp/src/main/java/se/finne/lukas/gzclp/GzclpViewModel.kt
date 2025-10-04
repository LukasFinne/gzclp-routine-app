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

sealed class T1{
    data class Squat(val scheme: T1Lifts = T1Lifts.FiveThree): T1()
    data class OHP(val scheme: T1Lifts = T1Lifts.FiveThree): T1()
    data class DeadLift(val scheme: T1Lifts = T1Lifts.FiveThree): T1()
    data class Bench(val scheme: T1Lifts = T1Lifts.FiveThree): T1()
}

sealed class T2{
    data class Squat(val scheme: T2Lifts = T2Lifts.ThreeTen): T2()
    data class OHP(val scheme: T2Lifts = T2Lifts.ThreeTen): T2()
    data class DeadLift(val scheme: T2Lifts = T2Lifts.ThreeTen): T2()
    data class Bench(val scheme: T2Lifts = T2Lifts.ThreeTen): T2()
}

sealed class T3{
    data class DumbbellRow(val scheme: T3Lifts = T3Lifts.ThreeFifteen): T3()
    data class LatPullDown(val scheme: T3Lifts = T3Lifts.ThreeFifteen): T3()
}

data class Workout(val t1: T1, val t2: T2, val t3: T3)


sealed class Lifts{
    data class Lift(val scheme: T1Lifts)
}
sealed class GzClpState{
    data object Loading: GzClpState()
    data class Loaded(val workout: Workout): GzClpState()
}

sealed class WorkOutState{
    data object T1: WorkOutState()
    data object T2: WorkOutState()
    data object T3: WorkOutState()
}


@HiltViewModel
class GzclpViewModel @Inject constructor(): ViewModel() {

    private val _workOutState = MutableStateFlow<WorkOutState>(WorkOutState.T1)
    val workOutState = _workOutState
    val uiState: StateFlow<GzClpState> = watchWorkout()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = GzClpState.Loading
        )

    private fun watchWorkout(): Flow<GzClpState> =
        getWorkOut(Workouts.A1.id).map { GzClpState.Loaded(it) }



    fun updateWorkOutState(workOutState: WorkOutState){
        _workOutState.update {
            workOutState
        }
    }

}


fun getWorkOut(id : String): Flow<Workout> = flowOf(
   when(Workouts.valueOf(id)){
        Workouts.A1 -> Workout(t1=T1.Squat(), t2 = T2.Bench(), t3 = T3.LatPullDown())
        Workouts.A2 -> Workout(t1=T1.Bench(), t2 = T2.Squat(), t3 = T3.LatPullDown())
        Workouts.B1 -> Workout(t1=T1.OHP(), t2 = T2.DeadLift(), t3 = T3.DumbbellRow())
        Workouts.B2 -> Workout(t1=T1.DeadLift(), t2 = T2.OHP(), t3 = T3.DumbbellRow())
    }
)
