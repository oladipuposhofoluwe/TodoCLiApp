package com.cli;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TodoOperation implements ITodoOperation{


    private static int listCount;
    private static boolean isDone = false;


    private static List<String> task = new ArrayList<>();

    public static List<String> getTask() {
        return task;
    }

    public static void setTask(List<String> task) {
        TodoOperation.task = task;
    }

    @Override
    public void writeTodoToFile() {
        try (FileWriter fileWriter = new FileWriter("src/main/resources/todoFile.json")) {
            fileWriter.write(new ObjectMapper().writeValueAsString(task));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void myTodoFile() {
        try (FileReader fileReader = new FileReader("src/main/resources/todoFile.json")) {
            task = new ObjectMapper().readValue(fileReader, List.class);
            listCount = task.size();
        } catch (MismatchedInputException ignored) {

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void listTodo() {
        if(!task.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            int count = 0;
            for (String item : task) {
                sb.append(++count).append(". ").append(item).append("\n");
            }
            System.out.print(sb);
        } else {
            System.out.println("No items in todo list.");
        }
    }

    void addTodo() {
        System.out.println("Please type in your todo");
        Scanner scanner = new Scanner(System.in);
        String scan = scanner.nextLine();
        String[] arr = scan.split(" ");
        if (arr.length > 0) {
            String value = String.join(" ", Arrays.copyOfRange(arr, 0, arr.length));
            task.add(value);
            isDone = true;
            writeTodoToFile();
            System.out.println("\nitem successfully added\n");
        } else {
            System.err.println("Please specify the item to be added to the list");
        }
    }

     public void deleteTodo() {
        listTodo();
        System.out.println("\nPlease input the item number you wish to delete");
        Scanner scan = new Scanner(System.in);
        int choice = scan.nextInt();
        if (choice > task.size()){
            System.out.println("Please enter a valid item number present in the list");
            deleteTodo();
        }
        for (int i = choice - 1; i <= task.size(); i++){
            choice--;
            if (choice == i){
                task.remove(choice);
                System.out.println("\nitem removed successfully\n" );
                listTodo();
            break;
            }else {
                System.out.println("Please enter a valid item number present in the list");
            }
        }

    }

}
