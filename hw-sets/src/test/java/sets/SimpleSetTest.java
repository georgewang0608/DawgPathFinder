package sets;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;

/**
 * SimpleSetTest is a glassbox test of the SimpleSet class.
 */
public class SimpleSetTest {

  /** Tests calculating the size of a set. */
  @Test
  public void testSize() {
    // TODO: implement this
    assertTrue(S.size() == 0);
    assertTrue(S1.size() == 1);
    assertTrue(S1.size() == S2.size());
    assertTrue(S12.size() == S13.size());
    assertTrue(S123.size() != S12.size());
    assertTrue(R1.size() == R2.size());
    assertTrue(R1.size() == R123.size());
    assertFalse(R1.size() == S.size());
    assertFalse(R1.size() == S1.size());
  }

  /** Tests converting a set to a string. */
  @Test
  public void testToString() {
    // TODO: implement
    assertEquals(S.toString(), "{}");
    assertEquals(S1.toString(), "{1.0}");
    assertEquals(S12.toString(), "{1.0, 2.0}");
    assertEquals(R.toString(), "R \\ {}");
    assertEquals(R1.toString(),"R \\ {1.0}");
    assertEquals(R12.toString(), "R \\ {1.0, 2.0}");
    assertEquals(R123.toString(), "R \\ {1.0, 2.0, 3.0}");
    assertNotEquals(S.toString(), R.toString());
    assertNotEquals(S.toString(), S1.toString());
  }

  private static SimpleSet S = new SimpleSet(new float[0]);
  private static SimpleSet S1 = new SimpleSet(new float[] {1});
  private static SimpleSet S2 = new SimpleSet(new float[] {2});
  private static SimpleSet S3 = new SimpleSet(new float[] {3});
  private static SimpleSet S12 = new SimpleSet(new float[] {1, 2});
  private static SimpleSet S13 = new SimpleSet(new float[] {1, 3});
  private static SimpleSet S23 = new SimpleSet(new float[] {2, 3});
  private static SimpleSet S123 = new SimpleSet(new float[] {1, 2, 3});

  private static SimpleSet R = S.complement();
  private static SimpleSet R1 = S1.complement();
  private static SimpleSet R2 = S2.complement();
  private static SimpleSet R3 = S3.complement();
  private static SimpleSet R12 = S12.complement();
  private static SimpleSet R13 = S13.complement();
  private static SimpleSet R23 = S23.complement();
  private static SimpleSet R123 = S123.complement();

  /** Tests equality of sets. */
  @Test
  public void testEquals() {
    assertTrue(S.equals(new SimpleSet(new float[0])));
    assertTrue(S.equals(S));
    assertFalse(S.equals(R));
    assertFalse(R.equals(S));

    assertFalse(S1.equals(S));
    assertFalse(S1.equals(R));
    assertTrue(S1.equals(S1));
    assertTrue(R1.equals(R1));
    assertFalse(S1.equals(R1));
    assertFalse(R1.equals(S1));
    assertFalse(S1.equals(S12));
    assertFalse(S1.equals(S123));

    assertFalse(S12.equals(S));
    assertFalse(S12.equals(R));
    assertTrue(S12.equals(S12));
    assertTrue(R12.equals(R12));
    assertFalse(S12.equals(R12));
    assertFalse(R12.equals(S12));
    assertFalse(S12.equals(S1));
    assertFalse(S12.equals(S123));
  }

  /** Tests taking the complement of a set. */
  @Test
  public void testComplement() {
    assertTrue(R.equals(S.complement()));
    assertTrue(R1.equals(S1.complement()));
    assertTrue(R2.equals(S2.complement()));
    assertTrue(R3.equals(S3.complement()));
    assertTrue(R12.equals(S12.complement()));
    assertTrue(R13.equals(S13.complement()));
    assertTrue(R23.equals(S23.complement()));
    assertTrue(R123.equals(S123.complement()));

    assertTrue(S.equals(R.complement()));
    assertTrue(S1.equals(R1.complement()));
    assertTrue(S2.equals(R2.complement()));
    assertTrue(S3.equals(R3.complement()));
    assertTrue(S12.equals(R12.complement()));
    assertTrue(S13.equals(R13.complement()));
    assertTrue(S23.equals(R23.complement()));
    assertTrue(S123.equals(R123.complement()));

    assertFalse(R12.equals(S1.complement()));
    assertFalse(R123.equals(S12.complement()));
    assertFalse(R1.equals(S123.complement()));
  }

