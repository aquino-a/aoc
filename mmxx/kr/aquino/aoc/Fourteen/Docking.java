package kr.aquino.aoc.Fourteen;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Docking {

    private final Map<Long, Long> memory = new HashMap<>();;
    private Bitmask bitmask;

    public void action(Instruction instruction){
        switch (instruction.type) {
            case mask:
                bitmask = instruction.mask;
                break;
            case mem:
                if(!memory.containsKey(instruction.address))
                    memory.put(instruction.address, mask(instruction.value));
                else memory.replace(instruction.address, mask(instruction.value));
            default:
                break;
        }
    }

    public void actionPartTwo(Instruction instruction){
        switch (instruction.type) {
            case mask:
                bitmask = instruction.mask;
                break;
            case mem:
                maskAddresses(instruction).forEach(a -> {
                    if(!memory.containsKey(a))
                        memory.put(a, instruction.value);
                    else memory.replace(a,instruction.value);
                });
            default:
                break;
        }
    }

    private Stream<Long> maskAddresses(Instruction instruction) {
        var startAddress = instruction.address | bitmask.on;
        HashSet<Long> set = createAddresses(bitmask.floaterIndexes.get(0), startAddress).collect(Collectors.toCollection(HashSet<Long>::new));
        var tempSet = new HashSet<Long>(set.size());
        for (int i = 1; i < bitmask.floaterIndexes.size(); i++) {
            createAddresses(i, set.stream()).forEachOrdered(tempSet::add);
            set.clear();
            var temp = set;
            set = tempSet;
            tempSet = temp;
        }
        return set.stream();
    }

    private Stream<Long> createAddresses(int index, long address) {
        var on = createOn(index);
        var off = Long.MAX_VALUE ^ on;
        return Stream.of(address | on, address & off);
    }

    private Stream<Long> createAddresses(int index, Stream<Long> stream){
        return stream.flatMap(a -> createAddresses(bitmask.floaterIndexes.get(index), a));
    }

    private long createOn(int index) {
        return 1l << index;
    }

    private long mask(long value) {
        value |= bitmask.on;
        value &= bitmask.off;
        return value;
    }

    public long sum() {
        return memory.values().stream().mapToLong(Long::longValue).sum();
    }
}
