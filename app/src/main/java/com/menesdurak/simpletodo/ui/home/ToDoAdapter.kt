package com.menesdurak.simpletodo.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.menesdurak.simpletodo.R
import com.menesdurak.simpletodo.data.local.entity.Priority
import com.menesdurak.simpletodo.data.local.entity.ToDo
import com.menesdurak.simpletodo.databinding.ItemTodoBinding

class ToDoAdapter(
    private val onItemClick: (ToDo) -> Unit,
    private val onItemLongClick: (Int, ToDo) -> Unit,
) : RecyclerView.Adapter<ToDoAdapter.ToDoHolder>() {

    private val toDos = mutableListOf<ToDo>()

    inner class ToDoHolder(private val binding: ItemTodoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(toDo: ToDo) {
            with(binding) {
                tvTitle.text = toDo.name
                tvNote.text = toDo.note

                when (toDo.priority) {
                    Priority.LOW -> {
                        linlayMain.setBackgroundColor(
                            root.resources.getColor(
                                R.color.low_priority,
                                null
                            )
                        )
                    }

                    Priority.MEDIUM -> {
                        linlayMain.setBackgroundColor(
                            root.resources.getColor(
                                R.color.medium_priority,
                                null
                            )
                        )
                    }

                    Priority.HIGH -> {
                        linlayMain.setBackgroundColor(
                            root.resources.getColor(
                                R.color.high_priority,
                                null
                            )
                        )
                    }
                }

                root.setOnClickListener {
                    onItemClick.invoke(toDo)
                }

                root.setOnLongClickListener {
                    onItemLongClick.invoke(adapterPosition, toDo)
                    true
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoHolder {
        val bind = ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ToDoHolder(bind)
    }

    override fun getItemCount(): Int = toDos.size

    override fun onBindViewHolder(holder: ToDoHolder, position: Int) {
        holder.bind(toDos[position])
    }

    fun updateToDoList(newList: List<ToDo>) {
        toDos.clear()
        toDos.addAll(newList)
        notifyDataSetChanged()
    }

    fun removeToDo(position: Int) {
        toDos.removeAt(position)
        notifyItemRemoved(position)
    }
}