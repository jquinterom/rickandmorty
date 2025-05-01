package co.mrcomondev.pro.rickandmorty.dataaccess.dtos

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by gesoft
 */
@JsonClass(generateAdapter = true)
data class CharacterDto(@Json(name = "id") val id: Int, @Json(name = "name") val name: String)