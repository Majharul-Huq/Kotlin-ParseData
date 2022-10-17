package com.ets.pasrsedata

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.ets.pasrsedata.model.Data
import com.ets.pasrsedata.model.ReqRes
import com.facebook.drawee.backends.pipeline.Fresco
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val dataList: MutableList<Data> = mutableListOf()
    private lateinit var myAdapter: UserListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AndroidNetworking.initialize(applicationContext)
        Fresco.initialize(applicationContext)

        myAdapter = UserListAdapter(dataList)
        recycleView.layoutManager = LinearLayoutManager(this)
        recycleView.addItemDecoration(DividerItemDecoration(this,OrientationHelper.VERTICAL))
        recycleView.adapter = myAdapter


        AndroidNetworking.get("https://reqres.in/api/users?page=2")
            .build()
            .getAsObject(ReqRes::class.java, object : ParsedRequestListener<ReqRes> {
                override fun onResponse(res: ReqRes) {
                    dataList.addAll(res.data)
                    myAdapter.notifyDataSetChanged()
                }
                override fun onError(anError: ANError?) {
                    print(anError);
                }
            })
    }
}