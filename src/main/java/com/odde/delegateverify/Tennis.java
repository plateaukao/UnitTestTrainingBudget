package com.odde.delegateverify;

public class Tennis {

    public enum PointName {
        ZERO(0, "Love"),
        ONE(1, "Fifteen"),
        TWO(2, "Thirty"),
        THREE(3, "Forty");

        private final int point;

        private final String name;

        PointName(int point, String name) {
            this.point = point;
            this.name = name;
        }

        public static String getPointName(int point) {
            for (PointName pointName: PointName.values()) {
                if(pointName.point == point) {
                    return pointName.name;
                }
            }
            return null;
        }

    }

    private int pointPlayer1 = 0;
    private int pointPlayer2 = 0;

    private final String player1Name;
    private final String player2Name;

    public Tennis(String player1, String player2) {
        this.player1Name = player1;
        this.player2Name = player2;
    }

    protected void setPoints(int pointPlayer1, int pointPlayer2) {
        this.pointPlayer1 = pointPlayer1;
        this.pointPlayer2 = pointPlayer2;
    }

    public String score() {
        // over 40
        if (pointPlayer1 >= 3 && pointPlayer2 >= 3) {
            if (isSameScore()) {
                return "Deuce";
            }

            if (pointPlayer1 - pointPlayer2 == 1) {
                return player1Name + " " + "Adv";
            } else if (pointPlayer2 - pointPlayer1 == 1) {
                return player2Name + " " + "Adv";
            } else if (pointPlayer1 - pointPlayer2 == 2) {
                return player1Name + " " + "Win";
            } else if (pointPlayer2 - pointPlayer1 == 2) {
                return player2Name + " " + "Win";
            }
        }

        // regular case
        if (pointPlayer1 <= 3 && pointPlayer2 <= 3) {
            if (isSameScore()) {
                return PointName.getPointName(pointPlayer1) + " " + "All";
            } else {
                return PointName.getPointName(pointPlayer1) + " " + PointName.getPointName(pointPlayer2);
            }
        }


        if (pointPlayer1 == 4) {
            return player1Name + " " + "Win";
        } else if (pointPlayer2 == 4){
            return player2Name + " " + "Win";
        } else {
            return "unknown:" + pointPlayer1 + pointPlayer2;
        }
    }

    private boolean isSameScore() {
        return pointPlayer1 == pointPlayer2;
    }
}
