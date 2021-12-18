package com.example.helloworld.layout

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.Toast
import com.example.helloworld.R
import kotlinx.android.synthetic.main.title.view.*
import java.util.jar.Attributes

class TitleLayout(context: Context, attributesset: AttributeSet?): LinearLayout(context, attributesset){

    init {
        LayoutInflater.from(context).inflate(R.layout.title, this)
        back.setOnClickListener{
            Log.e("qinhouliu", "press title back")
        }

        edit.setOnClickListener{
            Log.e("qinhouliu", "press edit back")
        }
    }


}