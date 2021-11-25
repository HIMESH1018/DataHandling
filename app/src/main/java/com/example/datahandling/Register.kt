package com.example.datahandling

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.pass)
        val repssword = findViewById<EditText>(R.id.repass)
        val register = findViewById<Button>(R.id.register)

        register.setOnClickListener(View.OnClickListener {

            val name = username.text.toString()
            val pass = password.text.toString()
            val repass = repssword.text.toString()


            if(name.isEmpty() || pass.isEmpty()){

                Toast.makeText(this,"Please Fill all the fields", Toast.LENGTH_SHORT).show();
            }
            else if(!(pass.equals(repass))){
                Toast.makeText(this,"Password not matching",Toast.LENGTH_SHORT).show();
            }
            else{
                //save to shared
                val sharedPreferences = getSharedPreferences("Credentials", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString("user", name)
                editor.putString("password", pass)
                editor.putBoolean("isRegistered",true)
                editor.commit()
                val intent = Intent(this, Login::class.java)
                startActivity(intent)
                Toast.makeText(this,"Register success", Toast.LENGTH_SHORT).show()
            }

        })
    }
}