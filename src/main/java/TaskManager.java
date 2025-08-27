import java.io.IOException;
import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> tasks;
    private Storage storage;
    
    TaskManager(Storage storage) {
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

    public void addTask(Task t) {
        tasks.add(t);
        save();
    }

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

    public void printTask() {
        int count = 1;
        for (Task s : tasks) {
            System.out.println(count + ". " + s);
            count++;
        }
    }

    public int assignId() {
        return this.tasks.size() + 1;
    }

    public Task getTask(int idx) {
        try {
            return tasks.get(idx);
        } catch (IndexOutOfBoundsException e)  {
            return null;
        }
    }
}
