package com.example.myeventapplication


import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.location.Location
import android.widget.Toast

class DatabaseHandler(val context: Context) :
    SQLiteOpenHelper(context, "journaldb", null, 1) {

    companion object {
        val TABLE_NAME = "Event_table"
        val ID = "id"
        val NAME = "title"
        val DESC = "desc"
        val DATE = "day"
        val LOCATION = "location"
        val PRICE = "price"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createQuery = "CREATE TABLE $TABLE_NAME($ID INTEGER PRIMARY KEY," +
                "$NAME TEXT, $DESC TEXT, $DATE TEXT, $LOCATION TEXT, $PRICE PRICE)"
        db?.execSQL(createQuery)
    }

    fun insertRoutine(title: String, desc: String, day: String, location: String, price: String) {
        val db = writableDatabase
        val values = ContentValues()
        values.put(NAME, title)
        values.put(DESC, desc)
        values.put(DATE, day)
        values.put(LOCATION, location)
        values.put(PRICE, price)
        val id = db.insert(TABLE_NAME, null, values)
        if (id.toInt() == -1) {
            //Error
            Toast.makeText(context, "Error: Data is not inserted", Toast.LENGTH_SHORT).show()
        } else {
            //Success
            Toast.makeText(context, "Data inserted successfully", Toast.LENGTH_SHORT).show()
        }
    }

    fun updateRoutine(id: Int, newTitle: String, newDesc: String, newDay: String,newLocation: String, newPrice: String) {
        val db = writableDatabase
        val values = ContentValues()
        values.put(ID, id)
        values.put(NAME, newTitle)
        values.put(DESC, newDesc)
        values.put(DATE, newDay)
        values.put(LOCATION, newLocation)
        values.put(PRICE, newPrice)
        val affectedRows = db.update(TABLE_NAME, values, "id = $id", null)
        if (affectedRows > 0) {
            //Success
            Toast.makeText(context, "Data updated successfully", Toast.LENGTH_SHORT).show()
        } else {
            //Failure
            Toast.makeText(context, "Error: Data is not updated", Toast.LENGTH_SHORT).show()
        }
    }
    fun getRoutine() : MutableList<RoutineModel>{

        val listRoutines  = mutableListOf<RoutineModel>()
        val db: SQLiteDatabase= readableDatabase
        val query = "select * from $TABLE_NAME"

        val cursor:Cursor = db.rawQuery(query, null)

        if (cursor != null && cursor.count > 0){
            cursor.moveToFirst()
            do {
                val idIndex = cursor.getColumnIndex(ID)
                val titleIndex = cursor.getColumnIndex(NAME)
                val descIndex = cursor.getColumnIndex(DESC)
                val dayIndex = cursor.getColumnIndex(DATE)
                val locationIndex = cursor.getColumnIndex(LOCATION)
                val priceIndex = cursor.getColumnIndex(PRICE)

                val id = cursor.getInt(idIndex)
                val title = cursor.getString(titleIndex)
                val desc = cursor.getString(descIndex)
                val day = cursor.getString(dayIndex)
                val location = cursor.getString(locationIndex)
                val price = cursor.getString(priceIndex)

                val routineModel = RoutineModel(id,title,desc,day,location,price)
                listRoutines.add(routineModel)

            }while (cursor.moveToNext())
            cursor.close()
        }
        return listRoutines
    }

    fun deleteRoutine(id: Int) {
        val db = writableDatabase
        val affectedRows = db.delete(TABLE_NAME, "id = $id", null)
        if (affectedRows > 0) {
            //Success
            Toast.makeText(context, "Data deleted successfully", Toast.LENGTH_SHORT).show()
        } else {
            //Failure
            Toast.makeText(context, "Error: Data is not deleted", Toast.LENGTH_SHORT).show()
        }
    }



    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

}