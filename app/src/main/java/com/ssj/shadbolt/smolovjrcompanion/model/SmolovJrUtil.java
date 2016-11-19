package com.ssj.shadbolt.smolovjrcompanion.model;

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

    public static int getWeight(int index, int weight, int increment) {

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

        return (int) Math.round(weight * percentage + increment * mulitplier);
    }

    public static String getPlateDiagram(int weight){
        String plates = "";
        int total = weight - 45;

        while(total - 90 >= 0){
            plates+=" 45";
            total-=90;
        }
        while(total - 70 >= 0){
            plates+=" 35";
            total-=70;
        }
        while(total - 50 >= 0){
            plates+=" 25";
            total-=50;
        }
        while(total - 20 >= 0){
            plates+=" 10";
            total-=20;
        }
        while(total - 10 >= 0){
            plates+=" 5";
            total-=10;
        }
        while(total - 5 >= 0){
            plates+=" 2.5";
            total-=5;
        }
        while(total - 2 >= 0){
            plates+=" 1";
            total-=2;
        }
        while(total - 1 > 0){
            plates+=" 0.5";
            total-=1;
        }
        while(total - 0.5 > 0){
            plates+=" 0.25";
            total-=0.5;
        }
        return plates;
    }
}
