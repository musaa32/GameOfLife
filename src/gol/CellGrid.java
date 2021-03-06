package gol;

import java.util.*;
import java.awt.Point;
import java.io.*;

/**
 * The Class CellGrid.
 */
public class CellGrid {
	
	
	/** The generation. */
	private int generation;
	private int columns;
	private int rows;
	private Cell[][] cells;

	

	/**
	 * Instantiates a new cell grid.
	 */
	public CellGrid() {

	}

	/**
	 * Instantiates a new cell grid.
	 *
	 * @param cols the cols
	 * @param rows the rows
	 */
	public CellGrid(int cols, int rows) {
		this.initGrid(cols, rows);
	}

	/**
	 * Sets the cell state.
	 *
	 * @param col the col
	 * @param row the row
	 * @param cellValue the cell value = Alive
	 */
	public void setCellState(int col, int row, boolean cellValue) {
		this.cells[col][row].setCellState((cellValue ? Cell.Status.Alive : Cell.Status.Dead));
	}

	/**
	 * Gets the cell state.
	 *
	 * @param col the col
	 * @param row the row
	 * @return the cell state
	 */
	public boolean getCellState(int col, int row) {
		return this.cells[col][row].getCellState() == Cell.Status.Alive ? true : false;
		
	}

	/**
	 * Sets the cols.
	 *
	 * @param cols the new cols
	 */
	private void setCols(int cols) {
		this.columns = cols;
	}

	/**
	 * Gets the cols.
	 *
	 * @return the cols
	 */
	public int getCols() {
		return this.columns;
	}

	/**
	 * Sets the rows.
	 *
	 * @param rows the new rows
	 */
	private void setRows(int rows) {
		this.rows = rows;
	}

	/**
	 * Gets the rows.
	 *
	 * @return the rows
	 */
	public int getRows() {
		return this.rows;
	}

	/**
	 * Sets the generation.
	 *
	 * @param generation the new generation
	 */
	public void setGeneration(int generation) {
		this.generation = generation;
	}

	/**
	 * Gets the generation.
	 *
	 * @return the generation
	 */
	public int getGeneration() {
		return this.generation;
	}

	/**
	 * Next generation.
	 */
	public void nextGeneration() {
		
		ArrayList<Cell> newCells = new ArrayList<Cell>();
		for(int y = 0; y < this.rows; y++)
		{
			for(int x = 0; x < this.columns; x++)
			{
				int neighbours = this.getAliveNeighboursCount(x, y);
				if(this.calculateNewCellState(x, y, neighbours)) // isAlive
				{
					newCells.add(new Cell(Cell.Status.Alive,x,y));
				}else{
					newCells.add(new Cell(Cell.Status.Dead,x,y));
				}
				
			}
		}
		
		
		this.setGeneration(this.getGeneration()+1);
		// your code
		for(Cell object: newCells){
		  this.setCellState(object.getX(), object.getY(), object.getCellState()  == Cell.Status.Alive ? true : false);
		}
		
		
		
		
		
	}

	/**
	 * Gets the alive neighbours count.
	 *
	 * @param col the col
	 * @param row the row
	 * @return the alive neighbours count
	 */
	private int getAliveNeighboursCount(int col, int row) {
		
		int anz = 0;
		for (Point p : this.getNeigbours(col,row))
		{
			if(p.x >= 0 && p.y >= 0 && p.x < this.columns && p.y < this.rows)
			if(this.isAlive(p.y, p.x))
				anz++;
		}
		
		return anz++;
	}

	/**
	 * Calculate new cell state.
	 *
	 * @param col the col
	 * @param row the row
	 * @param aliveNeighboursCount the alive neighbours count
	 * @return true, if successful and is alive
	 */
	private boolean calculateNewCellState(int col, int row,int aliveNeighboursCount) {
		boolean isAlive = false;
		
		if(this.isAlive(row, col) && aliveNeighboursCount < 2)
		{
			isAlive = false;
		}else if(this.isAlive(row, col) && aliveNeighboursCount > 3)
		{
			isAlive = false;
		}else if(this.isAlive(row, col) && (aliveNeighboursCount == 2 || aliveNeighboursCount == 3))
		{
			isAlive = true;
		}else if(!this.isAlive(row, col) && aliveNeighboursCount == 3)
		{
			isAlive = true;
		}else{
			isAlive = false;
		}
		return isAlive;
	}

