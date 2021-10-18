package com.example.myeventapplication


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myeventapplication.Adapter.OnTaskItemClicked
import com.example.myeventapplication.Adapter.RoutineAdapter


import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnTaskItemClicked {

    private var routineList: MutableList<RoutineModel> = mutableListOf()
    lateinit var mAdapter: RoutineAdapter
    lateinit var dbHandler: DatabaseHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        dbHandler = DatabaseHandler(this)
        routineList = dbHandler.getRoutine()

        mAdapter = RoutineAdapter(this, routineList, this)
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = mAdapter



        addBtn.setOnClickListener {
            dbHandler.insertRoutine("Meritate", "Good for mind and skin", "Tue","dhanbad","345")
        }

        updateBtn.setOnClickListener {
            dbHandler.updateRoutine(1, "Drink milk", "Good for children", "Mon","ranchi","3543")
        }

        deleteBtn.setOnClickListener {
            dbHandler.deleteRoutine(2)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onEditClicked(routine: RoutineModel) {

        dbHandler.updateRoutine(routine.id, "Drink milk", "Good for children", "Mon","LONDON","$32")
        routineList = dbHandler.getRoutine()
        routineList.clear()
        routineList.addAll(dbHandler.getRoutine())
        mAdapter.notifyDataSetChanged()
    }

    override fun onDeleteClicked(routine: RoutineModel) {
        dbHandler.deleteRoutine(routine.id)
        routineList.clear()
        routineList.addAll(dbHandler.getRoutine())
        routineList = dbHandler.getRoutine()

    }
}

private fun <E> MutableList<E>.add(element: String) {

}
