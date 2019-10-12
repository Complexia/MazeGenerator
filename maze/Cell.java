package maze;
/**
 * 
 * @author Youhan Xia
 * @author Jeffrey Chan
 * 
 * Object used in map matrix in maze.Maze. 
 * The solvers require that all neighbor cell objects are assigned correctly
 * and same wall object are shared by two cell objects next to the wall.
 */
public class Cell {
	/**
	 * row coordinate
	 */
	public int r;
	
	/**
	 * column coordinate
	 */
	public int c;
	
	/**
	 * wall[i] is the wall on direction i of the cell
	 * rectangular cells have 4 walls: wall[0], wall[2], wall[3], wall[5] are EAST, NORTH, WEST, SOUTH walls, respectively (see Maze.java)
	 * hexagonal cells have 6 walls instead
	 */
	public Wall wall[] = {null, null, null, null, null, null};
	
	/**
	 * neigh[i] is the neighbor cell of direction i
	 * rectangular cells have 4 neighbors: neigh[0], neigh[2], neigh[3], neigh[5] are EAST, NORTH, WEST, SOUTH neighbors, respectively
	 * hexagonal cells have 6 neighbors instead	 
	*/
	public Cell neigh[] = {null, null, null, null, null, null};
	
	/**
	 * the other end of a tunnel connected to it, null if not or not a tunnel maze
	 */
	public Cell tunnelTo = null;
	
	/**
	 * construct cell of position (r, c) in the maze
	 * @param r	Row coordinate in the maze
	 * @param c Column coordinate in the maze
	 */
	public Cell(int r, int c) {
		this.r = r;
		this.c = c;
	} // end of Cell()
	
	/**
	 * default constructor
	 */
	public Cell() {
		this(0, 0);
	} // end of Cell()
	
} // end of class Cell
