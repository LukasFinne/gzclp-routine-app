package se.finne.lukas.gzclp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import se.finne.lukas.room.RoomDatabase
import se.finne.lukas.room.dao.UserDao
import se.finne.lukas.room.entities.User
import se.finne.lukas.room.entities.workouts.Bench
import se.finne.lukas.room.entities.workouts.LatPullDown
import se.finne.lukas.room.entities.workouts.Squat


sealed class GzClpState{
    data object Loading: GzClpState()
    data class Loaded(val name: String, val lift: Lift?): GzClpState()
}
const val SIXTY = 60
@HiltViewModel
class GzclpViewModel @Inject constructor(
    val userDao: UserDao,
): ViewModel() {

    private val _selectedLiftKey = MutableStateFlow<WorkOutTier>(WorkOutTier.T1)


    init {
       viewModelScope.launch {
           userDao.insertUsers(
               User(
                   id = 0,
                   username = "Lukas",
                   currentWorkout = "A1",
                   squatId = 0,
                   benchId = 0,
                   latPullDownId = 0
               )
           )
            userDao.insertA1(
                squat = Squat(
                    id = 0  ,
                    weight = 9,
                    set = 5,
                    reps = 3,
                    userId = 0,
                ),
                bench = Bench(
                    id = 0,
                    weight = 9,
                    set = 3,
                    reps = 10,
                    userId = 0,
                ),
                latPullDown = LatPullDown(
                    id = 0,
                    weight = 9,
                    set = 3,
                    reps = 15,
                   userId = 0,
                ))
       }
    }



    val uiState: StateFlow<GzClpState> = watchWorkout()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = GzClpState.Loading
        )

    private val _timerValue = MutableStateFlow<Int?>(null)
    val timerValue: StateFlow<Int?> = _timerValue.asStateFlow()

    private val _currentSet = MutableStateFlow<Int>(0)
    val currentSet = _currentSet.asStateFlow()

    fun startTimer(minutes: Int) {
        viewModelScope.launch {
            var seconds = minutes
            _timerValue.update {
                 seconds
            }

            while (seconds > 0) {
                delay(1000)
                seconds--
                _timerValue.update {
                   seconds
                }
            }
            _timerValue.update {
                null
            }
        }
    }

    fun updateCurrentSet(liftSet: Int){
        _currentSet.update {
            if(it == liftSet){
                liftSet
            }else {
                it + 1
            }
        }
    }

    fun onLiftSelected(liftKey: WorkOutTier) {
        if(liftKey != WorkOutTier.T3){
            _selectedLiftKey.update { liftKey }
            _currentSet.update { 0 }
        }else {
            //Start next Workout
        }
    }

    private fun watchWorkout(): Flow<GzClpState> =
        getWorkOut(Workouts.A1.id).combine(_selectedLiftKey) { workout, liftKey ->
            GzClpState.Loaded(workout.name, workout.lifts[liftKey])
        }


    fun getWorkOut(id : String): Flow<Workout> = flowOf(
        when(Workouts.valueOf(id)){
            Workouts.A1 ->
                Workout(
                    name = "Squat day",
                    lifts = mapOf(
                        WorkOutTier.T1 to
                                Lift(
                                    name = "Squat",
                                    sets = T1Lifts.FiveThree.set,
                                    reps = T1Lifts.FiveThree.rep,
                                    nextWorkout = WorkOutTier.T2,
                                    weight = 10,
                                    restTime = 3
                                ),
                        WorkOutTier.T2 to
                                Lift(
                                    name = "Bench",
                                    sets = T2Lifts.ThreeTen.set,
                                    reps = T2Lifts.ThreeTen.rep,
                                    nextWorkout = WorkOutTier.T3,
                                    weight = 10,
                                    restTime = 2
                                ),
                        WorkOutTier.T3 to
                                Lift(
                                    name = "LatPullDown",
                                    sets = T3Lifts.ThreeFifteen.set,
                                    reps = T3Lifts.ThreeFifteen.rep,
                                    nextWorkout = WorkOutTier.T1,
                                    weight = 6,
                                    restTime = 1
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