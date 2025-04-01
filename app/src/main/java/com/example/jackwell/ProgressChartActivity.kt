package com.example.jackwell

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.*
import com.google.firebase.database.*

class ProgressChartActivity : AppCompatActivity() {

    private lateinit var barChart: BarChart
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress_chart)

        // Initialize BarChart
        barChart = findViewById(R.id.bar_chart1)

        // Connect to Firebase Database
        database = FirebaseDatabase.getInstance().reference.child("progressData")

        // Load Data from Firebase
        loadChartData()
    }

    private fun loadChartData() {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val entries = mutableListOf<BarEntry>()
                var index = 0

                for (data in snapshot.children) {
                    val value = data.getValue(Float::class.java) ?: 0f
                    entries.add(BarEntry(index.toFloat(), value))
                    index++
                }

                val dataSet = BarDataSet(entries, "Workout Progress")
                dataSet.color = resources.getColor(R.color.teal_200, null)

                val barData = BarData(dataSet)
                barChart.data = barData
                barChart.invalidate()  // Refresh chart
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
    }
}