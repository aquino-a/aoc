package kr.aquino.aoc.Seven;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import javax.crypto.Mac;

import kr.aquino.aoc.IOUtility;

public class Seven {
    public static void main(String[] args) throws IOException {
        var arguments = new kr.aquino.aoc.Arguments(args, 2);
        var path = arguments.SetText(0, "kr/aquino/aoc/Seven/input.txt");
        BiFunction<Map<String,Bag>, String, Long> func = arguments.SetText(1, "1").equals("1") ? Seven::getBagCount : Seven::getRequiredBags;

        var count = func.apply(IOUtility.ReadFile(path).stream().map(s -> new Bag(s)).collect(Collectors.toMap(b -> b.color, b -> b)), "shiny gold");
        System.out.println(String.format("Count: %d", count));
    }

    private static long getBagCount(Map<String,Bag> bags, String color){
        var colorBags = getBags(bags, color);
        return colorBags.size();
    }

    private static Set<Bag> getBags(Map<String,Bag> bags, String color) {
        var colorBags = bags.values().stream().filter(b -> b.possibleBags.containsKey(color)).collect(Collectors.toSet());
        var newBags = colorBags.stream().flatMap(cb -> getBags(bags, cb.color).stream()).collect(Collectors.toSet());
        colorBags.addAll(newBags);
        return colorBags;
    }

	private static long getRequiredBags(Map<String,Bag> bags, String color) {
        var colorBag = bags.get(color);
        var count = 0;
        for (Bag bag : colorBag.possibleBags.values()) {
            count += bag.getCount() + (bag.getCount()* getRequiredBags(bags, bag.color));
        }
        return count;
    }
    
}
