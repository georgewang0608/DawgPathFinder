package sets;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents an immutable, finite set of points on the real line. Each point
 * is represented by a Java float with a non-infinite, non-NaN value.
 */
public class FiniteSet {

  /**
   * Creates a FiniteSet containing just the given points.
   * @param points Array containing the points to make into a FiniteSet
   * @spec.requires points != null and has no NaNs, no infinities, and no dups
   * @return FiniteSet containing the same points given
   */
  static public FiniteSet of(float[] points) {
    points = Arrays.copyOf(points, points.length);
    Arrays.sort(points);

    float[] vals = new float[points.length + 2];
    vals[0] = Float.NEGATIVE_INFINITY;

    // Inv: vals[0], vals[1], ..., vals[i] = -infty, points[0], ..., points[i-1]
    for (int i = 0; i < points.length; i++) {
      assert Float.isFinite(points[i]);          // check for NaN or +/-infty
      assert i == 0 || points[i-1] < points[i];  // check for dups
      vals[i+1] = points[i];
    }

    vals[points.length+1] = Float.POSITIVE_INFINITY;
    return new FiniteSet(vals);
  }

  // Points are stored in an array, in sorted order, with an extra -infinity at
  // the front and +infinity at the end to simplify union etc.
  //
  // RI: -infinity = vals[0] < vals[1] < ... < vals[vals.length-1] = +infinity
  // AF(this) = {vals[1], vals[2], ..., vals[vals.length-2]}
  private final float[] vals;

