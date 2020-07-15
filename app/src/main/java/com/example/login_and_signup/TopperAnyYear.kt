package com.example.login_and_signup

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.login_and_signup.adapters.TopperAdapter
import com.example.login_and_signup.model.TopperItem
import com.example.login_and_signup.model.TopperModel
import com.example.login_and_signup.utils.ApiStudent
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_any_year.*
import kotlinx.android.synthetic.main.fragment_any_year.view.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TopperAnyYear.newInstance] factory method to
 * create an instance of this fragment.
 */
class TopperAnyYear : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_any_year, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ApiStudent()
            .addRetroFit()
            ?.getAnyYearTopper()
            ?.enqueue(object : Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.i("api", "---TTTT :: GET Throwable EXCEPTION:: " + t.message)
                }

                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful) {
                        val msg = response.body()?.string()
                        Log.i("api", "---TTTT :: GET msg from server :: " + msg)
                        Toast.makeText(context, "Im the msg" +  msg, Toast.LENGTH_SHORT).show()
                        val json = msg
                        val gson = Gson()
                        Log.i("marksssssss", "json -->$json")
                        val type: Type =
                            object : TypeToken<TopperModel>() {}.type
                        val studentTopper = gson.fromJson<TopperModel>(msg, type)
                        Log.i("marksssssss", "ssiizzeeeeee-->$studentTopper")

                        val rv_any_topper_list = getView()!!.findViewById<RecyclerView>(R.id.rv_any_topper_list)
                        val topper_adapter = TopperAdapter()
                        topper_adapter.setDataCustom(studentTopper)
                        if (rv_any_topper_list != null) {
                            rv_any_topper_list.layoutManager = LinearLayoutManager(
                                context, LinearLayoutManager.VERTICAL, false)
                        }
                        if (rv_any_topper_list != null) {
                            rv_any_topper_list.adapter = topper_adapter
                        }

                    }
                }
            })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AnyYear.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TopperAnyYear().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }

    }
}