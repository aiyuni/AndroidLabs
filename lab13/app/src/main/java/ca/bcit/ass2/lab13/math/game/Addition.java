package ca.bcit.ass2.lab13.math.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import ca.bcit.ass2.lab13.math.Quiz;

public class Addition implements Quiz {

    private Random random;
    private int first;
    private int second;
    private int answer;
    private List<Integer> options;

    public Addition() {
        random = new Random(System.currentTimeMillis());
        options = new ArrayList<>();
    }

    public int createFirstNumber() {
        return random.nextInt(MAX_BOUND);
    }

    public int createSecondNumber() {
        return random.nextInt(MAX_BOUND);
    }

    public int createAnswerFromNumbers(int first, int second) {
        return first + second;
    }

    public List<Integer> getPossibleWrongAnswers(int answer) {
        while (options.size() < 3) {
            int tempOption = random.nextInt(OPTION_HIGH - OPTION_LOW) + OPTION_LOW + answer;
            if (!options.contains(tempOption) && tempOption != answer) {
                options.add(tempOption);
            }
        }
        return options;
    }

    public String getQuestion() {
        first = createFirstNumber();
        second = createSecondNumber();
        return String.format("%s + %s ?", first, second);
    }

    public List<Integer> getOptions() {
        answer = createAnswerFromNumbers(first, second);
        options = getPossibleWrongAnswers(answer);
        options.add(answer);
        Collections.shuffle(options);
        return options;
    }

    public int getAnswerIndex() {
        for (int i = 0; i < options.size(); i++) {
            if (options.get(i) == answer) {
                return i;
            }
        }
        return -1;
    }

}
