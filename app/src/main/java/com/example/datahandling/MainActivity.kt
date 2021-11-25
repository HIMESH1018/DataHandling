package com.example.datahandling

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var btn_register = findViewById(R.id.register) as Button
        var btn_login = findViewById(R.id.login) as Button


        btn_login.setOnClickListener(View.OnClickListener {

            val intent = Intent(this, Login::class.java).apply {
            }
            startActivity(intent)
        })

        btn_register.setOnClickListener(View.OnClickListener {

            val sharedPreferences = getSharedPreferences("Credentials",
                Context.MODE_PRIVATE)
            val isRegistered = sharedPreferences.getBoolean("isRegistered",false)
            if(isRegistered){
                val intent = Intent(this, Login::class.java)
                startActivity(intent)
            }
            else {
                val intent = Intent(this, Register::class.java).apply {
                }
                startActivity(intent)
            }
        })
    }
}