  /** Tests forming the union of two sets. */
  @Test
  public void testUnion() {
    assertTrue(S.equals(S.union(S)));
    assertTrue(S1.equals(S1.union(S)));
    assertTrue(S1.equals(S1.union(S1)));
    assertTrue(S12.equals(S1.union(S2)));
    assertTrue(S12.equals(S12.union(S2)));
    assertTrue(S12.equals(S2.union(S12)));
    assertTrue(S123.equals(S12.union(S23)));
    assertTrue(S123.equals(S23.union(S12)));

    assertTrue(R.equals(R.union(R)));
    assertTrue(R.equals(R1.union(R)));
    assertTrue(R1.equals(R1.union(R1)));
    assertTrue(R.equals(R1.union(R2)));
    assertTrue(R2.equals(R12.union(R2)));
    assertTrue(R2.equals(R2.union(R12)));
    assertTrue(R2.equals(R12.union(R23)));
    assertTrue(R2.equals(R23.union(R12)));

    assertTrue(R.equals(S.union(R)));
    assertTrue(R.equals(S1.union(R)));
    assertTrue(R.equals(S1.union(R1)));
    assertTrue(R2.equals(S1.union(R2)));
    assertTrue(R.equals(S12.union(R2)));
    assertTrue(R1.equals(S2.union(R12)));
    assertTrue(R3.equals(S12.union(R23)));
    assertTrue(R1.equals(S23.union(R12)));

    assertTrue(R.equals(R.union(S)));
    assertTrue(R1.equals(R1.union(S)));
    assertTrue(R.equals(R1.union(S1)));
    assertTrue(R1.equals(R1.union(S2)));
    assertTrue(R1.equals(R12.union(S2)));
    assertTrue(R.equals(R2.union(S12)));
    assertTrue(R1.equals(R12.union(S23)));
    assertTrue(R3.equals(R23.union(S12)));
  }

  /** Tests forming the intersection of two sets. */
  @Test
  public void testIntersection() {
    assertTrue(S.equals(S.intersection(S)));
    assertTrue(S.equals(S1.intersection(S)));
    assertTrue(S1.equals(S1.intersection(S1)));
    assertTrue(S.equals(S1.intersection(S2)));
    assertTrue(S2.equals(S12.intersection(S2)));
    assertTrue(S2.equals(S2.intersection(S12)));
    assertTrue(S2.equals(S12.intersection(S23)));
    assertTrue(S2.equals(S23.intersection(S12)));

    assertTrue(R.equals(R.intersection(R)));
    assertTrue(R1.equals(R1.intersection(R)));
    assertTrue(R1.equals(R1.intersection(R1)));
    assertTrue(R12.equals(R1.intersection(R2)));
    assertTrue(R12.equals(R12.intersection(R2)));
    assertTrue(R12.equals(R2.intersection(R12)));
    assertTrue(R123.equals(R12.intersection(R23)));
    assertTrue(R123.equals(R23.intersection(R12)));

    assertTrue(S.equals(S.intersection(R)));
    assertTrue(S1.equals(S1.intersection(R)));
    assertTrue(S.equals(S1.intersection(R1)));
    assertTrue(S1.equals(S1.intersection(R2)));
    assertTrue(S1.equals(S12.intersection(R2)));
    assertTrue(S.equals(S2.intersection(R12)));
    assertTrue(S1.equals(S12.intersection(R23)));
    assertTrue(S3.equals(S23.intersection(R12)));

    assertTrue(S.equals(R.intersection(S)));
    assertTrue(S.equals(R1.intersection(S)));
    assertTrue(S.equals(R1.intersection(S1)));
    assertTrue(S2.equals(R1.intersection(S2)));
    assertTrue(S.equals(R12.intersection(S2)));
    assertTrue(S1.equals(R2.intersection(S12)));
    assertTrue(S3.equals(R12.intersection(S23)));
    assertTrue(S1.equals(R23.intersection(S12)));
  }

  /** Tests forming the difference of two sets. */
  @Test
  public void testDifference() {
    assertTrue(S.equals(S.difference(S)));
    assertTrue(S1.equals(S1.difference(S)));
    assertTrue(S.equals(S1.difference(S1)));
    assertTrue(S1.equals(S1.difference(S2)));
    assertTrue(S1.equals(S12.difference(S2)));
    assertTrue(S.equals(S2.difference(S12)));
    assertTrue(S1.equals(S12.difference(S23)));
    assertTrue(S3.equals(S23.difference(S12)));

    assertTrue(S.equals(R.difference(R)));
    assertTrue(S.equals(R1.difference(R)));
    assertTrue(S.equals(R1.difference(R1)));
    assertTrue(S2.equals(R1.difference(R2)));
    assertTrue(S.equals(R12.difference(R2)));
    assertTrue(S1.equals(R2.difference(R12)));
    assertTrue(S3.equals(R12.difference(R23)));
    assertTrue(S1.equals(R23.difference(R12)));

    assertTrue(S.equals(S.difference(R)));
    assertTrue(S.equals(S1.difference(R)));
    assertTrue(S1.equals(S1.difference(R1)));
    assertTrue(S.equals(S1.difference(R2)));
    assertTrue(S2.equals(S12.difference(R2)));
    assertTrue(S2.equals(S2.difference(R12)));
    assertTrue(S2.equals(S12.difference(R23)));
    assertTrue(S2.equals(S23.difference(R12)));

    assertTrue(R.equals(R.difference(S)));
    assertTrue(R1.equals(R1.difference(S)));
    assertTrue(R1.equals(R1.difference(S1)));
    assertTrue(R12.equals(R1.difference(S2)));
    assertTrue(R12.equals(R12.difference(S2)));
    assertTrue(R12.equals(R2.difference(S12)));
    assertTrue(R123.equals(R12.difference(S23)));
    assertTrue(R123.equals(R23.difference(S12)));
  }

}
