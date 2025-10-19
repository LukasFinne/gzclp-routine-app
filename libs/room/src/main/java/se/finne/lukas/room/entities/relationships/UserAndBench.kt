package se.finne.lukas.room.entities.relationships

import androidx.room.Embedded
import androidx.room.Relation
import se.finne.lukas.room.entities.User
import se.finne.lukas.room.entities.workouts.Bench

data class UserAndBench(
    @Embedded val user: User,
    @Relation(
        parentColumn = "benchId",
        entityColumn = "id"
    )
    val bench: Bench
)
