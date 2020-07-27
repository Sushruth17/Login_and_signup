package com.example.login_and_signup

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.login_and_signup.adapters.TopperAdapter
import com.example.login_and_signup.model.TopperModel
import com.example.login_and_signup.utils.ApiStudent
import com.example.login_and_signup.utils.StringUtils
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type

class Repo {
    lateinit var context: Context


    fun getTopperStudent(year : Int, branch : String, rv_topper_list : RecyclerView, progressBar:ProgressBar, context : Context?){
        val jsonObj = JsonObject()
        jsonObj.addProperty("year", year)
        jsonObj.addProperty("branch", branch)
        progressBar.visibility = View.VISIBLE
        rv_topper_list.visibility = View.GONE
        ApiStudent()
            .addRetroFit()
            ?.getAcedamicTopper(jsonObj)
            ?.enqueue(object : Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    progressBar.visibility = View.GONE
                    rv_topper_list.visibility = View.VISIBLE
                    Log.i("api", "---TTTT :: GET Throwable EXCEPTION:: " + t.message)
                }

                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    progressBar.visibility = View.GONE
                    rv_topper_list.visibility = View.VISIBLE
                    if (response.isSuccessful) {
                        val msg = response.body()?.string()
                        Log.i("api", "---TTTT :: GET msg from server :: " + msg)
                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                        if (msg != "No Data Found"){
                            val json = msg
                            val gson = Gson()
                            Log.i("marksssssss", "json -->$json")
                            val type: Type =
                                object : TypeToken<TopperModel>() {}.type
                            val studentTopper = gson.fromJson<TopperModel>(msg, type)
                            Log.i("marksssssss", "ssiizzeeeeee-->$studentTopper")
//                        val rv_topper_list = getView()?.findViewById<RecyclerView>(rv_topper_list_id)
                            val topper_adapter = TopperAdapter()
                            topper_adapter.setDataCustom(studentTopper)
                            rv_topper_list.setHasFixedSize(true)
                            rv_topper_list.layoutManager = LinearLayoutManager(
                                context, LinearLayoutManager.VERTICAL, false
                            )
                            rv_topper_list.adapter = topper_adapter
                        }
                    }
                }
            })
    }

 /*   fun getYearRange(){
        ApiStudent()
            .addRetroFit()
            ?.yearRange()
            ?.enqueue(object : Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.i("api", "---TTTT :: GET Throwable EXCEPTION:: " + t.message)
                }

                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    if (response.isSuccessful) {
                        val msg = response.body()?.string()
                        Log.i("api", "---TTTT :: GET msg from server :: " + msg)
                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                    }
                }
            })
    }*/
}
