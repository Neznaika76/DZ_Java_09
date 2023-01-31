import TasksPlanner.Planner;
import Tasks.Tasks;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        Tasks<Integer> task1 = new Tasks<>(
                1,
                "21.01.2023",
                "22.01.2023",
                "Pavel Mikhailov",
                "Create UI for app",
                Tasks.priorityEnum.urgent
        );

        Tasks<String> task2 = new Tasks<>(
                "str 1",
                "15.01.2023",
                "16.01.2023",
                "Sergey Nesterov",
                "Develop database",
                Tasks.priorityEnum.high
        );

        Planner planner = new Planner(task1);

        planner.addTask(task2);
        planner.setCurrentTask(task2);
        System.out.printf("Количество задач: %s\n", planner.getCount());
        planner.showAll();
        planner.exportToCSV();
        planner.exportToJSON();
    }
}