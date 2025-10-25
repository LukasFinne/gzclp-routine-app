package se.finne.lukas.room.entities.relationships

import androidx.room.Embedded
import androidx.room.Relation
import se.finne.lukas.room.entities.workouts.Workout

data class UserAndWorkout(
    @Embedded val user: se.finne.lukas.room.entities.User,
    @Relation(
        parentColumn = "id",
        entityColumn = "userCreatorId"
    )
    val workout: Workout
)