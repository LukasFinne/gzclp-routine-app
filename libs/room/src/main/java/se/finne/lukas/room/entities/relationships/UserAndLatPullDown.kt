package se.finne.lukas.room.entities.relationships

import androidx.room.Embedded
import androidx.room.Relation
import se.finne.lukas.room.entities.User
import se.finne.lukas.room.entities.workouts.LatPullDown

data class UserAndLatPullDown(
    @Embedded val user: User,
    @Relation(
       parentColumn = "id",
       entityColumn = "userId"
    )
    val latPullDown: LatPullDown
)
