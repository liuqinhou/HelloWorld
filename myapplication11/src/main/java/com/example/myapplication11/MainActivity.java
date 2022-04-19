package com.example.myapplication11;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;


import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.androidlibrary.MakeFriendUtil;
import com.example.androidlibrary.PrintlnUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //PrintlnUtil.Companion.printHelloworld();
        new PrintlnUtil().printAndroid();
        new MakeFriendUtil().makeGirlFriend();

        CardView cardView = new CardView(this);
        cardView.setCardBackgroundColor(Color.BLUE);
        cardView.setLayoutParams(new ViewGroup.LayoutParams(300, 300));
        LinearLayout linearLayout = findViewById(R.id.root);
        linearLayout.addView(cardView);
    }
}