package edu.okbu.cis2723.sp2021;

import java.text.DecimalFormat;
import java.util.Random;

public class BasketballTeamStatistics {
    // The number of points scored by players on a team over the course of a season.
    private double[][] points;
    // The number of minutes played by players on a team over the course of a season.
    private double[][] minutes;
    // The names of the players.
    private String[] playerNames;
    // The number of players on the team.
    private int numPlayers;
    // The number of game in the season.
    private int numGames;
    // The highest scoring average for a player
    private double highestScoringAverage;
    // The index of the player with the highest scoring average
    private int playerWithHighestScoringAverage;
    // The most points scored in any game
    private double highestGamePoints;
    // The player that scored the most points in a game
    private int playerWithHighestGamePoints;
    // The game in which the most points were scored
    private int gameWhereMostPointsScored;
    // The total points scored by the team
    private double totalTeamPointsScored;
    // The highest points / minute by a player
    private double highestPointsPerMinute;
    // The player with the highest points / minute
    private int playerWithHighestPointsPerMinute;

    /**
     * Class constructor. Intantiates data, initialized variables and generates data.
     * @param numPlayers The number of players on the team
     * @param numGames The number of games in the season
     * @param players The list of player names
     */
    public BasketballTeamStatistics(int numPlayers, int numGames, String[] players) {
        points = new double[numPlayers][numGames];
        minutes = new double[numPlayers][numGames];
        playerNames = players;
        this.numGames = numGames;
        this.numPlayers = numPlayers;

        generateData();
    }

    /**
     * Method to compute and store the player with the highest scoring average.
     */
    private void computeStatistics() {
        highestScoringAverage = 0.0;
        playerWithHighestScoringAverage = 0;
        highestGamePoints = 0.0;
        playerWithHighestGamePoints = 0;
        gameWhereMostPointsScored = 0;
        totalTeamPointsScored = 0.0;
        highestPointsPerMinute = 0.0;
        playerWithHighestPointsPerMinute = 0;
        for (int playerIdx = 0 ; playerIdx < numPlayers; playerIdx++) {
            double totalPoints = 0.0;
            double totalMinutes = 0.0;
            for (int gameIdx = 0; gameIdx < numGames; gameIdx++) {
                totalPoints = totalPoints + points[playerIdx][gameIdx];
                totalMinutes = totalMinutes + minutes[playerIdx][gameIdx];
                totalTeamPointsScored = totalTeamPointsScored + points[playerIdx][gameIdx];
                if (points[playerIdx][gameIdx] > highestGamePoints) {
                    highestGamePoints = points[playerIdx][gameIdx];
                    playerWithHighestGamePoints = playerIdx;
                    gameWhereMostPointsScored = gameIdx;
                }
            }
            double averagePointsPerGame = totalPoints / numGames;
            if (averagePointsPerGame > highestScoringAverage) {
                highestScoringAverage = averagePointsPerGame;
                playerWithHighestScoringAverage = playerIdx;
            }
            double averagePointsPerMinute = totalPoints / totalMinutes;
            if (averagePointsPerMinute > highestPointsPerMinute) {
                highestPointsPerMinute = averagePointsPerMinute;
                playerWithHighestPointsPerMinute = playerIdx;
            }
        }
    }

    /**
     * Method to generate random data.
     */
    private void generateData() {
        Random rand = new Random();
        for (int playerIdx = 0 ; playerIdx < numPlayers; playerIdx++) {
            for (int gameIdx = 0; gameIdx < numGames; gameIdx++) {
                points[playerIdx][gameIdx] = rand.nextInt(20);
                minutes[playerIdx][gameIdx] = rand.nextInt(40);
            }
        }
    }

    @Override
    public String toString() {
        DecimalFormat average = new DecimalFormat("0.00");
        computeStatistics();
        String output = "The team has " + numPlayers + " players and played " + numGames + " games.\n" +
                "Players\n-------";
        for (int i = 0; i < numPlayers; i++) {
            output = output + "\n\t" + playerNames[i];
        }
        output = output + "\nThe player with the highest scoring average is " +
                playerNames[playerWithHighestScoringAverage] +
                " at " + average.format(highestScoringAverage) + " points per game.";
        output = output + "\nThe player that scored the most points in a game is " +
                playerNames[playerWithHighestGamePoints] +
                " who scored " + average.format(highestGamePoints) + " in game " + gameWhereMostPointsScored;
        output = output + "\nThe player with the highest points / minute is " +
                playerNames[playerWithHighestPointsPerMinute] +
                " at " + average.format(highestPointsPerMinute);
        output = output + "\nThe average points scored / game by the team is " +
                average.format(totalTeamPointsScored / numGames);

        return output;
    }
}
