package co.mrcomondev.pro.rickandmorty.dataaccess.remote.dtos

import com.squareup.moshi.JsonClass

/**
 * Created by gesoft
 */
@JsonClass(generateAdapter = true)
data class ApiResponse(val results: List<CharacterApi>, val info: PageInfo)

@JsonClass(generateAdapter = true)
data class PageInfo(val count: Int, val pages: Int, val next: String, val prev: String?)