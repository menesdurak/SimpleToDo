package com.menesdurak.simpletodo.ui.detail

import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.menesdurak.simpletodo.R
import com.menesdurak.simpletodo.data.local.entity.Priority
import com.menesdurak.simpletodo.data.local.entity.ToDo
import com.menesdurak.simpletodo.databinding.FragmentDetailToDoBinding
import com.menesdurak.simpletodo.ui.home.ToDoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailToDoFragment : Fragment() {

    private lateinit var binding: FragmentDetailToDoBinding
    private lateinit var toDo: ToDo
    private val toDoViewModel by viewModels<ToDoViewModel>()
    private lateinit var priority: Priority
    private var priorityButtonControl = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentDetailToDoBinding.inflate(inflater, container, false)

        val bundle: DetailToDoFragmentArgs by navArgs()
        toDo = bundle.toDo

        binding.etTitle.setText(toDo.name)
        binding.etNote.setText(toDo.note)

        when (toDo.priority) {
            Priority.LOW -> {
                with(binding) {
                    btnLowPriority.backgroundTintList =
                        resources.getColorStateList(R.color.low_priority, null)
                    btnLowPriority.setTypeface(
                        binding.btnLowPriority.typeface,
                        Typeface.BOLD
                    )
                }
                priority = Priority.LOW
            }

            Priority.MEDIUM -> {
                with(binding) {
                    btnMediumPriority.backgroundTintList =
                        resources.getColorStateList(R.color.medium_priority, null)
                    btnMediumPriority.setTypeface(
                        binding.btnMediumPriority.typeface,
                        Typeface.BOLD
                    )
                }
                priority = Priority.MEDIUM
            }

            Priority.HIGH -> {
                with(binding) {
                    btnHighPriority.backgroundTintList =
                        resources.getColorStateList(R.color.high_priority, null)
                    btnHighPriority.setTypeface(
                        binding.btnHighPriority.typeface,
                        Typeface.BOLD
                    )
                }
                priority = Priority.HIGH
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnGrpPriority.addOnButtonCheckedListener { group, checkedId, isChecked ->
            priorityButtonControl = isChecked
            if (priorityButtonControl) {
                when (view.findViewById<Button>(checkedId).text) {
                    "Low" -> {
                        with(binding) {
                            btnLowPriority.backgroundTintList =
                                resources.getColorStateList(R.color.low_priority, null)
                            btnMediumPriority.backgroundTintList =
                                resources.getColorStateList(R.color.white, null)
                            btnHighPriority.backgroundTintList =
                                resources.getColorStateList(R.color.white, null)
                            btnLowPriority.setTypeface(
                                binding.btnLowPriority.typeface,
                                Typeface.BOLD
                            )
                            btnMediumPriority.typeface = Typeface.DEFAULT
                            btnHighPriority.typeface = Typeface.DEFAULT
                        }
                        priority = Priority.LOW
                    }

                    "Medium" -> {
                        with(binding) {
                            btnLowPriority.backgroundTintList =
                                resources.getColorStateList(R.color.white, null)
                            btnMediumPriority.backgroundTintList =
                                resources.getColorStateList(R.color.medium_priority, null)
                            btnHighPriority.backgroundTintList =
                                resources.getColorStateList(R.color.white, null)
                            priority = Priority.MEDIUM
                            btnLowPriority.typeface = Typeface.DEFAULT
                            btnMediumPriority.setTypeface(
                                binding.btnMediumPriority.typeface,
                                Typeface.BOLD
                            )
                            btnHighPriority.typeface = Typeface.DEFAULT
                        }
                    }

                    "High" -> {
                        with(binding) {
                            btnLowPriority.backgroundTintList =
                                resources.getColorStateList(R.color.white, null)
                            btnMediumPriority.backgroundTintList =
                                resources.getColorStateList(R.color.white, null)
                            btnHighPriority.backgroundTintList =
                                resources.getColorStateList(R.color.high_priority, null)
                            btnLowPriority.typeface = Typeface.DEFAULT
                            btnMediumPriority.typeface = Typeface.DEFAULT
                            btnHighPriority.setTypeface(
                                binding.btnHighPriority.typeface,
                                Typeface.BOLD
                            )
                            priority = Priority.HIGH
                        }
                    }
                }
            }
        }

        binding.btnUpdate.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val note = binding.etNote.text.toString()
            if (title.isNotBlank() && note.isNotBlank()) {
                val updatedToDo = ToDo(id = toDo.id, name = title, note = note, priority = priority)
                toDoViewModel.updateToDo(updatedToDo)
                findNavController().popBackStack()
            } else {
                Toast.makeText(requireContext(), "Please write title and note.", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

}