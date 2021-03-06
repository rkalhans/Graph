package examples;

import graph.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rkalhans on 3/20/2016.
 * Problem statement: Given an 2D array of 0 & 1 where 0 denotes water and 1 denotes land. Find the number of islands
 * which are present in the map. adjecency is only up down left right. Diagonals are not considered.
 */
public class CountIsland {
    final static int LENGTH = 5;
    final static int WIDTH = 5;
    public static void main(String[] args) {
       new CountIsland().start();
    }
    public void start(){
        int[][] map  = {
                {1,1,0,1,1},
                {1,1,0,0,1},
                {0,0,1,1,1},
                {1,1,0,0,0},
                {1,1,1,1,1}
        };
        Graph<Integer> graph = new Graph<>();

        // add nodes.
        for(int i = 0; i< WIDTH; i++)
            for(int j = 0; j < LENGTH; j++)
                if(map[i][j] == 1)
                    graph.addNode(new Node(new Point(i,j).hashCode()));
        // Add edge.
        for(int i = 0; i< WIDTH; i++){
            for(int j = 0; j < LENGTH; j++){
                Point thisPoint  = new Point(i,j);
                for(Point p: thisPoint.getAllAdjoiningPoints()){
                    if(map[p.x][p.y] == 1 && map[thisPoint.x][thisPoint.y] == 1)
                        graph.addDirectedEdge(thisPoint.hashCode(), p.hashCode());
                }
            }
        }
        // Do BFS
        Node<Integer> startNode = graph.getNextUnVisitedNode();
        int numberOfIslands=0;
        while (startNode != null) {
            graph.doBFS(startNode);
            numberOfIslands++;
            startNode = graph.getNextUnVisitedNode();
        }
        //numberOfIslands
        System.out.println("Total number of islands in this map = "+numberOfIslands);
    }

    class Point{
        int x, y;
        public Point(int x, int y){this.x = x; this.y = y;}
        boolean isInMap()
        {
            return this.x >= 0 && this.y >= 0 && this.x < WIDTH && this.y < LENGTH;
        }

        @Override
        public int hashCode(){return (this.x*WIDTH)+this.y;}

        List<Point> getAllAdjoiningPoints(){
            List<Point> retList = new ArrayList<>();
            // up
            Point u = new Point(this.x, this.y-1);
            if(u.isInMap())
                retList.add(u);
            // left
            Point l = new Point(this.x-1, this.y);
            if(l.isInMap())
                retList.add(l);
            // right
            Point r = new Point(this.x+1, this.y);
            if(r.isInMap())
                retList.add(r);
            // down
            Point d = new Point(this.x, this.y+1);
            if(d.isInMap())
                retList.add(d);
            return retList;
        }
    }
}