	/**
	 * Reset.
	 */
	public void reset() {
		//TODO implement
	}

	/**
	 * Randomize.
	 */
	public void randomize() {
	
		Random rand = new Random();
		for(int x = 0; x < this.columns; x++)
		{
			for(int y = 0; y < this.rows; y++)
			{
				int n = rand.nextInt(2); // 0 oder 1
				if(n == 0) // tot
				{
					cells[x][y] = new Cell(Cell.Status.Dead,x,y);
				}else{ //lebendig
					cells[x][y] = new Cell(Cell.Status.Alive,x,y);
				}
			}
		}
	}

	/**
	 * Inits the grid.
	 *
	 * @param cols the cols
	 * @param rows the rows
	 */
	private void initGrid(int cols, int rows) {
		
		this.columns = cols;
		this.rows = rows;
		
		this.cells = new Cell[this.columns][this.rows];
		
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		//grid 1 = > X  0 = > 0
		StringBuilder sb = new StringBuilder();
		for(int y = 0; y < this.rows; y++)
		{
			for(int x = 0; x < this.columns; x++)
			{
				
				if(cells[x][y].getCellState() == Cell.Status.Dead) // tot
				{
					sb.append("0");
				}else{ //lebendig
					sb.append("X");
				}
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	/**
	 * Checks if is alive.
	 *
	 * @param row the row
	 * @param col the col
	 * @return true, if is alive
	 */
	public boolean isAlive(int row, int col) {
		return this.cells[col][row].getCellState() == Cell.Status.Alive;
	}
	
	/**
	 * Gets the cell.
	 *
	 * @param row the row
	 * @param col the col
	 * @return the cell
	 */
	public Cell getCell(int row, int col){
		return this.cells[col][row];
	}
	
	/**
	 * Load grid.
	 *
	 * @param file the file
	 */
	public void loadGrid(File file) {
		String line;
		Vector<String> fileGrid = new Vector<String>();
		try {
			BufferedReader fileReader = new BufferedReader(new FileReader(file));
			while ((line = fileReader.readLine()) != null)
				fileGrid.add(line);
			fileReader.close();
		} catch (Exception e) {

		}

		this.setCols(fileGrid.get(0).length());
		this.setRows(fileGrid.size());
		this.setGeneration(0);

		this.cells = new Cell[this.getCols()][this.getRows()];
		
		for (int i = 0; i < this.getCols(); i++) {
			for (int j = 0; j < this.getRows(); j++) {
				String fieldValue = fileGrid.elementAt(j).substring(i, i + 1);
				boolean state = (fieldValue.equals("X") ? true : false);
				this.cells[i][j] = new Cell(state? Cell.Status.Alive : Cell.Status.Dead,i,j);
				
			}
		}
		
	}
	
	/**
	 * Save grid.
	 *
	 * @param file the file
	 */
	public void saveGrid(File file) {
		try {
			BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file));
			for (int i = 0; i < this.getRows(); i++) {
				for (int j = 0; j < this.getCols(); j++) {
					
					//TODO implement
				
				}
				fileWriter.newLine();
			}
			fileWriter.flush();
			fileWriter.close();
		} catch (Exception e) {
		}
	}
	
	private List<Point> getNeigbours(int x, int y)
	{
		ArrayList<Point> neighbours = new ArrayList<Point>();
		neighbours.add(new Point(x,y-1));
		neighbours.add(new Point(x+1,y-1));
		neighbours.add(new Point(x+1,y));
		neighbours.add(new Point(x+1,y+1));
		neighbours.add(new Point(x,y+1));
		neighbours.add(new Point(x-1,y+1));
		neighbours.add(new Point(x-1,y));
		neighbours.add(new Point(x-1,y-1));
		return neighbours;
	}
	
}
