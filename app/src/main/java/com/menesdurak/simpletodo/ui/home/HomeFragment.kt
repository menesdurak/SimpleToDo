package com.menesdurak.simpletodo.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.menesdurak.simpletodo.R
import com.menesdurak.simpletodo.data.local.entity.Priority
import com.menesdurak.simpletodo.data.local.entity.ToDo
import com.menesdurak.simpletodo.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val toDoViewModel by viewModels<ToDoViewModel>()
    private val toDoAdapter by lazy { ToDoAdapter(::onItemClick, ::onItemLongClick) }

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

        observeUiState()

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                searchToDo(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                searchToDo(newText)
                return false
            }

        })

        binding.fabAdd.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToAddToDoFragment()
            findNavController().navigate(action)
        }
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

    private fun onItemClick(toDo: ToDo) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailToDoFragment(toDo)
        findNavController().navigate(action)
    }

    private fun onItemLongClick(position: Int, toDo: ToDo) {
        Snackbar.make(requireView(), "Do you want to delete ${toDo.name}?", Snackbar.LENGTH_SHORT)
            .setAction("Yes") {
                toDoViewModel.deleteToDo(toDo)
                toDoAdapter.removeToDo(position)
            }.show()

    }

    private fun searchToDo(word: String) {
        toDoViewModel.searchToDos(word)
    }
}