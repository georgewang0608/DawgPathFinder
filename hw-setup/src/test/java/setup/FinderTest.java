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
 * FinderTest is a glassbox test of the Finder class.
 */
public class FinderTest {

    /** Tests Finder.find1 */
    @Test
    public void testFind1() {

        // Test empty
        int[] A = new int[] {};
        assertEquals("find1([], 3)", -1, Finder.find1(A, 3));
        assertEquals("find1([], -3)", -1, Finder.find1(A, -3));

        // Test singleton
        A = new int[] { 2 };
        assertEquals("find1([2], 1)", -1, Finder.find1(A, 1));
        assertEquals("find1([2], 2)", 0, Finder.find1(A, 2));
        assertEquals("find1([2], 3)", -1, Finder.find1(A, 3));

        // Test pairs
        A = new int[] { 2, 5 };
        assertEquals("find1([2, 5], 1)", -1, Finder.find1(A, 1));
        assertEquals("find1([2, 5], 2)", 0, Finder.find1(A, 2));
        assertEquals("find1([2, 5], 3)", -1, Finder.find1(A, 3));
        assertEquals("find1([2, 5], 4)", -1, Finder.find1(A, 4));
        assertEquals("find1([2, 5], 5)", 1, Finder.find1(A, 5));
        assertEquals("find1([2, 5], 6)", -1, Finder.find1(A, 6));

        // Test long example
        A = new int[] { 17, 5, 12, 2, 3, 8, 15, -1, 27 };
        assertEquals("find1(A, 1)", -1, Finder.find1(A, 1));
        assertEquals("find1(A, -1)", 7, Finder.find1(A, -1));
        assertEquals("find1(A, 17)", 0, Finder.find1(A, 17));
        assertEquals("find1(A, 27)", 8, Finder.find1(A, 27));
    }

    /** Tests Finder.find2 */
    @Test
    public void testFind2() {

        // Test empty
        int[] A = new int[] {};
        assertEquals("find2([], 3)", -1, Finder.find2(A, 3));
        assertEquals("find2([], -3)", -1, Finder.find2(A, -3));

        // Test singleton
        A = new int[] { 2 };
        assertEquals("find2([2], 1)", -1, Finder.find2(A, 1));
        assertEquals("find2([2], 2)", 0, Finder.find2(A, 2));
        assertEquals("find2([2], 3)", -1, Finder.find2(A, 3));

        // Test pairs
        A = new int[] { 2, 5 };
        assertEquals("find2([2, 5], 1)", -1, Finder.find2(A, 1));
        assertEquals("find2([2, 5], 2)", 0, Finder.find2(A, 2));
        assertEquals("find2([2, 5], 3)", -1, Finder.find2(A, 3));
        assertEquals("find2([2, 5], 4)", -1, Finder.find2(A, 4));
        assertEquals("find2([2, 5], 5)", 1, Finder.find2(A, 5));
        assertEquals("find2([2, 5], 6)", -1, Finder.find2(A, 6));

        // Test long example
        A = new int[] { 17, 5, 12, 2, 3, 8, 15, -1, 27 };
        assertEquals("find2(A, 1)", -1, Finder.find2(A, 1));
        assertEquals("find2(A, -1)", 7, Finder.find2(A, -1));
        assertEquals("find2(A, 17)", 0, Finder.find2(A, 17));
        assertEquals("find2(A, 27)", 8, Finder.find2(A, 27));
    }

    /** Tests Finder.find3 */
    @Test
    public void testFind3() {

        // Test empty
        int[] A = new int[] {};
        assertEquals("find3([], 3)", -1, Finder.find3(A, 3));
        assertEquals("find3([], -3)", -1, Finder.find3(A, -3));

        // Test singleton
        A = new int[] { 2 };
        assertEquals("find3([2], 1)", -1, Finder.find3(A, 1));
        assertEquals("find3([2], 2)", 0, Finder.find3(A, 2));
        assertEquals("find3([2], 3)", -1, Finder.find3(A, 3));

        // Test pairs
        A = new int[] { 2, 5 };
        assertEquals("find3([2, 5], 1)", -1, Finder.find3(A, 1));
        assertEquals("find3([2, 5], 2)", 0, Finder.find3(A, 2));
        assertEquals("find3([2, 5], 3)", -1, Finder.find3(A, 3));
        assertEquals("find3([2, 5], 4)", -1, Finder.find3(A, 4));
        assertEquals("find3([2, 5], 5)", 1, Finder.find3(A, 5));
        assertEquals("find3([2, 5], 6)", -1, Finder.find3(A, 6));

        // Test long example
        A = new int[] { 17, 5, 12, 2, 3, 8, 15, -1, 27 };
        assertEquals("find3(A, 1)", -1, Finder.find3(A, 1));
        assertEquals("find3(A, -1)", 7, Finder.find3(A, -1));
        assertEquals("find3(A, 17)", 0, Finder.find3(A, 17));
        assertEquals("find3(A, 27)", 8, Finder.find3(A, 27));
    }

    /** Tests Finder.find4 */
    @Test
    public void testFind4() {

        // Test empty
        int[] A = new int[] {};
        assertEquals("find4([], 3)", -1, Finder.find4(A, 3));
        assertEquals("find4([], -3)", -1, Finder.find4(A, -3));

        // Test singleton
        A = new int[] { 2 };
        assertEquals("find4([2], 1)", -1, Finder.find4(A, 1));
        assertEquals("find4([2], 2)", 0, Finder.find4(A, 2));
        assertEquals("find4([2], 3)", -1, Finder.find4(A, 3));

        // Test pairs
        A = new int[] { 2, 5 };
        assertEquals("find4([2, 5], 1)", -1, Finder.find4(A, 1));
        assertEquals("find4([2, 5], 2)", 0, Finder.find4(A, 2));
        assertEquals("find4([2, 5], 3)", -1, Finder.find4(A, 3));
        assertEquals("find4([2, 5], 4)", -1, Finder.find4(A, 4));
        assertEquals("find4([2, 5], 5)", 1, Finder.find4(A, 5));
        assertEquals("find4([2, 5], 6)", -1, Finder.find4(A, 6));

        // Test long example
        A = new int[] { 17, 5, 12, 2, 3, 8, 15, -1, 27 };
        assertEquals("find4(A, 1)", -1, Finder.find4(A, 1));
        assertEquals("find4(A, -1)", 7, Finder.find4(A, -1));
        assertEquals("find4(A, 17)", 0, Finder.find4(A, 17));
        assertEquals("find4(A, 27)", 8, Finder.find4(A, 27));
    }    
}
