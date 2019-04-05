package com.example.testindex

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.testindex.model.DataTandaJadi
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MainActivity : AppCompatActivity() {
    var tempDatas: ArrayList<DataTandaJadi>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getData()


        recyclerTandaJadi.addOnItemTouchListener(
            RecyclerItemClickListener(
                this@MainActivity,
                RecyclerItemClickListener.OnItemClickListener { view, position ->
                    val intent = Intent(this@MainActivity, AddTandaJadi::class.java)
                    intent.putExtra("editmode","1")
                    intent.putExtra("nim", "")
                    intent.putExtra("name","")



//                    intent.putExtra(DetailTandaJadiActivity.ID_TJ, tempDatas!![position].id)
//                    intent.putExtra(DetailTandaJadiActivity.TANDA_JADI, tempDatas!![position].tandaJadi)
//                    intent.putExtra(DetailTandaJadiActivity.CREATE_BY, tempDatas!![position].createBy)

//                    Log.d("CEK ID", "" + tempDatas!![position].tandaJadi)

                    startActivity(intent)
                })
        )



        swipeRefreshLayout.setOnRefreshListener { getData()


        }

        mFloatingActionButton.setOnClickListener {
            startActivity(Intent(this, AddTandaJadi::class.java))
        }

    }

    override fun onResume() {
        super.onResume()
        swipeRefreshLayout.isRefreshing = true
        getData()
        swipeRefreshLayout.setOnRefreshListener { getData() }
    }

    fun getData() {
        API.getDataTandaJadi().enqueue(object : Callback<ArrayList<DataTandaJadi>> {
            override fun onResponse(call: Call<ArrayList<DataTandaJadi>>, response: Response<ArrayList<DataTandaJadi>>) {
                if (response.code() == 200) {
                    tempDatas = response.body()
                    Log.i("Data Index", "" + tempDatas)
                    if (tempDatas == null) {
                        tv_no_data.visibility = View.VISIBLE
                    } else {
                        tv_no_data.visibility = View.GONE
                        recyclerTandaJadi?.setHasFixedSize(true)
                        recyclerTandaJadi?.layoutManager = LinearLayoutManager(this@MainActivity) as RecyclerView.LayoutManager?
                        recyclerTandaJadi?.adapter = TandaJadiAdapter(tempDatas!!)
                    }

                } else {
                    Toast.makeText(this@MainActivity, "Gagal", Toast.LENGTH_LONG).show()
                }
                swipeRefreshLayout.isRefreshing = false

            }

            override fun onFailure(call: Call<ArrayList<DataTandaJadi>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "gagal", Toast.LENGTH_SHORT).show()
                swipeRefreshLayout.isRefreshing = false

            }
        })
    }
}
