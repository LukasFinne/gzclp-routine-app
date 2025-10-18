package se.finne.lukas.room.entities.relationships

import androidx.room.Embedded
import androidx.room.Relation
import se.finne.lukas.room.entities.User
import se.finne.lukas.room.entities.workouts.Squat

data class UserAndSquat(
    @Embedded val user: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "userId"
    )
    val squat: Squat,
)
