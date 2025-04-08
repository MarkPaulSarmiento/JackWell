package com.example.jackwell

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MealAdapter(private val mealList: List<Any>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // View types for Day Headers and Meal Items
    private val VIEW_TYPE_DAY_HEADER = 0
    private val VIEW_TYPE_MEAL_ITEM = 1

    // ViewHolder for meal items
    class MealViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mealName: TextView = itemView.findViewById(R.id.mealName)
        val mealTime: TextView = itemView.findViewById(R.id.mealTime)
        val mealCalories: TextView = itemView.findViewById(R.id.mealCalories)
    }

    // ViewHolder for day headers
    class DayHeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dayHeader: TextView = itemView.findViewById(R.id.dayHeader)
    }

    // Get the view type for each item (either a day header or a meal item)
    override fun getItemViewType(position: Int): Int {
        return if (mealList[position] is String) { // Day header is a String
            VIEW_TYPE_DAY_HEADER
        } else {
            VIEW_TYPE_MEAL_ITEM
        }
    }

    // Create ViewHolder depending on the view type
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_DAY_HEADER) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.day_header_item, parent, false)
            DayHeaderViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_meal_item, parent, false)
            MealViewHolder(view)
        }
    }

    // Bind data to the ViewHolder
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MealViewHolder -> {
                val meal = mealList[position] as Meal
                holder.mealName.text = meal.name
                holder.mealTime.text = "Time: ${meal.time}"
                holder.mealCalories.text = "Calories: ${meal.calories} kcal"
            }
            is DayHeaderViewHolder -> {
                val dayHeader = mealList[position] as String
                holder.dayHeader.text = dayHeader
            }
        }
    }

    // Return the total item count (meal items and day headers)
    override fun getItemCount(): Int = mealList.size
}
