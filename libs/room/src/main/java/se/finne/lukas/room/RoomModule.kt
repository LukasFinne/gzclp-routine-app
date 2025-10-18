package se.finne.lukas.room

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import se.finne.lukas.room.dao.UserDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(
        @ApplicationContext context: Context
    ): RoomDatabase = Room.databaseBuilder(
        context,
        RoomDatabase::class.java,
        "se.finne.lukas.room"
    ).fallbackToDestructiveMigration(true).build()

    @Provides
    @Singleton
    fun provideUserDao(
        roomDatabase: RoomDatabase
    ): UserDao = roomDatabase.dao

}