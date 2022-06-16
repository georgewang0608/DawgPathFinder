
package tasks;

import java.util.Objects;

/**
 * Represents a dependency between two (immutable) tasks.
 */
public class Dependency {
    // Abstraction Function:
    // AF(this) = a dependency with this.beforeTask as the task that needs
    //            to be done first such that this.afterTask can be started.

    // Representation Invariant:
    // this.beforeTask != null && this.afterTask != null

    // The task that needs to be done first.
    private final Task beforeTask;

    // The task that can be worked on after beforeTask is done.
    private final Task afterTask;

    /**
     * @param beforeTask The task that needs to be done first
     * @param afterTask The task that depends on beforeTask.
     * @spec.requires beforeTask, afterTask are not null.
     * @spec.effects Constructs a new Dependency with the given before/after tasks.
     */
    public Dependency(Task beforeTask, Task afterTask) {
        this.beforeTask = beforeTask;
        this.afterTask = afterTask;
        checkRep();
    }

    /**
     * Throws an exception if the representation invariant is violated.
     */
    private void checkRep() {
        assert this.beforeTask != null : "beforeTask cannot be null";
        assert this.afterTask != null : "afterTask cannot be null";
    }

    /**
     * @return the task that needs to be done before in the dependency
     */
    public Task getBeforeTask() {
        checkRep();
        return beforeTask;
    }

    /**
     * @return the task that can be done after the depended task is finished.
     */
    public Task getAfterTask() {
        checkRep();
        return afterTask;
    }

    /**
     * @return a String representation of this dependency.
     */
    @Override
    public String toString() {
        checkRep();
        return "Dependency: " + beforeTask.name() + " -> " + afterTask.name();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Dependency)) {
            return false;
        }
        Dependency dep = (Dependency) obj;
        return this.beforeTask.equals(dep.getBeforeTask()) && this.afterTask.equals(dep.getAfterTask());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.beforeTask, this.afterTask);
    }
}