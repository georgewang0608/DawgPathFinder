package sets;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;

/**
 * FiniteSetTest is a glassbox test of the FiniteSet class.
 */
public class FiniteSetTest {

  /** Test creating sets. */
  @Test
  public void testCreation() {
    assertEquals(Arrays.asList(),
        FiniteSet.of(new float[0]).getPoints());         // empty
    assertEquals(Arrays.asList(1f),
        FiniteSet.of(new float[] {1}).getPoints());      // one item
    assertEquals(Arrays.asList(1f, 2f),
        FiniteSet.of(new float[] {1, 2}).getPoints());   // two items
    assertEquals(Arrays.asList(1f, 2f),
        FiniteSet.of(new float[] {2, 1}).getPoints());   // two out-of-order
    assertEquals(Arrays.asList(-2f, 2f),
        FiniteSet.of(new float[] {2, -2}).getPoints());  // negative
  }

  // Some example sets used by the tests below.
  private static FiniteSet S0 = FiniteSet.of(new float[0]);
  private static FiniteSet S1 = FiniteSet.of(new float[] {1});
  private static FiniteSet S12 = FiniteSet.of(new float[] {1, 2});

  /** Test set equality. */
  @Test
  public void testEquals() {
    assertTrue(S0.equals(S0));
    assertFalse(S0.equals(S1));
    assertFalse(S0.equals(S12));

    assertFalse(S1.equals(S0));
    assertTrue(S1.equals(S1));
    assertFalse(S1.equals(S12));

    assertFalse(S12.equals(S0));
    assertFalse(S12.equals(S1));
    assertTrue(S12.equals(S12));
  }

  /** Test set size. */
  @Test
  public void testSize() {
    assertEquals(S0.size(), 0);
    assertEquals(S1.size(), 1);
    assertEquals(S12.size(), 2);
  }
  
  // TODO: Feel free to initialize (private static) FiniteSet objects here
  //       if you plan to use them for the tests below.
  private static FiniteSet S2 = FiniteSet.of(new float[] {2});
  private static FiniteSet S13 = FiniteSet.of(new float[] {1,3});
  private static FiniteSet S3 = FiniteSet.of(new float[] {3});

  /** Tests forming the union of two finite sets. */
  @Test
  public void testUnion() {
    // TODO: implement this
    assertEquals(S0.union(S0), S0);
    assertEquals(S0.union(S1), S1);
    assertEquals(S1.union(S2), S12);
    assertEquals(S1.union(S1), S1);
  }

  /** Tests forming the intersection of two finite sets. */
  @Test
  public void testIntersection() {
    // TODO: implement this
    assertEquals(S0.intersection(S0), S0);
    assertEquals(S1.intersection(S1), S1);
    assertEquals(S1.intersection(S2), S0);
    assertEquals(S12.intersection(S13), S1);
  }

  /** Tests forming the difference of two finite sets. */
  @Test
  public void testDifference() {
    // TODO: implement this
    assertEquals(S0.difference(S0), S0);
    assertEquals(S1.difference(S1), S0);
    assertEquals(S1.difference(S2), S1);
    assertEquals(S12.difference(S13),S2);
    assertEquals(S13.difference(S12), S3);
  }
}
