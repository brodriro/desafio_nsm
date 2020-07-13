package com.example.domain.repository

import com.example.domain.entities.CustomResult
import com.example.domain.entities.Song


interface SearchRepository {
    fun search(song : String): CustomResult<List<Song>>
}