package se.finne.lukas.wiring

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import se.finne.lukas.declaration.WorkoutRepository
import se.finne.lukas.implementation.WorkoutRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
object WorkoutRepositoryModule {
    @Provides
    fun provideWorkoutRepository(): WorkoutRepository {
        return WorkoutRepositoryImpl()
    }
}