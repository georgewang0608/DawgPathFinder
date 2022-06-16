package sets;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an immutable set of points on the real line that is easy to
 * describe, either because it is a finite set, e.g., {p1, p2, ..., pN}, or
 * because it excludes only a finite set, e.g., R \ {p1, p2, ..., pN}. As with
 * FiniteSet, each point is represented by a Java float with a non-infinite,
 * non-NaN value.
 */
public class SimpleSet {

  // TODO: fill in and document the representation
  //       Make sure to include the representation invariant (RI)
  //       and the abstraction function (AF).
  // Represents if the SimpleSet is a complement of Real number or not
  // if true, the SimpleSet will be an infinite set of real number in complement to vals
  // else, the simpleSet will be a finite set of real number represented by vals
  //
  // AF(true) = R \ {vals[1], vals[2], ..., vals[vals.length-2]}
  // AF(false) = {vals[1], vals[2], ..., vals[vals.length-2]}
  private final boolean complement;

  // Represents an immutable, finite set of points on the real line. Each point
  // is represented by a Java float with a non-infinite, non-NaN value and in sorted order.
  //
  // RI: -infinity = vals[0] < vals[1] < ... < vals[vals.length-1] = +infinity
  // AF(this) = {vals[1], vals[2], ..., vals[vals.length-2]}
  private final FiniteSet vals;

  /**
   * Creates a simple set containing only the given points.
   * @param vals Array containing the points to make into a SimpleSet
   * @spec.requires points != null and has no NaNs, no infinities, and no dups
   * @spec.effects this = {vals[0], vals[1], ..., vals[vals.length-1]}
   */
  public SimpleSet(float[] vals) {
    // TODO: implement this
    this.complement = false;
    this.vals = FiniteSet.of(vals);
  }

