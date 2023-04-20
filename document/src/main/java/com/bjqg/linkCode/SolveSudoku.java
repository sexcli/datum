package com.bjqg.linkCode;

import java.util.ArrayList;
import java.util.List;

/**
 * 数独的解法需 遵循如下规则：
 *
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
 * 数独部分空格内已填入了数字，空白格用 '.' 表示。
 * @author: lbj
 * @date: 2023/4/20 14:17
 */
public class SolveSudoku {

    private static boolean[][] line = new boolean[9][9];
    private static boolean[][] column = new boolean[9][9];
    private static boolean[][][] block = new boolean[3][3][9];
    private static boolean valid = false;
    private static List<int[]> spaces = new ArrayList<int[]>();

    public static void main(String[] args) {
        char[][] board = {{'5','3','.','.','7','.','.','.','.'}
                        ,{'6','.','.','1','9','5','.','.','.'}
                        ,{'.','9','8','.','.','.','.','6','.'}
                        ,{'8','.','.','.','6','.','.','.','3'}
                        ,{'4','.','.','8','.','3','.','.','1'}
                        ,{'7','.','.','.','2','.','.','.','6'}
                        ,{'.','6','.','.','.','.','2','8','.'}
                        ,{'.','.','.','4','1','9','.','.','5'}
                        ,{'.','.','.','.','8','.','.','7','9'}};
        solveSudoku(board);
    }

    private static void solveSudoku(char[][] board) {
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (board[i][j] == '.'){
                    spaces.add(new int[]{i,j});
                }else {
                    int digit = board[i][j] - '0' - 1;
                    line[i][digit] = column[j][digit] = block[i/3][j/3][digit] = true;
                }
            }
        }
        dfs(board,0);
    }

    private static void dfs(char[][] board, int pos) {
        if (pos == spaces.size()){
            valid = true;
            return;
        }

        int[] space = spaces.get(pos);
        int i = space[0],j = space[1];
        for (int digit = 0;digit < 9 && !valid;++digit){
            if (!line[i][digit] && !column[j][digit] && !block[i/3][j/3][digit]){
                line[i][digit] = column[j][digit] = block[i/3][j/3][digit] = true;
                board[i][j] = (char) (digit + '0' + 1);
                dfs(board, pos + 1);
                line[i][digit] = column[j][digit] = block[i/3][j/3][digit] = false;
            }
        }
    }
}
