package com.bjqg.linkCode;

/**
 * 判断一个 9 x 9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。
 * @author: lbj
 * @date: 2023/4/18 10:35
 */
public class IsValidSudoku {

    public static void main(String[] args) {
        char[][] board =
                {{'5','3','.','.','7','.','.','.','.'}
                ,{'6','.','.','1','9','5','.','.','.'}
                ,{'.','9','8','.','.','.','.','6','.'}
                ,{'8','.','.','.','6','.','.','.','3'}
                ,{'4','.','.','8','.','3','.','.','1'}
                ,{'7','.','.','.','2','.','.','.','6'}
                ,{'.','6','.','.','.','.','2','8','.'}
                ,{'.','.','.','4','1','9','.','.','5'}
                ,{'.','.','.','.','8','.','.','7','9'}};
        boolean b = isValidSudoKu(board);
        System.out.println(b);
    }

    private static boolean isValidSudoKu(char[][] board) {
        int[][] rows = new int[9][9];
        int[][] columns = new int[9][9];
        int[][][] boxes = new int[3][3][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c != '.'){
                    int index = c - '0' - 1;
                    rows[i][index]++;
                    columns[j][index]++;
                    boxes[i/3][j/3][index]++;
                    if (rows[i][index] > 1 || columns[j][index] > 1 || boxes[i/3][j/3][index] > 1){
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
