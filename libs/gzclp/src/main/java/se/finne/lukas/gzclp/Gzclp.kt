package se.finne.lukas.gzclp

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope

@Composable
fun GzclpScreen(viewModel: GzclpViewModel, modifier: Modifier = Modifier){

    val data by viewModel.gzclp.collectAsStateWithLifecycle()
    Column(modifier) {
        Text(text = "Gzclp")
        Text("${data}")

    }
}