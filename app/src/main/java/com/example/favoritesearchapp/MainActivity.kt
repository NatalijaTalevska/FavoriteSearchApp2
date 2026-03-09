package com.example.favoritesearchapp

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.HashMap

class MainActivity : AppCompatActivity() {

    lateinit var editSearch: TextInputEditText
    lateinit var txtResult: TextView
    lateinit var btnSearch: MaterialButton

    lateinit var btnProgramming: MaterialButton
    lateinit var btnTravel: MaterialButton
    lateinit var btnMusic: MaterialButton
    lateinit var btnReset: MaterialButton

    val dictionary = HashMap<String, String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editSearch = findViewById(R.id.editSearch)
        txtResult = findViewById(R.id.txtResult)
        btnSearch = findViewById(R.id.btnSearch)

        btnProgramming = findViewById(R.id.btnProgramming)
        btnTravel = findViewById(R.id.btnTravel)
        btnMusic = findViewById(R.id.btnMusic)
        btnReset = findViewById(R.id.btnReset)

        loadDictionary()

        btnSearch.setOnClickListener {

            val word = editSearch.text.toString().trim().lowercase()

            if (dictionary.containsKey(word)) {
                txtResult.text = dictionary[word]
            } else {
                txtResult.text = "Word not found"
            }

            // Сите копчиња ги криеме
            btnProgramming.visibility = View.GONE
            btnTravel.visibility = View.GONE
            btnMusic.visibility = View.GONE

            // Само соодветното го прикажуваме
            if (word == "programming") {
                btnProgramming.visibility = View.VISIBLE
            }

            if (word == "travel") {
                btnTravel.visibility = View.VISIBLE
            }

            if (word == "music") {
                btnMusic.visibility = View.VISIBLE
            }
        }

        btnReset.setOnClickListener {

            txtResult.text = "Result will appear here"

            btnProgramming.visibility = View.VISIBLE
            btnTravel.visibility = View.VISIBLE
            btnMusic.visibility = View.VISIBLE

            editSearch.text?.clear()
        }
    }

    private fun loadDictionary() {

        try {

            val reader = BufferedReader(
                InputStreamReader(assets.open("recnik.txt"))
            )

            var line: String?

            while (reader.readLine().also { line = it } != null) {

                val parts = line!!.split(",")

                if (parts.size == 2) {

                    dictionary[parts[0].trim().lowercase()] = parts[1].trim()
                    dictionary[parts[1].trim().lowercase()] = parts[0].trim()

                }
            }

            reader.close()

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}