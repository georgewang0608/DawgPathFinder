package bases;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * BaseDigitsTest is a glassbox test of the BaseDigits class.
 */
public class BaseDigitsTest {

  /** Tests converting digits to characters. */
  @Test
  public void testDigitToChar() {
    assertEquals("digitToChar(0, 12) == '0'", '0',
        BaseDigits.digitToChar(0, 12));
    assertEquals("digitToChar(0, 16) == '0'", '0',
        BaseDigits.digitToChar(0, 16));
    assertEquals("digitToChar(5, 16) == '5'", '5',
        BaseDigits.digitToChar(5, 16));
    assertEquals("digitToChar(9, 16) == '9'", '9',
        BaseDigits.digitToChar(9, 16));
    assertEquals("digitToChar(10, 16) == 'a'", 'a',
        BaseDigits.digitToChar(10, 16));
    assertEquals("digitToChar(11, 16) == 'b'", 'b',
        BaseDigits.digitToChar(11, 16));
    assertEquals("digitToChar(15, 16) == 'f'", 'f',
        BaseDigits.digitToChar(15, 16));
  }

  /** Tests converting characters to digits. */
  @Test
  public void testCharToDigit() {
    assertEquals("charToDigit('0', 12) == 0", 0,
        BaseDigits.charToDigit('0', 12));
    assertEquals("charToDigit('0', 16) == 0", 0,
        BaseDigits.charToDigit('0', 16));
    assertEquals("charToDigit('5', 16) == 5", 5,
        BaseDigits.charToDigit('5', 16));
    assertEquals("charToDigit('9', 16) == 9", 9,
        BaseDigits.charToDigit('9', 16));
    assertEquals("charToDigit('a', 16) == 10", 10,
        BaseDigits.charToDigit('a', 16));
    assertEquals("charToDigit('b', 16) == 11", 11,
        BaseDigits.charToDigit('b', 16));
    assertEquals("charToDigit('f', 16) == 15", 15,
        BaseDigits.charToDigit('f', 16));
  }

}
