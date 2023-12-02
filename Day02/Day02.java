package Day02;
import java.io.File;
import java.util.*;

/**
 * Advent of Code - Day 02
 * */
public class Day02 {

    public static void main(String[] args) {
        int part1 = part1();
        System.out.println(part1);

        int part2 = part2();
        System.out.println(part2);
    }

    public static int part1(){
        int[] target = new int[]{12, 13, 14}; // max for red green blue respectively
        try {
            Scanner sc = new Scanner(new File("Day02/input.txt"));
            int sum = 0;
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                int game = Integer.valueOf(line.split(" ")[1].replaceFirst(":", ""));
                boolean check = true;
                for(String set : line.split(":")[1].split("; ")){
                    for(String subset : set.split(", ")){
                        switch (subset.split(" ")[1]) {
                            case "red":
                                if(Integer.valueOf(subset.split(" ")[0]) > target[0]){
                                    check = false;
                                }
                                break;
                            case "green":
                                if(Integer.valueOf(subset.split(" ")[0]) > target[1]){
                                    check = false;
                                }
                                break;
                            case "blue":
                                if(Integer.valueOf(subset.split(" ")[0]) > target[2]){
                                    check = false;
                                }
                                break;
                            default:
                                break;
                        }
                    }
                }
                if(check) {
                    sum += game;
                }
            }
            sc.close();
            return sum;
        } catch (Exception e) {
            System.err.println("Exception - "+e);
        }

        return -1;
    }

    public static int part2(){
        int totalSum = 0;
        try {
            Scanner sc = new Scanner(new File("Day02/input.txt"));
            // String s = """
            //     Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
            //     Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
            //     Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
            //     Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
            //     Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
            //         """;
            // Scanner sc = new Scanner(s);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                int tempProd = 0;
                int maxRed = 1;
                int maxGreen = 1;
                int maxBlue = 1;
                for(String game : line.split(": ")[1].split("; ")){
                    for(String subset : game.split(", ")){
                        System.out.println("Game = "+subset);
                        switch (subset.split(" ")[1]) {
                            case "red":
                                int red = Integer.valueOf(subset.split(" ")[0]);
                                if( red > maxRed){
                                    maxRed = red;
                                }
                                break;
                            case "green":
                                int green = Integer.valueOf(subset.split(" ")[0]);
                                if( green > maxGreen){
                                    maxGreen = green;
                                }
                                break;
                            case "blue":
                                int blue = Integer.valueOf(subset.split(" ")[0]);
                                if( blue > maxBlue){
                                    maxBlue = blue;
                                }
                                break;
                            default: break;
                        }
                    }
                }
                tempProd = maxBlue*maxGreen*maxRed;
                System.out.println(tempProd);
                totalSum += tempProd;
            }
            return totalSum;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}