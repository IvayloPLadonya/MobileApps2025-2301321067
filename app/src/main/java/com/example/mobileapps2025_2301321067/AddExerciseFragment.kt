package com.example.mobileapps2025_2301321067

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mobileapps2025_2301321067.data.Exercise
import com.example.mobileapps2025_2301321067.viewmodel.ExerciseViewModel

class AddExerciseFragment : Fragment() {

    private lateinit var mExerciseViewModel: ExerciseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_exercise, container, false)

        mExerciseViewModel = ViewModelProvider(this).get(ExerciseViewModel::class.java)

        val btnSave = view.findViewById<Button>(R.id.btnSave)
        val etName = view.findViewById<EditText>(R.id.etName)
        val etSets = view.findViewById<EditText>(R.id.etSets)
        val etReps = view.findViewById<EditText>(R.id.etReps)
        val etWeight = view.findViewById<EditText>(R.id.etWeight)

        btnSave.setOnClickListener {
            insertDataToDatabase(etName, etSets, etReps, etWeight)
        }

        return view
    }

    private fun insertDataToDatabase(etName: EditText, etSets: EditText, etReps: EditText, etWeight: EditText) {
        val name = etName.text.toString()
        val setsStr = etSets.text.toString()
        val repsStr = etReps.text.toString()
        val weightStr = etWeight.text.toString()

        if (inputCheck(name, setsStr, repsStr, weightStr)) {
            val exercise = Exercise(0, name, setsStr.toInt(), repsStr.toInt(), weightStr.toDouble())


            mExerciseViewModel.addExercise(exercise)

            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()

            findNavController().popBackStack()
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(name: String, sets: String, reps: String, weight: String): Boolean {
        return !(TextUtils.isEmpty(name) || TextUtils.isEmpty(sets) || TextUtils.isEmpty(reps) || TextUtils.isEmpty(weight))
    }
}