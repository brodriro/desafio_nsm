package com.example.domain.repository

import com.example.domain.entities.Song


interface SearchRepositoryPreference {
    fun saveList(songList: List<Song>)
    fun getList(): List<Song>
}