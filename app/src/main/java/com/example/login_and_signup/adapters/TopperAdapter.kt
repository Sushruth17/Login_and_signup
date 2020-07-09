package com.example.login_and_signup.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.example.login_and_signup.R
import com.example.login_and_signup.model.*
import kotlinx.android.synthetic.main.academic_topper_unit.view.*
import kotlinx.android.synthetic.main.fragment_any_year.view.*
import kotlinx.android.synthetic.main.student_info_unit.view.*
import kotlinx.android.synthetic.main.student_marks_unit.view.*

class TopperAdapter: RecyclerView.Adapter<TopperAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameType = itemView.topper_name
        val totalType = itemView.topper_total
        val yearType = itemView.topper_ac_year
    }


    lateinit var data: TopperModel
    fun setDataCustom(data: TopperModel) {
        this.data = data
        Log.i("data", "--setDataCustom-->"+data)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.i("adapter", "inside topper adapter on create viewholder-->" )
        return TopperAdapter.ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.academic_topper_unit, parent, false)
        )
    }


    override fun getItemCount(): Int {
        Log.i("itemcount", "ssiizzeeeeee-->" + (data.infoTopper?.size ?: 8766))
        return if (data.infoTopper != null)
            data.infoTopper!!.size
        else
            0;
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val unitData: TopperItem? = data.infoTopper?.get(position)
        if (unitData != null) {
            holder.nameType?.text = unitData.name
            holder.totalType?.text = unitData.total
            holder.yearType?.text = unitData.year.toString()

        }

    }
}