package bob.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import bob.storage.Storage;
import bob.task.TaskManager;

public class TaskManagerTest {
    Storage storage = new Storage();
    TaskManager manager = new TaskManager(storage);

    @Test
    void taskManager_mark_markInvalidTask() {
        assertEquals(manager.mark(69), null);
    }

    @Test
    void taskManager_unmark_unmarkInvalidTask() {
        assertEquals(manager.unmark(69), null);
    }

    @Test
    void taskManager_delete_deleteInvalidTask() {
        assertEquals(manager.deleteTask(69), null);
    }

    @Test
    void taskManager_getTask_getInvalidTask() {
        assertEquals(manager.getTask(69), null);
    }
}
