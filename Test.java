import java.awt.*;

public class Test {
    Frame f;
    Game_Board gb;

    public Test() {
        f = new Frame();
        gb = new Game_Board();

        f.setSize(300, 300);

        gb.setSize(300, 300);
        gb.setBackground(Color.DARK_GRAY);

        f.add(gb);
        f.setVisible(true);

    }

    public static void main(String[] args) {
        new Test();
    }
}

class Game_Board extends Panel{
    Cell[] spaces;

    public Game_Board(){
        int xpos = 0;
        int ypos = 0;
        int cursor = 0;

        spaces = new Cell[9];

        for(int i = 0; i < spaces.length; i++){
            spaces[i] = new Cell();
            spaces[i].number = i;
            spaces[i].p = new Point(xpos, ypos);
            xpos += 44;
            cursor++;

            if(cursor == 3){
                ypos += 49;
                cursor = 0;
                xpos = 0;
            }
            
        }
    }

    public void paint(Graphics g){
        super.paint(g);
        for(Cell c : spaces){
            c.draw(g);
        }
    }
}

class Cell{
    int number;
    char occupant;
    Point p;

    public void draw(Graphics g){
        if(this.number % 2 == 0){
            g.setColor(Color.RED);
            g.fillRect(p.x, p.y, 40, 45);
            g.setFont(new Font("Monospaced", 1, 40));
            g.setColor(Color.WHITE);
            g.drawString("X", p.x + 8, p.y + 40);
        }else{
            g.setColor(Color.white);
            g.fillRect(p.x, p.y, 40, 45);
            g.setFont(new Font("Monospaced", 1, 40));
            g.setColor(Color.RED);
            g.drawString("O", p.x + 8, p.y + 40);
        }
    }
}