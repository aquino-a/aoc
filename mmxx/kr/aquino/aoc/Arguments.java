package kr.aquino.aoc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Arguments {

    private List<Optional<String>> args; 

    public Arguments(String[] args, int count){
        this.args = new ArrayList<Optional<String>>(count);
        for (int i = 0; i < count; i++) {
            if(i < args.length)
                this.args.add(Optional.of(args[i]));
            else this.args.add(Optional.empty());
        }
    }

    public String SetText(int position, String defaultText){
        if(args.get(position).isEmpty())
            return defaultText;
        else return args.get(position).get();
    }
    
}
