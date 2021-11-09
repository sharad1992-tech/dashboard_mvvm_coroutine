package com.sharad.flexicodeassignment

import android.app.Dialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.sharad.flexicodeassignment.adapter.TrendingAdapter
import com.sharad.flexicodeassignment.model.Item
import com.sharad.flexicodeassignment.repo.MainRepository
import com.sharad.flexicodeassignment.ui.ProgressDialogg
import com.wegolook.itworks.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity(), TrendingAdapter.OnItemClickListener {

    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapterAdapter: TrendingAdapter
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var retryButton: Button
    private lateinit var errorLayout: RelativeLayout
    private lateinit var dialog: Dialog
    private var dataList = mutableListOf<Item>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)
        swipeRefreshLayout = findViewById(R.id.swipe)
        retryButton = findViewById(R.id.btn_retry)
        errorLayout = findViewById(R.id.rl_error_layout)
        dialog = ProgressDialogg.progressDialog(this)

        val retrofitService = RetrofitService.getInstance()
        viewModel = ViewModelProvider(this, ViewModelFactory(MainRepository(retrofitService))).get(MainViewModel::class.java)
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.getCategoryItemsList()
        }
        retryButton.setOnClickListener {
            dialog.show()
            viewModel.getCategoryItemsList()
        }
        //call Api for fetching data
        dialog.show()
        viewModel.getCategoryItemsList()

        viewModel.dataList.observe(this, Observer {
            dataList = it.items as MutableList<Item>
            initRecyclerview()
            swipeRefreshLayout.isRefreshing = false
            errorLayout.visibility = View.GONE
            dialog.dismiss()
        })

        viewModel.errorMessage.observe(this, Observer {
            dialog.dismiss()
            errorLayout.visibility = View.VISIBLE
        })
    }

    override fun onPause() {
        super.onPause()
        dialog.dismiss()
    }

    private fun initRecyclerview() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapterAdapter = TrendingAdapter(
                this@MainActivity, dataList as ArrayList<Item>, this@MainActivity
            )
            adapter = adapterAdapter
        }
    }

    override fun showDetails(description: String) {
        TODO("Not yet implemented")
    }


}