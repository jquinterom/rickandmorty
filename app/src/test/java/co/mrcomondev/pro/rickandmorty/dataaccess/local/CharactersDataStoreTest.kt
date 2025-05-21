package co.mrcomondev.pro.rickandmorty.dataaccess.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import java.io.File

/**
 * Created by gesoft
 */
@OptIn(ExperimentalCoroutinesApi::class)
class CharactersDataStoreTest {
  @get:Rule
  val temporaryFolder: TemporaryFolder = TemporaryFolder.builder().assureDeletion().build()

  private lateinit var mockCharactersDataStore: CharactersDataStore
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
  fun updateFavoriteCharacters_should_write_character_IDs_to_DataStore() = runTest {
    val expectedInitialState = emptySet<String>()

    val initialFavorites = mockCharactersDataStore.favoriteCharactersFlow.first()

    // Validate if the initial state is empty
    Assert.assertEquals(expectedInitialState, initialFavorites)

    mockCharactersDataStore.updateFavoriteCharacters(characterIdsToModify)

    // Assert: Verify that the data was written correctly
    val savedFavorites = mockCharactersDataStore.favoriteCharactersFlow.first()
    Assert.assertEquals(characterIdsToModify, savedFavorites)
  }


  @Test
  fun updateFavoriteCharacters_with_empty_set_should_clear_existing_favorites() = runTest {
    mockCharactersDataStore.updateFavoriteCharacters(characterIdsToModify)

    // Verify they were saved
    var currentFavorites = mockCharactersDataStore.favoriteCharactersFlow.first()
    Assert.assertEquals(characterIdsToModify, currentFavorites)

    val emptySetToSave = emptySet<String>()

    mockCharactersDataStore.updateFavoriteCharacters(emptySetToSave)

    // Assert: Verify that the favorites are now empty
    currentFavorites = mockCharactersDataStore.favoriteCharactersFlow.first()
    Assert.assertEquals(emptySetToSave, currentFavorites)
  }

  @Test
  fun updateFavoriteCharacters_should_overwrite_existing_favorites() = runTest {
    mockCharactersDataStore.updateFavoriteCharacters(characterIdsToModify)

    Assert.assertEquals(
      characterIdsToModify,
      mockCharactersDataStore.favoriteCharactersFlow.first()
    )

    val newCharacterIds = setOf("303", "304", "305")

    mockCharactersDataStore.updateFavoriteCharacters(newCharacterIds)

    // Assert: Verify that only the new favorites exist
    val updatedFavorites = mockCharactersDataStore.favoriteCharactersFlow.first()
    Assert.assertEquals(newCharacterIds, updatedFavorites)
  }
}