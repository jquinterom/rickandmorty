package co.mrcomondev.pro.rickandmorty.presentation.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import co.mrcomondev.pro.rickandmorty.presentation.composables.FullScreenError
import co.mrcomondev.pro.rickandmorty.presentation.composables.FullScreenLoading
import co.mrcomondev.pro.rickandmorty.presentation.composables.locations.LocationItem
import co.mrcomondev.pro.rickandmorty.presentation.viewmodel.LocationListViewModel

/**
 * Created by gesoft
 */
@Composable
fun LocationListScreen(
  viewModel: LocationListViewModel = hiltViewModel(),
) {
  val lazyPagingItems = viewModel.locationsPagingFlow.collectAsLazyPagingItems()


  when (lazyPagingItems.loadState.refresh) {
    is LoadState.Loading -> {
      FullScreenLoading()
    }

    is LoadState.Error -> {
      val error = (lazyPagingItems.loadState.refresh as LoadState.Error).error
      FullScreenError(
        message = error.localizedMessage ?: "Error to load locations",
        onRetry = { lazyPagingItems.retry() }
      )
    }

    else -> {
      LazyColumn(contentPadding = PaddingValues(16.dp)) {
        items(
          count = lazyPagingItems.itemCount,
          key = { index -> lazyPagingItems[index]?.id ?: index }) { index ->
          lazyPagingItems[index]?.let { location ->
            LocationItem(modifier = Modifier, location = location)
            Spacer(modifier = Modifier.height(8.dp))
          }
        }
      }
    }
  }

}