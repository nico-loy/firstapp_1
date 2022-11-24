package at.fh.mappdev.loggingviewsandactivity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity

class SettingsActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)



        val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)

        val usernameInput= findViewById<TextView>(R.id.username_input).toString()

        val darkmodeCheck = findViewById<Switch>(R.id.darkmode_switch).isChecked

        sharedPreferences.edit().putString("USERNAME", "no Username").apply()
        sharedPreferences.edit().putBoolean("DARKMODE", false).apply()


    }
}
