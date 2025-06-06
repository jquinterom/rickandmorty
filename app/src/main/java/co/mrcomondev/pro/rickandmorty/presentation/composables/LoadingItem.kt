package co.mrcomondev.pro.rickandmorty.presentation.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp

/**
 * Created by gesoft
 */
@Composable
fun LoadingItem() {
  Box(
    modifier = Modifier
      .fillMaxWidth()
      .padding(16.dp)
      .testTag("circularProgress"),
    contentAlignment = Alignment.Center
  ) {
    CircularProgressIndicator()
  }
}