  /**
   * Private constructor takes an array of values that already satisfy
   * @spec.requires vals is sorted, +/-infinity at ends, and has no dups
   * @spec.effects this = {vals[1], vals[2], ..., vals[vals.length-2]}
   */
  private FiniteSet(float[] vals) {
    this.vals = vals;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof FiniteSet))
      return false;

    FiniteSet other = (FiniteSet) o;
    // Since all arrays contain the infinities and the points in the set are in
    // sorted order, the points are the same iff the arrays are be identical.
    return Arrays.equals(vals, other.vals);
  }

  @Override
  public int hashCode() {
    return Arrays.hashCode(vals);
  }

  /**
   * Returns the number of points in this set.
   * @return the number of points in this
   */
  public int size() {
    // len(this) = len([vals[1], vals[2], ..., vals[vals.length-2]]) 
    //           = (vals.length - 2) - 1 + 1 = vals.length - 2
    return vals.length - 2;
  }

  /**
   * Returns a list containing the points in this set.
   * @return A list containing the points in this set.
   */
  public List<Float> getPoints() {
    ArrayList<Float> points = new ArrayList<>();

    // Inv: points = [vals[1], ..., vals[i-1]]
    for (int i = 1; i < vals.length - 1; i++)
      points.add(vals[i]);

    return points;
  }

  /**
   * Returns the union of this and other.
   * @param other Set to union with this one.
   * @spec.requires other != null
   * @return set of points in either this or other (or both)
   */
  public FiniteSet union(FiniteSet other) {
    float[] S = this.vals;   // new names to simplify notation
    float[] T = other.vals;

    // Pre: -infty = S[0] < S[1] < ... < S[n-1] = +infty, where n = S.length
    //      -infty = T[0] < T[1] < ... < T[m-1] = +infty, where m = T.length

    float[] U = new float[S.length + T.length - 2];
    U[0] = Float.NEGATIVE_INFINITY;

    int i = 1;
    int j = 1;
    int k = 1;

    // Inv: Pre and
    //      (1) -infty = U[0] < U[1] < ... < U[k-1] < min(S[i], T[j]) and
    //      (2) {U[1], ..., U[k-1]} = union of
    //              {S[1], ..., S[i-1]} and {T[1], ..., T[j-1]}
    while (S[i] < Float.POSITIVE_INFINITY || T[j] < Float.POSITIVE_INFINITY) {
      if (S[i] < T[j]) {
        U[k] = S[i];
        i = i + 1;
        k = k + 1;
      } else if (S[i] > T[j]) {
        U[k] = T[j];
        j = j + 1;
        k = k + 1;
      } else {  // S[i] == T[j]
        U[k] = S[i];
        i = i + 1;
        j = j + 1;
        k = k + 1;
      }
    }

    U[k] = Float.POSITIVE_INFINITY;

    // Post: -infty = U[0] < U[1] < ... < U[k] = +infinity and
    //       {U[1], ..., U[k-1]} = union of
    //           {S[1], ..., S[i-1]} and {T[1], ..., T[j-1]}
    return new FiniteSet(Arrays.copyOf(U, k+1));
  }

  /**
   * Returns the intersection of this and other.
   * @param other Set to intersect with this one.
   * @spec.requires other != null
   * @return set of points in both this and other
   */
  public FiniteSet intersection(FiniteSet other) {
    float[] S = this.vals;   // new names to simplify notation
    float[] T = other.vals;

    // Pre: -infty = S[0] < S[1] < ... < S[n-1] = +infty, where n = S.length
    //      -infty = T[0] < T[1] < ... < T[m-1] = +infty, where m = T.length

    float[] U = new float[S.length + 2];  // can't be longer than this one
    U[0] = Float.NEGATIVE_INFINITY;

    int i = 1;
    int j = 1;
    int k = 1;

    // Inv: Pre and
    //      (1) -infty = U[0] < U[1] < ... < U[k-1] < min(S[i], T[j]) and
    //      (2) max(S[i-1], T[j-1]) < min(S[i], T[j]) and
    //      (3) {U[1], ..., U[k-1]} = intersection of
    //              {S[1], ..., S[i-1]} and {T[1], ..., T[j-1]}
    while (S[i] < Float.POSITIVE_INFINITY || T[j] < Float.POSITIVE_INFINITY) {
      if (S[i] < T[j]) {
        i = i + 1;
      } else if (S[i] > T[j]) {
        j = j + 1;
      } else {  // S[i] == T[j]
        U[k] = S[i];
        i = i + 1;
        j = j + 1;
        k = k + 1;
      }
    }

    U[k] = Float.POSITIVE_INFINITY;

    // Post: -infty = U[0] < U[1] < ... < U[k] = +infinity and
    //       {U[1], ..., U[k-1]} = intersection of
    //           {S[1], ..., S[i-1]} and {T[1], ..., T[j-1]}
    return new FiniteSet(Arrays.copyOf(U, k+1));
  }

  /**
   * Returns the difference of this and other.
   * @param other Set to difference from this one.
   * @spec.requires other != null
   * @return set of points in this but not other
   */
  public FiniteSet difference(FiniteSet other) {
    float[] S = this.vals;   // new names to simplify notation
    float[] T = other.vals;

    // Pre: -infty = S[0] < S[1] < ... < S[n-1] = +infty, where n = S.length
    //      -infty = T[0] < T[1] < ... < T[m-1] = +infty, where m = T.length

    float[] U = new float[S.length + T.length - 2];
    U[0] = Float.NEGATIVE_INFINITY;

    int i = 1;
    int j = 1;
    int k = 1;

    // Inv: Pre and
    //      (1) -infty = U[0] < U[1] < ... < U[k-1] < min(S[i], T[j]) and
    //      (2) max(S[i-1], T[j-1]) < min(S[i], T[j]) and
    //      (3) {U[1], ..., U[k-1]} = {S[1], ..., S[i-1]} \ {T[1], ..., T[j-1]}
    while (S[i] < Float.POSITIVE_INFINITY || T[j] < Float.POSITIVE_INFINITY) {
      if (S[i] < T[j]) {
        // From what we already seen in our analysis of intersection:
        //  * Part (1) of the invariant will always hold when we either
        //    (a) leave k and U[k] unchanged or
        //    (b) set U[k] to the smaller of S[i] and T[j] and increment k.
        //  * Part (2) of the invariant will always hold because the code below
        //    increments i and j in same ways as we did with intersection, and
        //    part (2) of the invariant does not use U or k.
        //
        // Thus, all that remains is to decide, in each case, whether to assign
        // U[k] and increment k or leave both unchanged. We need to do so in
        // each case in order to make part (3) hold.
        // Here, we decided to set assign U[k] and increment k (option b). 
        
        // Inv and S[i] < T[j]
        U[k] = S[i];  // as with intersect, we have S[i] not in T[1..j-1]
        // -infty = U[0] < U[1] < ... < U[k] < min(S[i+1], T[j]) and
        // max(S[i], T[j-1]) < min(S[i+1], T[j]) and
        // {U[1], ..., U[k]} = {S[1], ..., S[i]} \ {T[1], ..., T[j-1]}
        k = k + 1;
        i = i + 1;
      } else if (S[i] > T[j]) {
        // Inv and S[i] > T[j]
        ;  // as with intersect, T[j] is not in {S[1], .., S[i-1]}
        // -infty = U[0] < U[1] < ... < U[k-1] < min(S[i], T[j+1]) and
        // max(S[i-1], T[j]) < min(S[i], T[j+1]) and
        // {U[1], ..., U[k-1]} = {S[1], ..., S[i-1]} \ {T[1], ..., T[j]}
        j = j + 1;
      } else {  // S[i] == T[j]
        // Inv and S[i] = T[j]
        ;  // S[i] also appears as T[j], so the difference is unchanged
        // -infty = U[0] < U[1] < ... < U[k-1] < min(S[i+1], T[j+1]) and
        // max(S[i], T[j]) < min(S[i+1], T[j+1]) and
        // {U[1], ..., U[k-1]} = {S[1], ..., S[i]} \ {T[1], ..., T[j]}
        i = i + 1;
        j = j + 1;
      }
    }

    U[k] = Float.POSITIVE_INFINITY;

    // Post: -infty = U[0] < U[1] < ... < U[k] = +infinity and
    //       {U[1], ..., U[k-1]} = {S[1], ..., S[n]} \ {T[1], ..., T[m]}
    return new FiniteSet(Arrays.copyOf(U, k+1));
  }

}
