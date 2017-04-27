package com.hai;

import java.util.ArrayList;

/**
 * Created by huseyin on 07.04.2017.
 *
 * char grid 5 by 5, this field takes answers as arraylist of words and corresponidng answerfields in an arraylist
 */
public class Grid {

    private char [][] grid;
    private ArrayList<Word> answers;
    private ArrayList<Field> answerFields;
    boolean possible;
    private int score;

    // takes a list of answerfields and returns the grid of the clue with unknowns showed by a ? and blocked parts with X grid filler will provide this answers
    // the purpose of this class is to check if we can create a grid with 10 guess sent here from grid filler
    public Grid(ArrayList<Field> answerFields) {
        this.grid = new char[5][5];
        this.answerFields = answerFields;
        cleanGrid();
        score = 0;
    }
    // constructor first puts all ! to everywhere in the grid,then since we know where the answers will go from the fields
    // then we try to insert answers to the grid with corresponding answer fields indicies
    public Grid(ArrayList<Field> answerFields, ArrayList<Word> answers) {
        possible = true;
        this.grid = new char[5][5];
        this.answers = answers;
        this.answerFields = answerFields;

        for(int i = 0; i < 5;i++)
        {
            for(int j = 0; j< 5 ; j++)
            {
                grid[i][j] = '!';
            }
        }

        // takes the next field, and next guess(assuming the list ordered correspondingly, 1st answer goes to 1st answer field etc.)
        // we check if the answer fits the current grid if true insert if not we should try with a different answer set
        for(int i = 0; i < answerFields.size(); i++){
            Field curF = answerFields.get(i);
            Word curGuess = answers.get(i);
            boolean fit = checkFit(curGuess, curF);
            if(fit){
                score += curGuess.getScore();
                insertAnswer(curGuess,curF);
            }
            else{
                score = 0;
                possible = false;
                cleanGrid();
            }
        }
    }
    // if the field is across inset answer char by char starting from start index of the field and end index  of the
    // field, if its ow then we need to incremetn index  by 5 to get to the immediate down place no need to check since check is another funcrion
    // TODO check if it makes sense
    public void insertAnswer(Word guess, Field f){
        int j = 0;
             if (f.getFieldType() == FieldType.ACROSS) {
                 for (int i = f.getStartIndex(); i <=f.getEndIndex(); i++) {
                     grid[i / 5][i % 5] = guess.getWord().charAt(j++);
                 }
             }
             if(f.getFieldType() == FieldType.DOWN){
                 for (int i = f.getStartIndex(); i <= f.getEndIndex(); i+=5) {
                     grid[i / 5][i % 5] = guess.getWord().charAt(j++);
                 }
             }
    }

    public boolean isPossible() {
        return possible;
    }
    public boolean checkFit(Word guess, Field f)
    {
        String guessStr = guess.getWord();
        int j =0;// iterator over
        if(f.getFieldType() == FieldType.ACROSS)
        {
            // blank tiles represented by the char '?'
            for (int i = f.getStartIndex(); i <= f.getEndIndex(); i++) {
                char letterInGrid = grid[i / 5][i % 5];
                if(letterInGrid == '!') // hit a block, might not be really possible since we know the length bu checked, TODO check logic
                    return false;
                if (letterInGrid != '?')
                    if (letterInGrid != guessStr.charAt(j++))
                        return false;
            }
            return true;
        }
        else
        {
            for(int i = f.getStartIndex(); i <= f.getEndIndex();i+=5){
                char letterInGrid = grid[i / 5][i % 5];
                if(letterInGrid == '!')
                    return false;
                if (letterInGrid != '?')
                    if (letterInGrid != guessStr.charAt(j++))
                        return false;
                }
            return true;
        }
    }


    // to clean the grid first put all ? to places that should have letters and put ! anywhere else
    public void cleanGrid(){
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
                    grid[i][j] = '!';
                }
            }
        }
    }
    public char[][] getGrid() {
        return grid;
    }

    public void setGrid(char[][] grid) {
        this.grid = grid;
    }


    public int getScore() {
        return score;
    }
}
