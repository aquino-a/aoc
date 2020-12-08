package kr.aquino.aoc.mmxx.Four;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import kr.aquino.aoc.mmxx.IOUtility;
import kr.aquino.aoc.mmxx.Arguments;

public class Four {

    public static void main(String[] args) throws IOException {
        var arguments = new Arguments(args, 2);
        var path = arguments.SetText(0, "kr/aquino/aoc/mmxx/Four/input.txt");
        var type = arguments.SetText(1, "1").equals("1")
                ? new FieldValidator[] {
                     new FieldValidator("byr", null),
                     new FieldValidator("iyr", null),
                     new FieldValidator("eyr", null),
                     new FieldValidator("hgt", null),
                     new FieldValidator("hcl", null),
                     new FieldValidator("ecl", null),
                     new FieldValidator("pid", null)
                }
                : 
                new FieldValidator[] {
                    new FieldValidator("byr", "^(19[2-9][0-9]|200[0-2])$"),
                    new FieldValidator("iyr", "^20(1[0-9]|20)$"),
                    new FieldValidator("eyr", "^20(2[0-9]|30)$"),
                    new FieldValidator("hgt", "^(1[5-9][0-9]cm|(59|6[0-9]|7[0-6])in)$"),
                    new FieldValidator("hcl", "^#[0-9a-f]{6}$"),
                    new FieldValidator("ecl", "^(amb|blu|brn|gry|grn|hzl|oth)$"),
                    new FieldValidator("pid", "^\\d{9}$")
               };

        var passports = ParsePassports(IOUtility.ReadFile(path));
        var pv = new PassportValidator(Arrays.stream(type).collect(Collectors.toMap(f -> f.getFieldName(), f -> f)));

        var count = passports.stream().filter(p -> pv.isValid(p)).count();
        System.out.println(String.format("Valid passports: %s", count));
    }

    private static List<Passport> ParsePassports(List<String> text) {
        var list = new ArrayList<Passport>();
        var passport = new Passport();
        for (var t : text) {
            if(t == null || t.isBlank()){
                list.add(passport);
                passport = new Passport();
                continue;
            }
            passport.addFields(t);
        }
        return list;
    }
    
}
