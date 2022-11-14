package at.fh.mappdev.loggingviewsandactivity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*

class LessonRatingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_rating)
        var listId = ""

        var id = intent.getStringExtra(LessonListActivity.EXTRA_LESSON_ID)?.let { //let wird benutzt weil null safe operation with safe call operator ?


            LessonRepository.lessonById(it,
                success = {
                    // handle success
                    findViewById<TextView>(R.id.lesson_rating_header).text =
                        "Lesson: " + it.name ?: "No lesson found"
                    listId = it.id
                },
                error = {
                    // handle error
                    Toast.makeText(this, "Error loading lesson", Toast.LENGTH_LONG).show()
                }
            )

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
                setResult(Activity.RESULT_OK, resultIntent)
                finish()

            }
        }

    }






