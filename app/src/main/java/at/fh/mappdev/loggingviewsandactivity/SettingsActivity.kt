package at.fh.mappdev.loggingviewsandactivity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat.startActivity

class SettingsActivity : AppCompatActivity(){

    companion object {
        val USERNAME = "USERNAME"
        val DARKMODE = "DARKMODE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val sharedPreferences = getSharedPreferences("at.fh.mappdev.loggingviewsandactivity", Context.MODE_PRIVATE)

        findViewById<Button>(R.id.save_settings_button).setOnClickListener {
            sharedPreferences.edit()
                .putString("USERNAME", findViewById<TextView>(R.id.username_input).text.toString())
                .apply()
            sharedPreferences.edit()
                .putBoolean("DARKMODE", findViewById<Switch>(R.id.darkmode_switch).isChecked)
                .apply()
            finish()
        }

        findViewById<TextView>(R.id.username_input).text = sharedPreferences.getString(USERNAME, null)


        if (findViewById<Switch>(R.id.darkmode_switch).isChecked) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}
