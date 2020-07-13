package com.example.desafionisum.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.example.desafionisum.R
import com.example.domain.entities.Song
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.search_list_item.view.*

class SearchAdapter(
    private var data: ArrayList<Song>,
    private val onAdapterListener: SearchHolder.OnAdapterListener
) : RecyclerView.Adapter<SearchAdapter.SearchHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHolder {
        val inflatedView = parent.inflate(R.layout.search_list_item, false)
        return SearchHolder(
            inflatedView
        )
    }

    fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
    }

    fun updateList(list: List<Song>) {
        this.data = list as ArrayList<Song>
        this.notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: SearchHolder, position: Int) {
        val row: Song = this.data[position]
        holder.itemView.item_detail_artistName.text = row.artistName
        holder.itemView.item_detail_collectionName.text = row.collectionName
        holder.itemView.item_detail_trackName.text = row.trackName

        if (!row.artworkUrl100.isNullOrEmpty()) {
            Picasso.get()
                .load(row.artworkUrl100)
                .resize(100, 100)
                .centerCrop()
                .into(holder.itemView.item_detail_image)
        }

        holder.itemView.setOnClickListener {
            onAdapterListener.onItemClickListener(
                row,
                holder.itemView
            )
        }
    }


    override fun getItemCount(): Int {
        return data.size
    }

    class SearchHolder(v: View) : RecyclerView.ViewHolder(v) {

        interface OnAdapterListener {
            fun onItemClickListener(item: Song, view: View)
        }

    }
}