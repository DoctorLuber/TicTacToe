import java.util.Scanner;

class TicTacToe{
    public static void main(String[] args) {
        printCoolLogo();
        menu();
        
    }


    // Allows 2 players to play TicTacToe against each other
    //
    public static void localMultiplayer () {
        boolean isGameFinished = false;
        while(!isGameFinished){
            String[][] gameBoard = generate();
            draw(gameBoard);

            
            //check if someone won

            // X plays 1st move
            gameBoard = userPlays(gameBoard, "X");

            for (byte move = 1; move <= 4; move++){        
                // O plays
                gameBoard = userPlays(gameBoard, "O");
                if (hasAnyoneWon(gameBoard, "O")){
                    System.out.println("End of the game.");
                    isGameFinished = true;
                    break;
                }

                // X plays
                gameBoard = userPlays(gameBoard, "X");
                if (hasAnyoneWon(gameBoard, "X")){
                    System.out.println("End of the game.");
                    isGameFinished = true;
                    break;
                }

            }
        
        menu();
        }
    }// END localMultiplayer


    // Validate the user input and return updated gameBoard
    //
    public static String[][] userPlays(String[][] gameBoard, String characterOorX) {
        String userMove = inputString(characterOorX+" plays: ").toUpperCase();

        if (!isCorectFormat(userMove)){
            System.out.println("Enter a valid coordinate. (Eg A1 or C3)");
            return userPlays(gameBoard, characterOorX);
        }else if (!isEmpty(gameBoard, userMove)){
            System.out.println("This move is illegal.");
            return userPlays(gameBoard, characterOorX);
        }else{
            gameBoard = makeMove(gameBoard, userMove, characterOorX);
            draw(gameBoard);
            return gameBoard;
        }        
    }


    // Check if someone won
    //
    public static boolean hasAnyoneWon(String[][] gameBoard, String character) {
        boolean bHasAnyoneWon = false;
        String[][] combinations = {
            {"A1","A2","A3"},
            {"B1","B2","B3"},
            {"C1","C2","C3"},
            {"A1","B1","C1"},
            {"A2","B2","C2"},
            {"A3","B3","C3"},
            {"A1","B2","C3"},
            {"C1","B2","A3"}};
        
        for (String[] combination : combinations){
            String fistOorX = gameBoard[parseCoordinates(combination[0])[0]][parseCoordinates(combination[0])[1]];
            String secondOorX = gameBoard[parseCoordinates(combination[1])[0]][parseCoordinates(combination[1])[1]];
            String thirdOorX = gameBoard[parseCoordinates(combination[2])[0]][parseCoordinates(combination[2])[1]];
            if (fistOorX.equals(character) & secondOorX.equals(character) & thirdOorX.equals(character)){
                bHasAnyoneWon = true;
                break;
            }
        }
        return bHasAnyoneWon;
    }


    // Return user input as a string after print a message
    //
    public static String inputString (String message){
        Scanner scanner = new Scanner(System.in);

        System.out.println(message);

        String userInput = scanner.nextLine();
        return userInput;

    }// END inputString


    // Parse the user input into coordinates after a message such that 
    // byte[0] = coloumn coordinate
    // byte[1] = row coordinate
    //
    public static byte[] parseCoordinates (String coordinates){
        byte[] parsedCoordinates = new byte[2];

        // Coloumns
        switch(coordinates.charAt(1)){
            case '1':
                parsedCoordinates[0] = 0;
                break;
            case '2':
                parsedCoordinates[0] = 1;
                break;
            case '3':
                parsedCoordinates[0] = 2;
                break;
            default:
                parsedCoordinates[0] = 10; //Error case
        }


        // Rows
        switch(coordinates.charAt(0)){
            case 'A':
                parsedCoordinates[1] = 0;
                break;
            case 'B':
                parsedCoordinates[1] = 1;
                break;
            case 'C':
                parsedCoordinates[1] = 2;
                break;
            default:
                parsedCoordinates[1] = 10; //Error case
        }
        return parsedCoordinates;
    }// END parseCoordinates


    // Reassignes the string value of a particular string in a string array to a given one
    //
    public static String[][] makeMove (String[][] boardGame, String coordinates, String character){
        byte[] parsedCoordinates = parseCoordinates(coordinates);
        boardGame[parsedCoordinates[0]][parsedCoordinates[1]] = character;
        return boardGame;
    }// END makeMove


