package au.com.anz.robot;

import au.com.anz.robot.model.Board;
import au.com.anz.robot.model.Robot;

/**
 * User: agwibowo
 * Date: 28/02/12
 * Time: 9:51 PM
 */
public class Main {

    private Board board;

    private Robot robot;

    public static void main(String[] args) {
        Board board = new Board(5, 5);
        Robot robot = new Robot(board);
    }
}
