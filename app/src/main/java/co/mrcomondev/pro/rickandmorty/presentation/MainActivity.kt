package co.mrcomondev.pro.rickandmorty.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import co.mrcomondev.pro.rickandmorty.presentation.viewmodel.RickAndMortyViewModel
import co.mrcomondev.pro.rickandmorty.ui.theme.RickAndMortyTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  private lateinit var viewModel: RickAndMortyViewModel


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setupViewModel()
    setupObservers()

    enableEdgeToEdge()
    setContent {
      RickAndMortyTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
          Greeting(
            name = "Android",
            modifier = Modifier.padding(innerPadding)
          )
        }
      }
    }
  }

  private fun setupViewModel() {
    val vm: RickAndMortyViewModel by viewModels()
    viewModel = vm
  }

  private fun setupObservers() {
    viewModel.hitResponse.observe(this) { result ->
      val response = result

      Log.d("MainActivityResponse", "setupObservers: $response")
    }
  }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
  Text(
    text = "Hello $name!",
    modifier = modifier
  )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
  RickAndMortyTheme {
    Greeting("Android")
  }
}