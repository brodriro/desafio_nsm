package com.example.data.network.repository


import com.example.data.network.ApiConfig
import com.example.data.network.entities.SearchResponse
import com.example.domain.entities.CustomNotFoundError
import com.example.domain.entities.CustomResult
import com.example.domain.entities.HttpError
import com.example.domain.entities.Song
import com.example.domain.repository.SearchRepository

class SearchRepositoryImp(private val apiConfig: ApiConfig) :
    SearchRepository {

    override fun search(song: String): CustomResult<List<Song>> {

        val callApi = apiConfig.searchSong(song, "song", "20").execute()
        return when (callApi.isSuccessful) {
            true -> {
                val response: SearchResponse? = callApi.body()
                if (response?.results != null)
                    CustomResult.OnSuccess(SearchResponse.run { toSongList(response) })
                else
                    CustomResult.OnError(CustomNotFoundError(message = "Problemas al obtener la informacion"))
            }
            false -> {
                CustomResult.OnError(
                    HttpError(code = callApi.code(), message = callApi.message())
                )
            }
        }
    }

}