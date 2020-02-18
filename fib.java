import java.util.ArrayList;
import java.util.Scanner;

public class fib {

    public static void main(String[] args) {
        Scanner mainScanner = new Scanner(System.in);
        ArrayList<Integer> heapArray = new ArrayList<>();
		
        //Creation of all the necessary sentences and integers 
	//created in final variables and normal variables
        final String CHOOSE_HEAP ="Choose a heap (1-";
        final String NUMBER_OF_TOKENS ="The  number of tokens you may take is between 1 and ";
        final String TOKENS_TO_BE_TAKEN = "How many tokens do you want to take? ";
	final String ERROR_CATCHING_IN_THE_TERMINAL = "Incorrect value entered in the terminal = ";
	final String ERROR_CHECKING_USER_ENTERS_RIGHT_HEAP = "Enter a valid heap number between 1 and ";
        final String EMPTY_HEAP = "There are no tokens left inside ";
	final String ERROR_INCORRECT_HEAP_CHOOSEN = "You must enter in a integer between 1 and ";
	final int DEFUALT_HEAP_SIZE = 9;
        final int DEFUALT_NUMBER_OF_HEAPS = 3;
        int roundCounter = 0;
        int maximumTokenToSelect =2;


        //This will check if the user has enter in new heap sizes in the terminal
        if (args.length>0) {

            for (int i = 0; i < args.length; i++) {

                //This will catch the users error(s) from the terminal and tell them that 
		//they have entered incorrect values and discard it
                try {
                    heapArray.add(Integer.parseInt(args[i]));
                }catch (Exception ex) {
                    System.out.println(ERROR_CATCHING_IN_THE_TERMINAL + args[i]);
                }
            }
        }
		
	// Initialize's default  values in case error
	if ((args.length == 0) || (heapArray.size() == 0)){
         	for (int k = 0; k < DEFUALT_NUMBER_OF_HEAPS; k++) {
                	heapArray.add(DEFUALT_HEAP_SIZE);
        	       }
        }

        //This is the start of the game
	while(!endGame(heapArray)) {
	    //output array
            for (int j = 0; j < heapArray.size(); j++) {
                System.out.println("Heap " + (j+1) + " " + heapArray.get(j));
            }
			
	     //check whos playing
            if(roundCounter %2 ==0) {
                System.out.println("Player 1's turn.");
            } else {
                System.out.println("Player 2's turn.");
            }
				
            System.out.print(CHOOSE_HEAP + heapArray.size() + "): ");


            //User Input validation
            while(!mainScanner.hasNextInt()){
                System.out.print(ERROR_CHECKING_USER_ENTERS_RIGHT_HEAP + heapArray.size() + ": ");
                mainScanner.next();
            }
            int choosenheap = mainScanner.nextInt()-1;

			
            if (choosenheap >= 0 && choosenheap <= (heapArray.size()-1)) {
				
                if (heapArray.get(choosenheap) == 0 ) {
                    System.out.println(EMPTY_HEAP);
					
		// handles possible range of values user can choose and subtracts from chosen heap
                } else { 
	
			int tokens;
			int numberOfTokemsInHeap;
			// checks within range
			if (maximumTokenToSelect > heapArray.get(choosenheap)) {
				System.out.println(NUMBER_OF_TOKENS + heapArray.get(choosenheap));
				System.out.print(TOKENS_TO_BE_TAKEN);
				tokens = tokenErrorHandler(mainScanner ,heapArray.get(choosenheap));
				numberOfTokemsInHeap = heapArray.get(choosenheap);
				
			} else {
				System.out.println(NUMBER_OF_TOKENS + maximumTokenToSelect);
				System.out.print(TOKENS_TO_BE_TAKEN);
				tokens = tokenErrorHandler(mainScanner ,maximumTokenToSelect);
				numberOfTokemsInHeap = heapArray.get(choosenheap);	
						
				// increments maximum number for the next user
				if ( maximumTokenToSelect == tokens ) {
					maximumTokenToSelect= maximumTokenToSelect + tokens;
				}
			}
		    //subtractions from heap 
                    numberOfTokemsInHeap = numberOfTokemsInHeap - tokens;
                    heapArray.set(choosenheap, numberOfTokemsInHeap);
					
                    roundCounter++;
                }
				
            } else {
                System.out.println(ERROR_INCORRECT_HEAP_CHOOSEN + heapArray.size());
            }
        }
		// displays who wins roundcounter changed 
        if(roundCounter %2 ==0) {
            System.out.println("Player 2 wins"); 
        } else {
            System.out.println("Player 1 wins");
        }
    }
	//method for User Input validation
    private static int tokenErrorHandler(Scanner mainScanner, int size){
		
		final String VALID_INTEGER = "Enter a valid token number: ";
		final String ERROR_CHECKING_WITHIN_RANGE = "Enter the number of tokens between 1 and ";

        boolean maincheck = true;
        int checktokens =0;

        //This verifies that the user enter's in the correct 
		//token value within the correct range
        while(maincheck){

            while(!mainScanner.hasNextInt()){
                System.out.print(VALID_INTEGER);
                mainScanner.next();

            }
            checktokens = (int)mainScanner.nextInt();

            if(checktokens>=1 && checktokens<=size){
                maincheck = false;
                return checktokens;
            }else{
                System.out.print(ERROR_CHECKING_WITHIN_RANGE+size + ": ");
            }
        }
		// in case error
		return -1;
    }
    // method to check if thew game should end
    private static boolean endGame(ArrayList<Integer> heapArray) {
		
	int firsttokensberInArray= 0;
        
	for (int j = 0; j < heapArray.size(); j++) {
            if (heapArray.get(j) != firsttokensberInArray){
                return false;
            }
        }
        return true;
    }
}
