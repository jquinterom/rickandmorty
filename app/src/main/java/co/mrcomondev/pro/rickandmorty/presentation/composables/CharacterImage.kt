package co.mrcomondev.pro.rickandmorty.presentation.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage

/**
 * Created by gesoft
 */
@Composable
fun CharacterImage(modifier: Modifier = Modifier, image: String, name: String) {
  SubcomposeAsyncImage(
    model = image,
    contentDescription = name,
    modifier = modifier
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
}