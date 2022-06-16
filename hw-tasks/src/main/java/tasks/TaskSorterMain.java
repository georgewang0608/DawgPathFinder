package tasks;

import java.util.*;

/**
 * Contains the main method for a command-line application to use TaskSorter.
 */
public class TaskSorterMain {

    /**
     * Runs a program where a user can add tasks or dependencies to a TaskSorter,
     * get details on the tasks/dependencies, and sort tasks in a way that satisfies
     * all the dependencies.
     *
     * @param args Arguments to this class, which are never used.
     */
    public static void main(String[] args) {
        Map<String, Task> tasks = new HashMap<>();
        TaskSorter sorter = new TaskSorter();
        Scanner sc = new Scanner(System.in);
        System.out.println("-- TaskSorter --");
        usageMessage();
        String curLine = "";
        while (!curLine.equals("exit")) {
            System.out.println("Please enter a command.");
            curLine = sc.nextLine();
            String[] parsed = curLine.split(" ");
            if (parsed[0].equals("TASK") && parsed.length == 4) {
                String name = parsed[1].replace("_", " ");
                String description = parsed[2].replace("_", " ");
                String team = parsed[3].replace("_", " ");
                if (!tasks.containsKey(name)) {
                    Task currentTask = new Task(name, description, team);
                    tasks.put(name, currentTask);
                    sorter.addTask(currentTask);
                    System.out.println("Task added successfully.");
                } else {
                    System.out.println("A task with the same name was already added!");
                }
            } else if (parsed[0].equals("DEP") && parsed.length == 3) {
                String taskName1 = parsed[1].replace("_", " ");
                String taskName2 = parsed[2].replace("_", " ");
                if (tasks.containsKey(taskName1) && tasks.containsKey(taskName2)) {
                    Task task1 = tasks.get(taskName1);
                    Task task2 = tasks.get(taskName2);
                    Dependency currentDep = new Dependency(task1, task2);
                    sorter.addDependency(currentDep);
                    System.out.println("Dependency added successfully.");
                } else {
                    System.out.println("Tasks haven't been added yet!");
                }
            } else if (parsed[0].equals("SORT") && parsed.length == 1) {
                List<Task> sortedTasks = sorter.sortTasks();
                if (sortedTasks == null) {
                    System.out.println("Cycle in dependencies, cannot be sorted!");
                } else {
                    for (Task task : sorter.sortTasks()) {
                        System.out.println(task);
                    }
                }
            } else if (parsed[0].equals("GET") && parsed.length == 2) {
                String name = parsed[1].replace("_", " ");
                if (!tasks.containsKey(name)) {
                    System.out.println("Task " + name + " not added yet!");
                } else {
                    System.out.println(tasks.get(name));
                }
            } else if (parsed[0].equals("GET_DEP") && parsed.length == 2) {
                String name = parsed[1].replace("_", " ");
                if (!tasks.containsKey(name)) {
                    System.out.println("Task " + name + " not added yet!");
                } else {
                    Task currentTask = tasks.get(name);
                    for (Dependency dep : sorter.getOutgoingDependencies(currentTask)) {
                        System.out.println(dep);
                    }
                }
            } else if (parsed[0].equals("CLEAR") && parsed.length == 1) {
                sorter = new TaskSorter();
                tasks.clear();
                System.out.println("TaskSorter cleared successfully.");
            } else if (!curLine.equals("EXIT")) {
                System.out.println("Unknown command!");
                usageMessage();
            }
        }
        System.out.println("Closing...");
    }

    // Prints the usage message for user to know how to use the program in the main method.
    private static void usageMessage() {
        System.out.println("Usage:");
        System.out.println("To add a task enter: TASK task_name task_description task_team");
        System.out.println("To add a dependency enter: DEP first_task_name second_task_name");
        System.out.println("To sort the tasks enter: SORT");
        System.out.println("To get details on a certain task enter: GET task_name");
        System.out.println("To get dependencies with a certain prerequisite enter: " +
                "GET_DEP task_name");
        System.out.println("To clear all tasks and dependencies enter: CLEAR");
        System.out.println("(Make sure all whitespace in names, descriptions or teams" +
                " are replaced by underscores.)");
        System.out.println("To exit enter: exit");
    }
}
