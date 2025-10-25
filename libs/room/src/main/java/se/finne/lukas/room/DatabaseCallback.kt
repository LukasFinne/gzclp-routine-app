package se.finne.lukas.room

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import se.finne.lukas.room.dao.UserDao
import se.finne.lukas.room.entities.User
import se.finne.lukas.room.entities.workouts.Workout
import javax.inject.Provider

class DatabaseCallback(
    private val userDao: Provider<UserDao>
) : RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        CoroutineScope(Dispatchers.IO).launch {
            userDao.get().insertUsers(
                User(
                    id = 0,
                    username = "Lukas",
                    currentWorkout = "A1",
                )
            )
            userDao.get().insertWorkout(
                Workout(
                    id = 0,
                    workoutName = "Squat",
                    userCreatorId = 0,
                    weight = 20,
                    tierOneRep = 3,
                    tierOneSet = 5,
                    tierTwoSet = 3,
                    tierTwoRep = 10,
                ),
            )

            userDao.get().insertWorkout(
                Workout(
                    id = 1,
                    workoutName = "Bench",
                    userCreatorId = 0,
                    weight = 20,
                    tierOneRep = 3,
                    tierOneSet = 5,
                    tierTwoSet = 3,
                    tierTwoRep = 10,
                ),
            )

            userDao.get().insertWorkout(
                Workout(
                    id = 2,
                    workoutName = "LatPullDown",
                    userCreatorId = 0,
                    weight = 20,
                    tierOneRep = 3,
                    tierOneSet = 15,
                    tierTwoSet = 3,
                    tierTwoRep = 15,
                ),
            )
        }
    }
}
