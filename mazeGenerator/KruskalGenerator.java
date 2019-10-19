package mazeGenerator;

import maze.Maze;
import maze.Cell;
import java.util.*;

public class KruskalGenerator implements MazeGenerator {

	@Override
	public void generateMaze(Maze maze) {
		
		//a set of all possible edges, initially stored as disjointed trees
		ArrayList<ArrayList<Cell>> edges = new ArrayList<ArrayList<Cell>>();

		//a set of all cells, intially stored as disjointed sets
		ArrayList<ArrayList<Cell>> cells = new ArrayList<ArrayList<Cell>>();
		
		//filling the list of all possible edges according to the following algorithm:
		//if the cell is not on the extremes to the north or east, create edge with north neigh and east neigh
		//if cell is on the east extreme, create edge only with north neigh
		//if cell is on the north extreme, create edge only with east neigh
		//if cell is on both extremes (corner cell), don't create any edge
		//this generates ((2n-1) + 2(n^2 - 2n -1)) -1 number of edges for any given n x n maze
		for(int i=0;i<maze.map.length;i++){
			for(int j=0;j<maze.map.length;j++){
				ArrayList<Cell> edge1 = new ArrayList<Cell>();
				ArrayList<Cell> edge2 = new ArrayList<Cell>();
				edge1.add(maze.map[i][j]);
				edge2.add(maze.map[i][j]);
				if(maze.map[i][j].r < maze.map.length -1){
					edge1.add(maze.map[i][j].neigh[2]);
				}
				if(maze.map[i][j].c < maze.map.length -1){
					edge2.add(maze.map[i][j].neigh[0]);
				}
				if(edge1.size() == 2){
					edges.add(edge1);
				}
				if(edge2.size() == 2){
					edges.add(edge2);
				}
			}
		}

		for(int i=0;i<maze.map.length;i++){
			for(int j=0;j<maze.map.length;j++){
				ArrayList<Cell> cells1 = new ArrayList<Cell>();
				cells1.add(maze.map[i][j]);
				cells.add(cells1);
			}
		}

		

		
		//until there is only one tree
		while(cells.size() > 1){

			Random r = new Random(); 
			int e1 = r.nextInt(edges.size()); //randomly selects an edge


			//randomly selects an edge, and joins the 2 sets of cells belonging to the edge
			//into one set of those cells. The below code carves passage between 2 cells 
			//according to the edge selected
			for(int i=0;i<cells.size();i++){
				if((cells.get(i).contains(edges.get(e1).get(0)) && !cells.get(i).contains(edges.get(e1).get(1)) ) ){

					if(edges.get(e1).get(0).neigh[0] != null &&
						edges.get(e1).get(0).neigh[0].equals(edges.get(e1).get(1))) {
							edges.get(e1).get(0).wall[0].present = false;
							maze.drawFtPrt(edges.get(e1).get(0));
						}
						if(edges.get(e1).get(0).neigh[2] != null &&
						edges.get(e1).get(0).neigh[2].equals(edges.get(e1).get(1))){
							edges.get(e1).get(0).wall[2].present = false;
							maze.drawFtPrt(edges.get(e1).get(0));
						}
						if(edges.get(e1).get(0).neigh[3] != null &&
						edges.get(e1).get(0).neigh[3].equals(edges.get(e1).get(1))){
							edges.get(e1).get(0).wall[3].present = false;
							maze.drawFtPrt(edges.get(e1).get(0));
						}
						if(edges.get(e1).get(0).neigh[5] != null &&
						edges.get(e1).get(0).neigh[5].equals(edges.get(e1).get(1))){
							edges.get(e1).get(0).wall[5].present = false;
							maze.drawFtPrt(edges.get(e1).get(0));
						}
						//joins the sets of cells (edges) together
						for(int j=0;j<cells.size();j++){
							if(cells.get(j).contains(edges.get(e1).get(0)) || cells.get(j).contains(edges.get(e1).get(1))){
								cells.get(i).addAll(cells.get(j));
								cells.remove(j);
								break;
								
							}
						}
				}

			}
			//discards the edge after considering it
			edges.remove(e1);

		}
		
	
	} // end of generateMaze()

	

} // end of class KruskalGenerator



