package se.finne.lukas.navigation

import kotlinx.serialization.Serializable

@Serializable
data object Finished : Destination{
    override fun name() = "Finished"
}