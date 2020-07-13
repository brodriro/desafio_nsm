package com.example.desafionisum.screens.search

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desafionisum.R
import com.example.desafionisum.adapter.SearchAdapter
import com.example.desafionisum.screens.detail.DetailActivity
import com.example.domain.entities.Song
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchActivity : AppCompatActivity(), SearchAdapter.SearchHolder.OnAdapterListener {

    private val searchViewModel : SearchViewModel by viewModel(clazz = SearchViewModel::class)

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: SearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initObservers()

        adapter = SearchAdapter(ArrayList(), this)
        linearLayoutManager = LinearLayoutManager(this)

        searchRecycler.layoutManager =  linearLayoutManager
        searchRecycler.adapter = adapter

        btn_search.setOnClickListener {
            val query : String = search_input.text.toString()
            if(query.isNotEmpty()) searchViewModel.search(query)
        }
    }

    private fun initObservers() {
        searchViewModel.searchListLiveData.observe(this, Observer {
            adapter.updateList(it)
            Log.e("LIST =>", it.size.toString())
        })
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onItemClickListener(item: Song, view : View) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.SONG_OBJECT, Gson().toJson(item))

        val root = view.findViewById<View>(R.id.item_detail_image)

        val options = ActivityOptions
            .makeSceneTransitionAnimation(this, root, resources.getString(R.string.transition_image))
        startActivity(intent, options.toBundle())
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if(currentFocus != null) {
            val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }

        return super.dispatchTouchEvent(ev)
    }
}
