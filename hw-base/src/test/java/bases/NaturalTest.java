package bases;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * NaturalTest is a glassbox test of the Natural class.
 */
public class NaturalTest {

  /** Tests that constructed numbers have the right base and value. */
  @Test
  public void testConstruction() {
    Natural val = new Natural(10);
    assertEquals("new Natural(10) == (10, 0)", 10, val.getBase());
    assertEquals("new Natural(10) == (10, 0)", 0, val.getValue());

    val = new Natural(10, new int[] { 5 });
    assertEquals("new Natural(10, [5]) == (10, 5)", 10, val.getBase());
    assertEquals("new Natural(10, [5]) == (10, 5)", 5, val.getValue());

    val = new Natural(12, new int[] { 5 });
    assertEquals("new Natural(12, [5]) == (12, 5)", 12, val.getBase());
    assertEquals("new Natural(12, [5]) == (12, 5)", 5, val.getValue());

    val = new Natural(10, new int[] { 5, 1 });
    assertEquals("new Natural(10, [5,1]) == (10, 15)", 10, val.getBase());
    assertEquals("new Natural(10, [5,1]) == (10, 15)", 15, val.getValue());

    val = new Natural(10, new int[] { 1, 5 });
    assertEquals("new Natural(10, [1,5]) == (10, 51)", 10, val.getBase());
    assertEquals("new Natural(10, [1,5]) == (10, 51)", 51, val.getValue());

    val = new Natural(2, new int[] { 0, 1, 1 });
    assertEquals("new Natural(2, [0,1,1]) == (2, 6)", 2, val.getBase());
    assertEquals("new Natural(2, [0,1,1]) == (2, 6)", 6, val.getValue());

    val = new Natural(2, new int[] { 1, 0, 1 });
    assertEquals("new Natural(2, [1,0,1]) == (2, 5)", 2, val.getBase());
    assertEquals("new Natural(2, [1,0,1]) == (2, 5)", 5, val.getValue());
  }

  /** Tests leadingDigit on various numbers. */
  @Test
  public void testLeadingDigit() {
    int[] digits = new int[] { 0 };
    assertEquals("leadingDigit(10) == 0", 0, Natural.leadingDigit(digits));

    digits = new int[] { 5 };
    assertEquals("leadingDigit(10, [5]) == 0", 0,
        Natural.leadingDigit(digits));

    digits = new int[] { 0, 1 };
    assertEquals("leadingDigit(10, [0,1]) == 1", 1,
        Natural.leadingDigit(digits));

    digits = new int[] { 5, 0 };
    assertEquals("leadingDigit(10, [5,0]) == 0", 0,
        Natural.leadingDigit(digits));

    digits = new int[] { 5, 0, 1 };
    assertEquals("leadingDigit(10, [5,0,1]) == 2", 2,
        Natural.leadingDigit(digits));

    digits = new int[] { 5, 1, 0 };
    assertEquals("leadingDigit(10, [5,1,0]) == 1", 1,
        Natural.leadingDigit(digits));

    digits = new int[] { 1, 0, 0 };
    assertEquals("leadingDigit(10, [1,0,0]) == 0", 0,
        Natural.leadingDigit(digits));
  }

  /** Tests converting numbers with bases to strings. */
  @Test
  public void testToString() {
    assertEquals("new Natural(10).toString() == \"0\"", "0",
        new Natural(10).toString());
    assertEquals("new Natural(10, [5]).toString() == \"5\"", "5",
        new Natural(10, new int[] { 5 }).toString());
    assertEquals("new Natural(12, [5]).toString() == \"5\"", "5",
        new Natural(12, new int[] { 5 }).toString());
    assertEquals("new Natural(10, [5,1]).toString() == \"15\"", "15",
        new Natural(10, new int[] { 5, 1 }).toString());
    assertEquals("new Natural(10, [1,5]).toString() == \"51\"", "51",
        new Natural(10, new int[] { 1, 5 }).toString());
    assertEquals("new Natural(2, [0,1,1]).toString() == \"110\"", "110",
        new Natural(2, new int[] { 0, 1, 1 }).toString());
    assertEquals("new Natural(2, [1,0,1]).toString() == \"101\"", "101",
        new Natural(2, new int[] { 1, 0, 1 }).toString());
  }

  /** Tests converting integers to numbers (and then strings). */
  @Test
  public void testGetDigits() {
    assertEquals("new Natural(10, 5).toString() == \"5\"", "5",
        new Natural(10, 5).toString());
    assertEquals("new Natural(2, 5).toString() == \"101\"", "101",
        new Natural(2, 5).toString());
    assertEquals("new Natural(10, 15).toString() == \"15\"", "15",
        new Natural(10, 15).toString());
    assertEquals("new Natural(2, 15).toString() == \"1111\"", "1111",
        new Natural(2, 15).toString());
    assertEquals("new Natural(8, 15).toString() == \"17\"", "17",
        new Natural(8, 15).toString());
    assertEquals("new Natural(10,321).toString() == \"321\"", "321",
        new Natural(10, 321).toString());
    assertEquals("new Natural(2, 321).toString() == \"101000001\"",
        "101000001", new Natural(2, 321).toString());
    assertEquals("new Natural(8, 321).toString() == \"501\"",
        "501", new Natural(8, 321).toString());
    assertEquals("new Natural(16, 321).toString() == \"141\"",
        "141", new Natural(16, 321).toString());
  }

