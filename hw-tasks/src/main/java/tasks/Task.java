
package tasks;

import java.util.Objects;

/**
 * Represents a task to be done, with an immutable name, description, and assigned team.
 * The task name and description are immutable, while the assigned team can be changed.
 */
public class Task {

    // Abstraction Function:
    // AF(this) = a task with name as this.name,
    // description as this.description, and team as this.team.

    // Representation Invariant:
    // this.name != null && this.description != null && this.team != null

    private final String name, description, team;

    /**
     * @param name        The task name.
     * @param description A description of the task.
     * @param team        The team assigned to this task.
     * @spec.requires name, description, team are not null.
     * @spec.effects Constructs a new Task with the given name, description, team.
     */
    public Task(String name, String description, String team) {
        this.name = name;
        this.description = description;
        this.team = team;
        checkRep();
    }

    /**
     * Throws an exception if the representation invariant is violated.
     */
    private void checkRep() {
        assert this.name != null : "Task name cannot be null";
        assert this.description != null : "Task description cannot be null";
        assert this.team != null : "Assigned team for task cannot be null";
    }

    /**
     * @return the task name
     */
    public String name() {
        checkRep();
        return this.name;
    }

    /**
     * @return the task description
     */
    public String description() {
        checkRep();
        return this.description;
    }

    /**
     * @return the team assigned to this task
     */
    public String team() {
        checkRep();
        return this.team;
    }

    /**
     * @return a String representation of this Task over 3 lines, with the description and team.
     */
    @Override
    public String toString() {
        checkRep();
        return "Task \"" + name + "\":\nDescription: " + description + "\nTeam: " + team;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Task)) {
            return false;
        }
        Task t = (Task) obj;
        return this.description.equals(t.description()) && this.name.equals(t.name()) && this.team.equals(t.team());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.description, this.name, this.team);
    }
}