package at.fh.mappdev.loggingviewsandactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LessonListActivity : AppCompatActivity() {
    val lessonAdapter = LessonAdapter() {
        Toast.makeText(this, "Lesson with name: ${it.name} has been clicked", Toast.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_list)

        lessonAdapter.updateList(LessonRepository.lessonsList())
        val recyclerView = findViewById<RecyclerView>(R.id.lesson_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = lessonAdapter
    }
}