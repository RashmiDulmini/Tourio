package com.example.tourio

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class PreDefinedToursFormActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_predefinedtoursform)

        val continueButton = findViewById<Button>(R.id.buttonContinue1)
        continueButton.setOnClickListener {
            addPreDefinedToursDetails()
        }
    }

    private fun addPreDefinedToursDetails() {
        val db = FirebaseFirestore.getInstance()
        val currentUser = FirebaseAuth.getInstance().currentUser

        if (currentUser != null) {
            val userId = currentUser.uid

            // Get data from EditText fields
            val tourTitle = findViewById<EditText>(R.id.tourTitle).text.toString()
            val destination1 = findViewById<EditText>(R.id.destination1).text.toString()
            val des1MapUrl = findViewById<EditText>(R.id.des1MapUrl).text.toString()
            val destination2= findViewById<EditText>(R.id.destination2).text.toString()
            val des2MapUrl= findViewById<EditText>(R.id.des2MapUrl).text.toString()
            val destination3= findViewById<EditText>(R.id.destination3).text.toString()
            val des3MapUrl= findViewById<EditText>(R.id.des3MapUrl).text.toString()
            val destination4= findViewById<EditText>(R.id.destination4).text.toString()
            val des4MapUrl= findViewById<EditText>(R.id.des4MapUrl).text.toString()
            val destination5 = findViewById<EditText>(R.id.destination5).text.toString()
            val des5MapUrl = findViewById<EditText>(R.id.des5MapUrl).text.toString()
            val optionTitle = findViewById<EditText>(R.id.optionTitle).text.toString()
            val option1 = findViewById<EditText>(R.id.option1).text.toString()
            val option2 = findViewById<EditText>(R.id.option2).text.toString()
            val option3 = findViewById<EditText>(R.id.option3).text.toString()
            val guideProfile = findViewById<EditText>(R.id.guideProfile).text.toString()
            val facilities = findViewById<EditText>(R.id.facilities).text.toString()
            val tourPrice = findViewById<EditText>(R.id.tourPrice).text.toString()

            if (tourTitle.isEmpty() || destination1.isEmpty() || des1MapUrl.isEmpty() || destination2.isEmpty() || des2MapUrl.isEmpty() || destination3.isEmpty()
                || des3MapUrl.isEmpty() || des3MapUrl.isEmpty() || destination4.isEmpty() || des4MapUrl.isEmpty() || destination5.isEmpty() || des5MapUrl.isEmpty()
                || optionTitle.isEmpty() || option1.isEmpty() || option2.isEmpty() || option3.isEmpty() || guideProfile.isEmpty() || facilities.isEmpty() || tourPrice.isEmpty()) {
                Toast.makeText(this, "Please fill out all fields.", Toast.LENGTH_SHORT).show()
                return
            }

            // Create a map to store the preDefined tours data
            val tourData = hashMapOf(
                "destination1" to destination1,
                "des1MapUrl" to des1MapUrl,
                "destination2" to destination2,
                "des2MapUrl" to des2MapUrl,
                "destination3" to destination3,
                "des3MapUrl" to des3MapUrl,
                "destination4" to destination4,
                "des4MapUrl" to des4MapUrl,
                "destination5" to destination5,
                "des5MapUrl" to des5MapUrl,
                "optionTitle" to optionTitle,
                "option1" to option1,
                "option2" to option2,
                "option3" to option3,
                "guideProfile" to guideProfile,
                "facilities" to facilities,
                "tourPrice" to tourPrice// Associate with current user's ID
            )

            // Add data to the 'preDefinedTours' collection
            db.collection("preDefineTours")
                .add(tourData) // Automatically generates a document ID
                .addOnSuccessListener { documentReference ->
                    Toast.makeText(this, "The tour has been created successfully!", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Failed to create the tour. Please try again: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        } else {
            // If user is not logged in
            Toast.makeText(this, "Cannot Identify the User", Toast.LENGTH_SHORT).show()
        }
    }
}