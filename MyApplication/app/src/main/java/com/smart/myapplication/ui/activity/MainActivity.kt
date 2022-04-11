package com.smart.myapplication.ui.activity

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.smart.myapplication.R
import com.smart.myapplication.ui.adapters.QuotesAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var dataList: ArrayList<String> = ArrayList()
    lateinit var quotesAdapter: QuotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupListView()
        update.setOnClickListener {
            dataList.clear()
            configItems()
            quotesAdapter.notifyDataSetChanged()
            openBottomSheet()
        }
    }

    private fun configItems() {
        for (i in 1..5) {
            dataList.add("Element $i")
        }
    }

    private fun setupListView() {
        configItems()
        quotesAdapter = QuotesAdapter(this@MainActivity, dataList, object :
            QuotesAdapter.ItemClickCallback {
            override fun itemClick(data: String) {
            }
        })

        re_quotes.apply {
            adapter = quotesAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        // callback which defines what should be done when items are swiped
        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            // delete the quote on Swipe
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                dataList.removeAt(position)
                quotesAdapter.notifyDataSetChanged()
            }
        }

        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(re_quotes)
        }
    }


    fun openBottomSheet() {
        val mBottomSheetDialog = BottomSheetDialog(
            this,
            R.style.CustomBottomSheetDialogTheme
        )
        val sheetView = layoutInflater.inflate(
            R.layout.bottom_sheet_open,
            null
        )
        mBottomSheetDialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        mBottomSheetDialog.setContentView(sheetView!!)
        mBottomSheetDialog.update?.setOnClickListener {
            mBottomSheetDialog.dismiss()
        }
        mBottomSheetDialog.show()
    }
}