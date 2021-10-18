package com.example.myeventapplication.Adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myeventapplication.R
import com.example.myeventapplication.RoutineModel


class RoutineAdapter(
    val context: Context,
    val routineList: MutableList<RoutineModel>,
    val listener: OnTaskItemClicked
) : RecyclerView.Adapter<RoutineAdapter.RoutineViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoutineViewHolder {
        val inflater = LayoutInflater.from(context)
        val view1: View = inflater.inflate(R.layout.item_layout, parent, false)
        return RoutineViewHolder(view1)

    }

    override fun onBindViewHolder(holder: RoutineViewHolder, position: Int) {
        val routine = routineList.get(position)
        holder.title.text = routine.title





        holder.editIv.setOnClickListener {
            listener.onEditClicked(routine)
        }

        holder.deleteIv.setOnClickListener {
            listener.onDeleteClicked(routine)
        }

    }

    override fun getItemCount(): Int {
        return routineList.size
    }


    class RoutineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.titleTv)
        var img: ImageView
        var editIv: ImageView
        var deleteIv: ImageView

//        var editTv: TextView
//        var delete: TextView

        init {
            img = itemView.findViewById(R.id.img)
            title = itemView.findViewById(R.id.titleTv)
            editIv = itemView.findViewById(R.id.editIv)
            deleteIv = itemView.findViewById(R.id.deleteIv)
        }
    }
}