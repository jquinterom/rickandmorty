package co.mrcomondev.pro.rickandmorty.presentation.composables.locations

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import co.mrcomondev.pro.rickandmorty.domain.models.LocationDomain

/**
 * Created by gesoft
 */
@Composable
fun LocationItem(modifier: Modifier = Modifier, location: LocationDomain) {
  Card(
    modifier = modifier
      .fillMaxWidth(),
    elevation = CardDefaults.cardElevation(4.dp),
    shape = MaterialTheme.shapes.medium
  ) {

    Box(
      modifier = Modifier
        .fillMaxSize()
        .background(
          brush = Brush.linearGradient(
            colors = listOf(
              MaterialTheme.colorScheme.secondary.copy(alpha = 0.2f),
              MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
            )
          )
        )
    ) {

      Row(
        modifier = Modifier
          .fillMaxSize()
          .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {

        LocationCardIcon()

        LocationCardBody(location)
      }
    }
  }
}
