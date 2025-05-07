package co.mrcomondev.pro.rickandmorty.domain.repository

import androidx.paging.PagingData
import co.mrcomondev.pro.rickandmorty.domain.models.Episode
import kotlinx.coroutines.flow.Flow

/**
 * Created by gesoft
 */
interface EpisodeRepository {
  fun getEpisodesStream() : Flow<PagingData<Episode>>
}