package com.picpay.desafio.android.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.domain.UserListDiffCallback
import com.picpay.desafio.android.model.User

class UserListAdapter : RecyclerView.Adapter<UserListItemViewHolder>() {

    var users = emptyList<User>()
        set(value) {
            updateList(field, value)
            field = value
        }

    private fun updateList(
        oldList: List<User>,
        newList: List<User>
    ) {
        val result = DiffUtil.calculateDiff(
            UserListDiffCallback(
                oldList,
                newList
            )
        )
        result.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_user, parent, false)

        return UserListItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserListItemViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int = users.size
}