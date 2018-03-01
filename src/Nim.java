//Main class of Nim. Plays the game Nim.

import java.util.Scanner;

public class Nim {

	public static void main(String[] args) {
	
	//
	//MAIN METHOD
	//
		
		//Instantiates and initialises Players and Piles
		Player One = null;
		Player Two = null;
		Pile A = null;
		Pile B = null;
		Pile C = null;
		char a = 'A';
		char b = 'B';
		char c = 'C';
		int first = 1;
		int second = 2;
		
		//Instantiates main class, plays intro splash 
		Nim nim = new Nim();
		introSplash();
		
		//Calls createPlayer() and createPile() methods to allow users to set up the game
		One = nim.createPlayer(first, One);
		Two = nim.createPlayer(second, Two);
		
		A = nim.createPile(A, a);
		B = nim.createPile(B, b);
		C = nim.createPile(C, c);
		
		int pchoice = 1;		//pchoice keeps track of whose turn it is
		
		startSplash();
		/*
		Cycles through successive rounds until all piles are empty, at which point the player
		referred to by the value of pchoice is the winner
		*/
		int pSum = (A.getQuantity() + B.getQuantity() + C.getQuantity()); //Game is over when this is 0
		String move;	//This will store the move made in each round to be added to the player's movelist
		
		while (pSum > 0){
			
			//Runs the Round() method and records the move made this round to be added to the current player
			move = Round(A, B, C, One, Two, pchoice);
			
			if(pchoice == 1) {
				One.addMvList(move);
			}
			else if (pchoice == 2) {
				Two.addMvList(move);
			}
			
			pchoice = pChoice(pchoice);
			pSum = (A.getQuantity() + B.getQuantity() + C.getQuantity());	//Recalculates sum of piles
		}
		
		//Runs method to declare winner and print move histories
		Winner(pchoice, One, Two);
	}
	
	
	
	
	
	//
	// METHODS
	//
	
	
	//Creates an instance of Player		
	public Player createPlayer(int num, Player Z) {
		
		String nmInput = new String();
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("\nPlayer " + num + ", enter your name: ");
		nmInput = scanner.nextLine();
		
		while(nmInput.isEmpty()) {
			System.out.print("\nPlease pick a name!\n");
			System.out.print("\nPlayer " + num + ", enter your name: ");
			nmInput = scanner.nextLine();
		}
		Z = new Player(nmInput);
		
		return Z;
	}

	//Creates an instance of Pile 
	public Pile createPile(Pile X, char Y) {
		
		String piInput = null;
		int size = 1, pass = 0;
		Scanner scanner = new Scanner(System.in);
		
		//This block takes the input and returns warnings if it is not a positive integer
		while(validInt(piInput) == false || size <= 0) {
			
			//Setting size back to 1 at the start of the loop simply prevents a minor style error
			size = 1;
			pass = 0;
			
			//This block loops until something is entered; pass only becomes 1 once something is entered
			while(pass == 0){
				System.out.print("\nEnter size of pile " + Y + " or 'q' to quit: ");
				piInput = scanner.nextLine();
				try{ 
					piInput.charAt(0);
					pass = 1;
				} catch(Exception e){
					System.out.print("\nPlease enter a number or the game won't work!\n\n");
				}
			}
			
			//If block to allow quitting
			if(piInput.charAt(0) == 'q') {
				System.exit(0);
			}
			
			//Offers error statements if the user enters a non-integer or a negative integer
			try {
				size = Integer.parseInt(piInput);
			} catch(NumberFormatException e) {
				System.out.print("\nPlease enter an integer!\n\n");
			}			
			if(size <= 0) {
				System.out.print("\nPlease enter a positive integer!\n\n");
			}
		}
		
		X = new Pile(Y, size);
		
		return X;
	}
	
	//This method switches which player will be used when the Round() method is called
	public static int pChoice(int pchoice) {
		if (pchoice == 1){
			pchoice++;
		}
		else if (pchoice == 2){
			pchoice--;
		}
		return pchoice;
	}

