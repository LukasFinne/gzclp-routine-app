import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import se.finne.lukas.gzclp.FinishedViewModel

@Composable
fun FinishedScreen(finishedScreenViewModel: FinishedViewModel, modifier: Modifier = Modifier, onDone: () ->Unit) {
    Column(
       modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Congratulations! ", fontSize = 24.sp)
        Button(
            onClick = onDone
        ) {
            Text("Done")
        }
    }
}