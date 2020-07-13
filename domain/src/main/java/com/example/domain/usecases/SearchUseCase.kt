package com.example.domain.usecases

import com.example.domain.entities.CustomResult
import com.example.domain.entities.Song
import com.example.domain.repository.SearchRepository
import com.example.domain.repository.SearchRepositoryPreference


open class SearchUseCase(
    private val searchRepository: SearchRepository,
    private val searchRepositoryPreference: SearchRepositoryPreference
) {

    open fun search(song: String): CustomResult<List<Song>> {
        val result = searchRepository.search(song)
        when (result) {
            is CustomResult.OnSuccess -> {
                searchRepositoryPreference.run {
                    saveList(result.data)
                }
            }

        }
        return result
    }

}