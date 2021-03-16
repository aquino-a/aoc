package kr.aquino.aoc.vi;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Group {

    private final List<String> answeredQuestions;

    public Group(){
        answeredQuestions = new ArrayList<>();
    }

    public void addQuestions(String questions){
        answeredQuestions.add(questions);
    }

    public long uniqueQuestionCount(){
        return answeredQuestions.stream()
                        .flatMapToInt(s -> s.chars())
                        .distinct()
                        .count();
    }

    public long everyoneYesCount(){
        if(answeredQuestions.size() == 1)
            return answeredQuestions.get(0).length();

        return answeredQuestions.stream()
                    .map(s -> s.chars()
                    .boxed()
                    .collect(Collectors.toCollection(HashSet<Integer>::new)))
                    .reduce((hs1, hs2) -> hs1.stream()
                                            .filter(i -> hs2.contains(i))
                                            .collect(Collectors.toCollection(HashSet<Integer>::new)))
                    .get()
                    .size();
    }
    
}
