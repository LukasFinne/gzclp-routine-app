package com.example.gzclp_routine.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import se.finne.lukas.navigation.Gzclp
import se.finne.lukas.navigation.Settings

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView(modifier: Modifier = Modifier){
    val navController = rememberNavController()

    var selectedDestination by rememberSaveable { mutableStateOf(Gzclp.name()) }

    Scaffold(
        modifier = modifier,
        bottomBar = {
            NavigationBar(windowInsets = NavigationBarDefaults.windowInsets){
                NavigationBarItem(
                    selected = Gzclp.name() == selectedDestination,
                    onClick = {
                        navController.navigate(Gzclp)
                        selectedDestination = Gzclp.name()
                    },
                    icon = {
                        Icon(
                            Icons.Default.FitnessCenter,
                            contentDescription = null
                        )
                    },
                    label = { Text("Gzclp") }
                )
                NavigationBarItem(
                    selected = Settings.name() == selectedDestination,
                    onClick = {
                        navController.navigate(Settings)
                        selectedDestination = Settings.name()
                    },
                    icon = {
                        Icon(
                            Icons.Default.Settings,
                            contentDescription = null
                        )
                    },
                    label = { Text("Settings") }
                )
            }
        }
    ){ contentPadding->
        AppNavHost(navController = navController, modifier = Modifier.padding(contentPadding))
    }

}