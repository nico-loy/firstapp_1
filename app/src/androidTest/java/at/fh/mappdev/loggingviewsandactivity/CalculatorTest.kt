package at.fh.mappdev.loggingviewsandactivity

import junit.framework.Assert.assertEquals
import org.junit.Test

class CalculatorTest {

    @Test
    fun testMultiply2By2() {
        val calculator = Calculator()
        val result = calculator.parse("2 * 2")
        assertEquals(4, result)
    }

    @Test
    fun testDivide2By2() {
        val calculator = Calculator()
        val result = calculator.parse("2 / 2")
        assertEquals(1, result)
    }

    @Test
    fun testAdd2And2() {
        val calculator = Calculator()
        val result = calculator.parse("2 + 2")
        assertEquals(4, result)
    }

    @Test
    fun testSubtract2By2() {
        val calculator = Calculator()
        val result = calculator.parse("2 - 2")
        assertEquals(0, result)
    }
}