  /** An array of numbers to populate Natural with. */
  private static int[] EXAMPLES = new int[] {
      0, 1, 2, 3, 5, 10, 15, 17, 19, 29, 89, 99, 100, 120, 121, 150, 151,
      202, 321, 351, 399, 500, 843, 999
  };

  /** Tests adding numbers */
  @Test
  public void testPlus() {
    for (int n : EXAMPLES) {
      Natural n2 = new Natural(2, n);
      Natural n10 = new Natural(10, n);
      Natural n16 = new Natural(16, n);

      for (int m : EXAMPLES) {
        Natural m2 = new Natural(2, m);
        Natural m10 = new Natural(10, m);
        Natural m16 = new Natural(16, m);

        assertEquals(
            String.format("(2,%d) + (2,%d) == (2,%d)", n, m, n+m),
            n+m, n2.plus(m2).getValue());
        assertEquals(
            String.format("(10,%d) + (10,%d) == (10,%d)", n, m, n+m),
            n+m, n10.plus(m10).getValue());
        assertEquals(
            String.format("(16,%d) + (16,%d) == (16,%d)", n, m, n+m),
            n+m, n16.plus(m16).getValue());
      }
    }
  }

  /**
   * Version of the test above that ignores the return value. This just makes
   * sure that the code runs without any exceptions.
   */
  @Test
  public void testPlusTopTwoLoops() {
    for (int n : EXAMPLES) {
      Natural n2 = new Natural(2, n);
      Natural n10 = new Natural(10, n);
      Natural n16 = new Natural(16, n);

      for (int m : EXAMPLES) {
        Natural m2 = new Natural(2, m);
        Natural m10 = new Natural(10, m);
        Natural m16 = new Natural(16, m);

        n2.plus(m2);
        n10.plus(m10);
        n16.plus(m16);
      }
    }
  }

  /** Tests shifting numbers to the left. */
  @Test
  public void testLeftShift() {
    assertEquals("(10, 5) << 0 == (10, 5)", 5,
        new Natural(10, 5).leftShift(0).getValue());
    assertEquals("(10, 5) << 1 == (10, 50)", 50,
        new Natural(10, 5).leftShift(1).getValue());
    assertEquals("(10, 5) << 1 == (10, 500)", 500,
        new Natural(10, 5).leftShift(2).getValue());

    assertEquals("(10, 51) << 0 == (10, 51)", 51,
        new Natural(10, 51).leftShift(0).getValue());
    assertEquals("(10, 51) << 1 == (10, 510)", 510,
        new Natural(10, 51).leftShift(1).getValue());
    assertEquals("(10, 51) << 1 == (10, 5100)", 5100,
        new Natural(10, 51).leftShift(2).getValue());

    assertEquals("(10, 329) << 0 == (10, 329)", 329,
        new Natural(10, 329).leftShift(0).getValue());
    assertEquals("(10, 329) << 1 == (10, 3290)", 3290,
        new Natural(10, 329).leftShift(1).getValue());
    assertEquals("(10, 329) << 1 == (10, 32900)", 32900,
        new Natural(10, 329).leftShift(2).getValue());
  }

  /** Tests multiplying by single digit numbers. */
  @Test
  public void testTimesOneDigit() {
    for (int n : EXAMPLES) {
      Natural n2 = new Natural(2, n);
      Natural n10 = new Natural(10, n);
      Natural n16 = new Natural(16, n);

      for (int m = 0; m < 16; m++) {

        if (m < 2) {
          assertEquals(
              String.format("(2,%d) * %d == (2,%d)", n, m, n*m),
              n*m, n2.times(m).getValue());
        }
        if (m < 10) {
          assertEquals(
              String.format("(10,%d) * %d == (10,%d)", n, m, n*m),
              n*m, n10.times(m).getValue());
        }
        assertEquals(
            String.format("(16,%d) * %d == (16,%d)", n, m, n*m),
            n*m, n16.times(m).getValue());
      }
    }
  }

  /** Tests multiplying numbers */
  @Test
  public void testTimes() {
    for (int n : EXAMPLES) {
      Natural n2 = new Natural(2, n);
      Natural n10 = new Natural(10, n);
      Natural n16 = new Natural(16, n);

      for (int m : EXAMPLES) {
        Natural m2 = new Natural(2, m);
        Natural m10 = new Natural(10, m);
        Natural m16 = new Natural(16, m);

        assertEquals(
            String.format("(2,%d) * (2,%d) == (2,%d)", n, m, n*m),
            n*m, n2.times(m2).getValue());
        assertEquals(
            String.format("(10,%d) * (10,%d) == (10,%d)", n, m, n*m),
            n*m, n10.times(m10).getValue());
        assertEquals(
            String.format("(16,%d) * (16,%d) == (16,%d)", n, m, n*m),
            n*m, n16.times(m16).getValue());
      }
    }
  }

  /** Tests converting numbers between bases. */
  @Test
  public void testToBase() {
    for (int n : EXAMPLES) {
      for (int m1 = 2; m1 <= 36; m1++) {
        for (int m2 = 2; m2 <= 36; m2++) {
          Natural n1 = new Natural(m1, n);
          Natural n2 = n1.toBase(m2);
          assertEquals(n, n1.getValue());
          assertEquals(String.format(
                  "(%d, %d).toBase(%d) == (%d, %d)", m2, n, m1, m1, n),
              m2, n2.getBase());
          assertEquals(String.format(
                  "(%d, %d).toBase(%d) == (%d, %d)", m2, n, m1, m1, n),
              n, n2.getValue());
        }
      }
    }
  }

}
