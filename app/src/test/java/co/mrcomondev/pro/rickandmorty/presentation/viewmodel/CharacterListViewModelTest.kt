package co.mrcomondev.pro.rickandmorty.presentation.viewmodel

import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.PagingData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListUpdateCallback
import co.mrcomondev.pro.rickandmorty.domain.models.CharacterDomain
import co.mrcomondev.pro.rickandmorty.domain.models.Origin
import co.mrcomondev.pro.rickandmorty.domain.usecases.GetCharactersStreamUseCase
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CharacterListViewModelTest {
  private lateinit var mockGetCharacterStreamUseCase: GetCharactersStreamUseCase

  private lateinit var viewModel: CharacterListViewModel

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

  val characterMorty = CharacterDomain(
    id = characterId,
    name = "Morty",
    image = "url",
    episode = listOf("https://rickandmortyapi.com/api/episode/2"),
    status = "Alive",
    species = "Human",
    gender = "Male",
    type = "",
    origin = Origin("Earth"),
    locationName = "Earth"
  )

  @Before
  fun setup() {
    Dispatchers.setMain(testDispatcher)

    mockGetCharacterStreamUseCase = mockk()
  }

  @After
  fun tearDown() {
    Dispatchers.resetMain()
  }

  @Test
  fun charactersPagingFlow_is_empty() = runTest {
    val fakePagingData = flowOf(PagingData.empty<CharacterDomain>())
    every { mockGetCharacterStreamUseCase.invoke() } returns fakePagingData
    viewModel = CharacterListViewModel(mockGetCharacterStreamUseCase)
    val differ: AsyncPagingDataDiffer<CharacterDomain> = AsyncPagingDataDiffer(
      diffCallback = CharacterDiffCallback(),
      updateCallback = NoopListCallback(),
      workerDispatcher = testDispatcher
    )

    val job: Job = launch {
      viewModel.charactersPagingFlow.collectLatest {
        differ.submitData(it)
      }
    }

    advanceUntilIdle()
    assertEquals(0, differ.snapshot().size)
    job.cancel()
  }


  @Test
  fun charactersPagingFlow_items_are_correctly_mapped() = runTest {
    val fakePagingData = flowOf(PagingData.from(listOf(character)))

    every { mockGetCharacterStreamUseCase.invoke() } returns fakePagingData

    viewModel = CharacterListViewModel(mockGetCharacterStreamUseCase)

    val differ: AsyncPagingDataDiffer<CharacterDomain> = AsyncPagingDataDiffer(
      diffCallback = CharacterDiffCallback(),
      updateCallback = NoopListCallback(),
      workerDispatcher = testDispatcher
    )

    val job: Job = launch {
      viewModel.charactersPagingFlow.collectLatest {
        differ.submitData(it)
      }
    }

    advanceUntilIdle()

    assertEquals(1, differ.snapshot().size)
    assertEquals("Rick", differ.snapshot()[0]?.name)

    job.cancel()
  }

  @Test
  fun charactersPagingFlow_items_are_correctly_mapped_when_two_items() = runTest {
    val fakePagingData = flowOf(PagingData.from(listOf(character, characterMorty)))
    every { mockGetCharacterStreamUseCase.invoke() } returns fakePagingData

    viewModel = CharacterListViewModel(mockGetCharacterStreamUseCase)

    val differ: AsyncPagingDataDiffer<CharacterDomain> = AsyncPagingDataDiffer(
      diffCallback = CharacterDiffCallback(),
      updateCallback = NoopListCallback(),
      workerDispatcher = testDispatcher
    )

    val job: Job = launch {
      viewModel.charactersPagingFlow.collectLatest {
        differ.submitData(it)
      }
    }

    advanceUntilIdle()

    assertEquals(2, differ.snapshot().size)
    assertEquals("Rick", differ.snapshot()[0]?.name)
    assertEquals("Morty", differ.snapshot()[1]?.name)

    job.cancel()
  }


}


class NoopListCallback : ListUpdateCallback {
  override fun onInserted(position: Int, count: Int) {}
  override fun onRemoved(position: Int, count: Int) {}
  override fun onMoved(fromPosition: Int, toPosition: Int) {}
  override fun onChanged(position: Int, count: Int, payload: Any?) {}
}


class CharacterDiffCallback : DiffUtil.ItemCallback<CharacterDomain>() {
  override fun areItemsTheSame(oldItem: CharacterDomain, newItem: CharacterDomain): Boolean {
    return oldItem.id == newItem.id
  }

  override fun areContentsTheSame(oldItem: CharacterDomain, newItem: CharacterDomain): Boolean {
    return oldItem == newItem
  }
}