//Class which contains methods and data regarding piles for Nim game



public class Pile {

	//The size and name of the Pile
	private int Quantity;
	private char Name;
	
	/*
	//CONSTRUCTOR
	*/
	
	//Constructs new Pile with name and size passed from main	
	public Pile(char name, int quantity) {
		Name = name; 
		Quantity = quantity;
	}
	
	/*
	//METHODS
	*/
	
	//Method for pile quantity access
	public int getQuantity() {
		return Quantity;
	}

	//Method for pile name access
	public char getName() {
		return Name;
	}
	
	//Method to alter pile size
	public void chngSize(int quan) {
		Quantity = this.getQuantity() - quan;
		if(Quantity < 0) {						//If a player attempts to take more from a pile than
			Quantity = 0;						//it contains, this is treated as emptying the pile
		}
	}
	
	//Displays name and size
	public String display() {
		String display = (this.Name + ":" + this.getQuantity());
		return display;
	}
	
	//Generates array for star output
	//len, passed from outside, ensures all three stars arrays are the same size
	public String[][] stars(int len) {
		int lines = this.getQuantity() / 3;
		int rem = this.getQuantity() % 3;
		String[][] stars = new String[len+1][3]; 			
		

		for(int i = 0; i < len+1; i++) {
			
			for(int j = 0; j < 3; j++) {
				stars[i][j] = "";
			}
		}

		for(int i = 0; i < lines; i++) {
			
			for(int j = 0; j < 3; j++) {
				stars[i][j] = "*";
			}
		}
		
		for(int j = 0; j < rem; j++) {
			stars[lines][j] = "*";
		}
		
		return stars;
	}

}