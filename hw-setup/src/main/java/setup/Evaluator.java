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
 * Evaluator contains static methods to evaluate polynomials.
 */
public class Evaluator {

  // Version of evalPoly from HW1 problem 9:
  /**
   * @param A (Non-null) list of coefficients, representing the polynomial
   *      A[0] + A[1] x + A[2] x^2 + ... + A[n-1] x^{n-1}
   * @param v Value at which to evaluate the polynomial (x).
   * @spec.requires {@literal A.length > 0}
   * @return the value of the polynomial at point v
   */
  public static float evalPoly1(float[] A, int v) {
    int i = A.length - 1;
    int j = 0;
    float val = A[i];

    // Inv: val = A[i] v^0 + A[i+1] v^1 + ... + A[n-1] v^j and i+j = n - 1
    while (j != A.length - 1) {
      j = j + 1;
      i = i - 1;
      val = val * v + A[i];
    }

    // Post: val = A[0] + A[1] v + A[2] v^2 + ... + A[n-1] v^{n-1}
    return val;
  }

  // Version which stores the coefficients in the reverse order:
  /**
   * @param A (Non-null) list of coefficients, representing the polynomial
   *      A[n-1] + A[n-2] x + ... + A[1] x^{n-2} + A[0] x^{n-1}
   * @param v Value at which to evaluate the polynomial (x).
   * @spec.requires {@literal A.length > 0}
   * @return the value of the polynomial at point v
   */
  public static float evalPoly2(float[] A, int v) {
    float val = 0;  // NOTE: do not change this line

    int j = -1;

    // Note that the invariant does not include "i" anymore, so that may
    // not be needed in this version.

    // Inv: val = A[j] v^0 + ... + A[1] v^{j-1} + A[0] v^j
    while (j != A.length - 1) {
      j = j + 1;
      val = val * v + A[j];
    }

    // Post: val = A[n-1] + A[n-2] v + ... + A[1] v^{n-2} + A[0] v^{n-1}
    return val;
  }

}
