package com.example.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import com.example.helloworld.adapter.RecyleSimpleAdapter
import com.example.helloworld.bean.Fruit
import kotlinx.android.synthetic.main.activity_test_recycle_view.*

class TestRecycleViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_recycle_view)
        //val layoutManager = LinearLayoutManager(this)
        //val layoutManager = GridLayoutManager(this, 3 //网格布局
        val layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL )//网格布局
        recycleview.layoutManager = layoutManager
        var simpleAdapter = RecyleSimpleAdapter(this, getData())
        recycleview.adapter = simpleAdapter
    }

    fun getData(): List<Fruit>{
        var list = ArrayList<Fruit>()
        repeat(100) {
            list.add(Fruit("苹果sssdddd", R.drawable.apple_pic))
            list.add(Fruit("香蕉ss", R.drawable.banana_pic))
            list.add(Fruit("草莓sssssssssssss", R.drawable.cherry_pic))
            list.add(Fruit("桔子", R.drawable.orange_pic))
            list.add(Fruit("梨", R.drawable.pear_pic))
            list.add(Fruit("西瓜ssssssssssssssssssssssssssssssssssssssscdhbchdbchsbdchsbdcshbchsbchb出版社城市低保曾几何时打包测试东北财经都睡不好茶几上的不错的还是不错的时间不长sss", R.drawable.watermelon_pic))
        }
        return list
    }
}