package co.mrcomondev.pro.rickandmorty.presentation.viewmodel

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import co.mrcomondev.pro.rickandmorty.dataaccess.local.CharactersDataStore
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import java.io.File

/**
 * Created by gesoft
 */
@OptIn(ExperimentalCoroutinesApi::class)
class CommonViewModelTest {
  @get:Rule
  val temporaryFolder: TemporaryFolder = TemporaryFolder.builder().assureDeletion().build()

  private lateinit var mockCharactersDataStore: CharactersDataStore
  private lateinit var viewModel: CommonViewModel
  private val testDispatcher = StandardTestDispatcher()
  private lateinit var testDataStoreFile: File
  private lateinit var dataStore: DataStore<Preferences>
  private lateinit var characterIdsToModify: Set<String>

  @Before
  fun setup() {
    Dispatchers.setMain(testDispatcher)

    testDataStoreFile = temporaryFolder.newFile("test_characters.preferences_pb")

    dataStore = PreferenceDataStoreFactory.create(
      scope = TestScope(testDispatcher),
      produceFile = { testDataStoreFile }
    )

    mockCharactersDataStore = CharactersDataStore(dataStore)

    characterIdsToModify = setOf("101", "102", "103")
  }

  @After
  fun tearDown() {
    Dispatchers.resetMain()
    characterIdsToModify = setOf<String>()
  }

  @Test
  fun getFavoriteCharacterIdsFlow() = runTest {
    mockCharactersDataStore = mockk()

    every { mockCharactersDataStore.favoriteCharactersFlow } returns flowOf(characterIdsToModify)

    viewModel = CommonViewModel(mockCharactersDataStore)

    advanceUntilIdle()

    viewModel.favoriteCharacterIdsFlow.collect {
      assert(it.size == 3)
      assert(it.contains("101"))
      assert(it.contains("102"))
      assert(it.contains("103"))
    }
  }


  @Test
  fun deleteFavoriteCharacter_should_remove_character_from_favorites() = runTest {
    val idToRemove = "101"
    mockCharactersDataStore.updateFavoriteCharacters(characterIdsToModify)

    viewModel = CommonViewModel(mockCharactersDataStore)
    viewModel.updateFavoriteCharacter(idToRemove, true)

    advanceUntilIdle()

    val favorites = viewModel.favoriteCharacterIdsFlow.first()
    assert(favorites.size == 2)
    assert(!favorites.contains(idToRemove))
  }
}