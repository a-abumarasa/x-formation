/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.x.formation.test.minesweeper;


/**
 * implementation for the minesweeper class 
 * @author aabum
 */
public class MineCommander implements MineSweeper {

    private String mineField;
    private int[][] mineFieldPositions;
    int n, m;

    @Override
    public void setMineField(String mineField) throws IllegalArgumentException {
        /*
        chack and convert the input string into array of int, to make it easy to process
        */
        if (mineField == null || mineField.trim().length() == 0) {
            throw new IllegalArgumentException("Empty Parameter is not allowed");
        }
        String[] lines = mineField.trim().split("\n");
        String line = lines[0].trim();

        n = lines.length;//number of lines        
        m = line.length();//number of supposed columns
        int[][] mines = new int[n][m];
        for (int i = 0; i < n; i++) {
            line = lines[i].trim();
            if (line.length() != m) {
                //if we have unequal line width
                throw new IllegalArgumentException(String.format("Different line length at line :%s", i + 1));
            }
            //iterate over the characters of the line
            char c;
            for (int j = 0; j < m; j++) {
                c = line.charAt(j);
                if (c == '*') {
                    mines[i][j] = 1;
                } else if (c == '.') {
                    mines[i][j] = 0;
                } else {
                    throw new IllegalArgumentException(String.format("Invalid character at line: %s and columns: %s", i + 1, j + 1));
                }
            }
        }
        this.mineFieldPositions = mines;
        this.mineField = mineField;
    }

    @Override
    public String getHintField() throws IllegalStateException {
        if (mineField == null) {
            throw new IllegalStateException("mineField is not defined");
        }

        StringBuilder hintFiels = new StringBuilder(mineFieldPositions.length * mineFieldPositions[0].length);
        for (int i = 0; i < mineFieldPositions.length; i++) {
            for (int j = 0; j < mineFieldPositions[0].length; j++) {
                if (mineFieldPositions[i][j] == 1) {
                    hintFiels.append('*');
                } else {
                    hintFiels.append(findAdjacent(i, j));
                }
            }
            hintFiels.append('\n');
        }
        return hintFiels.toString().trim();
    }

    private int findAdjacent(int i, int j) {
        /*
         Assume we want to check the adjacents of the cell number 5:
         1 2 3
         4 5 6
         7 8 9
         because we don't know in which place we are looking for in the field, 
         we have to check if the cell has adjacents or it's on the border
         */

        //check if number 4 exist
        int minesCounter = 0;
        if (j > 0) {
            minesCounter += mineFieldPositions[i][j - 1];//4
            //check if number 1 exist
            if (i > 0) {
                minesCounter += mineFieldPositions[i - 1][j - 1];//1
            }
        }

        //check if number 2 exist
        if (i > 0) {
            minesCounter += mineFieldPositions[i - 1][j];//2

            //check if number 3 exist
            if (j < m - 1) {
                minesCounter += mineFieldPositions[i - 1][j + 1];//3
            }
        }

        //check if number 6 exist
        if (j < m - 1) {
            minesCounter += mineFieldPositions[i][j + 1];//6

            //check if number 9 exist
            if (i < n - 1) {
                minesCounter += mineFieldPositions[i + 1][j + 1];//9
            }
        }

        //check if number 8 exist
        if (i < n - 1) {
            minesCounter += mineFieldPositions[i + 1][j];//8

            //check if number 7 exist
            if (j > 0) {
                minesCounter += mineFieldPositions[i + 1][j - 1];//7
            }
        }
        return minesCounter;
    }

}
