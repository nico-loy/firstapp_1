package at.fh.mappdev.loggingviewsandactivity

import androidx.lifecycle.MutableLiveData
import com.google.common.truth.Truth.assertThat
import org.junit.Assert.assertEquals
import org.junit.Test

class ModelUnitTest {
    @Test
    fun averageForEmptyRates_isCorrect() {
        // test whether the average is 0.0 when ratings are empty
        val testLesson = Lesson(
            "123",
            "TestLesson",
            "01.01.2022",
            "TestTopic",
            LessonType.LECTURE,listOf<Lecturer>(),
            mutableListOf<LessonRating>(),
            ""
        )

        val testAverage = testLesson.ratingAverage().toDouble()

        assertEquals(testAverage,testAverage, 0.0001)
    }

    @Test
    fun averageForNonEmptyRates_isCorrect() {
        // check whether the average is correct for a non-empty list of ratings
        val testLesson1 = Lesson(
            "",
            "",
            "",
            "",
            LessonType.LECTURE,
            listOf(),
            mutableListOf(LessonRating(1.0, "bad lesson"),LessonRating(5.0,"excellent lesson")),
            "")

        val average = testLesson1.ratingAverage()
        assertEquals(3.0, average, Math.abs(0.0-average))



    }
}