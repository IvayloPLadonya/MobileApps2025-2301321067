package com.example.mobileapps2025_2301321067

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mobileapps2025_2301321067.data.Exercise
import com.example.mobileapps2025_2301321067.viewmodel.ExerciseViewModel

class UpdateFragment : Fragment() {

    private lateinit var mExerciseViewModel: ExerciseViewModel

    private val args by navArgs<UpdateFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mExerciseViewModel = ViewModelProvider(this).get(ExerciseViewModel::class.java)

        val etName = view.findViewById<EditText>(R.id.etNameUpdate)
        val etSets = view.findViewById<EditText>(R.id.etSetsUpdate)
        val etReps = view.findViewById<EditText>(R.id.etRepsUpdate)
        val etWeight = view.findViewById<EditText>(R.id.etWeightUpdate)
        val btnUpdate = view.findViewById<Button>(R.id.btnUpdate)
        val btnDelete = view.findViewById<Button>(R.id.btnDelete) // Delete Button


        etName.setText(args.currentExercise.name)
        etSets.setText(args.currentExercise.sets.toString())
        etReps.setText(args.currentExercise.reps.toString())
        etWeight.setText(args.currentExercise.weight.toString())

        btnUpdate.setOnClickListener {
            updateItem(etName, etSets, etReps, etWeight)
        }


        btnDelete.setOnClickListener {
            deleteUser()
        }

        return view
    }

    private fun updateItem(etName: EditText, etSets: EditText, etReps: EditText, etWeight: EditText) {
        val name = etName.text.toString()
        val setsStr = etSets.text.toString()
        val repsStr = etReps.text.toString()
        val weightStr = etWeight.text.toString()

        if (inputCheck(name, setsStr, repsStr, weightStr)) {

            val updatedExercise = Exercise(args.currentExercise.id, name, setsStr.toInt(), repsStr.toInt(), weightStr.toDouble())
            mExerciseViewModel.updateExercise(updatedExercise)
            Toast.makeText(requireContext(), "Updated Successfully!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mExerciseViewModel.deleteExercise(args.currentExercise)
            Toast.makeText(requireContext(), "Successfully removed: ${args.currentExercise.name}", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete ${args.currentExercise.name}?")
        builder.setMessage("Are you sure you want to delete this exercise?")
        builder.create().show()
    }

    private fun inputCheck(name: String, sets: String, reps: String, weight: String): Boolean {
        return !(TextUtils.isEmpty(name) || TextUtils.isEmpty(sets) || TextUtils.isEmpty(reps) || TextUtils.isEmpty(weight))
    }
}