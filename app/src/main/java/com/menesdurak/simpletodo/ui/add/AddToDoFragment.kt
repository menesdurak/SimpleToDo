package com.menesdurak.simpletodo.ui.add

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
import com.menesdurak.simpletodo.R
import com.menesdurak.simpletodo.data.local.entity.Priority
import com.menesdurak.simpletodo.data.local.entity.ToDo
import com.menesdurak.simpletodo.databinding.FragmentAddToDoBinding
import com.menesdurak.simpletodo.ui.home.ToDoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddToDoFragment : Fragment() {

    private lateinit var binding: FragmentAddToDoBinding
    private val toDoViewModel by viewModels<ToDoViewModel>()
    private var priority = Priority.LOW
    private var priorityButtonControl = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentAddToDoBinding.inflate(inflater, container, false)

        with(binding) {
            btnGrpPriority.check(R.id.btnLowPriority)
            btnLowPriority.backgroundTintList =
                resources.getColorStateList(R.color.low_priority, null)
            btnLowPriority.setTypeface(
                binding.btnLowPriority.typeface,
                Typeface.BOLD
            )
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

        binding.btnAdd.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val note = binding.etNote.text.toString()

            if (title.isNotBlank() && note.isNotBlank()) {
                toDoViewModel.insertToDo(ToDo(name = title, note = note, priority = priority))
                findNavController().popBackStack()
            } else {
                Toast.makeText(requireContext(), "Please write title and note.", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

}