package ca.bcit.ass2.lab13.math;

import java.util.List;

public interface Quiz {
    int MAX_BOUND = 101;
    int OPTION_LOW = -10;
    int OPTION_HIGH = 10;

    int createFirstNumber();
    int createSecondNumber();
    int createAnswerFromNumbers(int first, int second);
    List<Integer> getPossibleWrongAnswers(int answer);
    String getQuestion();
    List<Integer> getOptions();
    int getAnswerIndex();
}

