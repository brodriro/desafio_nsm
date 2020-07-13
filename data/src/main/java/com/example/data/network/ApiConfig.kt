package com.example.data.network

import com.example.data.network.entities.SearchResponse
import retrofit2.Call
import retrofit2.http.*


interface ApiConfig {

    @GET("search")
    fun searchSong(@Query("term") title: String, @Query("entity") entity: String, @Query("limit") limit: String): Call<SearchResponse>
}