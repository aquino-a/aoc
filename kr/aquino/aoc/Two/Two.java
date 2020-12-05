package kr.aquino.aoc.Two;

import java.util.function.Predicate;

import kr.aquino.aoc.IOUtility;

public class Two {


    public static void main(String[] args){
        String url = "kr/aquino/aoc/Two/input.txt";
        if(args.length > 0)
            url = args[0];
        Predicate<PasswordPolicy> p = PasswordPolicy::IsValidOne;
        if(args.length > 1){
            if(args[1].equals("2"))
                p = PasswordPolicy::IsValidTwo;
        }


        try {
            var all = IOUtility.ReadFile(url);
            var validCount = all.stream()
                                .map(s -> new PasswordPolicy(s))
                                .filter(p)
                                .count();
            System.out.println(String.format("Valid Passwords: %d", validCount));
            System.out.println(String.format("Total Passwords: %d", all.size()));
            System.out.println();

        } catch (Exception e) {
            System.out.println("Not found.");
            e.printStackTrace();
        }
    }
    
}
