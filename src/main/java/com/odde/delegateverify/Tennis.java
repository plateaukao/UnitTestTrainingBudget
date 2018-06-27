package com.odde.delegateverify;

import java.util.HashMap;

public class Tennis {

    private int firstPlayerScore;
    private int secondPlayerScore;

    private String[] scoreName = {"Love", "Fifteen", "Thirty", "Forty"};

    public String score() {
        if (firstPlayerScore == secondPlayerScore) {
            return scoreName[firstPlayerScore] + " All";
        }

        if (secondPlayerScore >= 0 && firstPlayerScore >= 0) {
            return scoreName[firstPlayerScore] + " " + scoreName[secondPlayerScore];
        }

        return "Love All";
    }

    public void firstPlayerScore() {
        firstPlayerScore++;
    }

    public void secondPlayerScore() {
        secondPlayerScore++;
    }
}

