package co.mrcomondev.pro.rickandmorty.presentation.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import co.mrcomondev.pro.rickandmorty.presentation.composables.ErrorItem
import co.mrcomondev.pro.rickandmorty.presentation.composables.FullScreenError
import co.mrcomondev.pro.rickandmorty.presentation.composables.FullScreenLoading
import co.mrcomondev.pro.rickandmorty.presentation.composables.characters.CharacterItem
import co.mrcomondev.pro.rickandmorty.presentation.viewmodel.CharacterListViewModel
import co.mrcomondev.pro.rickandmorty.presentation.viewmodel.CommonViewModel

/**
 * Created by gesoft
 */
@Composable
fun CharacterListScreen(
  viewModel: CharacterListViewModel = hiltViewModel(),
  commonViewModel: CommonViewModel = hiltViewModel(),
  onCharacterClick: (Int) -> Unit
) {
  val lazyPagingItems = viewModel.charactersPagingFlow.collectAsLazyPagingItems()

  when (lazyPagingItems.loadState.refresh) {
    is LoadState.Loading -> {
      FullScreenLoading()
    }

    is LoadState.Error -> {
      val error = (lazyPagingItems.loadState.refresh as LoadState.Error).error
      FullScreenError(
        message = error.localizedMessage ?: "Error to load characters",
        onRetry = { lazyPagingItems.retry() }
      )
    }

    else -> {
      LazyColumn(contentPadding = PaddingValues(16.dp)) {
        items(
          count = lazyPagingItems.itemCount,
          key = { index -> lazyPagingItems[index]?.id ?: index },
        ) { index ->
          lazyPagingItems[index]?.let { character ->
            CharacterItem(
              character = character,
              onClick = { onCharacterClick(character.id) },
              isFavorite = commonViewModel.isFavoriteCharacter(character.id.toString())
                .collectAsState(initial = false).value,
              updateFavorite = { isFavorite ->
                commonViewModel.updateFavoriteCharacter(
                  characterId = character.id.toString(),
                  isFavorite = isFavorite
                )
              }
            )
            Spacer(modifier = Modifier.height(8.dp))
          }
        }

        item {
          if (lazyPagingItems.loadState.append is LoadState.Loading) {
            FullScreenLoading()
          }

          if (lazyPagingItems.loadState.append is LoadState.Error) {
            val error = (lazyPagingItems.loadState.append as LoadState.Error).error
            ErrorItem(
              message = error.localizedMessage ?: "Error al cargar",
              onRetry = { lazyPagingItems.retry() }
            )
          }
        }
      }
    }
  }
}
