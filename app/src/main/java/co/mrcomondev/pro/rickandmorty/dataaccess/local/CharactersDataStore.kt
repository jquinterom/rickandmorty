package co.mrcomondev.pro.rickandmorty.dataaccess.local

/**
 * Created by gesoft
 */
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "characters")

class CharactersDataStore(private val dataStore: DataStore<Preferences>) {

  companion object {
    val FAVORITE_CHARACTERS_KEY = stringSetPreferencesKey("favorite_characters")
  }

  suspend fun updateFavoriteCharacters(characterIds: Set<String>) {
    dataStore.edit { preferences ->
      preferences[FAVORITE_CHARACTERS_KEY] = characterIds
    }
  }

  val favoriteCharactersFlow: Flow<Set<String>> = dataStore.data
    .map { preferences ->
      preferences[FAVORITE_CHARACTERS_KEY] ?: emptySet()
    }
}