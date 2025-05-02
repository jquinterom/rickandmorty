package co.mrcomondev.pro.rickandmorty.presentation.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Created by gesoft
 */
@Composable
fun FullScreenLoading() {
  CircularProgressIndicator(
    modifier = Modifier
      .fillMaxSize()
      .padding(16.dp)
      .wrapContentWidth(Alignment.CenterHorizontally)
  )
}