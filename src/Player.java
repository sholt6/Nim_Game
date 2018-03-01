//Class which stores variables and methods for players of Nim game

public class Player {

	private String Name, mvList;
	
	/*
	//CONSTRUCTORS
	*/
	
	public Player(String input) {
		Name = new String(input);
	}
	
	//Appends moves to a move list
	public void addMvList(String move){
		//If statement prevents the eventual string beginning with null 
		if(mvList == null) {
			mvList = move;
		}
		else {
			mvList = mvList + move;
		}
	}
	
	/*
	//METHODS
	*/
	
	//Returns player name
	public String getName(){
		return Name;
	}
	
	//Outputs move list for this player
	public String getMvList(){
		return mvList;
	}
}
