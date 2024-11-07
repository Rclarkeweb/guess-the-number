import com.example.guess_the_number.ui.screens.isGuessCorrect
import org.junit.Assert.assertEquals
import org.junit.Test

class GameScreenTest {

    @Test
    fun `isGuessCorrect returns 'Correct' when guess matches the random number`() {
        val result = isGuessCorrect(randomNum = 10, guess = 10)
        assertEquals("Correct", result)
    }

    @Test
    fun `isGuessCorrect returns 'Too low' when guess is lower than the random number`() {
        val result = isGuessCorrect(randomNum = 15, guess = 10)
        assertEquals("Too low", result)
    }

    @Test
    fun `isGuessCorrect returns 'Too high' when guess is higher than the random number`() {
        val result = isGuessCorrect(randomNum = 10, guess = 15)
        assertEquals("Too high", result)
    }
}
