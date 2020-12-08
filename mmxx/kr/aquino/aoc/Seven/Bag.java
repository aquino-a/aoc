package kr.aquino.aoc.Seven;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

public class Bag {

//     clear crimson bags contain 3 pale aqua bags, 4 plaid magenta bags, 3 dotted beige bags, 3 dotted black bags.
// dim violet bags contain 5 bright brown bags.
    private final static Pattern FIRST_BAG_PATTERN = Pattern.compile("^([A-Za-z ]+) bags contain");
    private final static Pattern SECOND_BAG_PATTERN = Pattern.compile("(\\d+) ([A-Za-z ]+) bags?[,.]");
    // private final static Pattern FIRST_BAG_PATTERN = Pattern.compile("^([A-Za-z ]+) bags contain( no other bags( no other bags.|(?: (\\d+) ([A-Za-z ]+) bags[,.])+)$");


    public final String color;
    public final Map<String,Bag> possibleBags;
    private int count;

    public Bag(String text) {
        this.possibleBags = new HashMap<>();

        var m = FIRST_BAG_PATTERN.matcher(text);
        m.find();
        this.color = m.group(1);

        m = SECOND_BAG_PATTERN.matcher(text);
        while(m.find()){
            var count = m.group(1);
            var name = m.group(2);
            possibleBags.put(name, new Bag(name, Integer.parseInt(count)));
        }
    }

    public Bag(String color, int count){
        this.color = color;
        this.count = count;
        this.possibleBags = new HashMap<String,Bag>();
    }


    public String getColor(){
        return color;
    }
    public int getCount(){
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bag bag = (Bag) o;
        return color.equals(bag.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }
}
