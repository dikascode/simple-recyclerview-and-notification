package com.dikascode.simplerecyclerviewnotification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.dikascode.simplerecyclerviewnotification.adapter.RecyclerViewAdapter
import com.dikascode.simplerecyclerviewnotification.databinding.ActivityMainBinding
import com.dikascode.simplerecyclerviewnotification.model.SimpleNote
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private val simpleNote = arrayListOf(SimpleNote(1, "Good day"), SimpleNote(2, "What a day to code"))
    val recyclerViewAdapter by lazy { RecyclerViewAdapter(simpleNote) }

    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addButton.setOnClickListener {

        }
    }
}