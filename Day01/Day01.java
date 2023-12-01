package Day01;
import java.io.File;
import java.util.*;

/**
 * Advent of Code - Day 01
 * */
public class Day01 {

    public static void main(String[] args) {
        // int part1 = part1();
        int part2 = part2();
        // System.out.println("Part 1: "+part1);
        System.out.println("Part 2: "+part2);
        
    }

    public static int part1(){
        try {
            Scanner sc = new Scanner(new File("Day01/input.txt"));
            int total = 0;
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                String clean = line.replaceAll("\\D+", "");
                
                int x;
                if(line.length() > 1) x = Integer.valueOf(clean.charAt(0)+""+clean.charAt(clean.length()-1));
                else x = Integer.valueOf(clean +""+clean);
                total+= x;
            }
            return total;
        } catch (Exception e) {
            System.err.println("Exception found: "+e);
        }
        return -1;
    }

    public static int part2(){
        try {
            Scanner sc = new Scanner(new File("Day01/input.txt"));
            int total = 0;
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                Map<String, Integer> map = Map.of("one", 1, "two", 2, "three", 3, "four", 4,"five",5, "six", 6,"seven", 7,"eight", 8,"nine",9);

                line = parseFirst(line, map);
                line = parseLast(line, map);
                String clean = line.replaceAll("\\D+", "");

                if(line.length() > 1){
                    int x = Integer.valueOf(clean.charAt(0)+""+clean.charAt(clean.length()-1));
                    total+= x;
                }
                else{
                    int x = Integer.valueOf(clean +""+clean);
                    total+=x;
                }
            }
            return(total);
        } catch (Exception e) {
            System.err.println("Exception found: "+e);
        }
        return -1;
    }

    public static String parseFirst(String line, Map<String, Integer> map){
        for(int i = 0; i < line.length(); i++){
            for(int j = i; j < line.length(); j++){
                String sub = line.substring(i, j);
                if(sub.matches("[0-9]")) return line;
                if(map.keySet().contains(sub)) {
                    line = line.replaceFirst(sub, ""+map.get(sub));
                    return line;
                }
            }
        }
        return line;
    }

    public static String parseLast(String line, Map<String, Integer> map){
        for(int i = line.length()-1; i >= 0; i--){
            for(int j = i+1; j <= line.length(); j++){
                String sub = line.substring(i, j);
                if(sub.matches("[0-9]")) return line;
                if(map.keySet().contains(sub)) {
                    StringBuffer buffer = new StringBuffer(line);
                    buffer.replace(i, j, ""+map.get(sub));
                    return buffer.toString();
                }
            }
        }
        return line;
    }
}

