package com.example.datahandling.DB

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    private  val SQL_CREATE_ENTRIES =
        "CREATE TABLE ${StudentDetails.Student.TABLE_NAME} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "${StudentDetails.Student.COLUMN_NAME_NAME} TEXT," +
                "${StudentDetails.Student.COLUMN_NAME_ID} TEXT," +
                "${StudentDetails.Student.COLUMN_NAME_BIRTH_YEAR} TEXT)"

    private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${StudentDetails.Student.TABLE_NAME}"


     override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int)
    {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }


    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }
    companion object {
        // If you change the database schema, you must increment the database version.
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "MyDB.db"
    }

    fun insertStudent(name: String, studentId: String, birthYear: String):
            Boolean {
        val db = this.writableDatabase

        val contentValue = ContentValues()
        contentValue.put(StudentDetails.Student.COLUMN_NAME_NAME, name)
        contentValue.put(StudentDetails.Student.COLUMN_NAME_ID, studentId)
        contentValue.put(StudentDetails.Student.COLUMN_NAME_BIRTH_YEAR, birthYear)

        db.insert(StudentDetails.Student.TABLE_NAME,null, contentValue)
        return true
    }

    fun viewStudent(name: String): Cursor? {
        val db = this.readableDatabase
        val query = "select * from "+StudentDetails.Student.TABLE_NAME+" where "+
                StudentDetails.Student.COLUMN_NAME_NAME+
                " = '"+ name+ "'"
        val cursor = db.rawQuery(query,null)
        return cursor;
    }


    fun deleteStudent(name:String):Int{
        val db = this.writableDatabase
        return db.delete(StudentDetails.Student.TABLE_NAME,
            StudentDetails.Student.COLUMN_NAME_NAME+ " = ?", arrayOf(name));
    }

    fun updateStudent(name: String, studentId: String,birthYear: String): Boolean
    {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(StudentDetails.Student.COLUMN_NAME_NAME, name)
        contentValues.put(StudentDetails.Student.COLUMN_NAME_ID, studentId)
        contentValues.put(StudentDetails.Student.COLUMN_NAME_BIRTH_YEAR,
            birthYear)
        db.update(StudentDetails.Student.TABLE_NAME, contentValues,
            StudentDetails.Student.COLUMN_NAME_NAME +" = ?", arrayOf(name))
        return true
    }


}
