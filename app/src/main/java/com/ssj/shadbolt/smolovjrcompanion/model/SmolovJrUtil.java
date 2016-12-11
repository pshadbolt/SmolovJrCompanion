package com.ssj.shadbolt.smolovjrcompanion.model;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by shadbolt on 11/19/2016.
 */

public class SmolovJrUtil {
    public static int getSets(int index) {
        switch (index) {
            case 0:
            case 4:
            case 8:
                return 6;
            case 1:
            case 5:
            case 9:
                return 7;
            case 2:
            case 6:
            case 10:
                return 8;
            default:
                return 10;
        }
    }

    public static int getReps(int index) {
        switch (index) {
            case 0:
            case 4:
            case 8:
                return 6;
            case 1:
            case 5:
            case 9:
                return 5;
            case 2:
            case 6:
            case 10:
                return 4;
            default:
                return 3;
        }
    }

    public static int getWeight(int index, int weight, int increment, int round) {

        double percentage;
        int mulitplier = 2;

        switch (index) {
            case 0:
            case 4:
            case 8:
                percentage = .7;
                break;
            case 1:
            case 5:
            case 9:
                percentage = .75;
                break;
            case 2:
            case 6:
            case 10:
                percentage = .8;
                break;
            default:
                percentage = .85;
        }

        if (index < 8)
            mulitplier = 1;
        if (index < 4)
            mulitplier = 0;

        return (int) (round * (Math.round((weight * percentage + increment * mulitplier) / round)));
    }

    public static String getPlateDiagram(int weight, String units) {
        String plateDiagram = "===";
        int total;

        ArrayList<String> increments;

        if (units.equals("lbs")) {
            total = weight - 45;
            increments = new ArrayList<String>(Arrays.asList("45", "35", "25", "10", "5", "2.5", "1", "0.5", "0.25"));
        } else {
            total = weight - 20;
            increments = new ArrayList<String>(Arrays.asList("25", "20", "15", "10", "5", "2.5", "2", "1.5", "1", "0.5", "0.25"));
        }

        for (String increment : increments) {
            double plate = Double.parseDouble(increment);
            while (total - plate * 2 >= 0) {
                plateDiagram = plateDiagram + "|" + increment + "|";
                total -= plate * 2;
            }
        }
        return plateDiagram;
    }
}
