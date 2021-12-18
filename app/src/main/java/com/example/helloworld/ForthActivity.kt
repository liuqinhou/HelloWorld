package com.example.helloworld

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.helloworld.adapter.Test1ArrayAdapter
import com.example.helloworld.bean.Fruit
import kotlinx.android.synthetic.main.activity_forth.*

class ForthActivity : BaseActivity() {
    var adapter:ArrayAdapter<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forth)

//        //var array = listOf("11", "22", "33","44", "55", "66","77", "88", "99")
//        //var map = mapOf("11" to "pingguo", "33" to "xiangjiao", "55" to "juzi","77" to "dd")
//        //adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, array)
//        //adapter = ArrayAdapter(this, android.R.layout.simple_list_item_2, android.R.id.text1, array)
//        //adapter = ArrayAdapter(this, android.R.layout.select_dialog_singlechoice,  array)
//        //adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line,  array)
//        adapter = ArrayAdapter(this, android.R.layout.preference_category, android.R.id.title,  array)
//        list.adapter = adapter

        var date = getFruits()
        val adapter = Test1ArrayAdapter(this, R.layout.fruititem, date)
        list.adapter = adapter
        list.setOnItemClickListener { _, _, position: Int,_ ->
            Toast.makeText(this, "你点击的item是：${date[position].name}", Toast.LENGTH_SHORT ).show()
        }
    }

    private fun getFruits(): List<Fruit> {
        var list = ArrayList<Fruit>()
        repeat(128) {
            list.add(Fruit("苹果", R.drawable.apple_pic))
            list.add(Fruit("香蕉", R.drawable.banana_pic))
            list.add(Fruit("草莓", R.drawable.cherry_pic))
            list.add(Fruit("桔子", R.drawable.orange_pic))
            list.add(Fruit("梨", R.drawable.pear_pic))
            list.add(Fruit("西瓜", R.drawable.watermelon_pic))

        }
        return list
    }

    companion object {

        fun actionStart(context: Context, data1: String, data2: String) {
            val intent = Intent(context, ForthActivity::class.java)
            intent.putExtra("param1", data1)
            intent.putExtra("param2", data2)
            context.startActivity(intent)
        }
    }




}

fun main() {

}