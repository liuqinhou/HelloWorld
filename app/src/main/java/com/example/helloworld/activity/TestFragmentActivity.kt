package com.example.helloworld.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.helloworld.BaseActivity
import com.example.helloworld.R
import com.example.helloworld.fragment.RightFragment
import com.example.helloworld.fragment.ThirdFragment
import kotlinx.android.synthetic.main.activity_test_fragment.*
import kotlinx.android.synthetic.main.fragment_left.*

class TestFragmentActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_fragment) //静态加载fragment
       // setContentView(R.layout.activity_test_fragment2) //动态加载fragment
//        val fragmentRight = RightFragment()
//        replaceFragment(fragmentRight)
//
//        leftBtn.setOnClickListener{
//            val fragmentThird = ThirdFragment()
//            replaceFragment(fragmentThird)
//        }
    }

    fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}