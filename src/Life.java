public class Life {
    private int maxRow;
    private int maxCol;
 
    private int[][] grid;
 
    public Life(int maxRow, int maxCol) {//初始化生命
        this.maxRow = maxRow;
        this.maxCol = maxCol;
        grid = new int[maxRow + 2][maxCol + 2];
        for (int row = 0; row <= maxRow + 1; row++)
            for (int col = 0; col <= maxCol + 1; col++)
                grid[row][col] = 0;
    }
 
    public int[][] getGrid() {
        return grid;
    }
 
    public void setGrid(int[][] grid) {
        this.grid = grid;
    }
 
    public void update() {//生命演变更新
        int[][] newGrid = new int[maxRow + 2][maxCol + 2];
        for (int row = 1; row <= maxRow; row++)
            for (int col = 1; col <= maxCol; col++)
                switch (getNeighborCount(row, col)) {
                    case 2:
                        newGrid[row][col] = grid[row][col]; //状态保持不变
                        break;
                    case 3:
                        newGrid[row][col] = 1; // Cell is alive.
                        break;
                    default:
                        newGrid[row][col] = 0; // Cell is dead.
                }
 
        for (int row = 1; row <= maxRow; row++)
            for (int col = 1; col <= maxCol; col++)
                grid[row][col] = newGrid[row][col];
    }
 
    private int getNeighborCount(int row, int col) {
        int count = 0;
 
        for (int i = row - 1; i <= row + 1; i++)
            for (int j = col - 1; j <= col + 1; j++)
                count += grid[i][j]; // 如果邻居还活着，则增加计数。
        count -= grid[row][col]; // 减少计数，因为单元不是它自己的邻居。
 
        return count;
    }
}

