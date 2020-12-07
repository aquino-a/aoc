package kr.aquino.aoc.Seven;

import java.io.IOException;
import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;


import kr.aquino.aoc.IOUtility;

public class Seven {
    public static void main(String[] args) throws IOException {
        var arguments = new kr.aquino.aoc.Arguments(args, 2);
        var path = arguments.SetText(0, "kr/aquino/aoc/Seven/input.txt");
        BiFunction<Map<String,Bag>, String, Long> func = arguments.SetText(1, "1").equals("1") ? Seven::getBagCount : Seven::getRequiredBags;

        var count = func.apply(IOUtility.ReadFile(path).stream().map(s -> new Bag(s)).collect(Collectors.toMap(b -> b.color, b -> b)), "shiny gold");
        System.out.println(String.format("Count: %d", count));
    }

    private static long getBagCount(Map<String, Bag> bags, String color) {
        var reverseMap = new HashMap<String, Set<String>>();
        bags.values().forEach(b -> {
            b.possibleBags.values().forEach(pb -> {
                if(reverseMap.containsKey(pb.color)){
                    reverseMap.get(pb.color).add(b.color);
                } else {
                    var set = new HashSet<String>();
                    set.add(b.color);
                    reverseMap.put(pb.color, set);
                }
            });
        });

        var colorBags = getBagsReverse(reverseMap, color);
        return colorBags.size();
    }

    private static Set<String> getBagsReverse(Map<String, Set<String>> reverseMap, String color){
        var colors = reverseMap.get(color);
        if(colors == null)
            return Collections.emptySet();
        var newColors = colors.stream().flatMap(c -> getBagsReverse(reverseMap, c).stream()).collect(Collectors.toSet());
        colors.addAll(newColors);
        return colors;
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
