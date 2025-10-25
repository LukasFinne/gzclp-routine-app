package se.finne.lukas.gzclp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import se.finne.lukas.room.dao.UserDao
import se.finne.lukas.room.entities.User
import se.finne.lukas.room.entities.workouts.Workout


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


    init {
     viewModelScope.launch {
         userDao.getUsersWithWorkouts().collect {
                Log.d("GzclpViewModel", "Users with workouts: $it")
         }
     }
    }

    fun startTimer(minutes: Int) {
        viewModelScope.launch {
            var seconds = 1
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

    fun updateWorkout(restTime: Int, isSuccess: Boolean, workoutSet: Int, workoutId: Int, next: WorkOutTier){

        viewModelScope.launch {
            uiState.collect {
                if(it is GzClpState.Loaded){
                    val lift = it.lift
                    if(lift != null){

                    }
                }
            }
        }

        if(isSuccess){
            startTimer(restTime)
            updateCurrentSet(workoutSet)
        }else{
            startTimer(restTime)
            onLiftSelected(next)
        }
    }

    fun increateWeight(tier: WorkOutTier){
       when(tier){
           WorkOutTier.T1 -> {

           }
           WorkOutTier.T2 -> {

           }
           WorkOutTier.T3 -> {

           }
           else -> {}
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
            _selectedLiftKey.update { liftKey }
            _currentSet.update { 0 }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun watchWorkout(): Flow<GzClpState> =userDao.getUsersById(1).flatMapLatest {
       getWorkOut(it.currentWorkout, it.id).combine(_selectedLiftKey) { workout, selectedLiftKey ->
            val lift = workout.lifts[selectedLiftKey]
            GzClpState.Loaded(
                name = workout.name,
                lift = lift
            )
        }
    }


    fun getA1Workout(userId: Int) = combine(
        userDao.getWorkoutByNameAndUserId("Squat", userId),
        userDao.getWorkoutByNameAndUserId("Bench", userId),
        userDao.getWorkoutByNameAndUserId("LatPullDown", userId)
    ) { squat, bench, latPullDown ->
        WorkoutUI(
            name = "Squat day",
            mapOf(
                WorkOutTier.T1 to Lift(
                    id = squat.id,
                    name = squat.workoutName,
                    sets = squat.tierOneSet,
                    reps = squat.tierOneRep,
                    restTime = SIXTY,
                    weight = squat.weight,
                    onNext = WorkOutTier.T2
                ),
                WorkOutTier.T2 to Lift(
                    id = bench.id,
                    name = bench.workoutName,
                    sets = bench.tierTwoSet,
                    reps = bench.tierTwoRep,
                    restTime = SIXTY,
                    weight = bench.weight,
                    onNext = WorkOutTier.T3
                ),
                WorkOutTier.T3 to Lift(
                    id = latPullDown.id,
                    name = latPullDown.workoutName,
                    sets =  latPullDown.tierOneSet,
                    reps = latPullDown.tierOneRep,
                    restTime = SIXTY,
                    weight = latPullDown.weight,
                    onNext = WorkOutTier.Finished)

            )
        )
    }

    fun getWorkOut(id : String, userId: Int): Flow<WorkoutUI> = when(Workouts.valueOf(id)) {
        Workouts.A1 -> getA1Workout(userId)
        else -> flowOf()
    }
}
