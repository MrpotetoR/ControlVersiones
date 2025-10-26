package com.example.controlversiones

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editTextTask: EditText
    private lateinit var buttonAddTask: Button
    private lateinit var listViewTasks: ListView
    private lateinit var tasks: ArrayList<String>
    private lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextTask = findViewById(R.id.editTextTask)
        buttonAddTask = findViewById(R.id.buttonAddTask)
        listViewTasks = findViewById(R.id.listViewTasks)

        tasks = ArrayList()
        adapter = TaskAdapter(this, tasks)
        listViewTasks.adapter = adapter

        buttonAddTask.setOnClickListener {
            val task = editTextTask.text.toString()
            if (task.isNotEmpty()) {
                tasks.add(task)
                adapter.notifyDataSetChanged()
                editTextTask.text.clear()
            }
        }
    }

    inner class TaskAdapter(context: Context, private val tasks: ArrayList<String>) : ArrayAdapter<String>(context, 0, tasks) {

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            var itemView = convertView
            if (itemView == null) {
                itemView = LayoutInflater.from(context).inflate(R.layout.list_item_task, parent, false)
            }

            val task = tasks[position]

            val textViewTask = itemView!!.findViewById<TextView>(R.id.textViewTask)
            val buttonDeleteTask = itemView.findViewById<Button>(R.id.buttonDeleteTask)

            textViewTask.text = task

            buttonDeleteTask.setOnClickListener {
                tasks.removeAt(position)
                notifyDataSetChanged()
            }

            return itemView
        }
    }
}

