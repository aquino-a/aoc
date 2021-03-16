package kr.aquino.aoc.xiii;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Note {
    private final long minutes;
    private final long[] ids;

    public Note(String minutes, String busIds){
        this.minutes = Integer.parseInt(minutes);
        this.ids = Arrays.stream(busIds.split(","))
                        .mapToLong(s -> {
                            try{
                                return Long.parseLong(s);
                            }
                            catch(Exception e){
                                return -1;
                            }
                        })
                        .toArray();
    }

    public long partOne(){
        var bus = Arrays.stream(ids).filter(id -> id != -1).mapToObj(id -> {
            var r = minutes % id;
            return new Bus(id, minutes + (id - r));
        }).sorted((b1, b2) -> Long.compare(b1.earliestTime, b2.earliestTime)).findFirst().get();
        return bus.id * (bus.earliestTime - minutes);
    }

    public long partTwo(){
        var orderedIds = createOrderedIds(ids);
        var time = orderedIds.get(0).id;
        var step = time;
        for (int i = 1; i < orderedIds.size(); i++) {
            var oi = orderedIds.get(i);
            while(true){
                if((time + oi.position) % oi.id == 0){
                    step = lcm(step, oi.id);
                    break;
                }
                time += step;
            }
        }
        return time;
    }

    private List<OrderedId> createOrderedIds(long[] iteration) {
        var list = new ArrayList<OrderedId>(iteration.length);
        for (int i = 0; i < iteration.length; i++) {
            if(iteration[i] == -1)
                continue;
            list.add(new OrderedId(iteration[i], i));
        }
        return list;
    }
   
    private long lcm(long a, long b){
        var gcd = gcd(Math.max(a, b), Math.min(a, b));
        return ( a * b ) / gcd;
    }

    private long gcd(long big, long small){
        if(small == 0)
            return big;
        return gcd(small, big % small);
    }

    private class OrderedId {

        public final long id;
        public final int position;

        public OrderedId(long id, int position) {
            this.id = id;
            this.position = position;
        }
    }

    private class Bus {

        public final long earliestTime;
        public final long id;

        public Bus(long id, long earliestTime) {
            this.id = id;
            this.earliestTime = earliestTime;
        }
    }

}
