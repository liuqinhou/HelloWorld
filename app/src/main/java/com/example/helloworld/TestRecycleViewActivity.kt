package com.example.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.MotionEvent
import android.view.MotionEvent.ACTION_DOWN
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import com.example.helloworld.adapter.RecyleSimpleAdapter
import com.example.helloworld.bean.Fruit
import kotlinx.android.synthetic.main.activity_test_recycle_view.*

class TestRecycleViewActivity : AppCompatActivity() {
    var TAG = "TestRecycleViewActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_recycle_view)
        //val layoutManager = LinearLayoutManager(this)
        //val layoutManager = GridLayoutManager(this, 3 //网格布局
        val layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)//网格布局
        recycleview.layoutManager = layoutManager
        var simpleAdapter = RecyleSimpleAdapter(this, getData())
        recycleview.adapter = simpleAdapter

        scrollBtn.setOnClickListener {
            var px1 = 500
            var py1 = 1500
            var px2 = 500
            var py2 = 300
            analogUserScroll(recycleview, 0, px1.toFloat(), py1.toFloat(), px2.toFloat(), py2.toFloat())
            Log.e(TAG, "按钮被点击了")
        }
    }

    fun getData(): List<Fruit> {
        var list = ArrayList<Fruit>()
        repeat(100) {
            list.add(Fruit("苹果sssdddd", R.drawable.apple_pic))
            list.add(Fruit("香蕉ss", R.drawable.banana_pic))
            list.add(Fruit("草莓sssssssssssss", R.drawable.cherry_pic))
            list.add(Fruit("桔子", R.drawable.orange_pic))
            list.add(Fruit("梨", R.drawable.pear_pic))
            list.add(
                Fruit(
                    "西瓜ssssssssssssssssssssssssssssssssssssssscdhbchdbchsbdchsbdcshbchsbchb出版社城市低保曾几何时打包测试东北财经都睡不好茶几上的不错的还是不错的时间不长sss",
                    R.drawable.watermelon_pic
                )
            )
        }
        return list
    }

    fun analogUserScroll(view: View, type: Int, p1x: Float, p1y: Float, p2x: Float, p2y: Float) {
        Log.e(TAG, "正在模拟滑屏操作：p1->" + p1x + "," + p1y + ";p2->" + p2x + "," + p2y);
        if (view == null) {
            return;
        }
        var downTime = SystemClock.uptimeMillis()//模拟按下去的时间
        var eventTime = downTime
        var pX = p1x
        var pY = p1y
        var speed = 0 //快速滑动
        var touchTime = 11600//模拟滑动时发生的触摸事件次数
        //平均每次事件要移动的距离
        var perX = (p2x - p1x) / touchTime
        var perY = (p2y - p1y) / touchTime
        var isReversal: Boolean = perX < 0 || perY < 0//判断是否反向：手指从下往上滑动，或者手指从右往左滑动
        var isHandY = Math.abs(perY) > Math.abs(perX)//判断是左右滑动还是上下滑动
//        if (type == USER_TOUCH_TYPE_1) {//加速滑动
//            touchTime = 10//如果是快速滑动，则发生的触摸事件比均匀滑动更少
//            speed = if (isReversal) -20 else 20//反向移动则坐标每次递减
//        }
        touchTime = 10000//如果是快速滑动，则发生的触摸事件比均匀滑动更少
        speed = if (isReversal) -20 else 20//反向移动则坐标每次递减
        //模拟用户按下
        var downEvent: MotionEvent = MotionEvent.obtain(downTime, downTime + 100, ACTION_DOWN, pX, pY, 0)
        view.onTouchEvent(downEvent)
        //模拟移动过程中的事件
        var moveEvents: MutableList<MotionEvent> = listOf<MotionEvent>().toMutableList()
        var isSkip = false
        for (i in 0..touchTime) {
            pX += (perX + speed)
            pY += (perY + speed)
            if ((isReversal && pX < p2x) || (!isReversal && pX > p2x)) {
                pX = p2x
                isSkip = !isHandY
            }
            if ((isReversal && pY < p2y) || (!isReversal && pY > p2y)) {
                pY = p2y
                isSkip = isHandY
            }
            downTime = eventTime
            eventTime = eventTime + 100//事件发生的时间要不断递增
            var moveEvent: MotionEvent = getMoveEvent(downTime, eventTime, pX, pY)
            moveEvents.add(moveEvent)
            view.onTouchEvent(moveEvent)
            /*if (type == USER_TOUCH_TYPE_1) {//加速滑动
                speed += if (isReversal) -70 else 70
            }*/
            speed += if (isReversal) -70 else 70
            if (isSkip) {
                break
            }
        }
        while(true) {

        }
        print("hello world")
    }

    fun getMoveEvent(downTime: Long, evntTime: Long, x: Float, y: Float): MotionEvent {
        return MotionEvent.obtain(downTime, evntTime, MotionEvent.ACTION_MOVE, x, y, 0)
    }

}