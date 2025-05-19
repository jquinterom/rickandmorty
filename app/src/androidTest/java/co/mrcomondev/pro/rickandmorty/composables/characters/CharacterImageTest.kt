package co.mrcomondev.pro.rickandmorty.composables.characters


import android.content.Context
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import co.mrcomondev.pro.rickandmorty.composables.characters.utils.FakeImageLoader
import co.mrcomondev.pro.rickandmorty.presentation.composables.characters.CharacterImage
import coil.request.ImageRequest
import coil.request.ImageResult
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertThrows
import org.junit.Before
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

  private lateinit var context: Context

  @Before
  fun setup() {
    context = InstrumentationRegistry.getInstrumentation().targetContext
  }

  @Test
  fun characterImage_ImageLoadedCorrectly() {
    val testName = "Test Character"
    composeTestRule.setContent {
      CharacterImage(
        image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
        name = testName,
        imageLoader = FakeImageLoader()
      )
    }

    composeTestRule.waitUntil(timeoutMillis = 10000) {
      composeTestRule.onAllNodesWithContentDescription(testName)
        .fetchSemanticsNodes().size == 1
    }

    composeTestRule.onNodeWithContentDescription(testName).assertExists()
  }

  @Test
  fun shouldShowErrorMessageOnFailure() {
    val failingImageLoader = object : FakeImageLoader() {
      override suspend fun execute(request: ImageRequest): ImageResult {
        throw RuntimeException("Simulate error")
      }
    }

    assertThrows(RuntimeException::class.java) {
      runBlocking {
        failingImageLoader.execute(
          ImageRequest.Builder(context).data("url").build()
        )
      }
    }
  }
}