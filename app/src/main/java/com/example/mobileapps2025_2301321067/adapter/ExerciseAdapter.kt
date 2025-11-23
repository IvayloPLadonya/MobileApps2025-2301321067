package com.example.mobileapps2025_2301321067.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileapps2025_2301321067.R
import com.example.mobileapps2025_2301321067.data.Exercise

class ExerciseAdapter : RecyclerView.Adapter<ExerciseAdapter.MyViewHolder>() {

    private var exerciseList = emptyList<Exercise>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvDetails: TextView = itemView.findViewById(R.id.tvDetails)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = exerciseList[position]
        holder.tvName.text = currentItem.name
        holder.tvDetails.text = "${currentItem.sets} sets x ${currentItem.reps} reps @ ${currentItem.weight}kg"
    }

    override fun getItemCount(): Int {
        return exerciseList.size
    }


    fun setData(exercise: List<Exercise>) {
        this.exerciseList = exercise
        notifyDataSetChanged()
    }
}