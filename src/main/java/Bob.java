import java.util.Scanner;

public class Bob {
    public static void main(String[] args) {
        String logo = " /$$$$$$$   /$$$$$$  /$$$$$$$ \n" + //
                        "| $$__  $$ /$$__  $$| $$__  $$\n" + //
                        "| $$  \\ $$| $$  \\ $$| $$  \\ $$\n" + //
                        "| $$$$$$$ | $$  | $$| $$$$$$$ \n" + //
                        "| $$__  $$| $$  | $$| $$__  $$\n" + //
                        "| $$  \\ $$| $$  | $$| $$  \\ $$\n" + //
                        "| $$$$$$$/|  $$$$$$/| $$$$$$$/\n" + //
                        "|_______/  \\______/ |_______/ \n" + //
                        "                              \n" + //
                        "                              \n" + //
                        "                              ";
        System.out.println("Hello from\n" + logo);
        String lines = "____________________________________________________________";
        System.out.println(lines);
        System.out.println("Hello! I'm Bob");
        System.out.println("What can I do for you?");
        System.out.println(lines);
        boolean run = true;
        Scanner sc = new Scanner(System.in);
        while (run) {
            System.out.print("Enter text: ");
            String input = sc.nextLine();
            switch (input) {
                case "bye":
                    run = false;
                    System.out.println(lines);
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println(lines);
                    break;
                default:
                    System.out.println(lines);
                    System.out.println(input);
                    System.out.println(lines);
            } 
        }
        sc.close();

    }
}
