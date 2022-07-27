package com.company;

import java.util.ArrayList;

public class ScoreCalculator {

    private ArrayList<Integer> scores;

    public ScoreCalculator(String scores) {
        setScores(scores);
    }

    public ArrayList<Integer> getScores() {
        return this.scores;
    }

    public void setScores(String scores) {
        String[] scoresArr = scores.split(" ");
        this.scores = new ArrayList<Integer>();
        for (String score : scoresArr)
            this.scores.add(Integer.parseInt(score));
    }

    public String calculateTotals() {
        String totals = "";
        int prevScore = 0;
        for (int i = 0; i < scores.size(); i++) {
            if (determineRollType(i) == 0) {
                prevScore += scores.get(i) + scores.get(i+1) + scores.get(i+2);
                totals +=  prevScore + " ";
            }
            else if (determineRollType(i) == 1) {
                prevScore += scores.get(i) + scores.get(i+1)+ scores.get(i+2);
                totals +=  prevScore + " ";
                i++;
            }
            else {
                if (i < scores.size() - 1)
                    prevScore += scores.get(i) + scores.get(i+1);
                else
                    prevScore += scores.get(i);
                totals +=  prevScore + " ";
                i++;
            }
        }
        return totals;
    }

    /*  returns 0 for strike, 1 for spare, 2 for open   */
    private int determineRollType(int i) {
        if (i < scores.size() - 3 && scores.get(i) == 10) return 0;
        else if (i < scores.size() - 3 && scores.get(i) + scores.get(i+1) == 10) return 1;
        return 2;
    }

    @Override
    public String toString() {
        return "Cumulative Scores: " + this.scores.toString();
    }
}
