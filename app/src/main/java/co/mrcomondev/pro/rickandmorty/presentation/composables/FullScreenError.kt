package co.mrcomondev.pro.rickandmorty.presentation.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Created by gesoft
 */
@Composable
fun FullScreenError(message: String, onRetry: () -> Unit) {
  Text(
    text = message,
    color = MaterialTheme.colorScheme.error,
    modifier = Modifier
      .padding(16.dp)
      .clickable {
        onRetry()
      }
  )
}