package com.hai;

/**
 * Created by huseyin on 04.04.2017.
 */
public class Word {private String word;
   private double score;

   public Word(String word, double score){
       this.word = word;
       this.score = score;
   }
    public String getWord() {
        return word;
    }
    public double getScore() {
        return score;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
