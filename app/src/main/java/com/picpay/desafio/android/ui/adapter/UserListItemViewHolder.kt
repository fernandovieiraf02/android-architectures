package com.picpay.desafio.android.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.model.User
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_user.view.*

class UserListItemViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    fun bind(user: User) {
        setNameAndUsername(user)
        showLoading()
        loadAvatarImage(user)
    }

    private fun setNameAndUsername(user: User) {
        itemView.name.text = user.name ?: itemView.context.getString(R.string.unknow)
        itemView.username.text = user.username
    }

    private fun loadAvatarImage(user: User) {
        Picasso.get()
            .load(user.img)
            .error(R.drawable.ic_round_account_circle)
            .into(itemView.picture, object : Callback {
                override fun onSuccess() {
                    hideLoading()
                }

                override fun onError(e: Exception?) {
                    hideLoading()
                }
            })
    }

    private fun showLoading() {
        itemView.progressBar.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        itemView.progressBar.visibility = View.GONE
    }
}