import java.util.Scanner;

public class tictactoe {
    Space[] board;

    public tictactoe(){
        board = new Space[9];

        for(short i = 0; i < board.length; i++){
            board[i] = new Space();
            board[i].number = i ;
            board[i].number++;

            /* if(i % 2 ==0)
            board[i].occupant = 'X';
            if(i % 2 == 1)
            board[i].occupant = 'O'; */
        }
    }
    public void draw_board(){
        short cursor = 1;

        for(Space s : board){
            if(s.occupant == '\u0000'){
                if(cursor < 3){
                    System.out.print(s.number);
                    System.out.print("|");
                    cursor++;
                }else{
                    System.out.print(s.number);
                    System.out.print("\n");
                    if(s.number == 9){
                        break;
                    }
                    cursor = 1;
                    draw_tween();
                }
            }else {
                if(cursor < 3){
                    System.out.print(s.occupant);
                    System.out.print("|");
                    cursor++;
                }else{
                    System.out.print(s.occupant);
                    System.out.print("\n");
                    if(s.number == 9){
                        break;
                    }
                    cursor = 1;
                    draw_tween();
                }
            }
        }
    }

    private void draw_tween(){
        System.out.print("_|_|_\n");
    }

    public void game_loop(){
        boolean game_over = false;
        Scanner sc = new Scanner(System.in);
        String move = "";
        int location = 0;
        char piece = ' ';
        String winner = "";

        while(!game_over){
            draw_board();
            System.out.println("Enter a move (PieceLocation ex. X3, Q to quit): ");
            move = sc.nextLine();
            if(move.charAt(0) == 'X' || move.charAt(0) == 'O'){

                try {
                    if(move.length() != 2){
                        System.out.println("***************\n");
                        System.out.println("Invalid input.");
                        System.out.println("\n***************");
                        continue;
                    }

                    if(move.charAt(1) == '0'){
                        location = Integer.parseInt(move.substring(1, 2));
                    }else{
                        location = Integer.parseInt(move.substring(1, 2)) - 1;
                    }
                    
                    piece = move.charAt(0);
                    if(board[location].occupant != '\u0000'){
                        System.out.println("***************\n");
                        System.out.println("Invalid location.");
                        System.out.println("\n***************");
                    }else{
                        board[location].occupant = piece;
                    }
                    
                } catch (NumberFormatException nfe) {
                    System.out.println("***************\n");
                    System.out.println("Invalid location.");
                    System.out.println("\n***************");
                }
                
                
            }else if(move.toLowerCase().charAt(0) == 'q'){
                System.exit(0);
            }else{
                System.out.println("***************\n");
                System.out.println("Invalid input.");
                System.out.println("\n***************");
            } 
            winner = check_win();
            if(winner.length() > 0){
                System.out.println(winner);
                game_over = true;
            }
            if(check_draw() == 0){
                System.out.println("Draw!");
                game_over = true;
            }
        }

    }
    private int check_draw() {
        for(Space s : board){
            if(s.occupant == '\u0000'){
                return -1;
            }
        }
        return 0;
    }

    private String check_win(){
        int[][] win_states = {{0, 1, 2},
                              {3, 4, 5},
                              {6, 7, 8},
                              {0, 3, 6},
                              {1, 4, 7},
                              {2, 5, 8},
                              {0, 4, 8},
                              {2, 4, 6}
                              };
        
        for(int[] state : win_states)
        {
            if(board[state[0]].occupant != '\u0000' && board[state[0]].occupant == board[state[1]].occupant && board[state[1]].occupant == board[state[2]].occupant)
            {
                return board[state[0]].occupant + " Wins!";
            }
        }

        return "";
    }

    public static void main(String[] args) {
        tictactoe t = new tictactoe();
        t.game_loop();
    }
}

class Space {
    short number;
    char occupant;
}
