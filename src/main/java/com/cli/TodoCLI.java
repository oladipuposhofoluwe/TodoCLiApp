package com.cli;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TodoCLI {
    private static final String LIST = "list";
    private static final String COMPLETE = "Complete";
    private static final String ADD = "add";
    private static final String UPDATE = "update";
    private static final String DELETE = "delete";

    private static boolean isDone = false;


    public static void main(String[] args) {

        TodoOperation todoOperation =  new TodoOperation();
        boolean isRunning = true;

        while (isRunning) {
            todoOperation.myTodoFile();
            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();

        switch (choice) {
            case LIST:
                todoOperation.listTodo();
                break;
            case ADD:
                todoOperation.addTodo();
                break;
            case DELETE:
                todoOperation.deleteTodo();
                break;
            default:
                System.err.println("Please specify a valid command.");
        }
        }
    }
}
