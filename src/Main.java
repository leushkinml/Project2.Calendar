import task.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws Exception {
        TaskService taskService = new TaskService();
        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                printMenu();
                System.out.print("Выберите пункт меню: ");
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            addTask(taskService, scanner);
                            break;
                        case 2:
                            getTaskByDay(taskService, scanner);
                            break;
                        case 3:
                            removeTask(taskService, scanner);
                            break;
//                        case 4:
//                            getAllTaskFromTaskList();
//                            break;
//                        case 5:
//                            editTask(scanner);
//                            break;
//                        case 6:
//                            taskList.getDeletedTask();
//                            break;
                        case 0:
                            break label;
                    }
                } else {
                    scanner.next();
                    System.out.println("Используйте цифры");
                    System.out.println();
                }
            }
        }
    }
    private static void printMenu() {
        System.out.println(
                "1. Добавить задачу\n" +
                        "2. Получить задачи на указанный день\n" +
                        "3. Удалить задачу\n" +
                        "4. Получить все задачи\n" +
                        "5. Редактировать заголовок и описание задачи\n" +
                        "6. Получить список всех удалённых задач\n" +
                        "0. Выход\n"
        );
    }
    private static String getTitleFromUser(Scanner scanner) {
        boolean forceUserToAnswer2 = true;
        String resultTitle = "Стартовый заголовок";
        System.out.println("Введите заголовок задачи: ");
        while (forceUserToAnswer2) {
            scanner.nextLine();
            String name = scanner.nextLine();
            //System.out.println("name = " + name);
                if (name == null || name.isBlank()) {
                    System.out.println("Вы ввели некорректную информацию!");
                    System.out.println("Пожалуйста, введите заголовок задачи ещё раз:");
                } else {
                    resultTitle = name;
                    System.out.println("Заголовок задачи: " + resultTitle);
                    forceUserToAnswer2 = false;
                }
       }
        return resultTitle;
    }
    private static String getDescriptionFromUser(Scanner scanner) {
        boolean forceUserToAnswer3 = true;
        String resultDescription = "Стартовое описание";
        System.out.println("Введите описание задачи: ");
        while (forceUserToAnswer3) {
            String descrip = scanner.nextLine();
            //System.out.println("descrip = " + descrip);
            if (descrip == null || descrip.isBlank()) {
                System.out.println("Вы ввели некорректную информацию!");
                System.out.println("Пожалуйста, введите заголовок задачи ещё раз:");
            } else {
                resultDescription = descrip;
                System.out.println("Описание задачи: " + resultDescription);
                forceUserToAnswer3 = false;
            }
        }
        return resultDescription;
    }
    private static LocalDate getDateFromUser(Scanner scanner) {
        boolean forceUserToAnswer = true;
        LocalDate resultDate = null;
        while (forceUserToAnswer) {
            System.out.println("Введите дату задачи в формате dd.mm.yyyy: ");
            try {
                String date = scanner.nextLine();
                resultDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                forceUserToAnswer = false;
            } catch (Exception e) {
                System.out.println("Введите дату задачи в формате dd.mm.yyyy ещё раз, пожалуйста!");
            }
        }
        return resultDate;
    }
    private static LocalTime getTimeFromUser(Scanner scanner) {
        boolean forceUserToAnswer4 = true;
        LocalTime resultTime = null;
        while (forceUserToAnswer4) {
            System.out.println("Введите время задачи в формате HH:mm: ");
            try {
                String time = scanner.nextLine();
                resultTime = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
                forceUserToAnswer4 = false;
            } catch (Exception e) {
                System.out.println("ВВведите время задачи в формате HH:mm ещё раз, пожалуйста!");
            }
        }
        return resultTime;
    }

    public static void addTask(TaskService taskService, Scanner scanner) {
        String title = getTitleFromUser(scanner);
//        System.out.println("Введите заголовок задачи: ");
//        String title = scanner.nextLine();
//        scanner.nextLine();
//        if ((title == null || title.isBlank())) {
//           System.out.println("Вы ввели некорректную информацию!");
//           return;
//            }
        String description = getDescriptionFromUser(scanner);
//        System.out.println("Введите описание задачи: ");
//        String description = scanner.nextLine();
//        scanner.nextLine();
//        if ((description == null || description.isBlank())) {
//            System.out.println("Вы ввели некорректную информацию!");
//            return;
//        }
        LocalDate taskDate = getDateFromUser(scanner);
        LocalTime taskTime = getTimeFromUser(scanner);
//        System.out.println("Введите время задачи в формате HH:mm: ");
//        String time = scanner.nextLine();
//        LocalTime taskTime = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
        LocalDateTime resultDate = LocalDateTime.of(taskDate, taskTime);

        System.out.println("Введите тип задачи:");
        System.out.println("1. Личный.");
        System.out.println("2. Рабочий.");
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.println("Вы ввели не число. Попробуйте ещё раз:");
        }
        int type = scanner.nextInt();
        Type taskType = type == 1 ? Type.PERSONAL : Type.WORK;
//        if (type == 1) {
//            taskType = Type.PERSONAL;
//        } else {
//            taskType = Type.WORK;
//        }

        System.out.println("Введите повторяемость задачи: ");
        System.out.println(" 0. Не повторяется.");
        System.out.println(" 1. Ежедневная.");
        System.out.println(" 2. Еженедельная.");
        System.out.println(" 3. Ежемесячная.");
        System.out.println(" 4. Ежегодная.");
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.println("Вы ввели не число. Попробуйте ещё раз:");
        }
        int typeTask = scanner.nextInt();
        switch (typeTask) {
            case 0:
                taskService.add(new Task(title, description, taskType, resultDate));
                break;
            case 1:
                taskService.add(new DailyTask(title, description, taskType, resultDate));
                break;
            case 2:
                taskService.add(new WeeklyTask(title, description, taskType, resultDate));
                break;
            case 3:
                taskService.add(new MonthlyTask(title, description, taskType, resultDate));
                break;
            case 4:
                taskService.add(new AnnuallyTask(title, description, taskType, resultDate));
                break;
            default:
                throw new RuntimeException("Нет такого типа задач!");
        }
    }

    private static void removeTask(TaskService taskService, Scanner scanner) {
        System.out.println("Введите id задачи, которую нужно удалить");
        int id = scanner.nextInt();
        taskService.remove(id);
    }

    private static void getTaskByDay(TaskService taskService, Scanner scanner) {
        System.out.println("Введите дату задачи в формате dd.mm.yyyy: ");
        scanner.nextLine();
        String date = scanner.nextLine();
        LocalDate taskDate =  LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        var allTaskByDay = taskService.getAllByDate(taskDate);
        System.out.println("Список задач этого дня: ");
        for (Task task: allTaskByDay) {
            System.out.println(task);
        }
        System.out.println();
    }


}