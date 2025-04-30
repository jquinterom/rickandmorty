package co.mrcomondev.pro.rickandmorty.dataaccess.remote.models

/**
 * Created by gesoft
 */
data class ApiResponse(val results: List<CharacterApi>, val info: PageInfo)


data class PageInfo(val count: Int, val pages: Int, val next: String, val prev: String?)