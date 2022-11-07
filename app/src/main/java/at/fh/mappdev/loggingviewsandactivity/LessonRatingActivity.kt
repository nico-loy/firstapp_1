package at.fh.mappdev.loggingviewsandactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class LessonRatingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_rating)

        var id = intent.getStringExtra(LessonListActivity.EXTRA_LESSON_ID)?.let { //let wird benutzt weil null safe operation with safe call operator ?
            LessonRepository.lessonById(it)
        }
        findViewById<TextView>(R.id.lesson_rating_header).text = id?.name ?: "No lesson found"
    }
}