  /**
   * Private constructor that directly fills in the fields above.
   * @param complement Whether this = points or this = R \ points
   * @param points List of points that are in the set or not in the set
   * @spec.requires points != null
   * @spec.effects this = R \ points if complement else points
   */
  private SimpleSet(boolean complement, FiniteSet points) {
    // TODO: implement this
    this.complement = complement;
    this.vals = points;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof SimpleSet))
      return false;
    SimpleSet other = (SimpleSet) o;
    return this.vals.equals(other.vals) && (this.complement == other.complement);  // TODO: replace this with a correct check
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  /**
   * Returns the number of points in this set.
   * @return N      if this = {p1, p2, ..., pN} and
   *         infty  if this = R \ {p1, p2, ..., pN}
   */
  public float size() {
    // TODO: implement this
    if (complement) {
      return Float.POSITIVE_INFINITY;
    } else {
      return vals.size();
    }
  }

  /**
   * Returns a string describing the points included in this set.
   * @return the string "R" if this contains every point,
   *     a string of the form "R \ {p1, p2, .., pN}" if this contains all
   *        but {@literal N > 0} points, or
   *     a string of the form "{p1, p2, .., pN}" if this contains
   *        {@literal N >= 0} points,
   *     where p1, p2, ... pN are replaced by the individual numbers.
   */
  public String toString() {
    // TODO: implement this with a loop. document its invariant
    //       a StringBuilder may be useful for creating the string
    StringBuilder ret = new StringBuilder();
    List<Float> simpleSet = vals.getPoints();
    if (complement) {
      ret.append("R \\ {");
      if (simpleSet.size() != 0) {
        ret.append(simpleSet.get(0));
      }
      // inv: ret = R \ {val[0], val[1], ... val[i-1]
      for (int i = 1; i < simpleSet.size(); i++) {
        ret.append(", " + simpleSet.get(i));
      }
    } else {
      ret.append("{");
      if (simpleSet.size() != 0) {
        ret.append(simpleSet.get(0));
      }
      // inv: ret = {val[0], val[1], ... val[i-1]
      for (int i = 1; i < simpleSet.size(); i++) {
        ret.append(", " + simpleSet.get(i));
      }
    }
    ret.append("}");
    return ret.toString();
  }

  /**
   * Returns a set representing the points R \ this.
   * @return R \ this
   */
  public SimpleSet complement() {
    // TODO: implement this method
    //       include sufficient comments to see why it is correct (hint: cases)
    // when complement is true then the complement would just be a simple sets with
    // value vals else it should return R/vals
    if (complement) {
      return new SimpleSet(false, vals);
    } else {
      return new SimpleSet(true, vals);
    }
  }

  /**
   * Returns the union of this and other.
   * @param other Set to union with this one.
   * @spec.requires other != null
   * @return this union other
   */
  public SimpleSet union(SimpleSet other) {
    // TODO: implement this method
    //       include sufficient comments to see why it is correct (hint: cases)
    if (this.complement == other.complement) {
      // case 1: both are infinite sets, we wants the intersection of the number that both of
      // the sets doesnt have, and return its complement
      if (this.complement) {
        return new SimpleSet(true, this.vals.intersection(other.vals));
      } else {
        // case 2: both are finite sets, use union function of finite set from finite set
        return new SimpleSet(false, this.vals.union(other.vals));
      }
    } else {
      // case 3: this set is infinite and the other set  is finite, we want the intersection between the value that this
      // doest not have and the value other have and return the complement of that difference of the value this does
      // not have to the intersection.
      if (this.complement) {
        return new SimpleSet(true, this.vals.difference(this.vals.intersection(other.vals)));
      } else {
        // case 3: other set is infinite and this set is finite, same logic as case 3 simply switch this with other
        return new SimpleSet(true, other.vals.difference(this.vals.intersection(other.vals)));
      }
    }
  }

  /**
   * Returns the intersection of this and other.
   * @param other Set to intersect with this one.
   * @spec.requires other != null
   * @return this intersect other
   */
  public SimpleSet intersection(SimpleSet other) {
    // TODO: implement this method
    //       include sufficient comments to see why it is correct
    // NOTE: There is more than one correct way to implement this.
    // Case 1: both sets are infinite, then in order to find the intersection, we want the complement
    // of the union of the values that both sets do not have
    if (this.complement == other.complement) {
      if (this.complement) {
        return new SimpleSet(true, this.vals.union(other.vals));
      } else {
        // Case 2: both sets are finite, we can just use the intersection method of the finiteset
        return new SimpleSet(false, this.vals.intersection(other.vals));
      }
    } else {
      // Case 3: If this set is infinite and other set is finite, then in order to find the intersection,
      // we want to get value that both this and other set have. In order to do so, we find the difference
      // between other set and the value this set does not have. That means these value also exits in this set
      if (this.complement) {
        return new SimpleSet(false, other.vals.difference(this.vals));
      } else {
        // Case 4: This set is finite and other set is infinite, same logic as case 3 just switch this and other.
        return new SimpleSet(false, this.vals.difference(other.vals));
      }
    }

  }

  /**
   * Returns the difference of this and other.
   * @param other Set to difference from this one.
   * @spec.requires other != null
   * @return this minus other
   */
  public SimpleSet difference(SimpleSet other) {
    // TODO: implement this method
    //       include sufficient comments to see why it is correct
    // NOTE: There is more than one correct way to implement this.
    // Case 1: Both sets are inifinite, in order to get the difference between this and other, we want the values
    // that this have but other does not. So we want the complement of the difference between number
    // that other does not have and this does not have.
    if (this.complement == other.complement) {
      if (this.complement) {
        return new SimpleSet(false, other.vals.difference(this.vals));
      } else {
        // Case 2: Both sets are finite, in this case, we can just call the difference function of finiteset
        return new SimpleSet(false, this.vals.difference(other.vals));
      }
    } else {
      // Case 3: this set is inifinte and other set is finite, in this case, we want the complement of the union
      // of the values that this does not have and other has
      if (this.complement) {
        return new SimpleSet(true, this.vals.union(other.vals));
      } else {
        // Case 4: this set is finite and other set is infinte, we know its going to be finite so complement will be
        // false, and the difference will just be the intersection of the values that other don't have and the value
        // this have
        return new SimpleSet(false, this.vals.intersection(other.vals));
      }
    }


  }

}
