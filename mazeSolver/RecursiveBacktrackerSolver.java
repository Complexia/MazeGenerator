package mazeSolver;

import maze.Maze;
import java.util.*;
import maze.Cell;

/**
 * Implements the recursive backtracking maze solving algorithm.
 */
public class RecursiveBacktrackerSolver implements MazeSolver {

	boolean mazeSolved = false;

	@Override
	public void solveMaze(Maze maze) {
		
		Integer walls[] = new Integer[]{0,2,3,5};			
		List<Integer> l = new LinkedList<Integer>(Arrays.asList(walls));

		Cell mapCopy[][] = new Cell[maze.map.length][maze.map.length];
		for(int i=0; i<maze.map.length; i++){

  			for(int j=0; j<maze.map.length; j++){

    			mapCopy[i][j]=maze.map[i][j];
  			}
		}

		Cell currentCell = maze.entrance;
		List<Cell> visitedCells = new ArrayList<Cell>();
		visitedCells.add(currentCell);
		mapCopy[currentCell.r][currentCell.c] = null;
		maze.drawFtPrt(currentCell);
		l = adjustWallsAndNeighs(currentCell.r, currentCell.c, maze);
		
		//!visitedCells.get(visitedCells.size()-1).equals(currentCell.neigh[l.get(i)])
		int k = 0;
		int u = 0;
		while(currentCell != maze.exit){
			u = 1;
			l = adjustWallsAndNeighs(currentCell.r, currentCell.c, maze);
			for(int i=0;i<l.size();i++){
				if(!currentCell.wall[l.get(i)].present && 
					mapCopy[maze.map[currentCell.r][currentCell.c].neigh[l.get(i)].r][maze.map[currentCell.r][currentCell.c].neigh[l.get(i)].c] != null) {
						
						
						
						currentCell = currentCell.neigh[l.get(i)];
						mapCopy[currentCell.r][currentCell.c] = null;
						visitedCells.add(currentCell);
						maze.drawFtPrt(currentCell);
						k = 0;
						u = 0;
						
						break;

				}
				if(i == l.size()-1 && u != 0){
									
					currentCell = visitedCells.get((visitedCells.size()-1)-k);
						

				}

			}
			if(k<visitedCells.size() && u != 0){
				k++;
			}
			if(currentCell.equals(maze.exit)){
				mazeSolved = true;
			}
		}
		

	

	} // end of solveMaze()


	@Override
	public boolean isSolved() {
		if(mazeSolved == true){
			return true;
		}
		return false;
	} // end if isSolved()


	@Override
	public int cellsExplored() {
		// TODO Auto-generated method stub
		return 0;
	} // end of cellsExplored()

	private List<Integer> adjustWallsAndNeighs(int r, int c, Maze maze){

		Integer walls[] = new Integer[]{0,2,3,5};			
		List<Integer> l = new LinkedList<Integer>(Arrays.asList(walls));

		//if on east edge, cannot look further east, etc...
		if(r == 0){
				Iterator itr = l.iterator(); 
        		while (itr.hasNext()) 
        		{ 
		            int x = (Integer)itr.next(); 
		            if (x == 5) 
		                itr.remove(); 
        		}
			}
			if(c == 0){
				Iterator itr = l.iterator(); 
        		while (itr.hasNext()) 
        		{ 
		            int x = (Integer)itr.next(); 
		            if (x == 3) 
		                itr.remove(); 
        		}
			}
			if(r == maze.map.length -1){
				Iterator itr = l.iterator(); 
        		while (itr.hasNext()) 
        		{ 
		            int x = (Integer)itr.next(); 
		            if (x == 2) 
		                itr.remove(); 
        		}
			}
			if(c == maze.map.length -1){
				Iterator itr = l.iterator(); 
        		while (itr.hasNext()) 
        		{ 
		            int x = (Integer)itr.next(); 
		            if (x == 0) 
		                itr.remove(); 
        		}
			}
			Collections.shuffle(l);
			return l;
	} // end adjustWallsAndNeighs

} // end of class RecursiveBackTrackerSolver
