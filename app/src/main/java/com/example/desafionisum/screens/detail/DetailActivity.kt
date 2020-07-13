package com.example.desafionisum.screens.detail

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.desafionisum.R
import com.example.domain.entities.Song
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.search_list_item.view.*

class DetailActivity : AppCompatActivity() {
    companion object {
        const val SONG_OBJECT = "song_object"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        updateInfo()
    }

    private fun updateInfo() {
        val data = intent.getStringExtra(SONG_OBJECT)

        if (data == null) {
            finish()
            return
        }
        val song: Song = Gson().fromJson(data, Song::class.java)

        artistName.text = song.artistName
        trackName.text = song.trackName

        if (!song.artworkUrl100.isNullOrEmpty()) {
            Picasso.get().load(song.artworkUrl100)
                .resize(100, 100)
                .centerCrop()
                .into(detail_image)
        }

        if (song.artistViewUrl.isNullOrEmpty()) openiTunes.visibility = View.GONE
        openiTunes.setOnClickListener {
            val intent: Intent = Intent(Intent.ACTION_VIEW, Uri.parse(song.artistViewUrl))
            startActivity(intent)
        }

    }
}
