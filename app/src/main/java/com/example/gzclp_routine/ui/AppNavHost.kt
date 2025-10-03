package com.example.gzclp_routine.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.gzclp_routine.Greeting
import se.finne.lukas.gzclp.GzclpScreen
import se.finne.lukas.navigation.Gzclp
import se.finne.lukas.navigation.Settings

@Composable
fun AppNavHost(navController: NavHostController, modifier: Modifier = Modifier){
    NavHost(navController, modifier = modifier, startDestination = Gzclp ){
        composable<Gzclp>{
            GzclpScreen()
        }

        composable<Settings>{
            Column(modifier = modifier){
                Greeting("Settings")
            }
        }
    }
}