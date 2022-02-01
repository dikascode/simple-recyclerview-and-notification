package com.dikascode.simplerecyclerviewnotification.notification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dikascode.simplerecyclerviewnotification.databinding.ActivityMainBinding
import com.dikascode.simplerecyclerviewnotification.databinding.ActivityNotificationBinding

class NotificationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNotificationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Extra content from the bundle passed in intent
        val bundle = intent.extras
        binding.noteTv.text = bundle?.getString("Intent_Note", "Not found")
    }
}