package co.mrcomondev.pro.rickandmorty.composables

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import co.mrcomondev.pro.rickandmorty.presentation.composables.LoadingItem
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by gesoft
 */
@RunWith(AndroidJUnit4::class)
class LoadingItemTest {
  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun circularProgressIsShowing() {
    val testMessage = "circularProgress"
    composeTestRule.setContent {
      LoadingItem()
    }

    composeTestRule.onNodeWithTag(testMessage).assertExists()
  }
}