package au.com.anz.robot.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * User: agwibowo
 */
public class BoardUnitTest {

    private Board board;

    @Before
    public void setup() {
        board = new Board(5, 5);
    }

    @Test
    public void isValidPosition_should_return_false_when_given_negative_x() throws Exception {
        assertFalse(board.isValidPosition(-1, 0));
    }

    @Test
    public void isValidPosition_should_return_false_when_given_negative_y() throws Exception {
        assertFalse(board.isValidPosition(0, -1));
    }

    @Test
    public void isValidPosition_should_return_false_when_given_coordinate_beyond_the_width() throws Exception {
        assertFalse(board.isValidPosition(5, 0));
    }

    @Test
    public void isValidPosition_should_return_false_when_given_coordinate_beyond_the_height() throws Exception {
        assertFalse(board.isValidPosition(0, 5));
    }

    @Test
    public void isValidPosition_should_return_true_when_given_valid_coordinate() {
        assertTrue(board.isValidPosition(0, 0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_not_allow_negative_value_for_board_width() {
        new Board(-1, 5);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void should_not_allow_negative_value_for_board_height() {
        new Board(1, -5);
    }
}
