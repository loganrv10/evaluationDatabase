package com.example.myeventapplication.Adapter

import com.example.myeventapplication.RoutineModel

interface OnTaskItemClicked {
    fun onEditClicked(routine: RoutineModel)

    fun onDeleteClicked(routine: RoutineModel)
}