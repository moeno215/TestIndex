package com.example.testindex

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_add_tanda_jadi.*
import org.json.JSONObject

class AddTandaJadi : AppCompatActivity() {

    lateinit var i: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_tanda_jadi)
        i = intent

        if (i.hasExtra("editmode")) {

            if (i.getStringExtra("editmode").equals("1")) {

                onEditMode()


            }

        }



        btnCreate.setOnClickListener {
            create()
        }

      btnUpdate.setOnClickListener {
            update()
       }
        btnDelete.setOnClickListener {
        AlertDialog.Builder(this)
            .setTitle("Konfirmasi")
            .setMessage("Hapus data ini ?")
              .setPositiveButton("HAPUS", DialogInterface.OnClickListener { dialogInterface, i ->
                    delete()
                })
                .setNegativeButton("BATAL", DialogInterface.OnClickListener { dialogInterface, i ->
                    dialogInterface.dismiss()
                })
                .show()
        }
    }


    private fun onEditMode() {

        txt_tanda_jadi.setText(i.getStringExtra("tanda_jadi"))
        txt_create_by.setText(i.getStringExtra("create_by"))
//        txt_create_by.isEnabled = false

        btnCreate.visibility = View.GONE
        btnUpdate.visibility = View.VISIBLE
        btnDelete.visibility = View.VISIBLE

//        gender = i.getStringExtra("gender")
//
//        if(gender.equals("Pria")){
//            rgGender.check(R.id.radioBoy)
//        }else{
//            rgGender.check(R.id.radioGirl)
//        }

    }

    private fun create() {

        val loading = ProgressDialog(this)
        loading.setMessage("Menambahkan data...")
        loading.show()

        AndroidNetworking.post(ApiEndPoint.CREATE)
            .addBodyParameter("tanda_jadi", txt_tanda_jadi.text.toString())
            .addBodyParameter("create_by", txt_create_by.text.toString())
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {

                override fun onResponse(response: JSONObject?) {

                    loading.dismiss()
                    Toast.makeText(applicationContext, response?.getString("message"), Toast.LENGTH_SHORT).show()

                    if (response?.getString("message")?.contains("successfully")!!) {
                        this@AddTandaJadi.finish()
                    }

                }

                override fun onError(anError: ANError?) {
                    loading.dismiss()
                    Log.d("ONERROR", anError?.errorDetail?.toString())
                    Toast.makeText(applicationContext, "Connection Failure", Toast.LENGTH_SHORT).show()
                }


            })
    }

    private fun update() {

        val loading = ProgressDialog(this)
        loading.setMessage("Mengubah data...")
        loading.show()

        AndroidNetworking.post(ApiEndPoint.UPDATE)
            .addBodyParameter("tanda_jadi", txt_tanda_jadi.text.toString())
            .addBodyParameter("create_by", txt_create_by.text.toString())
//            .addBodyParameter("address",txAddress.text.toString())
//            .addBodyParameter("gender",gender)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {

                override fun onResponse(response: JSONObject?) {

                    loading.dismiss()
                    Toast.makeText(applicationContext, response?.getString("message"), Toast.LENGTH_SHORT).show()

                    if (response?.getString("message")?.contains("successfully")!!) {
                        this@AddTandaJadi.finish()
                    }

                }

                override fun onError(anError: ANError?) {
                    loading.dismiss()
                    Log.d("ONERROR", anError?.errorDetail?.toString())
                    Toast.makeText(applicationContext, "Connection Failure", Toast.LENGTH_SHORT).show()
                }


            })

    }

    private fun delete(){

        val loading = ProgressDialog(this)
        loading.setMessage("Menghapus data...")
        loading.show()

        AndroidNetworking.get(ApiEndPoint.DELETE+"tanda_jadi="+txt_create_by.text.toString())
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {

                override fun onResponse(response: JSONObject?) {

                    loading.dismiss()
                    Toast.makeText(applicationContext,response?.getString("message"),Toast.LENGTH_SHORT).show()

                    if(response?.getString("message")?.contains("successfully")!!){
                        this@AddTandaJadi.finish()
                    }

                }

                override fun onError(anError: ANError?) {
                    loading.dismiss()
                    Log.d("ONERROR",anError?.errorDetail?.toString())
                    Toast.makeText(applicationContext,"Connection Failure", Toast.LENGTH_SHORT).show()                    }


            })

    }



}
