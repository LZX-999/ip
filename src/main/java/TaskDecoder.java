import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TaskDecoder {
    

    public static Task retTask(String taskDesc) {
        String[] splits = taskDesc.split("\\|");
        String tags = splits[0];
        switch(tags) {
        case "T":
            return new Todo(splits[1], splits[2].equals("X"));
        case "D":
            return new Deadline(
                splits[1],
                LocalDate.parse(splits[2], DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                splits[3].equals("X")
            );
        case "E":
            return new Event(
                splits[1],
                LocalDate.parse(splits[2], DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                LocalDate.parse(splits[3], DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                splits[4].equals("X")
            );
        
        default:
            throw new IllegalArgumentException("Wrong task type found");
        }
    }
}
