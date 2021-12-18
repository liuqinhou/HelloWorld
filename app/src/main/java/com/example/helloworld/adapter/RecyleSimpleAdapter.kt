package com.example.helloworld.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SimpleAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.helloworld.R
import com.example.helloworld.bean.Fruit
import kotlinx.android.synthetic.main.fruititem.view.*

class RecyleSimpleAdapter(val context: Context, val data: List<Fruit>): RecyclerView.Adapter<RecyleSimpleAdapter.MyViewHolder>() {
    lateinit var view : View
    var count :Int = 0
    class MyViewHolder(var view: View): RecyclerView.ViewHolder(view) {
        //public val img = view.i
        val img = view.img
        val name = view.fruitname
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        if (!::view.isInitialized) {

        }
        count++
        Log.e("qinhouliu", "onCreateViewHolder === $count")
        //view = LayoutInflater.from(context).inflate(R.layout.fruititem, parent, false)
        view = LayoutInflater.from(context).inflate(R.layout.fruititem2, parent, false) //专用于recycleview 瀑布布局
        //view = LayoutInflater.from(parent.context).inflate(R.layout.fruititem, parent, false)
        val viewholder = MyViewHolder(view)
        return viewholder
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.e("qinhouliu", "onBindViewHolder === $count")
        holder.img.setImageResource(data[position].resid)
        holder.name.setText(data[position].name)
    }

    override fun getItemCount(): Int {
       return data.size
    }

}
