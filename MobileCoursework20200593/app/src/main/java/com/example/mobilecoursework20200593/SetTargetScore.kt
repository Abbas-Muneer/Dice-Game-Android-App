package com.example.mobilecoursework20200593

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.mobilecoursework20200593.R

class SetTargetScore  : AppCompatActivity() {
    lateinit var playGame: Button
    lateinit var targetScore: EditText
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_targetscore)

        playGame = findViewById(R.id.play);
        targetScore = findViewById(R.id.targetScore);

        playGame.setOnClickListener{
            val intent = Intent(this,NewGame::class.java)
            intent.putExtra("targetScore", Integer.parseInt(targetScore.text.toString()))
            startActivity(intent)
            finish()
        }

    }
}