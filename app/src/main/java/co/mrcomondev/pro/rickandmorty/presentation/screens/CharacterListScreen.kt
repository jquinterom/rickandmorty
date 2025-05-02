package co.mrcomondev.pro.rickandmorty.presentation.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import co.mrcomondev.pro.rickandmorty.presentation.viewmodel.CharacterListViewModel

/**
 * Created by gesoft
 */
@Composable
fun CharacterListScreen(
  viewModel: CharacterListViewModel = hiltViewModel(),
  onCharacterClick: (Int) -> Unit
) {
  val listState = rememberLazyListState()
  val state by viewModel.characterResponse.observeAsState()

  LazyColumn(
    modifier = Modifier.fillMaxSize(),
    contentPadding = PaddingValues(16.dp),
    state = listState
  ) {
    items(state?.characters ?: emptyList()) { character ->
      CharacterItem(
        character = character,
        onClick = { onCharacterClick(character.id) }
      )
      Spacer(modifier = Modifier.height(8.dp))
    }

    item {
      if (state?.isLoading == true) {
        CircularProgressIndicator(
          modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .wrapContentWidth(Alignment.CenterHorizontally)
        )
      }
    }
  }

  LaunchedEffect(listState) {
    snapshotFlow {
      val lastVisible = listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
      val totalItems = listState.layoutInfo.totalItemsCount
      Pair(lastVisible, totalItems)
    }.collect { (lastVisibleItemIndex, totalItemsCount) ->
      if (
        lastVisibleItemIndex >= totalItemsCount - 5 &&
        state?.isLoading == false &&
        state?.endReached == false
      ) {
        viewModel.loadCharacters()
      }
    }
  }


  if (state?.error != null) {
    Text(
      text = state?.error ?: "Unknown error",
      color = MaterialTheme.colorScheme.error,
      modifier = Modifier.padding(16.dp)
    )
  }
}