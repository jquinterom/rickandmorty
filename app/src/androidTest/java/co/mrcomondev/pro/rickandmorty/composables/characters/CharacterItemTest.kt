package co.mrcomondev.pro.rickandmorty.composables.characters

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import co.mrcomondev.pro.rickandmorty.domain.models.CharacterDomain
import co.mrcomondev.pro.rickandmorty.domain.models.Origin
import co.mrcomondev.pro.rickandmorty.presentation.composables.characters.CharacterItem
import org.junit.Rule
import org.junit.Test

/**
 * Created by gesoft
 */

class CharacterItemTest {
  @get:Rule
  val composeTestRule = createComposeRule()

  val character = CharacterDomain(
    id = 1,
    name = "Rick Sanchez",
    image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
    episode = listOf("S01E01", "S01E02"),
    status = "Alive",
    species = "Human",
    gender = "Male",
    type = "",
    origin = Origin("Earth"),
    locationName = "Citadel of Ricks"
  )

  @Test
  fun shouldDisplayCharacterName() {
    composeTestRule.setContent {
      CharacterItem(
        character = character,
        onClick = {},
        isFavorite = false,
        updateFavorite = {}
      )
    }

    composeTestRule.onNodeWithText(character.name).assertIsDisplayed()
  }

  @Test
  fun imageIsDisplayed() {
    composeTestRule.setContent {
      CharacterItem(
        character = character,
        onClick = {},
        isFavorite = false,
        updateFavorite = {}
      )
    }

    composeTestRule.onNodeWithText(character.name).assertIsDisplayed()
  }
}
