package kr.aquino.aoc.ii;

import java.util.function.Predicate;

import kr.aquino.aoc.Arguments;
import kr.aquino.aoc.IOUtility;

public class ii {

    public static void main(String[] args){

        var arguments = new Arguments(args, 2);
        var path = arguments.SetText(0, "kr/aquino/aoc/ii/input.txt");
        Predicate<PasswordPolicy> p = arguments.SetText(1, "1").equals("1") ? 
            PasswordPolicy::IsValidOne : PasswordPolicy::IsValidTwo;

        try {
            var all = IOUtility.ReadFile(path);
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
