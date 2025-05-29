package com.example.oht_2

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var genderGroup: RadioGroup
    private lateinit var readingCheckBox: CheckBox
    private lateinit var travelingCheckBox: CheckBox
    private lateinit var themeToggle: ToggleButton
    private lateinit var countrySpinner: Spinner
    private lateinit var submitButton: Button
    private lateinit var rootLayout: ScrollView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Bind views
        nameEditText = findViewById(R.id.nameEditText)
        emailEditText = findViewById(R.id.emailEditText)
        genderGroup = findViewById(R.id.genderGroup)
        readingCheckBox = findViewById(R.id.readingCheckBox)
        travelingCheckBox = findViewById(R.id.travelingCheckBox)
        themeToggle = findViewById(R.id.themeToggle)
        countrySpinner = findViewById(R.id.countrySpinner)
        submitButton = findViewById(R.id.submitButton)
        rootLayout = findViewById(R.id.rootLayout)

        // Setup Spinner with country list
        val countries = arrayOf("Pakistan", "USA", "UK", "Canada", "India")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, countries)
        countrySpinner.adapter = adapter

        // ToggleButton behavior
        themeToggle.setOnCheckedChangeListener { _, isChecked ->
            val message = if (isChecked) "Dark Mode On" else "Light Mode On"
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }

        // Submit button logic
        submitButton.setOnClickListener {
            val name = nameEditText.text.toString().trim()
            val email = emailEditText.text.toString().trim()

            if (name.isEmpty() || email.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val selectedGenderId = genderGroup.checkedRadioButtonId
            val selectedGender = if (selectedGenderId != -1) {
                findViewById<RadioButton>(selectedGenderId).text.toString()
            } else {
                "Not Specified"
            }

            val hobbies = mutableListOf<String>()
            if (readingCheckBox.isChecked) hobbies.add("Reading")
            if (travelingCheckBox.isChecked) hobbies.add("Traveling")
//            if (readingCheckBox.isChecked) hobbies.add("Sports")
//            if (readingCheckBox.isChecked) hobbies.add("Cooking")
//            if (readingCheckBox.isChecked) hobbies.add("Painting")

            val selectedCountry = countrySpinner.selectedItem.toString()

            Toast.makeText(this, "Profile Submitted!", Toast.LENGTH_SHORT).show()
            Snackbar.make(rootLayout, "Profile saved successfully.", Snackbar.LENGTH_SHORT).show()

            AlertDialog.Builder(this)
                .setTitle("Profile Summary")
                .setMessage(
                    "Name: $name\n" +
                            "Email: $email\n" +
                            "Gender: $selectedGender\n" +
                            "Hobbies: ${if (hobbies.isEmpty()) "None" else hobbies.joinToString(", ")}\n" +
                            "Country: $selectedCountry"
                )
                .setPositiveButton("OK", null)
                .show()
        }
    }
}
