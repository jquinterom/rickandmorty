package co.mrcomondev.pro.rickandmorty.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import co.mrcomondev.pro.rickandmorty.presentation.composables.FullScreenError
import co.mrcomondev.pro.rickandmorty.presentation.composables.FullScreenLoading
import co.mrcomondev.pro.rickandmorty.presentation.viewmodel.LocationListViewModel

/**
 * Created by gesoft
 */
@Composable
fun LocationListScreen(
  modifier: Modifier = Modifier,
  viewModel: LocationListViewModel = hiltViewModel(),
  onLocationClick: (Int) -> Unit
) {
  val lazyPagingItems = viewModel.locationsPagingFlow.collectAsLazyPagingItems()

  Box(modifier = modifier.fillMaxSize()) {
    when (lazyPagingItems.loadState.refresh) {
      is LoadState.Loading -> {
        FullScreenLoading()
      }

      is LoadState.Error -> {
        val error = (lazyPagingItems.loadState.refresh as LoadState.Error).error
        FullScreenError(
          message = error.localizedMessage ?: "Error al cargar los personajes",
          onRetry = { lazyPagingItems.retry() }
        )
      }

      else -> {
        Text(lazyPagingItems.itemCount.toString())
      }
    }
  }
}