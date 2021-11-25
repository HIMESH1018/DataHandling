package com.example.datahandling

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.pass)
        val login = findViewById<Button>(R.id.login)

        login.setOnClickListener(View.OnClickListener {
            val name = username.text.toString()
            val pass = password.text.toString()

            if(username.text.isEmpty() || password.text.isEmpty()){

                Toast.makeText(this,"Please Fill all",Toast.LENGTH_SHORT).show();
            }
            else{
                val sharedPreferences = getSharedPreferences("Credentials", Context.MODE_PRIVATE)
                val sname = sharedPreferences.getString("user","")
                val spassword = sharedPreferences.getString("password","")

                if (name == sname){
                    if(pass == spassword){
                        Toast.makeText(this,"Login Success", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, SaveDataActivity::class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this,"Wrong password",
                            Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(this,"Wrong username",
                        Toast.LENGTH_SHORT).show()
                }
            }

        })
    }
}