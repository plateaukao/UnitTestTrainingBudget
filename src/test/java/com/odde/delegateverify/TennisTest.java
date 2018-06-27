package com.odde.delegateverify;

import org.assertj.core.groups.Tuple;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;


public class TennisTest {

    Tennis tennis = new Tennis("Joey", "Joseph");

    @Test
    public void testScoreUnder3and3() {
        List<Tuple> testData = asList(
                new Tuple(0, 0, "Love All"),
                new Tuple(1, 0, "Fifteen Love"),
                new Tuple(0, 1, "Love Fifteen"),
                new Tuple(1, 1, "Fifteen All"),
                new Tuple(2, 1, "Thirty Fifteen"),
                new Tuple(2, 2, "Thirty All"),
                new Tuple(2, 0, "Thirty Love"),
                new Tuple(3, 2, "Forty Thirty"),
                new Tuple(4, 2, "Joey Win"),
                new Tuple(3, 3, "Deuce"),
                new Tuple(3, 4, "Joseph Adv"),
                new Tuple(4, 4, "Deuce"),
                new Tuple(3, 5, "Joseph Win"));

        for(Tuple tuple: testData) {
            tennis.setPoints((int)tuple.toList().get(0), (int)tuple.toList().get(1));
            assertThat(tennis.score()).isEqualTo((String)tuple.toList().get(2));
        }
    }
}