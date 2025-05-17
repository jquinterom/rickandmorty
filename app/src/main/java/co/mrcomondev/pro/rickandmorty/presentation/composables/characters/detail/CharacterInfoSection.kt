package co.mrcomondev.pro.rickandmorty.presentation.composables.characters.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.mrcomondev.pro.rickandmorty.domain.models.CharacterDomain
import kotlin.text.ifEmpty

/**
 * Created by gesoft
 */
@Composable
fun CharacterInfoSection(character: CharacterDomain) {
  Card(
    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
  ) {
    Column(modifier = Modifier.padding(16.dp)) {
      InfoRow(label = "Gender", value = character.gender)
      HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
      InfoRow(label = "Type", value = character.type.ifEmpty { "Unknown" })
      HorizontalDivider(
        modifier = Modifier.padding(vertical = 8.dp),
      )
      InfoRow(label = "Origin", value = character.origin.name)
      HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
      InfoRow(label = "Location", value = character.locationName)
    }
  }
}