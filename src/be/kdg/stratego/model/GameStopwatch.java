/*
    Class: GameStopwatch
    Responsiblity: Handles the stopwatch on the BattleFieldView
 */
package be.kdg.stratego.model;

public class GameStopwatch {
    private int hours;
    private int minutes;
    private int seconds;

    public void tick() {
        this.seconds++;
        if (this.seconds == 60) {
            this.seconds = 0;
            this.minutes++;

            if (this.minutes == 60) {
                this.minutes = 0;
                this.hours++;
            }
        }
    }

    // Getters & setters
    public int getHours() {
        return hours;
    }
    public int getMinutes() {
        return minutes;
    }
    public int getSeconds() {
        return seconds;
    }
}
