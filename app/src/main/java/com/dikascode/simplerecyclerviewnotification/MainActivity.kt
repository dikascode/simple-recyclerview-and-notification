package com.dikascode.simplerecyclerviewnotification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.*
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dikascode.simplerecyclerviewnotification.adapter.RecyclerViewAdapter
import com.dikascode.simplerecyclerviewnotification.databinding.ActivityMainBinding
import com.dikascode.simplerecyclerviewnotification.model.SimpleNote
import com.dikascode.simplerecyclerviewnotification.notification.NotificationActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private val simpleNote =
        arrayListOf(SimpleNote(1, "Good day students"), SimpleNote(2, "Excited to write your first notification code?"))
    private val recyclerViewAdapter by lazy { RecyclerViewAdapter(simpleNote) }
    var snCount = simpleNote.size

    private lateinit var binding: ActivityMainBinding
    lateinit var builder: Notification.Builder


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.adapter = recyclerViewAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        binding.addButton.setOnClickListener {
            addNote()
        }
    }

    private fun addNote() {
        if (binding.etNote.text.isNullOrEmpty()) {
            showToast("Please enter a note")
        } else {
            snCount++
            simpleNote.add(SimpleNote(snCount, binding.etNote.text.toString()))
            recyclerViewAdapter.notifyDataSetChanged()

            showNotification()

            showToast("Note has been added successfully")
            binding.etNote.text.clear()
            binding.etNote.clearFocus()
        }
    }

    private fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }

    private fun showNotification() {
        val channelId = "recyclerViewApp.notifications"
        val description = "Simple Recyclerview & Notification"
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val intent = Intent(this, NotificationActivity::class.java)

        //Pass bundle into intent
        val bundle = Bundle()
        bundle.putString("Intent_Note", binding.etNote.text.toString())
        intent.putExtras(bundle)

        val updatedPendingIntent = getActivity(
            applicationContext,
            0,
            intent,
            FLAG_IMMUTABLE or FLAG_UPDATE_CURRENT
        )


        builder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel =
                NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(notificationChannel)
            Notification.Builder(this, channelId)
                .setSmallIcon(R.drawable.ic_notifications)
                .setContentIntent(updatedPendingIntent)
                .setContentTitle("iNotification")
                .setAutoCancel(true)
                .setContentText(binding.etNote.text)
        } else {
            Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_notifications)
                .setContentIntent(updatedPendingIntent)
                .setContentTitle("iNotification")
                .setAutoCancel(true)
                .setContentText(binding.etNote.text)
        }
        notificationManager.notify(12, builder.build())
    }
}