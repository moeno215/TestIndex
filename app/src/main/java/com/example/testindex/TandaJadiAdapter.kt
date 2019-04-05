package com.example.testindex

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.testindex.model.DataTandaJadi

import java.util.ArrayList

class TandaJadiAdapter(tempData: ArrayList<DataTandaJadi>) :
    RecyclerView.Adapter<TandaJadiAdapter.TandaJadiViewHolder>() {
    private val dataSet: List<DataTandaJadi>?

    init {
        dataSet = tempData
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TandaJadiViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_tj, parent, false)

        return TandaJadiViewHolder(itemView)
    }


    override fun getItemCount(): Int {
        return dataSet?.size ?: 0
    }

    inner class TandaJadiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtTandaJadi: TextView
        var txtCreateBy: TextView

        init {
            txtTandaJadi = itemView.findViewById(R.id.txt_tanda_jadi)
            txtCreateBy = itemView.findViewById(R.id.txt_create_by)

        }
    }

    override fun onBindViewHolder(holder: TandaJadiViewHolder, position: Int) {
        val dataTandaJadi = dataSet!![position]
        holder.txtTandaJadi.text = dataTandaJadi.tandaJadi
        holder.txtCreateBy.text = dataTandaJadi.createBy



    }

}

