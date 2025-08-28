package bob.task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import bob.storage.Storage;

/**
 * Class for managing task
 */
public class TaskManager {
    private ArrayList<Task> tasks;
    private Storage storage;

    /**
     * Constructor of TaskManager
     * 
     * @param storage Storage object for saving task
     */
    public TaskManager(Storage storage) {
        this.storage = storage;
        try {
            this.tasks = storage.loadData();
        } catch (IOException e) {
            this.tasks = new ArrayList<Task>();
            System.out.println("Unable to load data, starting with empty task");
        }
    }

    private void save() {
        try {
            storage.saveTask(tasks);
        } catch (IOException e) {
            System.out.println("Unable to save action");
        }
    }

    /**
     * Method for adding task to the list
     * 
     * @param t Task to be added
     */
    public void addTask(Task t) {
        tasks.add(t);
        save();
    }

    /**
     * Method for deleting task
     * 
     * @param idx Id of the task to be deleted
     * @return
     */
    public Task deleteTask(int idx) {
        try {
            if (idx < 0 || idx - 1 >= tasks.size()) {
                throw new IndexOutOfBoundsException();
            }
            Task task = tasks.get(idx);
            tasks.remove(idx);
            save();
            return task;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Provide valid task number! Usage delete <task number>");
            return null;
        } catch (NumberFormatException e) {
            System.out.println("Enter a valid number!");
            return null;
        }
    }

    /**
     * Method that mark task as done
     * 
     * @param idx Id of the to be marked
     * @return Task that has been marked
     */
    public Task mark(int idx) {
        try {
            if (idx < 0 || idx - 1 >= tasks.size()) {
                throw new IndexOutOfBoundsException();
            }
            tasks.get(idx).markDone();
            save();
            return tasks.get(idx);
        } catch (NumberFormatException e) {
            System.out.println("Enter a valid number!");
            return null;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Attempted to mark task that does not exists!");
            return null;
        }
    }

    /**
     * Method that mark class as not done
     * 
     * @param idx Id of the class to be unmarked
     * @return Task that has been unmarked
     */
    public Task unmark(int idx) {
        try {
            if (idx < 0 || idx - 1 >= tasks.size()) {
                throw new IndexOutOfBoundsException();
            }
            tasks.get(idx).unmarkDone();
            save();
            return tasks.get(idx);
        } catch (NumberFormatException e) {
            System.out.println("Enter a valid number!");
            return null;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Attempted to mark task that does not exists!");
            return null;
        }
    }

    /**
     * Method for printing all tasks
     */
    public void printTask() {
        int count = 1;
        for (Task s : tasks) {
            System.out.println(count + ". " + s);
            count++;
        }
    }

    /**
     * Method for assigning Id to task
     * 
     * @return Id of the task assigned
     */
    public int assignId() {
        return this.tasks.size() + 1;
    }

    /**
     * Method to retrieve task from the list
     * 
     * @param idx Id of the task to be retrieved
     * @return Task retrieved from the list
     */

    public Task getTask(int idx) {
        try {
            return tasks.get(idx);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public void find(String query) {
        ArrayList<Task> found = new ArrayList<Task>();
        int count = 0;
        Pattern pattern = Pattern.compile(Pattern.quote(query.trim()), Pattern.CASE_INSENSITIVE);
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            Matcher matcher = pattern.matcher(task.toString());
            if (matcher.find()) {
                found.add(task);
                count++;
            }
        }
        if (count == 0) {
            System.out.println("No task found with that query");
        } else {
            System.out.println("Task found!");
            for (int i = 0; i < found.size(); i++) {
                System.out.println(i + 1 + ". " + found.get(i));
            }
        }
    }
}
