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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GzclpScreen(viewModel: GzclpViewModel, modifier: Modifier = Modifier){

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val timerValue by viewModel.timerValue.collectAsStateWithLifecycle()

    when(val data = uiState){
        is GzClpState.Loaded -> {
            Column(modifier.fillMaxHeight()) {
                Row(Modifier.padding(4.dp).fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    Text(data.name, fontSize = 24.sp)
                }

                Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceAround) {

                    ListItem(data.lift, timerValue)

                    Row( Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                        Button(
                            enabled = timerValue == null,
                            onClick ={

                        }) {
                            Text("Failed")
                        }
                        Button(
                            enabled = timerValue == null,
                            onClick ={
                            if (data.lift?.nextWorkout != null) {
                               // viewModel.onLiftSelected(data.lift.nextWorkout)
                                data.lift.restTime.let { viewModel.startTimer(it) }
                            }
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
fun ListItem(lift: Lift?,timerValue: Int?, modifier: Modifier = Modifier){

    Column(modifier = modifier) {
        Column(Modifier.fillMaxWidth().padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Exercise", fontSize = 24.sp)
            Text(lift?.name ?: "Not found", fontWeight = FontWeight.Bold)
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            TextItem("Weight", "${lift?.weight} Kg")
            TextItem("Set / Rep", "${lift?.sets} / ${lift?.reps}")
        }

        Column(Modifier.fillMaxWidth().padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            TextItem(
                "Rest Time",
                timerValue?.let {
                    "%02d:%02d".format(it / 60, it % 60)
                } ?:
                    "${lift?.restTime} min"
            )
        }
        Row(Modifier.fillMaxWidth().padding(top=16.dp), horizontalArrangement = Arrangement.Center){
           TextItem(
               "Current set",
               "1"
           )
        }
    }
}

@Composable
fun TextItem(header:String, text: String, modifier: Modifier = Modifier){
    Column(modifier.padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(header, fontSize = 24.sp)
        Text(text, fontWeight = FontWeight.Bold)
    }
}