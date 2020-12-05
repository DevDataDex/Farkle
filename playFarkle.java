import java.util.*;
public class playFarkle{

	enum TYPE{FARKLE, STRAIT, THREE, FOUR, FIVE, SIX, TWOTRIPS, THREEPAIRS;}
	static myScanner scn = new myScanner();
	
	public static void main(String[] args) throws InterruptedException{
		int a = 6;
		int pl = 1;
		int[] turn = {6,0};
		int turnScore = 0;
		int ans = 0;
		System.out.print("Welcome to Farkle! How many people are playing? ");
		int size = scn.nextInt();
		Player[] players = new Player[size];
		for(int i = 0; i<size; i++){
			System.out.println("What is your name Player " + pl +"?");
			players[i] = new Player(scn.next()); Thread.sleep(1000); pl++;
		}
		System.out.println("Check out your info before playing."); Thread.sleep(2000);
		for(Player p : players){p.displayPlayer(); Thread.sleep(2000);}
		System.out.println("Let's Go!"); Thread.sleep(2000);

		do{
			for(Player p : players){
				System.out.println("It's your turn " + p.getName() + "!"); Thread.sleep(1000);
				if(turn[0]<6){
					System.out.println("Would you like to build off the previous player's roll? [1] Yes [0] No");
					ans = scn.nextInt();
					if(ans == 1){a = turn[0]; turnScore = turn[1];}
					else{a=6; turnScore=0;}
				}
				turn = turn(p, a, turnScore);
			}
			System.out.println("\nCurrent Standings:"); Thread.sleep(0500);
			for(Player p : players){p.displayPlayer(); if(p.win()){pl=0;} Thread.sleep(2000);}
		
			//System.out.println("\nDevin my Creator, would you like to continue? (Enter 0 to exit loop)"); pl = scn.nextInt();
		}while(pl != 0);
		System.out.println("\nFianl Standings:"); Thread.sleep(0500);
		for(Player p : players){p.displayPlayer(); Thread.sleep(2000);}

	}

	public static int[] turn(Player p1, int a, int turnScore)throws InterruptedException{
			int[] roll;
			int b;
			int c;
			int[] turn = new int[2];
			do{
				roll = roll(a);
				displayRoll(roll);
				if(isFarkle(roll)){System.out.println("FARKLE!!"); turnScore = 0; Thread.sleep(3000); break;}
				System.out.print("How many dice would you like to hold? ");
				b = scn.nextInt();if(a>6){a=6;} int[] keep = new int[b];
				System.out.print("Which dice would you like to hold?\n(Please enter the position of the die rather than the number shown on the die) ");
				for(int i=0; i<b; i++){keep[i]= (scn.nextInt());}
				turnScore += score(keep, roll);
				System.out.println("Current Turn Score: " + turnScore);
				a = a - b;
				if(a==0){a=6;}
				System.out.println("Roll Again[1] or Pass[0] You have " + a + " Dice to Roll");
				c = scn.nextInt(); if(c > 1){System.out.println("Invalid Input. You pass by default."); c=0;}
			}while(c != 0);
			p1.setScore(turnScore);
			turn[0]=a; turn[1]=turnScore;
			return turn;
			//return a;
	}

	public static int score(int[] keep, int[] roll){
		int count;
		int countoftrips = 0;
		int countofpairs = 0;
		int countstraight = 0;
		int countoffour = 0;
		int score = 0;
		for(int i = 1; i<7; i++){
			count = 0;
			for(int j = 0; j<keep.length; j++){
				if(roll[keep[j]-1] == i){
					count++;
				}
			}
			if(count == 1){
				countstraight++;
				if(countstraight==6){score+=1350;}
				if(i == 1){score += 100;}
				if(i == 5){score += 50;}
			}
			if(count == 2){
				if(countoffour == 1){score+=1500; break;}
				if(i==1){score =+ 200;} if(i==5){score =+ 100;}
				if(countofpairs==0 && keep.length==6){countofpairs++; continue;}
				if(countofpairs==1 && keep.length==6){countofpairs++; continue;}
				if(countofpairs==2){score = 1500; break;}
			}
			if(count == 3){
				score+= (i*100);
				if(countoftrips==0 && keep.length == 6 ){countoftrips++; continue;}
				if(countoftrips==1){score = 2500; break;}
			}
			if(count == 4){
				countoffour++;
				if(countofpairs==1){score+=1500; break;}
				else{score =+ 1000;}
			}
			if(count == 5){score += 2000;}
			if(count == 6){score += 3000;}
		}
		return score;
		
	}

	public static boolean isFarkle(int[] roll){
		int pairs=0;
		int straight=0;
		int count;
		for(int i = 1; i<=6; i++){
			count = 0;
			for(int num : roll){
				if(num == 1){return false;}
				if(num == 5){return false;}
				if(num == i){count++;}
			}
			if(count == 1){straight++;}
			if(count == 2){pairs++;}
			if(count>2){return false;}
		}
		if(straight==6){return false;}
		if(pairs == 3){return false;}
		return true;
	}

	/*public static int score(TYPE type, int a){
		return 0;
	}*/

	public static int[] roll(int a){
		int[] roll = new int[a];
		for(int i = 0; i<a; i++){
			roll[i] = (int)(Math.random()*6)+1;
		}
		return roll;
	}

	public static void displayRoll(int[] roll){
		for(int die : roll){
			System.out.print(die + " ");
		}
		System.out.println();
	}
}

class Player{

	private String name;
	private int score;

	public Player(String name){
		this.name = name;
		this.score = 0;
	}

	public void setScore(int turnscore){score += turnscore;}
	public int getScore(){return score;}
	public String getName(){return name;}
	public void displayPlayer(){
		System.out.println(name + ": " + score);
	}
	public boolean win(){if(score >= 10000){return true;}else{return false;}}

}

//class Computer extends Player{}