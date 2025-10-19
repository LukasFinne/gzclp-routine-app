package se.finne.lukas.room

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import se.finne.lukas.room.dao.UserDao
import se.finne.lukas.room.entities.User
import se.finne.lukas.room.entities.workouts.Bench
import se.finne.lukas.room.entities.workouts.LatPullDown
import se.finne.lukas.room.entities.workouts.Squat
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
                    squatId = 0,
                    benchId = 0,
                    latPullDownId = 0
                )
            )
            userDao.get().insertA1(
                squat = Squat(
                    id = 0,
                    weight = 9,
                    set = 5,
                    reps = 3,
                ),
                bench = Bench(
                    id = 0,
                    weight = 9,
                    set = 3,
                    reps = 10,
                ),
                latPullDown = LatPullDown(
                    id = 0,
                    weight = 9,
                    set = 3,
                    reps = 15,
                )
            )
        }
    }
}
