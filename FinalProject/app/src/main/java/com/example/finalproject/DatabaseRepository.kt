package com.example.finalproject

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.core.Context
import java.lang.IllegalStateException
import java.util.*

private const val DATABASE_NAME = "finalproject"

class DatabaseRepository private constructor(context: Context){
    companion object {
        private var instance: DatabaseRepository? = null

        fun initialize(context: Context){
            if (instance == null)
                instance = DatabaseRepository(context)
        }

        fun get(): DatabaseRepository{
            return instance ?:
            throw IllegalStateException("DatabaseRepository must be initialized")
        }
    }

}

