// Board.java
package com.booleanuk.model;

public class Board {
    private char[][] grid;

    public Board() {
        this.grid = new char[10][10];
        // Initialize the board with empty values
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                grid[i][j] = '-';
            }
        }
    }

    public char[][] getGrid() {
        return grid;
    }

    public boolean placeShip(int x, int y, int length, boolean horizontal) {
        if (horizontal) {
            if (y + length > 10) return false;
            for (int i = 0; i < length; i++) {
                if (grid[x][y + i] != '-') return false;
            }
            for (int i = 0; i < length; i++) {
                grid[x][y + i] = 'S';
            }
        } else {
            if (x + length > 10) return false;
            for (int i = 0; i < length; i++) {
                if (grid[x + i][y] != '-') return false;
            }
            for (int i = 0; i < length; i++) {
                grid[x + i][y] = 'S';
            }
        }
        return true;
    }

    public boolean attack(int x, int y) {
        if (grid[x][y] == '-') {
            grid[x][y] = 'M'; // Miss
            return false;
        } else if (grid[x][y] == 'S') {
            grid[x][y] = 'H'; // Hit
            return true;
        } else {
            throw new IllegalStateException("This position has already been attacked.");
        }
    }
}