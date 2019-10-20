package mazeGenerator;

import maze.Maze;
import maze.Cell;
import java.util.*;

public class HuntAndKillGenerator implements MazeGenerator {

	//cell instance for updates of the cell from hunt
	Cell globalCurrentCell;

	@Override
	public void generateMaze(Maze maze) {
		
		//random coords of the first element
		Random r = new Random();
		int rX = r.nextInt(10);
		int rY = r.nextInt(10);

		//a copy of the map to keep track of visited cells
		Cell mapCopy[][] = new Cell[maze.map.length][maze.map.length];
		for(int i=0; i<maze.map.length; i++){

  			for(int j=0; j<maze.map.length; j++){

    			mapCopy[i][j]=maze.map[i][j];
  			}
		}

		Cell currentCell = maze.map[rX][rY];
		globalCurrentCell = currentCell;
		
		//defines all possible directions for walls and neighbors
		Integer walls[] = new Integer[]{0,2,3,5};			
		List<Integer> l = new LinkedList<Integer>(Arrays.asList(walls));

		//walk
		walk(currentCell,maze,mapCopy,l);
		
		//hunt and walk again recursively if need to
		while(hunt(mapCopy,currentCell,maze,l)){
			currentCell = globalCurrentCell;			
			walk(currentCell,maze,mapCopy,l);
		}

	} // end of generateMaze()

	//method defining the walking stage of hunt and kill
	private void walk(Cell currentCell, Maze maze, Cell[][] mapCopy, List<Integer> l){

		//a condition to determine when walking is finished in a dead end
		boolean doneWalking = false;

		while(doneWalking == false){
			
			//chooses appropriate random direction in which to seek neighbor
			l = adjustWallsAndNeighs(currentCell.r, currentCell.c, maze);

			//if neighbor of currentCell is not visited, carves passage, updates currentCell, and breaks loop
			for(int i=0;i<l.size();i++){

				if(currentCell.tunnelTo != null){
					mapCopy[currentCell.r][currentCell.c] = null;
					maze.drawFtPrt(currentCell);
					currentCell = currentCell.tunnelTo;
					currentCell.tunnelTo = null;
					
					maze.drawFtPrt(currentCell);
					System.out.println("Here");
					break;
					
				}
				if(mapCopy[currentCell.neigh[l.get(i)].r][currentCell.neigh[l.get(i)].c] != null) {
					currentCell.wall[l.get(i)].present = false;
					mapCopy[currentCell.r][currentCell.c] = null;
					
					maze.drawFtPrt(currentCell);
					currentCell = currentCell.neigh[l.get(i)];
					break;
				}

				//if there are no unvisited neighbors, doneWalking is invoked and walking stage is finished
				if(i == l.size()-1){
					mapCopy[currentCell.r][currentCell.c] = null;

					doneWalking = true;
					maze.drawFtPrt(currentCell);
				}
			}
		}

	} // end walk

	//defining the hunting stage of hunt and kill
	private boolean hunt(Cell[][] mapCopy, Cell currentCell, Maze maze, List<Integer> l){

		//starting from 0,0, looks for visited cell with unvisited neighbor
		currentCell = maze.map[0][0];
		for(int i=0;i<mapCopy.length;i++){
			for (int j=0;j<mapCopy.length;j++){

				//if cell is visited
				if(mapCopy[i][j] == null){

					//adjusts l to contain appropriate direction to look for neighbor
					l = adjustWallsAndNeighs(i,j,maze);
					for(int k=0;k<l.size();k++){

						//if neighbor is unvisited, carves passage, sets the neighbor as currentCell, and 
						//returns true, which leads to walking mode
						if(mapCopy[maze.map[i][j].neigh[l.get(k)].r][maze.map[i][j].neigh[l.get(k)].c] != null){
							maze.map[i][j].wall[l.get(k)].present = false;
							currentCell = maze.map[i][j].neigh[l.get(k)];
							globalCurrentCell = currentCell;
							return true;
						}
					}
				}
			}
		}
		//if no matches are found, returns false, and finishes the algorithm
		return false;

	}

	//defines and shuffles directions for walls and neighbors
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
	}

} // end of class HuntAndKillGenerator
