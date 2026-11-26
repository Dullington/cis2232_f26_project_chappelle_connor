package ca.hccis.files.bo;

import ca.hccis.files.entity.Tennis;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TennisBOTest {

    /**
     * Tests the Win Rate calculation using a test driven approach.
     * The test expects a player who won 2 of 3 sets to have a
     * Win Rate of approximately 66.67%.
     */
    @Test
    public void testCalculateWinRate() {

        Tennis tennis = new Tennis();
        tennis.setSetsPlayed(3);
        tennis.setPlayerSetsWon(2);

        TennisBO tennisBO = new TennisBO();

        double result = tennisBO.calculate(tennis);

        assertEquals(66.67, result, 0.01);
    }
    /**
     * Tests the Win Rate calculation using a test driven approach.
     * The test expects a player who won all 3 of 3 sets to have a
     * Win Rate of 100%.
     */
    @Test
    public void testCalculatePerfectWinRate() {

        Tennis tennis = new Tennis();
        tennis.setSetsPlayed(3);
        tennis.setPlayerSetsWon(3);

        TennisBO tennisBO = new TennisBO();

        double result = tennisBO.calculate(tennis);

        assertEquals(100.0, result);
    }
    /**
     * Tests the Win Rate calculation using a test driven approach.
     * The test expects a player who won 0 of 3 sets to have a
     * Win Rate of 0%.
     */
    @Test
    public void testCalculateZeroWinRate() {

        Tennis tennis = new Tennis();
        tennis.setSetsPlayed(3);
        tennis.setPlayerSetsWon(0);

        TennisBO tennisBO = new TennisBO();

        double result = tennisBO.calculate(tennis);

        assertTrue(result == 0.0);
    }
}
