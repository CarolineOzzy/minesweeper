package model;

import java.util.Random;

public class Matrix {

    Random random = new Random();
    int size = 8; // Size of minefield (it's a square matrix)
    int mMines = 7; // number of mines in the minefield
    int[][] mMinefield; // The board is a matrix

    public Matrix() {
        this.createMatrix();
        this.placeMines();
        this.calculateHints();
    }
    // Creating the 8x8 matrix
    public void createMatrix() {
        mMinefield = new int[size][size];
    }

    // Filling the matrix with mines
    public void placeMines() {
        int minesPlaced = 0;
        while (minesPlaced < mMines) {
            int x = random.nextInt(size);
            int y = random.nextInt(size);
            // no mines on top of each other
            if (mMinefield[y][x] != -1) {
                mMinefield[y][x] = -1;
                minesPlaced++;
            }
        }
    }
    // This puts the hints on the board
    public void calculateHints() {
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                if (mMinefield[y][x] != -1) {
                    mMinefield[y][x] = minesNear(y, x);
                }
            }
        }
    }

    public int minesNear(int y, int x) {
        int mines = 0;
        // checks neighbouring mines to any given cell in the matrix
        mines += mineAt(y - 1, x - 1);  // NW
        mines += mineAt(y - 1, x);      // N
        mines += mineAt(y - 1, x + 1);  // NE
        mines += mineAt(y, x - 1);      // W
        mines += mineAt(y, x + 1);      // E
        mines += mineAt(y + 1, x - 1);  // SW
        mines += mineAt(y + 1, x);      // S
        mines += mineAt(y + 1, x + 1);  // SE
        return mines;
    }

    // returns 1 if there is a mine at y,x or 0 if there isn't
    public int mineAt(int y, int x) {
        if (y >= 0 && y < size && x >= 0 && x < size && mMinefield[y][x] == -1) {
            return 1;
        } else {
            return 0;
        }
    }


    public boolean isHint (int x, int y){
        if (this.mMinefield[x][y] >= 1 && this.mMinefield[x][y] <= 8)
            return true;
        return false;
    }

    public boolean isBomb (int x, int y){
        if (this.mMinefield[x][y] == -1)
            return true;
        return false;
    }

    public boolean isNothing (int x, int y){
        if (this.mMinefield[x][y] == 0)
            return true;
        return false;
    }

    public boolean isValidCoord (int x, int y){
        if (x >= 0 && x < 8 && y >= 0 && y <8)
            return true;
        return false;
    }
    public int getValueAt (int x, int y){
        return this.mMinefield[x][y];
    }

    public int getmMines (){
        return mMines;
    }

    public void searchForZeroes (int x, int y){
        this.mMinefield[x][y] = -2;
        if (this.isValidCoord(x-1,y-1)){
            if (this.isNothing(x-1,y-1))
                this.searchForZeroes(x-1,y-1);
            else if (this.mMinefield[x-1][y-1] >= 0 && this.mMinefield[x-1][y-1] <= 8)
                this.mMinefield[x-1][y-1] += 8;
        }
        if (this.isValidCoord(x,y-1)){
            if (this.isNothing(x,y-1))
                this.searchForZeroes(x,y-1);
            else if (this.mMinefield[x][y-1] >= 0 && this.mMinefield[x][y-1] <= 8)
                this.mMinefield[x][y-1] += 8;
        }
        if (this.isValidCoord(x+1,y-1)){
            if (this.isNothing(x+1,y-1))
                this.searchForZeroes(x+1,y-1);
            else if (this.mMinefield[x+1][y-1] >= 0 && this.mMinefield[x+1][y-1] <= 8)
                this.mMinefield[x+1][y-1] += 8;
        }
        if (this.isValidCoord(x-1,y)){
            if (this.isNothing(x-1,y))
                this.searchForZeroes(x-1,y);
            else if (this.mMinefield[x-1][y] >= 0 && this.mMinefield[x-1][y] <= 8)
                this.mMinefield[x-1][y] += 8;
        }
        if (this.isValidCoord(x+1,y)){
            if (this.isNothing(x+1,y))
                this.searchForZeroes(x+1,y);
            else if (this.mMinefield[x+1][y] >= 0 && this.mMinefield[x+1][y] <= 8)
                this.mMinefield[x+1][y] += 8;
        }
        if (this.isValidCoord(x-1,y+1)){
            if (this.isNothing(x-1,y+1))
                this.searchForZeroes(x-1,y+1);
            else if (this.mMinefield[x-1][y+1] >= 0 && this.mMinefield[x-1][y+1] <= 8)
                this.mMinefield[x-1][y+1] += 8;
        }
        if (this.isValidCoord(x,y+1)){
            if (this.isNothing(x,y+1))
                this.searchForZeroes(x,y+1);
            else if (this.mMinefield[x][y+1] >= 0 && this.mMinefield[x][y+1] <= 8)
                this.mMinefield[x][y+1] += 8;
        }
        if (this.isValidCoord(x+1,y+1)){
            if (this.isNothing(x+1,y+1))
                this.searchForZeroes(x+1,y+1);
            else if (this.mMinefield[x+1][y+1] >= 0 && this.mMinefield[x+1][y+1] <= 8)
                this.mMinefield[x+1][y+1] += 8;
        }
    }

    public void setValueBack (int x, int y){
        this.mMinefield[x][y] -= 8;
    }

    public void printMatrix (){
        System.out.print('\n');
        for (int i = 0; i< 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (this.mMinefield[i][j] == -1)
                    System.out.print("B ");
                else if (this.mMinefield[i][j] == -2)
                    System.out.print("A ");
                else {System.out.print(this.mMinefield[i][j]); System.out.print(' ');}
            }
            System.out.print('\n');
        }
    }
}