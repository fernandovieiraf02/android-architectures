package com.picpay.desafio.android.ui.home

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.model.*
import com.picpay.desafio.android.ui.adapter.UserListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewModel by viewModel<HomeViewModel>()

    private val recyclerView by lazy {
        findViewById<RecyclerView>(R.id.recyclerView)
    }

    private val progressBar by lazy {
        findViewById<ProgressBar>(R.id.user_list_progress_bar)
    }

    private val adapter = UserListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupRecyclerView()
        bindUsersListProvider()
        viewModel.fetchUsersList()
    }

    private fun bindUsersListProvider() {
        viewModel
            .listUsersProvider
            .observe(this) { event ->
                onUserListEvent(event)
            }
    }

    private fun onUserListEvent(event: EventData<List<User>>) {
        hideLoading()
        when (event) {
            is SuccessEvent -> updateUserList(event.data)
            is ErrorEvent -> showError()
            is LoadingEvent -> showLoading()
        }
    }

    private fun updateUserList(userList: List<User>?) {
        userList?.let { list -> adapter.users = list }
    }

    private fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    private fun setupRecyclerView() {
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    private fun showError() {
        Toast
            .makeText(this, getString(R.string.error), Toast.LENGTH_SHORT)
            .show()
    }
}
