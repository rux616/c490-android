/*--------------------------------------------------------------------------------------------------
 * Author:      Dan Cassidy
 * Date:        2015-07-25
 * Assignment:  HW6-3
 * Source File: Craps.java
 * Language:    Java
 * Course:      CSCI-C 490, Android Programming, MoWe 08:00
--------------------------------------------------------------------------------------------------*/
package dancassidy.craps;

import java.util.Random;

/**
 * Simple craps game implementation.
 *
 * @author Dan Cassidy
 */
public class Craps {
    public static enum Status {
        ONGOING,
        WIN,
        LOSE
    }

    private int pointRoll1 = 0;
    private int pointRoll2 = 0;
    private int auxRoll1 = 0;
    private int auxRoll2 = 0;
    private Status gameStatus = Status.ONGOING;

    private static Random generator = new Random();

    // BEGIN GETTERS -->
    public int getPointRoll1() {
        return pointRoll1;
    }

    public int getPointRoll2() {
        return pointRoll2;
    }

    public int getPoint() {
        return pointRoll1 + pointRoll2;
    }

    public int getAuxRoll1() {
        return auxRoll1;
    }

    public int getAuxRoll2() {
        return auxRoll2;
    }

    public Status getGameStatus() {
        return gameStatus;
    }
    // <-- END GETTERS

    /**
     * Roll the appropriate set of die and handle the results.
     */
    public void roll() {
        if (gameStatus != Status.ONGOING)
            return;

        // Assign rolls to their respective variables, then check the results and set the game
        // status accordingly.
        if (pointRoll1 == 0) {
            pointRoll1 = generator.nextInt(6) + 1;
            pointRoll2 = generator.nextInt(6) + 1;

            switch (pointRoll1 + pointRoll2) {
                case 7:
                case 11:
                    gameStatus = Status.WIN;
                    break;

                case 2:
                case 3:
                case 12:
                    gameStatus = Status.LOSE;
                    break;
            }
        }
        else {
            auxRoll1 = generator.nextInt(6) + 1;
            auxRoll2 = generator.nextInt(6) + 1;

            switch (auxRoll1 + auxRoll2) {
                case 7:
                    gameStatus = Status.LOSE;
                    break;

                default:
                    if (pointRoll1 + pointRoll2 == auxRoll1 + auxRoll2)
                        gameStatus = Status.WIN;
                    break;
            }
        }
    }

    /**
     * Reset the game to its default state.
     */
    public void reset() {
        pointRoll1 = 0;
        pointRoll2 = 0;
        auxRoll1 = 0;
        auxRoll2 = 0;
        gameStatus = Status.ONGOING;
    }
}
