package tasks;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

/**
 * DependencyTest is a glassbox test of the Dependency class.
 */
public class DependencyTest {

    // Tasks to be used in this test suite.
    private static Task task1, task2, empty;

    @BeforeClass
    public static void setup() {
        task1 = new Task("task1", "desc1", "team1");
        task2 = new Task("task2", "desc2", "team1");
        empty = new Task("", "", "");
    }

    /** Test creating tasks with correct parameters. */
    @Test
    public void testCreationCorrect() {
        assertEquals(task1, new Dependency(task1,task2).getBeforeTask());
        assertEquals(task2, new Dependency(task1,task2).getAfterTask());
        assertEquals(task1, new Dependency(task1,task1).getBeforeTask());
        assertEquals(task1, new Dependency(task1,task1).getAfterTask());
    }

    @Test(expected=AssertionError.class)
    public void testCreationFirstArgNull() {
        Dependency shouldFail = new Dependency(null, task1);
    }

    @Test(expected=AssertionError.class)
    public void testCreationSecondArgNull() {
        Dependency shouldFail = new Dependency(task1, null);
    }

    @Test
    public void testToString() {
        Dependency oneTwo = new Dependency(task1, task2);
        Dependency oneEmpty = new Dependency(task1, empty);
        Dependency emptyTwo = new Dependency(empty, task2);
        Dependency bothEmpty = new Dependency(empty, empty);

        assertEquals("Dependency: task1 -> task2", oneTwo.toString());
        assertEquals("Dependency: task1 -> ", oneEmpty.toString());
        assertEquals("Dependency:  -> task2", emptyTwo.toString());
        assertEquals("Dependency:  -> ", bothEmpty.toString());
    }
}
