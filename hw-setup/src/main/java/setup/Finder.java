/*
 * Copyright (C) 2022 Kevin Zatloukal.  All rights reserved.  Permission is
 * hereby granted to students registered for University of Washington
 * CSE 331 for use solely during Spring Quarter 2022 for purposes of
 * the course.  No other use, copying, distribution, or modification
 * is permitted without prior written consent. Copyrights for
 * third-party components of this work must be honored.  Instructors
 * interested in reusing these course materials should contact the
 * author.
 */

package setup;

/**
 * Finder contains static methods to find the index of a value in an array.
 */
public class Finder {

  // Version of find from HW1 problem 8:
  /**
   * @param A (Non-null) array
   * @param val Value to find in the array.
   * @return an index i such that A[i] = val or -1 if none exists
   */
  public static int find1(int[] A, int val) {
    int i = 0;

    // Inv: A[0] != val, A[1] != val, ..., A[i-1] != val
    while (i != A.length) {
      assert checkInv(A, val, i-1);

      if (A[i] == val) {
        return i;
      }

      i = i + 1;
    }

    assert checkInv(A, val, i-1);
    return -1;  // val not in A
  }

  /**
   * @param A (Non-null) array
   * @param val Value to find in the array.
   * @return an index i such that A[i] = val or -1 if none exists
   */
  public static int find2(int[] A, int val) {
    int i = -1;

    // Inv: A[0] != val, A[1] != val, ..., A[i] != val
    while (i  < A.length - 1) {
      assert checkInv(A, val, i);  // NOTE: this statement must remain here
      i = i + 1;  // NOTE: this statement must remain here
      if (A[i] == val) {
        return i;
      }

    }

    assert checkInv(A, val, i);  // NOTE: this statement must remain here
    return -1;  // val not in A
  }

  /**
   * @param A (Non-null) array
   * @param val Value to find in the array.
   * @return an index i such that A[i] = val or -1 if none exists
   */
  public static int find3(int[] A, int val) {
    int i = -1;

    // Inv: A[0] != val, A[1] != val, ..., A[i-1] != val
    while (i < A.length - 1) {
      assert checkInv(A, val, i-1);  // NOTE: this statement must remain here
      i = i + 1;  // NOTE: this statement must remain here
      if (A[i] == val) {
        return i;
      }

    }

    assert checkInv(A, val, i-1);  // NOTE: this statement must remain here
    return -1;  // val not in A
  }

  /**
   * @param A (Non-null) array
   * @param val Value to find in the array.
   * @return an index i such that A[i] = val or -1 if none exists
   */
  public static int find4(int[] A, int val) {
    int i = -1;

    // Inv: A[0] != val, A[1] != val, ..., A[i] != val
    while (i < A.length - 1) {
      assert checkInv(A, val, i);  // NOTE: this statement must remain here

      if (A[i + 1] == val ) {
        return i+1;
      }
      i = i + 1;  // NOTE: this statement must remain here
    }

    assert checkInv(A, val, i);  // NOTE: this statement must remain here
    return -1;  // val not in A
  }

  // NOTE: do not modify this method!
  /**
   * Private helper method to check the invariants of the methods above.
   * @param A (Non-null) array
   * @param val Value to look for in the array.
   * @return true iff A[0] != val, A[1] != val, ..., A[i] != val
   */
  private static boolean checkInv(int[] A, int val, int i) {
    for (int j = 0; j <= i; j++) {
      if (A[j] == val)
        return false;
    }
    return true;
  }

}
