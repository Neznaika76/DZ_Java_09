package TasksPlanner;

import Tasks.ITasks;
import Tasks.Tasks;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Planner {
    private static int count = 0;
    private List<Tasks> tasksList;
    private ITasks currentTask;

    public Planner() {
        tasksList = new ArrayList<>();
    }

    public Planner(Tasks task) {
        this();
        tasksList.add(task);
        count++;
    }

    public int getCount() {
        return count;
    }

    public ITasks getCurrentTask() {
        return currentTask;
    }

    public void setCurrentTask(Tasks task) {
        if (!tasksList.contains(task)) {
            addTask(task);
        }
        currentTask = task;
    }

    public void addTask(Tasks task) {
        this.tasksList.add(task);
        count++;
    }

    public void showAll() {
        for (ITasks task : tasksList) {
            task.showInfo();
        }
    }

    public void deleteTask(ITasks task) {
        tasksList.remove(task);
        count--;
    }

    public void exportToCSV() throws IOException {
        FileWriter csvWriter = new FileWriter("tasks.csv");
        csvWriter.append("ID,created,deadline,author,description,priority\n");
        for (Tasks task : tasksList) {
            csvWriter.append(String.format("%s,%s,%s,%s,%s,%s\n",
                    task.getId(),
                    task.getCreationDate(),
                    task.getDeadLine(),
                    task.getAuthor(),
                    task.getDescription(),
                    task.getPriority()));
        }
        csvWriter.flush();
        csvWriter.close();
    }

    public void exportToJSON() {
        JSONArray jsonArr = new JSONArray();
        try {
            for (Tasks task : tasksList) {
                JSONObject jsonObj = new JSONObject();
                jsonObj.put("id", task.getId());
                jsonObj.put("creationDate", task.getCreationDate());
                jsonObj.put("deadLine", task.getDeadLine());
                jsonObj.put("author", task.getAuthor());
                jsonObj.put("description", task.getDescription());
                jsonObj.put("priority", task.getPriority());
                jsonArr.put(jsonObj);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try (PrintWriter out = new PrintWriter(new FileWriter("tasksAll.json"))) {
            out.write(jsonArr.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
