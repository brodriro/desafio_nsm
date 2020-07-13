package com.example.data.preference

import android.util.Log
import com.example.data.preference.manager.PreferencesManager
import com.example.domain.entities.Song
import com.example.domain.repository.SearchRepositoryPreference
import com.google.gson.Gson

class UserPreference(private val sharedPreferenceManager: PreferencesManager, private val mGson: Gson) :
    SearchRepositoryPreference {

    companion object {
        const val SONG_LIST = "SONG_LIST"
    }
    override fun saveList(songList: List<Song>) {
        Log.e("LIST=>",mGson.toJson(songList))
        sharedPreferenceManager.setValue(SONG_LIST,  mGson.toJson(songList) )
    }

    override fun getList(): List<Song> {
        val list = sharedPreferenceManager.getString(SONG_LIST)
        return mGson.fromJson(list, Array<Song>::class.java).asList()
    }


}