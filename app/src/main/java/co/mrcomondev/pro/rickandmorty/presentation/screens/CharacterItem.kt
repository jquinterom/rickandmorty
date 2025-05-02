package co.mrcomondev.pro.rickandmorty.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import co.mrcomondev.pro.rickandmorty.domain.models.CharacterDomain
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage

/**
 * Created by gesoft
 */
@Composable
fun CharacterItem(
  character: CharacterDomain,
  onClick: () -> Unit
) {
  Card(
    onClick = onClick,
    modifier = Modifier
      .fillMaxWidth(),
    elevation = CardDefaults.cardElevation(4.dp)
  ) {
    Row(
      modifier = Modifier.padding(16.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      SubcomposeAsyncImage(
        model = character.image,
        contentDescription = character.name,
        modifier = Modifier
          .size(64.dp)
          .clip(CircleShape),
        contentScale = ContentScale.Crop,
        loading = {
          Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
          ) {
            CircularProgressIndicator(modifier = Modifier.size(32.dp))
          }
        },
        error = {
          Icon(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = "Error loading image"
          )
        }
      )

      Spacer(modifier = Modifier.width(16.dp))

      Column {
        Text(
          text = character.name,
          style = MaterialTheme.typography.titleMedium
        )
      }
    }
  }
}