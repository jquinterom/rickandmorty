package co.mrcomondev.pro.rickandmorty.composables

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import co.mrcomondev.pro.rickandmorty.presentation.composables.FullScreenError
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by gesoft
 */
@RunWith(AndroidJUnit4::class)
class FullScreenErrorTest {
  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun textErrorIsShowing() {
    val testMessage = "Error"
    composeTestRule.setContent {
      FullScreenError(message = testMessage, onRetry = {})
    }

    composeTestRule.onNodeWithText(testMessage)
  }

  @Test
  fun textMessageIsIncorrect() {
    val testMessage = "Error Message"
    composeTestRule.setContent {
      FullScreenError(message = "Error", onRetry = {})
    }
    composeTestRule.onNodeWithText(testMessage).assertDoesNotExist()
  }

  @Test
  fun clickOnButtonRetryWorks() {
    var retryClicked = false
    val onRetry: () -> Unit = { retryClicked = true }

    composeTestRule.setContent {
      FullScreenError(message = "Error", onRetry = onRetry)
    }

    composeTestRule.onNodeWithText("Retry").performClick()
    assert(retryClicked)
  }

  @Test
  fun buttonDoesNotExists() {
    composeTestRule.setContent {
      FullScreenError(message = "Error", onRetry = null)
    }

    composeTestRule.onNodeWithText("Retry").assertDoesNotExist()
  }
}