package co.mrcomondev.pro.rickandmorty.presentation.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.mrcomondev.pro.rickandmorty.domain.models.CharacterDomain

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
      CharacterImage(
        modifier = Modifier.size(64.dp),
        image = character.image,
        name = character.name
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