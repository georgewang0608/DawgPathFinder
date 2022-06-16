package bases;

/**
 * Routines for mapping between characters and digits in different bases.
 */
public class BaseDigits {

  /**
   * Returns the character corresponding to the given digit in this base.
   *
   * @param base the base used to represent numbers with digits
   * @param digit the value we will convert to char based on base
   * @spec.requires {@literal 0 <= digit < base and 2 <= base < 36}
   * @return the character corresponding to the given digit in the given base
   */
  public static char digitToChar(int digit, int base) {
    assert 2 <= base && base <= 36;
    assert 0 <= digit && digit < base;

    if (digit < 10) {
      return (char)('0' + digit);
    } else {
      return (char)('a' + digit - 10);
    }
  }

  /**
   * Returns the digit coresponding to the given character in this base.
   *
   * @param base the base used to represent numbers with digits
   * @param ch the char that we will convert into a digit
   * @spec.requires {@literal 2 <= base < 36 and ch is in a-z or 0-9}.
   * @return the digit corresponding to the given character in the given base
   */
  public static int charToDigit(char ch, int base) {
    assert 2 <= base && base <= 36;

    if ('0' <= ch && ch <= '9') {
      return ch - '0';
    } else {
      assert 'a' <= ch && ch <= 'z';
      return 10 + ch - 'a';
    }
  }

}
