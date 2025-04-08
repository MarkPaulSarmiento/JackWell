package com.example.jackwell

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MealPlannerActivity : AppCompatActivity() {

    private lateinit var mealRecyclerView: RecyclerView
    private lateinit var mealAdapter: MealAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal_planner)

        mealRecyclerView = findViewById(R.id.mealRecyclerView)
        mealRecyclerView.layoutManager = LinearLayoutManager(this)

        // Meals grouped by day
        val meals = listOf(
            "Day 1",
            Meal("Oatmeal with Berries", "Breakfast", 350, "Day 1"),
            Meal("Grilled Chicken Salad", "Lunch", 500, "Day 1"),
            Meal("Protein Shake", "Snack", 200, "Day 1"),
            Meal("Salmon with Veggies", "Dinner", 600, "Day 1"),

            "Day 2",
            Meal("Pancakes with Syrup", "Breakfast", 450, "Day 2"),
            Meal("Tuna Sandwich", "Lunch", 400, "Day 2"),
            Meal("Apple & Almonds", "Snack", 150, "Day 2"),
            Meal("Steak with Mashed Potatoes", "Dinner", 700, "Day 2"),

            "Day 3",
            Meal("Avocado Toast", "Breakfast", 400, "Day 3"),
            Meal("Chicken Caesar Salad", "Lunch", 450, "Day 3"),
            Meal("Greek Yogurt", "Snack", 180, "Day 3"),
            Meal("Grilled Fish with Rice", "Dinner", 650, "Day 3"),

            "Day 4",
            Meal("Smoothie Bowl", "Breakfast", 350, "Day 4"),
            Meal("Veggie Wrap", "Lunch", 350, "Day 4"),
            Meal("Cottage Cheese & Berries", "Snack", 200, "Day 4"),
            Meal("Spaghetti with Tomato Sauce", "Dinner", 700, "Day 4")
        )

        mealAdapter = MealAdapter(meals)
        mealRecyclerView.adapter = mealAdapter
    }
}
