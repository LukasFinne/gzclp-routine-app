package se.finne.lukas.gzclp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GzclpScreen(viewModel: GzclpViewModel, modifier: Modifier = Modifier){

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    when(val data = uiState){
        is GzClpState.Loaded -> {
            Column(modifier.fillMaxHeight()) {
                Row(Modifier.padding(4.dp).fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    Text(data.name, fontSize = 24.sp)
                }

                Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceAround) {

                    ListItem(data.lift)

                    Row( Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                        Button(onClick ={

                        }) {
                            Text("Failed")
                        }
                        Button(onClick ={
                            if (data.lift?.nextWorkout != null)
                                viewModel.onLiftSelected(data.lift.nextWorkout)
                        }) {
                            Text("Success")
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


@Composable
fun ListItem(lift: Lift?, modifier: Modifier = Modifier){
    Column(modifier = modifier) {
        Column(Modifier.fillMaxWidth().padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Exercise", fontSize = 24.sp)
            Text(lift?.name ?: "Not found", fontWeight = FontWeight.Bold)
        }


        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            Column(Modifier.padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Weight", fontSize = 24.sp)
                Text("10 Kg", fontWeight = FontWeight.Bold)
            }
            Column(Modifier.padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Set / Rep", fontSize = 24.sp)
                Text("${lift?.sets ?:0 } / ${lift?.reps ?: 0}", fontWeight = FontWeight.Bold)
            }
        }

        Column(Modifier.fillMaxWidth().padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Rest Time", fontSize = 24.sp)

            Row {
                Text("3 min", fontWeight = FontWeight.Bold)
                Text(" / ", fontWeight = FontWeight.Bold)
                Text("5 min", fontWeight = FontWeight.Bold)
            }
        }
    }
}