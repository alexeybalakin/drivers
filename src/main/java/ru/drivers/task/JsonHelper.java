package ru.drivers.task;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonHelper {
    public static void jsonWrite(String filename, List<Driver> drivers) {
        Gson gson = new Gson();
        String str = gson.toJson(drivers);
        try (FileWriter fileWriter = new FileWriter(filename)) {
            fileWriter.write(str);
        } catch (IOException ex) {
            System.out.println("IO error");
        }
    }

    public static List<Driver> jsonRead(String filename) {
        Gson gson = new Gson();
        List<Driver> drivers = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            Type type = new TypeToken<List<Driver>>(){}.getType();
            drivers = gson.fromJson(reader, type);
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        }
        return drivers;
    }
}
