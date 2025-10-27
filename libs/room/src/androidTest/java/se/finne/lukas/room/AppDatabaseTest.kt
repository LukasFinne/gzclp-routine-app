package se.finne.lukas.room

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.turbine.test
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import se.finne.lukas.room.dao.UserDao
import se.finne.lukas.room.entities.User
import java.io.IOException
import kotlin.test.assertEquals

@RunWith(AndroidJUnit4::class)
class AppDatabaseTest {
    private lateinit var userDao: UserDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context,
            AppDatabase::class.java).build()
        userDao = db.userDao()

    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeUserAndReadInList() = runTest {
        val user = User(username = "TextUser", currentWorkout = "A1")
        userDao.insertUsers(user)
        val users = userDao.getUsersById(1)

        users.test {
            val item: User = awaitItem()
            assertEquals(user, item)
            awaitComplete()
        }

    }
}
