package co.mrcomondev.pro.rickandmorty.presentation.viewmodel

import co.mrcomondev.pro.rickandmorty.domain.models.CharacterDomain
import co.mrcomondev.pro.rickandmorty.domain.models.Episode
import co.mrcomondev.pro.rickandmorty.domain.models.Origin
import co.mrcomondev.pro.rickandmorty.domain.models.Result
import co.mrcomondev.pro.rickandmorty.domain.usecases.GetCharacterUseCase
import co.mrcomondev.pro.rickandmorty.domain.usecases.GetEpisodesUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CharacterDetailViewModelTest {
  private lateinit var mockGetCharacterUseCase: GetCharacterUseCase
  private lateinit var mockGetEpisodesUseCase: GetEpisodesUseCase

  private lateinit var viewModel: CharacterDetailViewModel

  private val testDispatcher = StandardTestDispatcher()

  val characterId = 1
  val character = CharacterDomain(
    id = characterId,
    name = "Rick",
    image = "url",
    episode = listOf("https://rickandmortyapi.com/api/episode/1"),
    status = "Alive",
    species = "Human",
    gender = "Male",
    type = "",
    origin = Origin("Earth"),
    locationName = "Earth"
  )

  val episode = Episode(1, "Pilot", "December 2, 2013", "S01E01", listOf(), "", "")

  val episodes = listOf(episode)

  @Before
  fun setup() {
    Dispatchers.setMain(testDispatcher)

    mockGetCharacterUseCase = mockk()
    mockGetEpisodesUseCase = mockk()
    viewModel = CharacterDetailViewModel(mockGetCharacterUseCase, mockGetEpisodesUseCase)
  }

  @After
  fun tearDown() {
    Dispatchers.resetMain()
  }

  @Test
  fun `loadCharacter should update states when success`() = runTest {
    coEvery { mockGetCharacterUseCase(characterId) } returns Result.Success(character)
    coEvery { mockGetEpisodesUseCase(listOf(1)) } returns episodes

    viewModel.loadCharacter(characterId)
    advanceUntilIdle()

    assert(viewModel.characterState.value is CharacterState.Success)
    assert(viewModel.episodesState.value is EpisodesState.Success)
    val success = viewModel.characterState.value as CharacterState.Success
    assert(success.character.name == "Rick")
  }

  @Test
  fun `loadCharacter should failure when error` () = runTest {
    coEvery { mockGetCharacterUseCase(characterId) } returns Result.Failure(Exception("Error to load character"))
    coEvery { mockGetEpisodesUseCase(listOf(1)) } returns episodes

    viewModel.loadCharacter(characterId)
    advanceUntilIdle()

    assert(viewModel.characterState.value is CharacterState.Error)
    assert(viewModel.episodesState.value is EpisodesState.Error)

    val episodeError = viewModel.episodesState.value as EpisodesState.Error
    assert(episodeError.message == "Character not found, episodes not loaded")

    val error = viewModel.characterState.value as CharacterState.Error
    assert(error.message == "Error to load character")
  }

  @Test
  fun `loadEpisodes should emit error when episode id can't be parsed`() = runTest {
    val characterId = 1
    val episodeId = "abc"
    val character = CharacterDomain(
      id = characterId,
      name = "Morty",
      image = "url",
      episode = listOf("https://rickandmortyapi.com/api/episode/${episodeId}"), // abc fails to parse int
      status = "Alive",
      species = "Human",
      gender = "Male",
      type = "",
      origin = Origin("Earth"),
      locationName = "Earth"
    )

    coEvery { mockGetCharacterUseCase(characterId) } returns Result.Success(character)

    viewModel.loadCharacter(characterId)
    advanceUntilIdle()

    val episodeError = viewModel.episodesState.value as EpisodesState.Error
    assert(viewModel.episodesState.value is EpisodesState.Error)
    assert(episodeError.message == "For input string: \"${episodeId}\"")
  }
}