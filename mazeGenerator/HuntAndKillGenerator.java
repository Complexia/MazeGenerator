package mazeGenerator;

import maze.Maze;
import maze.Cell;
import java.util.*;

public class HuntAndKillGenerator implements MazeGenerator {

	Cell globalCurrentCell;

	@Override
	public void generateMaze(Maze maze) {
		
		Random r = new Random();
		int rX = r.nextInt(10);
		int rY = r.nextInt(10);
		Cell mapCopy[][] = new Cell[maze.map.length][maze.map.length];
		for(int i=0; i<maze.map.length; i++){

  			for(int j=0; j<maze.map.length; j++){

    			mapCopy[i][j]=maze.map[i][j];
  			}
		}
		
		
		
		Cell currentCell = maze.map[rX][rY];
		globalCurrentCell = currentCell;
		
		Integer walls[] = new Integer[]{0,2,3,5};			
		List<Integer> l = new LinkedList<Integer>(Arrays.asList(walls));
		

		//walk

		walk(currentCell,maze,mapCopy,l);
		
		//hunt and walk again recursively if need to

		if(hunt(mapCopy,currentCell,maze,l)){
			System.out.println("Current cell after hunt: " + currentCell.r + " || " + currentCell.c);
			walk(currentCell,maze,mapCopy,l);

		}
		
			System.out.println("Maze complete!");
		
		

	} // end of generateMaze()

	private void walk(Cell currentCell, Maze maze, Cell[][] mapCopy, List<Integer> l){
		System.out.println("Walking........................................................");
		boolean doneWalking = false;
		
		
		//walk


		while(doneWalking == false){

			System.out.println(currentCell.r + " || " + currentCell.c);
			
			l = adjustWallsAndNeighs(currentCell.r, currentCell.c, maze);


			
			System.out.println("Directions: ");
			
				
			System.out.println(l);
			
			System.out.println("End Directions: ");
			
			
			for(int i=0;i<l.size();i++){
				if(mapCopy[currentCell.neigh[l.get(i)].r][currentCell.neigh[l.get(i)].c] != null) {
					currentCell.wall[l.get(i)].present = false;
					mapCopy[currentCell.r][currentCell.c] = null;
					maze.drawFtPrt(currentCell);
					currentCell = currentCell.neigh[l.get(i)];
					
					
					break;
				}
				if(i == l.size()-1){
					mapCopy[currentCell.r][currentCell.c] = null;
					doneWalking = true;
					
				}
			}
		}

	} // end walk

	private boolean hunt(Cell[][] mapCopy, Cell currentCell, Maze maze, List<Integer> l){
		System.out.println("Hunting...............................................................");
		currentCell = maze.map[0][0];
		for(int i=0;i<mapCopy.length;i++){
			for (int j=0;j<mapCopy.length;j++){
				if(mapCopy[i][j] == null){
					l = adjustWallsAndNeighs(i,j,maze);
					for(int k=0;k<l.size();k++){
						if(mapCopy[maze.map[i][j].neigh[l.get(k)].r][maze.map[i][j].neigh[l.get(k)].c] != null){
							maze.map[i][j].wall[l.get(k)].present = false;
							currentCell = maze.map[i][j].neigh[l.get(k)];
							System.out.println("Cell hunted: " + maze.map[i][j].neigh[l.get(k)].r + " || " + maze.map[i][j].neigh[l.get(k)].c);
							return true;
						}
					}
				}
			}
		}
		return false;

	}

	private List<Integer> adjustWallsAndNeighs(int r, int c, Maze maze){

		Integer walls[] = new Integer[]{0,2,3,5};			
		List<Integer> l = new LinkedList<Integer>(Arrays.asList(walls));

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
