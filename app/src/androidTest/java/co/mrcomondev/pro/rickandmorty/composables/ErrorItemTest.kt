package co.mrcomondev.pro.rickandmorty.composables

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import co.mrcomondev.pro.rickandmorty.presentation.composables.ErrorItem
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by gesoft
 */
@RunWith(AndroidJUnit4::class)
class ErrorItemTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun errorItem_DisplaysCorrectMessage() {
    val testMessage = "Test error message"

    composeTestRule.setContent {
      ErrorItem(message = testMessage)
    }
    composeTestRule.onNodeWithText(testMessage).assertExists()
  }

  @Test
  fun errorItem_RetryButtonWorks() {
    var retryClicked = false
    val onRetry: () -> Unit = { retryClicked = true }

    composeTestRule.setContent {
      ErrorItem(
        message = "Error",
          onRetry = onRetry
      )
    }

    composeTestRule.onNodeWithText("Retry").performClick()
    assert(retryClicked)
  }

  @Test
  fun errorItem_WithNullRetryAction_HidesButton() {
    composeTestRule.setContent {
      ErrorItem(message = "Error", onRetry = null)
    }

    composeTestRule.onNodeWithText("Retry")
      .assertDoesNotExist()
  }
}

