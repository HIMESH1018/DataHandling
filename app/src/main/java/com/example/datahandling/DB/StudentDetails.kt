package com.example.datahandling.DB

import android.provider.BaseColumns

object StudentDetails {

    object Student:BaseColumns{

        const val TABLE_NAME = "students"
        const val COLUMN_NAME_NAME = "name"
        const val COLUMN_NAME_ID = "id"
        const val COLUMN_NAME_BIRTH_YEAR = "bYear"

    }

}