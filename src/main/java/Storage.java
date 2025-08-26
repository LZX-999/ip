import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


public class Storage {
    private final String filePath = "data/tasks.txt";

    Storage() {
    }

    public ArrayList<Task> loadData() throws IOException{
        ArrayList<Task> ret = new ArrayList<Task>();
        try {
            File file = new  File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                boolean created = file.createNewFile();
                return ret;
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String taskDesc = scanner.nextLine();
                Task task = TaskDecoder.retTask(taskDesc);
                ret.add(task);
            }
            scanner.close();
        }
        catch (IOException e) {
            throw new IOException("Unable to read  file");
        }
        return ret;
    }

    public void saveTask(List<Task> tasks) throws IOException {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            else {
                file.delete();
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(filePath);
            for (int i = 0; i < tasks.size(); i++) {
                Task curr = tasks.get(i);
                writer.write(curr.saveString() + System.lineSeparator());
            }
            writer.close();
        }
        catch (IOException e) {
            throw new IOException("Unable to save task");
        }
    }


}
