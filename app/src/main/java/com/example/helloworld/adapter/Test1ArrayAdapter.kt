package com.example.helloworld.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.helloworld.R
import com.example.helloworld.bean.Fruit
import kotlinx.android.synthetic.main.fruititem.view.*

class Test1ArrayAdapter(context: Context, val resouceId:Int, val data: List<Fruit>): ArrayAdapter<Fruit>( context, resouceId, data)  {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        //初级写法
//        var view = LayoutInflater.from(context).inflate(R.layout.fruititem, parent, false)
//        var fruit = getItem(position)
//        view.img.setImageResource(fruit.resid)
//        view.img.setOnClickListener {
//            Toast.makeText(context, "你点击的水果是：${fruit.name}", Toast.LENGTH_SHORT ).show()
//        }
//        view.fruitname.setText(fruit.name)
//        return view

        //高级写法
        var view:View
        var viewHolder: ViewHolder
        var fruit = getItem(position)
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.fruititem, parent, false)
            viewHolder = ViewHolder(view.img, view.fruitname)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        viewHolder.fruitImg.setImageResource(fruit.resid)
        view.img.setOnClickListener {
            Toast.makeText(context, "你点击的水果是：${fruit.name}", Toast.LENGTH_SHORT ).show()
        }
        viewHolder.fruitName.setText(fruit.name)
        return view


//        val view = LayoutInflater.from(context).inflate(resouceId, parent, false)
//        val fruitImage: ImageView = view.findViewById(R.id.img)
//        val fruitName: TextView = view.findViewById(R.id.fruitname)
//        val fruit = getItem(position) // 获取当前项的Fruit实例
//        if (fruit != null) {
//            fruitImage.setImageResource(fruit.resid)
//            fruitName.text = fruit.name
//        }
//        return view
    }

    override fun getItem(position: Int): Fruit {
        return data[position]
    }

    inner class ViewHolder(val fruitImg:ImageView, val fruitName: TextView)

}