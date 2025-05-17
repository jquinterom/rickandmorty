package co.mrcomondev.pro.rickandmorty.presentation.composables.characters.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import co.mrcomondev.pro.rickandmorty.domain.models.CharacterDomain
import co.mrcomondev.pro.rickandmorty.presentation.composables.characters.CharacterImage

/**
 * Created by gesoft
 */

@Composable
fun CharacterHeaderSection(
  character: CharacterDomain,
  updateFavorite: (Boolean) -> Unit,
  isFavorite: Boolean
) {

  val favoriteIcon =
    if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder

  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = Modifier
      .fillMaxWidth()
  ) {
    CharacterImage(modifier = Modifier.size(180.dp), image = character.image, name = character.name)

    Spacer(modifier = Modifier.height(16.dp))

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
      Text(
        text = character.name,
        style = MaterialTheme.typography.headlineLarge,
        textAlign = TextAlign.Center
      )

      Icon(
        imageVector = favoriteIcon,
        contentDescription = "favorite",
        modifier = Modifier
          .clip(CircleShape)
          .clickable { updateFavorite(isFavorite) },
        tint = MaterialTheme.colorScheme.primary
      )

      Spacer(modifier = Modifier.height(4.dp))

      Row(verticalAlignment = Alignment.CenterVertically) {
        StatusIndicator(status = character.status)
        Spacer(modifier = Modifier.width(8.dp))
        Text(
          text = "${character.status} - ${character.species}",
          style = MaterialTheme.typography.titleMedium
        )
      }
    }
  }
}