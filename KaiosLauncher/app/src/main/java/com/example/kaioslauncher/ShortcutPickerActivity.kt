package com.example.kaioslauncher

import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class ShortcutPickerActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var shortcutKey: String
    private lateinit var prefs: android.content.SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shortcut_picker)

        listView = findViewById(R.id.appListView)
        shortcutKey = intent.getStringExtra("shortcut_key") ?: return
        prefs = getSharedPreferences("shortcuts", MODE_PRIVATE)

        val apps = packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
            .filter { packageManager.getLaunchIntentForPackage(it.packageName) != null }

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,
            apps.map { it.loadLabel(packageManager).toString() })

        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            val app = apps[position]
            prefs.edit().putString(shortcutKey, app.packageName).apply()
            Toast.makeText(this, "میانبر ذخیره شد", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}