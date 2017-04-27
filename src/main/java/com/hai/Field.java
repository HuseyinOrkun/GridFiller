package com.hai;
/**
 * Created by mrsfy on 04-Apr-17.
 *
 * field class, every answer has a field in the puzzle, this field has start index end index field type etc, start index and
 * endindex are from 0 to size*size so linear conversion of the size by size matrix puzzle(size =10), some functionality are not used
 */
public class Field {

    private int startIndex;   // Starting index of the answer field counted from top to bottom, like 1d array
    private int endIndex;      //same way end index
    private String clue;       // corresponding clue
    private Word word;          // the word in corresponding field
    private FieldType fieldType;
    private int clueNum;       // indicates the corresponding clue's no

    public Field() {
    }

    public Field(int startIndex, int endIndex, String clue, Word word, int fieldIndex, FieldType fieldType, int clueNum) {
        this.startIndex = startIndex;
        this.clue = clue;
        this.word = word;
        this.fieldType = fieldType;
        this.clueNum = clueNum;
        this.endIndex = endIndex;
    }

    public String getClue() {
        return clue;
    }

    public void setClue(String clue) {
        this.clue = clue;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public FieldType getFieldType() {
        return fieldType;
    }

    public void setFieldType(FieldType fieldType) {
        this.fieldType = fieldType;
    }

    public int getClueNum() {
        return clueNum;
    }

    public void setClueNum(int clueNum) {
        this.clueNum = clueNum;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }


/*
    public boolean checkFit( Word guess)
    {
        String guessStr = guess.getWord();

        if ( guessStr.length() != this.word.getWord().length())
            return false;
        // blank tiles represented by the char '?'
        for ( int i = 0; i < this.word.getWord().length(); i++)
            if ( this.word.getWord().charAt(i) != '?')
                if ( this.word.getWord().charAt(i) != guessStr.charAt(i))
                    return false;

        return true;
    }
    */
    public void clear(){
        String empty = "";
        for(int i = 0; i < word.getWord().length();i++)
        {
            empty +="?";
        }
        this.word.setWord(empty);

    }

}