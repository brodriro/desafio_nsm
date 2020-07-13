package com.example.desafionisum.screens.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.entities.CustomError
import com.example.domain.entities.CustomResult
import com.example.domain.entities.Song
import com.example.domain.usecases.SearchUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchUseCase: SearchUseCase
) : ViewModel() {

    val searchListLiveData = MutableLiveData<List<Song>>()
    val errorLiveData = MutableLiveData<CustomError>()

    fun search(input: String) {
        val song = input.trim().replace(" ", "+")
        GlobalScope.launch(Dispatchers.IO) {
            when (val list = searchUseCase.search(song)) {
                is CustomResult.OnSuccess -> {
                    searchListLiveData.postValue(list.data)
                }
                is CustomResult.OnError -> {
                    errorLiveData.postValue(list.error)
                }
            }
        }
    }
}