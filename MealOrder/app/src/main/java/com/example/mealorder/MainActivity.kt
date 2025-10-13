package com.example.mealorder

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private val startForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            val intent = result.data
            val mainMeal = intent?.getStringExtra("Main Meal")
            val menu = findViewById<TextView>(R.id.menu)
            menu.text = "飲料：$mainMeal"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val mainMeal=findViewById<Button>(R.id.mainMeal)
        val sideDishes=findViewById<Button>(R.id.sideDishes)
        val drink=findViewById<Button>(R.id.drink)


        mainMeal.setOnClickListener(myListener)
        sideDishes.setOnClickListener(myListener)
        drink.setOnClickListener(myListener)
    }

    private val myListener = View.OnClickListener { v ->
        when(v.id){
            R.id.mainMeal->{
                val intent = Intent(this, MainMealActivity::class.java)
                startForResult.launch(intent)
            }
            R.id.sideDishes->{
                Toast.makeText(this, "side dishes", Toast.LENGTH_SHORT).show()
            }
            R.id.drink->{
                Toast.makeText(this, "drink", Toast.LENGTH_SHORT).show()
            }
        }
    }
}