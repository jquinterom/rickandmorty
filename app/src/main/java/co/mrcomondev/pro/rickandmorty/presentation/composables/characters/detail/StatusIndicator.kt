package co.mrcomondev.pro.rickandmorty.presentation.composables.characters.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * Created by gesoft
 */

@Composable
fun StatusIndicator(status: String) {
  val color = when (status.lowercase()) {
    "alive" -> Color(0xFF55CC44)
    "dead" -> Color(0xFFD63D2E)
    else -> Color(0xFF9E9E9E)
  }

  Box(
    modifier = Modifier
      .size(10.dp)
      .clip(CircleShape)
      .background(color)
  )
}