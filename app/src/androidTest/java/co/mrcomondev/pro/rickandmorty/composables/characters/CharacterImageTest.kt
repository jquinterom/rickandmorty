package co.mrcomondev.pro.rickandmorty.composables.characters


import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.test.ext.junit.runners.AndroidJUnit4
import co.mrcomondev.pro.rickandmorty.presentation.composables.characters.CharacterImage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Created by gesoft
 */
@RunWith(AndroidJUnit4::class)
class CharacterImageTest {
  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun characterImage_ImageLoadedCorrectly() {
    val testName = "Test Character"
    composeTestRule.setContent {
      CharacterImage(
        image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
        name = testName,
      )
    }

    composeTestRule.waitUntil(timeoutMillis = 10000) {
      composeTestRule.onAllNodesWithContentDescription(testName)
        .fetchSemanticsNodes().size == 1
    }

    composeTestRule.onNodeWithContentDescription(testName).assertExists()
  }
}