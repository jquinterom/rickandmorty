package co.mrcomondev.pro.rickandmorty.presentation.composables.characters

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import co.mrcomondev.pro.rickandmorty.domain.models.CharacterDomain

/**
 * Created by gesoft
 */
@Composable
fun CharacterItem(
  character: CharacterDomain,
  onClick: () -> Unit,
  isFavorite: Boolean,
  updateFavorite: (Boolean) -> Unit,
) {

  val favoriteIcon = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder

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
      CharacterImage(
        modifier = Modifier.size(64.dp),
        image = character.image,
        name = character.name
      )

      Spacer(modifier = Modifier.width(16.dp))

      Column(
        modifier = Modifier.weight(1f)
      ) {
        Text(
          text = character.name,
          style = MaterialTheme.typography.titleMedium
        )
      }

      Icon(
        imageVector = favoriteIcon,
        contentDescription = "favorite",
        modifier = Modifier
          .clip(CircleShape)
          .clickable {
            updateFavorite(isFavorite)
          },
        tint = MaterialTheme.colorScheme.primary
      )
    }
  }
}