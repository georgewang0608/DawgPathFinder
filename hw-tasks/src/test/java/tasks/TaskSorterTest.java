package tasks;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

/**
 * TaskSorterTest is a glassbox test of the TaskSorter class.
 */
public class TaskSorterTest {

    // Example from the Wikipedia article on topological sort:

    private static Task t2 = new Task("2", "two", "A");
    private static Task t3 = new Task("3", "three", "A");
    private static Task t5 = new Task("5", "five", "B");
    private static Task t7 = new Task("7", "seven", "B");
    private static Task t8 = new Task("8", "eight", "B");
    private static Task t9 = new Task("9", "nine", "A");
    private static Task t10 = new Task("10", "ten", "A");
    private static Task t11 = new Task("11", "eleven", "A");

    private static Dependency dep_11_2 = new Dependency(t11, t2);
    private static Dependency dep_11_9 = new Dependency(t11, t9);
    private static Dependency dep_11_10 = new Dependency(t11, t10);
    private static Dependency dep_5_11 = new Dependency(t5, t11);
    private static Dependency dep_7_11 = new Dependency(t7, t11);
    private static Dependency dep_7_8 = new Dependency(t7, t8);
    private static Dependency dep_8_9 = new Dependency(t8, t9);
    private static Dependency dep_3_8 = new Dependency(t3, t8);
    private static Dependency dep_3_10 = new Dependency(t3, t10);

    private TaskSorter sorter;

    // Helper routine to add all the tasks above.
    private void addTasks() {
      sorter.addTask(t2);
      sorter.addTask(t3);
      sorter.addTask(t5);
      sorter.addTask(t7);
      sorter.addTask(t8);
      sorter.addTask(t9);
      sorter.addTask(t10);
      sorter.addTask(t11);
    }

    // Helper routine to add all the dependencies between the tasks above.
    private void addDependencies() {
        sorter.addDependency(dep_11_2);
        sorter.addDependency(dep_11_9);
        sorter.addDependency(dep_11_10);
        sorter.addDependency(dep_5_11);
        sorter.addDependency(dep_7_11);
        sorter.addDependency(dep_7_8);
        sorter.addDependency(dep_8_9);
        sorter.addDependency(dep_3_8);
        sorter.addDependency(dep_3_10);
    }

    @Before
    public void initTaskSorter() {
        sorter = new TaskSorter();
    }

    @Test
    public void testCreation() {
        assertTrue(sorter.getTasks().isEmpty());
    }

    @Test
    public void testTasks() {
        assertEquals(new HashSet<>(Arrays.asList()), sorter.getTasks());

        // single task
        sorter.addTask(t2);
        assertEquals(new HashSet<>(Arrays.asList(t2)), sorter.getTasks());

        // two tasks
        sorter.addTask(t3);
        assertEquals(new HashSet<>(Arrays.asList(t2, t3)), sorter.getTasks());

        // repeated task addition shouldn't cause error
        sorter.addTask(t3);
        assertEquals(new HashSet<>(Arrays.asList(t2, t3)), sorter.getTasks());

        // even more tasks
        sorter.addTask(t5);
        sorter.addTask(t7);
        sorter.addTask(t8);
        sorter.addTask(t9);
        sorter.addTask(t10);
        sorter.addTask(t11);
        assertEquals(
            new HashSet<>(Arrays.asList(t2, t3, t5, t7, t8, t9, t10, t11)),
            sorter.getTasks());

        // adding everything again shouldn't change this
        addTasks();
        assertEquals(
            new HashSet<>(Arrays.asList(t2, t3, t5, t7, t8, t9, t10, t11)),
            sorter.getTasks());
    }

    @Test
    public void testDependencies() {
        addTasks();

        assertEquals(new HashSet<Dependency>(),
            sorter.getOutgoingDependencies(t2));
        assertEquals(new HashSet<Dependency>(),
            sorter.getOutgoingDependencies(t3));
        assertEquals(new HashSet<Dependency>(),
            sorter.getOutgoingDependencies(t5));
        assertEquals(new HashSet<Dependency>(),
            sorter.getOutgoingDependencies(t11));

        addDependencies();

        assertEquals(new HashSet<Dependency>(),
            sorter.getOutgoingDependencies(t2));
        assertEquals(new HashSet<>(Arrays.asList(dep_3_8, dep_3_10)),
            sorter.getOutgoingDependencies(t3));
        assertEquals(new HashSet<>(Arrays.asList(dep_5_11)),
            sorter.getOutgoingDependencies(t5));
        assertEquals(new HashSet<>(Arrays.asList(dep_7_11, dep_7_8)),
            sorter.getOutgoingDependencies(t7));
        assertEquals(new HashSet<>(Arrays.asList(dep_8_9)),
            sorter.getOutgoingDependencies(t8));
        assertEquals(new HashSet<Dependency>(),
            sorter.getOutgoingDependencies(t9));
        assertEquals(new HashSet<Dependency>(),
            sorter.getOutgoingDependencies(t10));
        assertEquals(
            new HashSet<>(Arrays.asList(dep_11_2, dep_11_9, dep_11_10)),
            sorter.getOutgoingDependencies(t11));
    }

    @Test
    public void testSortTasks() {
        // empty graph
        assertEquals(new ArrayList<>(), sorter.sortTasks());

        // one node graph
        sorter.addTask(t2);
        assertEquals(Arrays.asList(t2), sorter.sortTasks());

        // two node graph
        sorter.addTask(t11);
        sorter.addDependency(dep_11_2);
        assertEquals(Arrays.asList(t11, t2), sorter.sortTasks());

        // three node graph
        sorter.addTask(t5);
        sorter.addDependency(dep_5_11);
        assertEquals(Arrays.asList(t5, t11, t2), sorter.sortTasks());

        // four node graph
        sorter.addTask(t7);
        sorter.addDependency(dep_7_11);
        assertEquals(Arrays.asList(t5, t7, t11, t2), sorter.sortTasks());

        // mostly disconnected graph
        // NOTE: that these are sorted alphabeticaly, so 10 is before 3
        addTasks();
        assertEquals(Arrays.asList(t10, t3, t5, t7, t11, t2, t8, t9),
            sorter.sortTasks());

        // full example graph from Wikipedia
        addDependencies();
        assertEquals(Arrays.asList(t3, t5, t7, t11, t10, t2, t8, t9),
            sorter.sortTasks());

        // create a cycle
        sorter.addDependency(new Dependency(t2, t3));
        assertEquals(null, sorter.sortTasks());
    }
}
