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

import org.junit.Test;
import org.junit.BeforeClass;
import static org.junit.Assert.*;

/**
 * EvalutatorTest is a glassbox test of the Evalutator class.
 */
public class EvaluatorTest {

    /** Tests evaluating a constant polynomial. */
    @Test
    public void testConstant() {
        float[] A = new float[] {2};

        assertEquals(2, Evaluator.evalPoly1(A, 1), 1e-5);
        assertEquals(2, Evaluator.evalPoly1(A, 10), 1e-5);

        assertEquals(2, Evaluator.evalPoly2(A, 1), 1e-5);
        assertEquals(2, Evaluator.evalPoly2(A, 10), 1e-5);
    }

    /** Tests evaluating a linear polynomial. */
    @Test
    public void testLinear() {
        float[] A = new float[] {3, 2};

        assertEquals(3, Evaluator.evalPoly1(A, 0), 1e-5);
        assertEquals(5, Evaluator.evalPoly1(A, 1), 1e-5);
        assertEquals(1, Evaluator.evalPoly1(A, -1), 1e-5);
        assertEquals(23, Evaluator.evalPoly1(A, 10), 1e-5);

        float[] B = new float[] {2, 3};  // reverse of above

        assertEquals(3, Evaluator.evalPoly2(B, 0), 1e-5);
        assertEquals(5, Evaluator.evalPoly2(B, 1), 1e-5);
        assertEquals(1, Evaluator.evalPoly2(B, -1), 1e-5);
        assertEquals(23, Evaluator.evalPoly2(B, 10), 1e-5);
    }

    /** Tests evaluating a quadratic polynomial. */
    @Test
    public void testQuadratic() {
        float[] A = new float[] {-25, 0, 1};

        assertEquals(-25, Evaluator.evalPoly1(A, 0), 1e-5);
        assertEquals(0, Evaluator.evalPoly1(A, 5), 1e-5);
        assertEquals(0, Evaluator.evalPoly1(A, -5), 1e-5);
        assertEquals(11, Evaluator.evalPoly1(A, 6), 1e-5);

        float[] B = new float[] {1, 0, -25};  // reverse of above

        assertEquals(-25, Evaluator.evalPoly2(B, 0), 1e-5);
        assertEquals(0, Evaluator.evalPoly2(B, 5), 1e-5);
        assertEquals(0, Evaluator.evalPoly2(B, -5), 1e-5);
        assertEquals(11, Evaluator.evalPoly2(B, 6), 1e-5);
    }

    /** Tests evaluating another quadratic polynomial. */
    @Test
    public void testQuadratic2() {
        float[] A = new float[] {3, 6, 2};

        assertEquals(3, Evaluator.evalPoly1(A, 0), 1e-5);
        assertEquals(11, Evaluator.evalPoly1(A, 1), 1e-5);
        assertEquals(-1, Evaluator.evalPoly1(A, -1), 1e-5);
        assertEquals(23, Evaluator.evalPoly1(A, 2), 1e-5);

        float[] B = new float[] {2, 6, 3};  // reverse of above

        assertEquals(3, Evaluator.evalPoly2(B, 0), 1e-5);
        assertEquals(11, Evaluator.evalPoly2(B, 1), 1e-5);
        assertEquals(-1, Evaluator.evalPoly2(B, -1), 1e-5);
        assertEquals(23, Evaluator.evalPoly2(B, 2), 1e-5);
    }

    /** Tests evaluating a larger polynomial. */
    @Test
    public void testQuintic() {
        float[] A = new float[] {3, 1, 2, 0, 2};

        assertEquals(3, Evaluator.evalPoly1(A, 0), 1e-5);
        assertEquals(8, Evaluator.evalPoly1(A, 1), 1e-5);
        assertEquals(6, Evaluator.evalPoly1(A, -1), 1e-5);
        assertEquals(20213, Evaluator.evalPoly1(A, 10), 1e-5);

        float[] B = new float[] {2, 0, 2, 1, 3};

        assertEquals(3, Evaluator.evalPoly2(B, 0), 1e-5);
        assertEquals(8, Evaluator.evalPoly2(B, 1), 1e-5);
        assertEquals(6, Evaluator.evalPoly2(B, -1), 1e-5);
        assertEquals(20213, Evaluator.evalPoly2(B, 10), 1e-5);
    }

}
