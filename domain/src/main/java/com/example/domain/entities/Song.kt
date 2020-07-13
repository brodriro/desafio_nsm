package com.example.domain.entities

data class Song(
    var artistId: Int? = null,
    var collectionId: Int? = null,
    var trackId: Int? = null,
    var artistName: String? = null,
    var collectionName: String? = null,
    var trackName: String? = null,
    var collectionArtistName: String? = null,
    var artistViewUrl: String? = null,
    var previewUrl: String? = null,
    var artworkUrl30: String? = null,
    var artworkUrl60: String? = null,
    var artworkUrl100: String? = null
)