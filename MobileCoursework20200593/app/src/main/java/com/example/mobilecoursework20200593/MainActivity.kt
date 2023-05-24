package com.example.mobilecoursework20200593

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

//video Link
//https://drive.google.com/file/d/11oZV08jUkyipA5iCzajALszkRyw8PmUY/view?usp=share_link

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var buttonClick = findViewById<Button>(R.id.about_button)
        buttonClick.setOnClickListener {
            val intent = Intent(this, AboutPage::class.java)
            startActivity(intent)
        }

        buttonClick = findViewById<Button>(R.id.newgamebutton)
        buttonClick.setOnClickListener {
            val intent = Intent(this, SetTargetScore::class.java)
            startActivity(intent)
        }
    }
}