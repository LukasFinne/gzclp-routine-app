package com.example.gzclp_routine.ui

import FinishedScreen
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import se.finne.lukas.gzclp.GzclpScreen
import se.finne.lukas.navigation.Finished
import se.finne.lukas.navigation.Gzclp
import se.finne.lukas.navigation.Settings

@Composable
fun AppNavHost(navController: NavHostController, modifier: Modifier = Modifier){
    NavHost(navController, modifier = modifier, startDestination = Gzclp ){
        composable<Gzclp>{
            GzclpScreen(viewModel = hiltViewModel()){
                navController.navigate(Finished)
            }
        }

        composable<Settings>{
            Column(modifier = modifier){
                Text(text = "Settings Screen")
            }
        }

        composable<Finished> {
            FinishedScreen(finishedScreenViewModel = hiltViewModel()){
                navController.navigate(Gzclp)
            }
        }
    }
}