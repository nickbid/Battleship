package battleship;
//imports

import java.util.Scanner;


public class Main{
public static void main(String[] args){
//making variables
Scanner k = new Scanner(System.in);//initialize keyboard input
Grid grid=new Grid();//make grid object
//placing ships
System.out.println("Place gunboat (2 long)");
makeship(grid,k,2);
System.out.println("Place submarine (3 long)");
makeship(grid,k,3);
System.out.println("Place battleship (4 long)");
makeship(grid,k,4);
System.out.println("Place aircraft carrier (5 long)");
makeship(grid,k,5);
grid.printgrid();//show grid with all ships on it
k.close();
}
public static int checkinput(Scanner k){//takes input, checks if it is an integer, and returns the input.	
	while(!k.hasNextInt()){//checks if input is int
	System.out.println("Invalid Input. Try again.");
	System.out.print(">");
	k.next();//prints more than once when there is a space in the input FIX THIS
}
return k.nextInt();
}
public static void makeship(Grid grid, Scanner k,int length){//takes input from user and places ship on grid (very convenient method)
	int x;
	int y;
	int dir;
	do{
	grid.printgrid();//show user new grid
	System.out.println("input x (1-8)");//taking user inputs
	System.out.print(">");
	x = checkinput(k);
	System.out.println("input y (1-8)");
	System.out.print(">");
	y = checkinput(k);
	System.out.println("input direction (up=1, right=2, down=3, left=4)");
	System.out.print(">");
	dir = checkinput(k);
	}while(!grid.placeship(x, y, dir, length));//keep attempting to place ship until it is put in a valid position
}
}
//begin grid class
class Grid{
//variables
private int[][] grid = new int[8][8];
public Grid(){
}
public int getpoint(int x, int y){//returns the value at (x,y)
 return grid[x][y];
}
public boolean checkpoint(int x,int y){//returns true if the point is in bounds and is 0
 try{//catching ArrayIndexOutOfBoundsException 
   getpoint(x,y);
 }catch(ArrayIndexOutOfBoundsException e){
     System.out.println("Ship goes out of bounds!");
   return false;
   }
 if(getpoint(x,y)==1){//checking that the spot is empty
  System.out.println("Another ship is in this location!");
   return false;
 }
 return true;
}
public boolean placeship(int x, int y, int dir, int length){//places a ship (1s) of variable length at a certain direction, starting at point (x,y)
x=x-1;
y=y-1;
if(dir>4||dir<1){//checks if direction is valid
	System.out.println("Invalid direction!");
	return false;
}
boolean state = true;
 for(int i=0;i<length;i++){//checks that all the points that the ship will be in are empty
   switch(dir){
     case 1:
   	state = checkpoint(x,y-i);
     break;
     case 2:
    state = checkpoint(x+i,y);
     break;
     case 3:
    state = checkpoint(x,y+i);
     break;
     case 4:
    state = checkpoint(x-i,y);
     break;
   }
   if(!state){//if it is not clear exit loop
	   i=100;
	   return state;//let the program know the ship was not placed
   }
 }
//if all spots are clear place the ship
 for(int i=0;i<length;i++){
   switch(dir){
     case 1:
   	 grid[x][y-i]=1;
     break;
     case 2:
     grid[x+i][y]=1;
     break;
     case 3:
     grid[x][y+i]=1;
     break;
     case 4:
     grid[x-i][y]=1;
     break;
   }
  }
 return state;//let the program know the ship was placed
 }
public void printgrid(){//outputs the grid to the console
 for(int y=0;y<=7;y++){
   for(int x=0;x<=7;x++){
     System.out.print(grid[x][y]);
   }
   System.out.println("");
 }
}
}