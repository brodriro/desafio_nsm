package com.example.data.network.entities

import com.example.domain.entities.Song
import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @field:SerializedName("resultCount")
    val resultCount: Int? = null,

    @field:SerializedName("results")
    var results: List<SongResponse>? = null
) {
    companion object {
        fun toSongList(response: SearchResponse): List<Song> {
            val mList = ArrayList<Song>()
            if (response.results == null)
                return ArrayList()
            for ( row in response.results!!) mList.add(row.toSong())
            return mList
        }
    }
}

data class SongResponse(
    @field:SerializedName("artistId")
    var artistId: Int? = null,

    @field:SerializedName("collectionId")
    var collectionId: Int? = null,

    @field:SerializedName("trackId")
    var trackId: Int? = null,

    @field:SerializedName("artistName")
    var artistName: String? = null,

    @field:SerializedName("collectionName")
    var collectionName: String? = null,

    @field:SerializedName("trackName")
    var trackName: String? = null,

    @field:SerializedName("collectionArtistName")
    var collectionArtistName: String? = null,

    @field:SerializedName("artistViewUrl")
    var artistViewUrl: String? = null,

    @field:SerializedName("previewUrl")
    var previewUrl: String? = null,

    @field:SerializedName("artworkUrl30")
    var artworkUrl30: String? = null,

    @field:SerializedName("artworkUrl60")
    var artworkUrl60: String? = null,

    @field:SerializedName("artworkUrl100")
    var artworkUrl100: String? = null

) {
    fun toSong() = Song(
        artistId,
        collectionId,
        trackId,
        artistName,
        collectionName,
        trackName,
        collectionArtistName,
        artistViewUrl,
        previewUrl,
        artworkUrl30,
        artworkUrl60,
        artworkUrl100
    )
}
