package tasks;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

/**
 * TaskTest is a glassbox test of the Task class.
 */
public class TaskTest {

    /** Test creating tasks with correct parameters. */
    @Test
    public void testCreationCorrect() {
        assertEquals("name 1",
                new Task("name 1", "desc 3", "team 2").name());  // nonempty name
        assertEquals("desc 3",
                new Task("name 1", "desc 3", "team 2").description());  // nonempty description
        assertEquals("team 2",
                new Task("name 1", "desc 3", "team 2").team());  // nonempty team
        assertEquals("", new Task("", "", "").name());  // empty name
        assertEquals("", new Task("", "", "").description());  // empty description
        assertEquals("", new Task("", "", "").team());  // empty team
    }

    @Test(expected=AssertionError.class)
    public void testCreationNullName() {
        Task shouldFail = new Task(null, "desc", "team");
    }

    @Test(expected=AssertionError.class)
    public void testCreationNullDescription() {
        Task shouldFail = new Task("name", null, "team");
    }

    @Test(expected=AssertionError.class)
    public void testCreationNullTeam() {
        Task shouldFail = new Task("name", "desc", null);
    }

    @Test
    public void testToString() {
        Task empty = new Task("", "", "");
        Task shortTask = new Task("task1", "desc1", "team1");
        Task withSpaces = new Task("Here is the name.",
                                   "Here is the description.",
                                   "CSE 331 Staff");
        assertEquals("Task \"\":\nDescription: \nTeam: ", empty.toString());
        assertEquals("Task \"task1\":\nDescription: desc1\nTeam: team1", shortTask.toString());
        assertEquals("Task \"Here is the name.\":\nDescription: " +
                        "Here is the description.\nTeam: CSE 331 Staff", withSpaces.toString());
    }
}