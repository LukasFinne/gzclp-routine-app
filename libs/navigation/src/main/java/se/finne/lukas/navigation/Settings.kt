package se.finne.lukas.navigation

import kotlinx.serialization.Serializable

@Serializable
data object Settings : Destination{
    override fun name() = "Settings"
}