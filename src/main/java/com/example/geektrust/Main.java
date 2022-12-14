package com.example.geektrust;

import com.example.geektrust.Model.Coordinates;
import com.example.geektrust.Service.PowerService;
import com.example.geektrust.Utils.Constants;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main implements Constants {
    public static void main(String[] args) {
        int coordinateX1 = 0;
        int coordinateY1 = 0;
        int coordinateX2 = 0;
        int coordinateY2 = 0;
        String directionFacing = EMPTY_STRING;
        try {
            FileInputStream inputFile = new FileInputStream(args[0]);
            Scanner sc = new Scanner(inputFile);
            while (sc.hasNextLine()) {
                String inputLine = sc.nextLine();
                String[] inputData = inputLine.split(SPACE);
                switch (inputData[INPUT_TYPE]) {
                    case "SOURCE":
                        coordinateX1 = Integer.parseInt(inputData[SOURCE_INDEX_X]);
                        coordinateY1 = Integer.parseInt(inputData[SOURCE_INDEX_Y]);
                        directionFacing = inputData[DIRECTION_FACING_INDEX];
                        break;
                    case "DESTINATION":
                        coordinateX2 = Integer.parseInt(inputData[DESTINATION_INDEX_X]);
                        coordinateY2 = Integer.parseInt(inputData[DESTINATION_INDEX_Y]);
                        break;
                    case "PRINT_POWER":
                        Coordinates coordinates = new Coordinates(coordinateX1,coordinateY1,
                                coordinateX2,coordinateY2,directionFacing);
                        PowerService powerService = new PowerService();
                        int powerLeft = powerService.calculatePowerLeft(coordinates);
                        System.out.println("POWER " + powerLeft);
                        break;
                }
            }
            sc.close();
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
