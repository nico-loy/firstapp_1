package at.fh.mappdev.loggingviewsandactivity

import com.squareup.moshi.JsonClass
import java.math.RoundingMode
import java.text.DecimalFormat

@JsonClass(generateAdapter = true)

class Lesson(
    val id: String, val name: String, val date: String, val topic: String,
    val type: LessonType, val lecturers: List<Lecturer>, val ratings: MutableList<LessonRating>,
    val imageUrl: String
) {
    fun ratingAverage(): Double {

        var ratingsList = mutableListOf<Double>()


        ratings.forEach() {
            ratingsList.add(it.ratingValue)
        }

        //for rounding  rating number

        val df = DecimalFormat("#.###") //# is number of decimal places
        df.roundingMode = RoundingMode.DOWN

        val result = df.format(ratingsList.sum() / ratingsList.size).toDouble()
        if (result.isNaN()){
            return 0.0
            }
        else{
            return result
        }
    }

    fun feedback(): String {
        //show the first rating that is not blank
        return ratings.firstOrNull { it.feedback.isNotBlank() }?.feedback ?: ""
        //return ratings.map { it.feedback }.filter { it.isNotEmpty() }.joinToString()
    }
}