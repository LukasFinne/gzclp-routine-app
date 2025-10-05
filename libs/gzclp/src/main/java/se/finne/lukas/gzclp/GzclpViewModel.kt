package se.finne.lukas.gzclp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import se.finne.lukas.gzclp.GzClpState.*


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


sealed class GzClpState{
    data object Loading: GzClpState()
    data class Loaded(val name: String, val lift: Lift?): GzClpState()
}

data class Testout(val name: String, val lift: Lift?)

@HiltViewModel
class GzclpViewModel @Inject constructor(): ViewModel() {

    private val selectedLiftKey = MutableStateFlow("T1")

    val uiState: StateFlow<GzClpState> = watchWorkout()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = GzClpState.Loading
        )

    fun onLiftSelected(liftKey: String) {
        selectedLiftKey.update { liftKey }
    }

    private fun watchWorkout(): Flow<GzClpState> =
        getWorkOut(Workouts.A1.id).combine(selectedLiftKey) { workout, liftKey ->
            GzClpState.Loaded(workout.name, workout.lifts[liftKey])
        }

    fun getWorkOut(id : String): Flow<Workout> = flowOf(
        when(Workouts.valueOf(id)){
            Workouts.A1 ->
                Workout(
                    name ="Squat day",
                    lifts = mapOf(
                        "T1" to
                                Lift(
                                    name = "Squat",
                                    sets = T1Lifts.FiveThree.set,
                                    reps = T1Lifts.FiveThree.rep,
                                    nextWorkout = "T2"

                                ),
                        "T2" to

                                Lift(
                                    name = "Bench",
                                    sets = T2Lifts.ThreeTen.set,
                                    reps = T2Lifts.ThreeTen.rep,
                                    nextWorkout = "T3"
                                ),
                        "T3" to
                                Lift(
                                    name = "LatPullDown",
                                    sets = T3Lifts.ThreeFifteen.set,
                                    reps = T3Lifts.ThreeFifteen.rep,
                                    nextWorkout = "T1"
                                )
                    )
                    ,
                )
            else -> Workout(
                "else",
                lifts = mapOf()
            )
        }
    )
}
