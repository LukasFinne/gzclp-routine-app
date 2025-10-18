package se.finne.lukas.room

import android.content.Context
import androidx.room.Room
import androidx.startup.Initializer

class RoomDatabaseInitializer: Initializer<RoomDatabase>{

    companion object {
        private lateinit var instance: RoomDatabase
        fun getInstance(): RoomDatabase {
            return instance
        }
    }

    override fun create(context: Context): RoomDatabase {
        instance = Room.databaseBuilder(
            context,
            RoomDatabase::class.java,
            "se.premex.byggappen.db"
        ).fallbackToDestructiveMigrationFrom(true).build()

        return instance
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> = mutableListOf()
}