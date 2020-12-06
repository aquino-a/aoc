package kr.aquino.aoc.Six;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.ToLongFunction;

import kr.aquino.aoc.IOUtility;

public class Six {
    public static void main(String[] args) throws IOException {
        var arguments = new kr.aquino.aoc.Arguments(args, 2);
        var path = arguments.SetText(0, "kr/aquino/aoc/Six/input.txt");
        ToLongFunction<Group> type = arguments.SetText(1, "1").equals("1") ? Group::uniqueQuestionCount : Group::everyoneYesCount;

        var groups = ParseGroups(IOUtility.ReadFile(path));
        var sum = groups.stream().mapToLong(type).sum();

        System.out.println(String.format("Sum: %d", sum));

    }

    private static List<Group> ParseGroups(List<String> text) {
        var list = new ArrayList<Group>();
        var group = new Group();
        for (String s : text) {
            if(s.isBlank()){
                list.add(group);
                group = new Group();
                continue;
            }
            group.addQuestions(s);
        }
        list.add(group);
        return list;
    }
    
}
