package task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class TaskService {

    private Map<Integer, Task> taskMap = new HashMap<>();
    private Collection<Task> removedTasks;
    private Map<Integer, Task> groupedTaskMap = new TreeMap<>();
    public void add(Task task) {
        taskMap.put(task.getId(), task);
    }

    public void remove(int id) {
        taskMap.remove(id);
    }

    public Collection<Task> getAllByDate(LocalDate taskDate) {
        List<Task> resultList = new ArrayList<>();
        for (Map.Entry<Integer, Task> integerTaskEntry : taskMap.entrySet()) {
            var task = integerTaskEntry.getValue();
            if (task.isAvailable(taskDate)) {
                //System.out.println(task);
                resultList.add(task);
            }
        }
        return resultList;
    }

//    public Map<LocalDate, List<Task>> getAllTaskByGroup() {
//        Map<LocalDate, List<Task>> taskByDate = new HashMap<>();
//        for (Task value : taskMap.values()) {
//            addGroupedTask(taskByDate, value);
//        }
//        return taskByDate;
//    }
//
//    public void addGroupedTask(Map<LocalDate, List<Task>> taskByDate, Task task) {
//        if (!taskByDate.containsKey(task.getDateTime().toLocalDate())) {
//            taskByDate.put(task.getDateTime().toLocalDate(), List.of(task));
//        } else {
//            var groupedTask = taskByDate.get(task.getDateTime().toLocalDate());
//            groupedTask.add(task);
//            taskByDate.put(task.getDateTime().toLocalDate(), List.of(task));
//        }
//
//    }
}
