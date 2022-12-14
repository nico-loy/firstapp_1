package at.fh.mappdev.loggingviewsandactivity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.bumptech.glide.Glide

class LessonRatingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_rating)

        var listId = ""
        var listName = ""
        intent.getStringExtra(LessonListActivity.EXTRA_LESSON_ID)?.let {
            LessonRepository.lessonById(it,
                success = {
                    // handle success
                    findViewById<TextView>(R.id.lesson_rating_header).text = it.name ?: "No lesson found"
                    findViewById<TextView>(R.id.lesson_avg_rating_value).text = String.format("%.2f", it.ratingAverage())
                    findViewById<RatingBar>(R.id.lesson_rating_bar_fetched).rating = it.ratingAverage().toFloat() ?: 0.0f
                    findViewById<TextView>(R.id.textview_feedback).text = it.feedback() ?: "No feedback found"
                    val imageView = findViewById<ImageView>(R.id.lesson_rating_img_view)
                    Glide
                        .with(this)
                        .load(it.imageUrl)
                        .into(imageView)
                    listId = it.id
                    listName = it.name
                },
                error = {
                    // handle error
                    Toast.makeText(this, "Error loading lesson", Toast.LENGTH_LONG).show()
                }
            )
        }


        findViewById<Button>(R.id.lesson_note_button).setOnClickListener {
            val intent = Intent(this, LessonNoteActivity::class.java)
            intent.putExtra(LessonListActivity.EXTRA_LESSON_ID, listId)
            intent.putExtra(LessonListActivity.EXTRA_LESSON_NAME, listName)
            startActivity(intent)
        }

        findViewById<Button>(R.id.rate_lesson).setOnClickListener {
            //create instance of LessonRating
            val rating = LessonRating(
                findViewById<RatingBar>(R.id.lesson_rating_bar).rating.toDouble(),
                findViewById<EditText>(R.id.lesson_feedback).text.toString()
            )

            //add rating to lesson
            Log.v("Values before calling", listId + "Rating: " + rating.toString())
            LessonRepository.rateLesson(listId, rating)
            val resultIntent = Intent()
            setResult(Activity.RESULT_OK,resultIntent)
            finish()

        }
    }
}






