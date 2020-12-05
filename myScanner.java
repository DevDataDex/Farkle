import java.util.*;
public class myScanner{

	Scanner scn;
	boolean cont;
	
	public myScanner(){
		scn = new Scanner(System.in);
	}

	public int nextInt(){
		int a = 0;
		do{
			cont = true;
			try{
				a = scn.nextInt();
				cont = false;
			}
			catch(InputMismatchException E){
				System.out.println("Not a valid input, please try again.");
				scn.next();
			}
		}while(cont);

		return a;
	}

	public double nextDouble(){
		double a = 0;
		do{
			cont = true;
			try{
				a = scn.nextDouble();
				cont = false;
			}
			catch(InputMismatchException E){
				System.out.println("Not a valid input, please try again.");
				scn.next();
			}
		}while(cont);

		return a;
	}

	public char nextChar(){
		char a = '0';
		do{
			cont = true;
			try{
				a = scn.next().charAt(0);
				cont = false;
			}
			catch(InputMismatchException E){
				System.out.println("Not a valid input, please try again.");
				scn.next();
			}
		}while(cont);

		return a;
	}

	public String next(){
		String a = "0";
		do{
			cont = true;
			try{
				a = scn.next();
				cont = false;
			}
			catch(InputMismatchException E){
				System.out.println("Not a valid input, please try again.");
				scn.next();
			}
		}while(cont);

		return a;
	}

	public String nextLine(){
		String a = "0";
		do{
			cont = true;
			try{
				a = scn.nextLine();
				cont = false;
			}
			catch(InputMismatchException E){
				System.out.println("Not a valid input, please try again.");
				scn.next();
			}
		}while(cont);

		return a;
	}
}