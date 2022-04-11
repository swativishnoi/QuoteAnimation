package com.smart.myapplication.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smart.myapplication.R
import kotlinx.android.synthetic.main.quote_item.view.*

class QuotesAdapter(
    var context: Context,
    private var dataList: ArrayList<String>,
    var itemClick: ItemClickCallback
) :
    RecyclerView.Adapter<QuotesAdapter.ViewHolder>() {

    interface ItemClickCallback {
        fun itemClick(data: String)
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): QuotesAdapter.ViewHolder {
        val view: View = LayoutInflater.from(context)
            .inflate(R.layout.quote_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.rvAuthorTvnew.text = "ItemNo $position"
        holder.itemView.setOnClickListener { itemClick.itemClick(dataList[position]) }
    }

    override fun getItemCount() = dataList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
