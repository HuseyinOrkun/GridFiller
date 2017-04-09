package com.hai;

import java.util.ArrayList;

/**
 * Created by huseyin on 07.04.2017.
 */
public class Grid {

    private char [][] grid;
    private ArrayList<Word> answers;

    // takes a list of answerfields and returns the grid of the clue with unknowns showed by a ? and blocked parts with X
    public Grid(ArrayList<Field> answerFields) {
        this.grid = new char[5][5];

        for(int i = 0; i < answerFields.size(); i++){
            Field curF = answerFields.get(i);
            if(curF.getFieldType() == FieldType.ACROSS){
                for(int j = curF.getStartIndex(); j <= curF.getEndIndex();j++){
                    grid[i][j] = '?';
                }
            }
        }
        for(int i = 0; i < 5;i++){
            for(int j = 0; j< 5 ; j++){
                if(grid[i][j] != '?'){
                    grid[i][j] = 'X';
                }
            }
        }

    }

    public Grid(ArrayList<Field> answerFields, ArrayList<Word> answers) {
        this.grid = new char[5][5];

        for(int i = 0; i < 5;i++)
        {
            for(int j = 0; j< 5 ; j++)
            {
                grid[i][j] = 'X';
            }
        }
        for(int i = 0; i < answerFields.size(); i++){
            Field curF = answerFields.get(i);
            String curAnswer = answers.get(i).getWord();
            boolean fit = insertAnswer()
        }
        for(int i = 0; i < 5;i++){
            for(int j = 0; j< 5 ; j++){
                if(grid[i][j] != '?'){
                    grid[i][j] = 'X';
                }
            }
        }
    }
    public boolean insertAnswer(Word guess, Field f){
         if(checkFit(guess, f)) {
             if (f.getFieldType() == FieldType.ACROSS) {
                 for (int i = f.getStartIndex(); i <=f.getEndIndex(); i++) {
                     grid[i / 5][i % 5] = guess.getWord().charAt(i);
                 }
                 return true;
             }
             if(f.getFieldType() == FieldType.DOWN){
                 for (int i = f.getStartIndex(); i <= f.getEndIndex(); i+=5) {
                     grid[i / 5][i % 5] = guess.getWord().charAt(i);
                 }
                 return true;
             }
         }
         return false;
    }

    public boolean checkFit(Word guess, Field f)
    {
        String guessStr = guess.getWord();

        if(f.getFieldType() == FieldType.ACROSS)
        {
            // blank tiles represented by the char '?'
            for (int i = f.getStartIndex(); i <= f.getEndIndex(); i++) {
                char letterInGrid = grid[i / 5][i % 5];
                if (letterInGrid != '?')
                    if (letterInGrid != guessStr.charAt(i))
                        return false;
            }
            return true;
        }
        else
        {
            for(int i = f.getStartIndex(); i <= f.getEndIndex();i+=5){
                char letterInGrid = grid[i / 5][i % 5];
                if (letterInGrid != '?')
                    if (letterInGrid != guessStr.charAt(i))
                        return false;
                }
            return true;
        }
    }
    public char[][] getGrid() {
        return grid;
    }

    public void setGrid(char[][] grid) {
        this.grid = grid;
    }


}
