package com.ets.pasrsedata

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ets.pasrsedata.model.Data
import kotlinx.android.synthetic.main.user_item_view.view.*

class UserListAdapter(private val dataList: MutableList<Data>) : RecyclerView.Adapter<UserHolder>() {

    private lateinit var context : Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        context = parent.context
        return UserHolder(LayoutInflater.from(context).inflate(R.layout.user_item_view,parent,false))
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        val data = dataList[position];

        val userPhoto = holder.itemView.imgPhoto
        userPhoto.setImageURI(data.avatar)

        val userNameTextView = holder.itemView.txtName
        val fullName = "${data.firstName} ${data.lastName}"
        userNameTextView.text = fullName

        val userEmailTextView = holder.itemView.txtEmail
        userEmailTextView.text = data.email
    }

    override fun getItemCount(): Int = dataList.size


}