package mazeGenerator;

import maze.Maze;
import maze.Cell;
import java.util.*;

public class KruskalGenerator implements MazeGenerator {

	@Override
	public void generateMaze(Maze maze) {
		
		ArrayList<ArrayList<Cell>> edges = new ArrayList<ArrayList<Cell>>();
		ArrayList<ArrayList<Cell>> cells = new ArrayList<ArrayList<Cell>>();
		
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

		

		// while(edges.size() > 1){

		// 	Random r = new Random();
		// 	int e1 = r.nextInt(edges.size());
		// 	int e2 = r.nextInt(edges.size());

		// 	while(e1 == e2){
		// 		e2 = r.nextInt(edges.size());

		// 	}
		// 	if(!(Collections.disjoint(edges.get(e1),edges.get(e2)))){
		// 		edges.get(e1).addAll(edges.get(e2));
				
		// 		edges.remove(e2);
		// 		// for(int i=0;i<edges.get(e1).size();i++){
		// 		// 	for(int j=0;j<edges.get(e2).size();j++){

		// 		// 	}
		// 		// }


		// 	}
		// 	// else{

		// 	// 	edges.remove(e1);
		// 	// 	if(e1 > e2){
		// 	// 		edges.remove(e2);
		// 	// 	}
		// 	// 	else{
		// 	// 		edges.remove(e2-1);
		// 	// 	}
				
		// 	// }

		// }
		System.out.println(edges.size());
		// while(edges.size()>0){
		// 	Random r = new Random();
		// 	int e1 = r.nextInt(edges.size());

		// 	for(int i=0;i<edges.size();i++){
		// 		if(i != e1 && !Collections.disjoint(edges.get(e1),edges.get(i))){

		// 			if(edges.get(e1).get(0).neigh[0] != null && 
		// 				edges.get(e1).get(0).neigh[0].equals(edges.get(e1).get(1))){
		// 				edges.get(e1).get(0).wall[0].present = false;
		// 				maze.drawFtPrt(edges.get(e1).get(0));
		// 				break;
		// 			}
		// 			if(edges.get(e1).get(0).neigh[2] != null && 
		// 				edges.get(e1).get(0).neigh[2].equals(edges.get(e1).get(1))){
		// 				edges.get(e1).get(0).wall[2].present = false;
		// 				maze.drawFtPrt(edges.get(e1).get(0));
		// 				break;
		// 			}
		// 			if(edges.get(e1).get(0).neigh[3] != null && 
		// 				edges.get(e1).get(0).neigh[3].equals(edges.get(e1).get(1))){
		// 				edges.get(e1).get(0).wall[3].present = false;
		// 				maze.drawFtPrt(edges.get(e1).get(0));
		// 				break;
		// 			}
		// 			if(edges.get(e1).get(0).neigh[5] != null && 
		// 				edges.get(e1).get(0).neigh[5].equals(edges.get(e1).get(1))){
		// 				edges.get(e1).get(0).wall[5].present = false;
		// 				maze.drawFtPrt(edges.get(e1).get(0));
		// 				break;
		// 			}

		// 		}
		// 	}
		// 	edges.remove(e1);
		// }

		while(cells.size() > 1){

			Random r = new Random();
			int e1 = r.nextInt(edges.size());

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

						for(int j=0;j<cells.size();j++){
							if(cells.get(j).contains(edges.get(e1).get(0)) || cells.get(j).contains(edges.get(e1).get(1))){
								cells.get(i).addAll(cells.get(j));
								cells.remove(j);
								break;
								
							}
						}
				}

			}
			edges.remove(e1);

		}
		
		




		// System.out.println(edges.size());
		// System.out.println(edges.get(0).size());
		// for(int i=0;i<edges.get(0).size()-1;i+=2){
			
		// 	if((edges.get(0).get(i).neigh[0] != null) && 
		// 		(edges.get(0).get(i).neigh[0].equals(edges.get(0).get(i+1)))){

		// 		edges.get(0).get(i).wall[0].present = false;
		// 		maze.drawFtPrt(edges.get(0).get(i));

		// 	}

		// 	else if((edges.get(0).get(i).neigh[2] != null) && 
		// 		(edges.get(0).get(i).neigh[2].equals(edges.get(0).get(i+1)))){

		// 		edges.get(0).get(i).wall[2].present = false;
		// 		maze.drawFtPrt(edges.get(0).get(i));

		// 	}

		// 	else if((edges.get(0).get(i).neigh[3] != null) && 
		// 		(edges.get(0).get(i).neigh[3].equals(edges.get(0).get(i+1)))){

		// 		edges.get(0).get(i).wall[3].present = false;
		// 		maze.drawFtPrt(edges.get(0).get(i));

		// 	}

		// 	else if((edges.get(0).get(i).neigh[5] != null) && 
		// 		(edges.get(0).get(i).neigh[5].equals(edges.get(0).get(i+1)))){

		// 		edges.get(0).get(i).wall[5].present = false;
		// 		maze.drawFtPrt(edges.get(0).get(i));

		// 	}
		// }
		






	} // end of generateMaze()

	


} // end of class KruskalGenerator