	//Method to run a round
	public static String Round(Pile A, Pile B, Pile C, Player One, Player Two, int pchoice) {
		
		Player player = null;
		String input = null, qchoice = null;;
		char rchoice = 'z';
		int quan = 0, appropriate = 1, pass = 0;
		Scanner scanner = new Scanner(System.in);
		
		//Prints the current status of the piles
		showStars(A, B, C);
		String status = (A.display() + "\t" + B.display() + "\t" + C.display());
		System.out.print("\n\t" + status + "\n\n");
		
		//Identifies player for this round
		if(pchoice == 1) {
			player = One;
		}
		else if (pchoice == 2) {
			player = Two;
		}
		
		//Asks for pile choice until a valid choice is made
		while(rchoice != 'A' && rchoice != 'B' && rchoice != 'C' || appropriate == 0) {
			appropriate = 1;
			pass = 0;
			System.out.print(player.getName() + ", choose a pile or enter 'q' to quit: ");
			input = scanner.nextLine();
			
			//Checks input is given
			while(pass == 0) {
				try {
					rchoice = input.charAt(0);
					pass = 1;
				} catch(Exception e) {
					System.out.print("\nPick a pile! Or are you scared?\n\n");
				}
			}
			
			//If block to allow quitting
			if(rchoice == 'q') {						
				System.exit(0);
			}
			
			//This if block allows pile choice to be lower case
			if(rchoice >= 'a' && rchoice <= 'z') {		
				rchoice -= 32;
			}
			
			//Checks chosen pile isn't empty
			if(rchoice == A.getName() && A.getQuantity() < 1){
				appropriate = 0;
				System.out.print("\nThat pile is empty!\n\n");
			}
			else if(rchoice == B.getName() && B.getQuantity() < 1){
				appropriate = 0;
				System.out.print("\nThat pile is empty!\n\n");
			}
			else if(rchoice == C.getName() && C.getQuantity() < 1){
				appropriate = 0;
				System.out.print("\nThat pile is empty!\n\n");
			}
		}
		
		//Takes quantity selection, ensures its validity
		while(validInt(qchoice) == false || quan == 0 || appropriate == 0) {
			quan = 0;
			System.out.print(player.getName() + ", choose how many to take, or enter a negative to quit: ");
			qchoice = scanner.nextLine();
			try {
				quan = Integer.parseInt(qchoice);
			} catch(NumberFormatException e) {
				System.out.print("Please enter a valid, positive integer!\n");
			}
			
			//If block to allow quitting
			if(quan < 0) {						
				System.exit(0);
			}
			
			//This block checks if the quantity chosen is greater than the size of the pile
			appropriate = 1;
			if(rchoice == A.getName() && quan > A.getQuantity()){
					appropriate = 0;
					System.out.print("Your number is too big!\n\n");
			}
			else if(rchoice == B.getName() && quan > B.getQuantity()){
					appropriate = 0;
					System.out.print("Your number is too big!\n\n");
			}
			else if(rchoice == C.getName() && quan > C.getQuantity()){
					appropriate = 0;
					System.out.print("Your number is too big!\n\n");
			}
		}
		
		//Modifies selected pile
		if(rchoice == A.getName()){
			A.chngSize(quan);
		}
		else if(rchoice == B.getName()){
			B.chngSize(quan);
		}
		else if(rchoice == C.getName()){
			C.chngSize(quan);
		}
		
		//These final two lines produce a string of the move made and append it to the current player's movelist
		String move = "\nChose pile [" + rchoice + "] and removed value [" + qchoice + "]"; 
		
		return move;
	}
	
	/*
	Method to detect and declare the winner. Prints list of each player's moves.
	Paradoxically, 2 refers to player 1, while 1 refers to player 2. This cannot 
	be helped and must occur somewhere in the program.
	*/
	public static void Winner(int pchoice, Player One, Player Two) {
		String winner;
		if(pchoice == 2) {
			winner = One.getName();
		}
		else {
			winner = Two.getName();
		}
		
		System.out.print("\n\n\t##########################################\n\n");
		System.out.print("\t Congratulations " + winner + " you are the winner!");
		System.out.print("\n\n\t##########################################\n");
	
		System.out.print("\n\n" + One.getName() + "'s moves were: \n" + One.getMvList());
		System.out.print("\n\n" + Two.getName() + "'s moves were: \n" + Two.getMvList() + "\n");
	}
	
	//A method for checking an input is a valid integer
	public static boolean validInt(String qchoice) {
		try {
			Integer.parseInt(qchoice);
			return true;
		} catch(NumberFormatException e) {
			return false;
		}
	}
	
	//A method to control star printing
	public static void showStars(Pile A, Pile B, Pile C) {
		//This first section determines the largest pile and passes its size to the Pile.stars() method 
		int quants [] = new int[3];
		quants[0] = A.getQuantity();
		quants[1] = B.getQuantity();
		quants[2] = C.getQuantity();
		int max = 0;

		for(int i = 0; i<3; i++){
			if(max < quants[i]){
				max = quants[i];
			}
		}
		
		max = max /3;		//An array position is needed for each row of stars: 3 stars per row so array size is 1/3 of max
		
		String Aa[][] = A.stars(max);
		String Ba[][] = B.stars(max);
		String Ca[][] = C.stars(max);
		
		//This second section prints the stars
		System.out.print("\n");
		for(int i = max; i> -1; i--){
			System.out.print("\t" + Aa[i][0] + Aa[i][1] + Aa[i][2] + "\t" + 
									Ba[i][0] + Ba[i][1] + Ba[i][2] + "\t" + 
									Ca[i][0] + Ca[i][1] + Ca[i][2] + "\n");
		}		
	}
	

	//Intro splash
	public static void introSplash() {
		System.out.print("\n\n");
		System.out.print("\t==============================\n");
		System.out.print("\t|                            |\n");
		System.out.print("\t|     Welcome to Nim!!!      |\n");
		System.out.print("\t|                            |\n");
		System.out.print("\t==============================\n\n\n\n");
	}
	
	//Start splash
	public static void startSplash() {
		System.out.print("\n\n");
		System.out.print("\t===================\n");
		System.out.print("\t| Enjoy the game! |\n");
		System.out.print("\t===================\n\n\n\n");
	}

}
