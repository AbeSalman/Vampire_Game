package as_vampire_game;

import java.util.Scanner;

/**
 *
 * @author iCloud
 */
public class AS_Vampire_Game {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        char[][] grid = new char[10][10];

        // vampire
        System.out.print("Enter (i, j) for vampire: ");
        int newI = input.nextInt();
        int newJ = input.nextInt();
        Creature vampire = new Creature('V', newI, newJ);

        // hunter
        System.out.print("Enter (i, j) for hunter: ");
        int I = input.nextInt();
        int J = input.nextInt();
        Creature hunter = new Creature('H', I, J);

        // Can hunter move 0 no 1 yes
        System.out.print("Would you like hunter to move? (0: no, 1: yes): ");
        int moving = input.nextInt();
        hunter.setMoving(moving);
        // display grid
        clearGrid(grid);
        //place hunter and vampire on 10x10 grid
        vampire.setIJ(newI, newJ);
        hunter.setIJ(I, J);
        //display grid with vampire and human
        vampire.display(grid);
        hunter.display(grid);
        //Draw grid with human and vampire on it
        drawGrid(grid);
        //Display vampire and hunter positions
        System.out.println("Vampire at: " + vampire.getI()
                + " " + vampire.getJ());
        System.out.println("Hunter at: " + hunter.getI()
                + " " + hunter.getJ());

        // user input
        System.out.print("Enter command (0 to quit): ");
        int command = input.nextInt();

        while (command != 0) { // while not 0 do not quit
            //clear grid
            clearGrid(grid);
            //update vampire space (up, left down, right)
            vampire.update(command);
            //check to see if vampire has bitten human, always do this check first
            if (sameSquare(vampire, hunter) == true) {
                System.out.println("The vampire has bit his victim!");
                break;
            }
            //Update hunter
            hunter.update();
            //check to see if human suicides now
            if (sameSquare(vampire, hunter) == true) {
                System.out.println("The human has sacrifed himself!");
                break;
            }
            //Display updated grid
            hunter.display(grid);
            vampire.display(grid);
            //draw grid
            drawGrid(grid);
            //Output vampire and human location on 10x10 map
            System.out.println("Vampire at: " + vampire.getI()
                    + " " + vampire.getJ());
            System.out.println("Human at: " + hunter.getI()
                    + " " + hunter.getJ());
            //Take user's input again
            System.out.print("Enter command (0 to quit): ");
            command = input.nextInt();

        } // while (command != 0)

    }

    public static void clearGrid(char[][] g) {
        for (int i = 0; i < g.length; i++) {
            for (int j = 0; j < g.length; j++) {
                g[i][j] = '.';
            }
        }
    }

    public static void drawGrid(char[][] g) {
        System.out.println("0123456789");
        for (int i = 0; i < g.length; i++) {
            for (int j = 0; j < g.length; j++) {
                System.out.print(g[i][j]);
            }
            System.out.println(i);
        }
    }

    public static boolean sameSquare(Creature c1, Creature c2) {
        // if c1 and c2 have identical (i, j) coordinates, return true
        if (c1.getI() == c2.getI() && c1.getJ() == c2.getJ()) {
            return true;
        } // else return false
        else {
            return false;
        }
    }

}

class Creature {

    // display character for creature
    private char pic;
    // (i, j) coordinates for creature
    private int i = 0; // row
    private int j = 0; // column 
    private boolean canMove = true; // creature movement

    Creature(char c, int nI, int nJ) {
        // set display character to c
        pic = c;
        // set position using global values i and j
        setIJ(i, j);
    }

    public void setIJ(int nI, int nJ) {
        // between 0 and 9
        if (nI < 10 && nI >= 0) {
            i = nI;
        }
        if (nJ < 10 && nJ >= 0) {
            j = nJ;
        }
    }

    public void setMoving(int n) {
        if (n == 0) {
            canMove = false; // do not move
        } else {
            canMove = true; // move
        }
    }

    public int getI() {
        return i; // return global value i
    }

    public int getJ() {
        return j; // return global value j
    }

    public void update() {
        // random value between 1-4
        int c = 0;
        c = ((int) ((Math.random() * ((4 - 1) + 1)) + 1)); // Algorithm used to generate random #
        if (canMove != false) {
            //1: j-- (left)
            if (c == 1) {
                if (j > 0) {
                    j--;
                } else {
                    System.out.println("Invalid position change; position set to(" + i + ", " + j + ")");
                    return;
                }
            }

            // 2: i++ (down)
            if (c == 2) {
                if (i + 1 < 10) {
                    i++;
                } else {
                    System.out.println("Invalid position change; position set to(" + i + ", " + j + ")");
                    return;
                }
            }
            // 3: i-- (up)
            if (c == 3) {
                if (i > 0) {
                    i--;
                } else {
                    System.out.println("Invalid position change; position set to(" + i + ", " + j + ")");
                    return;
                }
            }
            // 4: j++ (right)
            if (c == 4) {
                if (j + 1 < 10) {
                    j++;
                } else {
                    System.out.println("Invalid position change; position set to(" + i + ", " + j + ")");
                    return;
                }
            }
        }
    }

    public void update(int c) {
        // Update will always move
        // 1: j-- (left)
        if (c == 1) {
            if (j > 0) {
                j--;
            } else {
                System.out.println("Invalid position change; position set to(" + i + ", " + j + ")");
                return;
            }
        }

        // 2: i++ (down)
        if (c == 2) {
            if (i + 1 < 10) {
                i++;
            } else {
                System.out.println("Invalid position change; position set to(" + i + ", " + j + ")");
                return;
            }
        }
        // 3: i-- (up)
        if (c == 3) {
            if (i > 0) {
                i--;
            } else {
                System.out.println("Invalid position change; position set to(" + i + ", " + j + ")");
                return;
            }
        }
        // 4: j++ (right)
        if (c == 4) {
            if (j + 1 < 10) {
                j++;
            } else {
                System.out.println("Invalid position change; position set to(" + i + ", " + j + ")");
                return;
            }
        }

    }

    public void display(char[][] g) {
        g[i][j] = pic;
    }

}
