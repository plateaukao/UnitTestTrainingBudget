package com.odde.delegateverify;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TennisTest {

    Tennis tennis = new Tennis();

    @Test
    public void love_all() {
        scoreEquals("Love All");
    }

    @Test
    public void one_zero() {
        tennis.firstPlayerScore();

        scoreEquals("Fifteen Love");
    }

    @Test
    public void two_zero() {
        givenFirstPlayerScore(2);

        scoreEquals("Thirty Love");
    }

    @Test
    public void three_zero() {
        givenFirstPlayerScore(3);

        scoreEquals("Forty Love");
    }

    @Test
    public void zero_one() {
        tennis.secondPlayerScore();

        scoreEquals("Love Fifteen");
    }

    @Test
    public void zero_two() {
        givenSecondPlayerScore(2);

        scoreEquals("Love Thirty");

    }
    
    @Test
    public void one_one() {
        tennis.firstPlayerScore();
        tennis.secondPlayerScore();

        scoreEquals("Fifteen All");
    }

    private void givenSecondPlayerScore(int times) {
        for (int i = 0; i < times; i++) {
            tennis.secondPlayerScore();
        }
    }

    private void givenFirstPlayerScore(int times) {
        for (int i = 0; i < times; i++) {
            tennis.firstPlayerScore();
        }
    }

    private void scoreEquals(String score) {
        assertEquals(score, tennis.score());
    }

}