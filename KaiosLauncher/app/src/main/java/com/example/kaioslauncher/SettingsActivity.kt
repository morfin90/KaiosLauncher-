package com.example.kaioslauncher

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {

    private lateinit var switchDarkMode: Switch
    private lateinit var prefs: android.content.SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        prefs = getSharedPreferences("shortcuts", MODE_PRIVATE)
        switchDarkMode = findViewById(R.id.switchDark)

        switchDarkMode.isChecked = prefs.getBoolean("dark_mode", true)

        switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean("dark_mode", isChecked).apply()
            Toast.makeText(this, "حالت شب ${if (isChecked) "فعال شد" else "غیرفعال شد"}", Toast.LENGTH_SHORT).show()
        }

        findViewById<Button>(R.id.btnUp).setOnClickListener {
            openShortcutPicker("shortcut_up")
        }
        findViewById<Button>(R.id.btnDown).setOnClickListener {
            openShortcutPicker("shortcut_down")
        }
        findViewById<Button>(R.id.btnLeft).setOnClickListener {
            openShortcutPicker("shortcut_left")
        }
        findViewById<Button>(R.id.btnRight).setOnClickListener {
            openShortcutPicker("shortcut_right")
        }
    }

    private fun openShortcutPicker(key: String) {
        val intent = Intent(this, ShortcutPickerActivity::class.java)
        intent.putExtra("shortcut_key", key)
        startActivity(intent)
    }
}