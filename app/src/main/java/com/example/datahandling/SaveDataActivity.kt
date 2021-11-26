package com.example.datahandling

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.datahandling.DB.DBHelper
import com.example.datahandling.DB.StudentDetails

class SaveDataActivity : AppCompatActivity() {
    @SuppressLint("Range")
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

        //insert student details
        insert.setOnClickListener(View.OnClickListener {
            val name = username.text.toString()
            val id = sid.text.toString()
            val year = birthyear.text.toString()

            if(name.isEmpty()||id.isEmpty()||year.isEmpty()){
                Toast.makeText(this,"Please Enter Data", Toast.LENGTH_LONG).show()
            }else{
                if(dbHelper.insertStudent(name,id,year)){
                    username.setText("")
                    sid.setText("")
                    birthyear.setText("")
                    Toast.makeText(this,"Data inserted Successfully",
                        Toast.LENGTH_LONG).show()

                }else{
                    Toast.makeText(this,"Something went Wrong :(",
                        Toast.LENGTH_LONG).show()
                }
            }

        })
        //view data using username
        view.setOnClickListener(View.OnClickListener {

            val name = username.text.toString()

            if (name.isEmpty()){
                Toast.makeText(this,"Enter the name", Toast.LENGTH_LONG).show()
            }else {
                try {
                    val data = dbHelper.viewStudent(name)
                    if (data != null && data.moveToFirst()) {
                        val ids = data.getString(data.getColumnIndex(StudentDetails.Student.COLUMN_NAME_ID))
                        val birthYears = data.getString(data.getColumnIndex(StudentDetails.Student.COLUMN_NAME_BIRTH_YEAR))
                        sid.setText(ids)
                        birthyear.setText(birthYears)
                    } else {
                        Toast.makeText(this, "No Data Found", Toast.LENGTH_LONG).show()
                    }
                } catch (e: Exception) {
                    print(e)
                    Toast.makeText(this, "No Data Found", Toast.LENGTH_LONG).show()
                }
            }
        })

        //delete data using username
        delete.setOnClickListener(View.OnClickListener {

            val name = username.text.toString()
            try{
                if(dbHelper.deleteStudent(name)>0){
                    Toast.makeText(this,name + " deleted Successfully", Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(this,name + " not found",Toast.LENGTH_LONG).show()
                }
            }catch (e: Exception) {
                print(e)
                Toast.makeText(this, "No Data Found", Toast.LENGTH_LONG).show()
            }
        })

        //update method
        update.setOnClickListener(View.OnClickListener {

            val name = username.text.toString()
            val id = sid.text.toString()
            val year = birthyear.text.toString()
            if(name.isEmpty()||id.isEmpty()||year.isEmpty()){
                Toast.makeText(this,"Please enter data", Toast.LENGTH_LONG).show()
            }else{
                try {
                    if(dbHelper.updateStudent(name,id,year)){
                        Toast.makeText(this,"Data updated Successfully", Toast.LENGTH_LONG).show()
                    }
                }catch (e:Exception){
                    print(e)
                    Toast.makeText(this,"Something Went Wrong :(", Toast.LENGTH_LONG).show()
                }
            }

        })

    }


}