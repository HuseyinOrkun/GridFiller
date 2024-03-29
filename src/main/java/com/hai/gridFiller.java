package com.hai;

import java.util.ArrayList;

/**
 * Created by huseyin on 04.04.2017.
 *
 * assuming this grid filler will take a list with size of the clue number as answer fields consisting of filed
 * and an arraylist of arraylist of words that has the list of guess of the program to the corresponding clues the first list is the
 * first guesslist to the clue 1 etc
 */

public class gridFiller {
    ArrayList<Field> answerFields;     //
    ArrayList<ArrayList<Word>> listOfAnswerLists;


    public gridFiller(ArrayList<Field> answerFields, ArrayList<ArrayList<Word>> listOfAnswerLists){
        this.answerFields = answerFields;
        this.listOfAnswerLists = listOfAnswerLists;

    }

    public  void fillGrid() {

        Grid gridWithMaxScore = new Grid(answerFields);
        // create a tentative solution from the possible answers
        // reject the çakışan answers


        int indexBase = (listOfAnswerLists.get(0)).size();  // all lists should be in the same size
        int limit = (int) Math.pow(listOfAnswerLists.size(),indexBase);
        int digitNumber = listOfAnswerLists.size();

        baseNIndex index = new baseNIndex(indexBase,digitNumber);
        ArrayList<Word> elements = new ArrayList<Word>();
        for(int j = 0;j < limit;j++){
            elements = getElements(index);
            Grid curGrid = new Grid(answerFields,elements);
            if(curGrid.isPossible() && curGrid.getScore() > gridWithMaxScore.getScore())
            {
                gridWithMaxScore = curGrid;
            }
            index.increment();
        }


    }
    // this method is used to get the words at indicies given by the Base N index number to a list
    public ArrayList <Word>getElements(baseNIndex index){
        ArrayList<Word> elements = new ArrayList<Word>();
        for(int i = 0; i <listOfAnswerLists.size();i++ ){
            elements.add(listOfAnswerLists.get(i).get(index.getDigit(i)));
        }
        return elements;
    }

    private void clearFields() {
        for(int i = 0; i < answerFields.size();i++){
             answerFields.get(i).clear();
        }
    }

    class baseNIndex
    {
        private int base;
        private int[] digits;

        public baseNIndex( int base, int digitNum)
        {
            this.base = base;
            digits = new int[digitNum + 2];
        }

        public int getDigit( int i)
        {
            return digits[i];
        }
        public int getBase()
        {
            return base;
        }

        public void increment()
        {
            digits[0]++;
            for(int i = 0; i < digits.length - 1; i++){
                if ( digits[i] == base){
                    digits[i] = 0;
                    digits[i+1]++;
                }
            }
        }
    }
}
