package com.menesdurak.simpletodo.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.menesdurak.simpletodo.R
import com.menesdurak.simpletodo.data.local.entity.Priority
import com.menesdurak.simpletodo.data.local.entity.ToDo
import com.menesdurak.simpletodo.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val toDoViewModel by viewModels<ToDoViewModel>()
    private val toDoAdapter by lazy { ToDoAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        with(binding.recyclerView) {
            adapter = toDoAdapter
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val toDo = ToDo(name = "Musluk", note = "Damlayan musluğu tamir ettir.", priority = Priority.HIGH)
//        toDoViewModel.insertToDo(toDo)
//        val toDo2 = ToDo(id = 1, name = "Araba", note = "Benzin Al")
//        toDoViewModel.deleteToDo(toDo2)
        observeUiState()
    }

    private fun observeUiState() {
        toDoViewModel.getAllToDos()
        toDoViewModel.homeUiState.observe(viewLifecycleOwner) {
            when (it) {
                is HomeUiState.Error -> {
                    Toast.makeText(requireContext(), it.exception.toString(), Toast.LENGTH_SHORT)
                        .show()
                    Log.e("HomeUiState", "Error")
                }

                HomeUiState.Loading -> {
                    Log.e("HomeUiState", "Loading")
                }

                is HomeUiState.Success -> {
                    Log.e("HomeUiState", "Success")
                    toDoAdapter.updateToDoList(it.data)
                }
            }
        }
    }
}