    public static void menu (){
        boolean userMadeChoice = false;
        
        while (!userMadeChoice){
            System.out.println("Choose what mode you want to play.\n");
            System.out.println("'1' for Local Multiplayer");
            System.out.println("'2' for Other Options");
            System.out.println("'0' for Exit");
            String userChoice = inputString("Enter your choice below: ");
            switch (userChoice){
                case "1":
                    System.out.println("Starting Local Multiplayer...\n");
                    localMultiplayer();
                    userMadeChoice = true;
                    break;
                case "2":
                    System.out.println("Sorry, that is still in development...\n");
                    break;
                case "0":
                    System.out.println("Good bye! Till next time!\n");
                    userMadeChoice = true;
                    System.exit(0);
                    break;
                default:
                    System.out.println("Ops... I didn't get that. Let's try again.\n");
            }
        }    
    }// END menu


    // Prints a ASCII art saying Tic Tac Toe
    //
    public static void printCoolLogo (){
        System.out.println(
    "████████╗██╗░█████╗░  ████████╗░█████╗░░█████╗░  ████████╗░█████╗░███████╗\n" +
    "╚══██╔══╝██║██╔══██╗  ╚══██╔══╝██╔══██╗██╔══██╗  ╚══██╔══╝██╔══██╗██╔════╝\n" +
    "░░░██║░░░██║██║░░╚═╝  ░░░██║░░░███████║██║░░╚═╝  ░░░██║░░░██║░░██║█████╗░░\n" +
    "░░░██║░░░██║██║░░██╗  ░░░██║░░░██╔══██║██║░░██╗  ░░░██║░░░██║░░██║██╔══╝░░\n" +
    "░░░██║░░░██║╚█████╔╝  ░░░██║░░░██║░░██║╚█████╔╝  ░░░██║░░░╚█████╔╝███████╗\n" +
    "░░░╚═╝░░░╚═╝░╚════╝░  ░░░╚═╝░░░╚═╝░░╚═╝░╚════╝░  ░░░╚═╝░░░░╚════╝░╚══════╝\n");
    }// END printCoolLogo


    // Returns a 2D string array that is 3x3 with each item being a " "
    //
    public static String[][] generate(){
        String[][] gameBoard = {
            {" "," "," "},
            {" "," "," "},
            {" "," "," "}};
    
        return gameBoard;
    }// END generate


    // Prints a tictactoe board with values of the 2D string array between
    //
    public static void draw(String[][] boardGame){
        System.out.print(
        "    A   B   C\n" +
        "  ┏ ━ ┳ ━ ┳ ━ ┓\n" +
        "1 ┃ "+boardGame[0][0]+" ┃ "+boardGame[0][1]+" ┃ "+boardGame[0][2]+" ┃\n" +
        "  ┣ ━ ╋ ━ ╋ ━ ┫\n" +
        "2 ┃ "+boardGame[1][0]+" ┃ "+boardGame[1][1]+" ┃ "+boardGame[1][2]+" ┃\n" +
        "  ┣ ━ ╋ ━ ╋ ━ ┫\n" +
        "3 ┃ "+boardGame[2][0]+" ┃ "+boardGame[2][1]+" ┃ "+boardGame[2][2]+" ┃\n" +
        "  ┗ ━ ┻ ━ ┻ ━ ┛\n"
        );
    }//END draw


    // Return true/false if a specific coordinate is empty
    //
    public static boolean isEmpty (String[][] boardGame, String coordinates){
        byte[] parsedCoordinates = parseCoordinates(coordinates);
    
        boolean isEmpty = (boardGame[parsedCoordinates[0]][parsedCoordinates[1]].equals(" ")) ;
    
        return isEmpty;
    }// END isEmpty


    // Check if the user entered correct format of coordinates
    //
    public static boolean isCorectFormat(String userCoordinates) {
        String [] validCoordinates = {"A1", "B1", "C1",
                                      "A2", "B2", "C2",
                                      "A3", "B3", "C3"};
        boolean isValidFormat = false;
        for (String coordinate : validCoordinates){
            if (coordinate.equals(userCoordinates))
                isValidFormat = true;
        }
        return isValidFormat;
    }

}