package se.finne.lukas.gzclp

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope

@Composable
fun GzclpScreen(viewModel: GzclpViewModel, modifier: Modifier = Modifier){

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val workOutState by viewModel.workOutState.collectAsStateWithLifecycle()


    when(val data = uiState){
        is GzClpState.Loaded -> {
            Column(modifier) {
                when(workOutState){
                    WorkOutState.T1 -> {
                        Text(text = "${data.workout.t1}")
                        Button(onClick = {
                            viewModel.updateWorkOutState(WorkOutState.T2)
                        }) {
                            Text("Next")
                        }
                    }
                    WorkOutState.T2 ->{
                        Text(text = "${data.workout.t2}")
                        Button(onClick = {
                            viewModel.updateWorkOutState(WorkOutState.T3)
                        }) {
                            Text("Next")
                        }
                    }
                    WorkOutState.T3 ->{
                        Text(text = "${data.workout.t3}")
                        Button(onClick = {
                            viewModel.updateWorkOutState(WorkOutState.T1)
                        }){
                            Text("Finished")
                        }
                    }
                }
            }
        }
        GzClpState.Loading -> {
            Column {
                Text("Loading.. ")
            }
        }
    }

}