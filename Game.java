import java.awt.*;

public class Game {
    Frame game_window;
    Game_Surface game_surface;
    State state;

    public Game(){
        state = new State();
        game_window = new Frame();
        game_window.setSize(300, 300);
        game_surface = new Game_Surface(state);
        game_surface.setSize(300, 300);
        game_surface.setBackground(Color.darkGray);
        game_window.add(game_surface);
        game_window.setVisible(true);
    }

    public static void main(String[] args) {
        new Game();
    }
}

class Game_Surface extends Panel {
    State state;
    String[] board;

    public Game_Surface(State s){
        this.state = s;
    }

    public void paint(Graphics g){
        board = state.get_board();
        short ypos = 95;

        g.setColor(Color.LIGHT_GRAY);
        g.setFont(new Font("Monospaced", 1, 20));
        for(String row : board){
            g.drawString(row, 120, ypos);
            ypos += 22;
        } 
    }
}

class State {
    private Space[] board;
    private String[] board_strings;

    public State(){
        board = new Space[9];
        board_strings = new String[5];
        for(short i = 0; i < board.length; i++ ){
            board[i] = new Space();
            board[i].number = i;
            board[i].number++;
        }

        for(short i = 0; i < board_strings.length; i++){
            board_strings[i] = new String();
        }

        build_board();
    }

    private void build_board(){
        short column = 1;
        short row = 0;
        for(Space s : board){
            if(s.occupant == '\u0000'){
                if(column < 3){
                    board_strings[row] += String.valueOf(s.number);
                    board_strings[row] += "|";
                    column++;
                }else{
                    board_strings[row] += String.valueOf(s.number);
                    row++;
                    if(s.number == 9){
                        break;
                    }
                    board_strings[row] = "_|_|_";
                    row++;
                    column = 1;
                }
            }
        }
    }

    public String[] get_board(){
        return board_strings;
    }
}

class Space {
    short number;
    char occupant;
}
