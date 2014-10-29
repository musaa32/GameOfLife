package gol;

import java.awt.Point;

/**
 * The Class Cell represents a single cell
 */
public class Cell{
	
	public enum Status{
		Dead,
		Alive
	}
	
	
	private Status cellState;
	private Point location;
	
	
	public Cell()
	{
		this.cellState = Status.Dead;
		
	}
	public Cell(Status state,int x, int y)
	{
		this.cellState = state;
		this.location = new Point();
		this.location.x = x;
		this.location.y = y;
	}
	public Status getCellState() {
		return cellState;
	}

	public void setCellState(Status cellState) {
		this.cellState = cellState;
	}
	
	public int getX()
	{
		return this.location.x;
	}
	
	public int getY()
	{
		return this.location.y;
	}
	
}
