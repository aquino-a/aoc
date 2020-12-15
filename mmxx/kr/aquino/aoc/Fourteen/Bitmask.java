package kr.aquino.aoc.Fourteen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bitmask {

    public final long on;
    public final long off;
    public final List<Integer> floaterIndexes;

    public Bitmask(long on, long off, ArrayList<Integer> floaterIndexes){
        this.off = off;
        this.on = on;
        this.floaterIndexes = Collections.unmodifiableList(floaterIndexes);
    }

    
}
