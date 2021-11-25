package com.example.datahandling

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.datahandling.DB.DBHelper
import com.example.datahandling.DB.StudentDetails

class SaveDataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_data)


        val username = findViewById<EditText>(R.id.username)
        val sid = findViewById<EditText>(R.id.pass)
        val birthyear = findViewById<EditText>(R.id.repass)

        val insert = findViewById<Button>(R.id.Insert)
        val view = findViewById<Button>(R.id.View)
        val update = findViewById<Button>(R.id.update)
        val delete = findViewById<Button>(R.id.delete)
        val dbHelper = DBHelper(this)

        val name = username.text.toString()
        val id = sid.text.toString()
        val year = birthyear.text.toString()

        insert.setOnClickListener(View.OnClickListener {

            if(name.isEmpty()||id.isEmpty()||year.isEmpty()){
                Toast.makeText(this,"Please Enter Data", Toast.LENGTH_LONG).show()
            }else{
                if(dbHelper.insertStudent(name,id,year)){
                    Toast.makeText(this,"Data inserted Successfully",
                        Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(this,"Something went Wrong :(",
                        Toast.LENGTH_LONG).show()
                }
            }

        })

        view.setOnClickListener(View.OnClickListener {
        })

    }


}