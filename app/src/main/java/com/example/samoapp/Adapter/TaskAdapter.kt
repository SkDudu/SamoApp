package com.example.samoapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.samoapp.Entity.Task
import com.example.samoapp.R

class TaskAdapter(val tasks: List<Task>): RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    class TaskViewHolder(view: View): RecyclerView.ViewHolder(view){

        val mTitle: TextView = view.findViewById(R.id.textView_itemtask_title)
        val mValue: TextView = view.findViewById(R.id.textView_itemtask_value)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_item_task, parent, false)
        return TaskViewHolder(view)

    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.mTitle.text = tasks[position].title
        holder.mValue.text = tasks[position].value
    }

    override fun getItemCount(): Int {
        return tasks.size
    }
}