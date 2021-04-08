package edu.okbu.cis2723.sp2021;

public class Main {

    public static void main(String[] args) {
        String players[] = new String[] {"A", "B", "C", "D", "E", "F", "G", "H"};
        BasketballTeamStatistics team = new BasketballTeamStatistics(8, 10, players);
        System.out.println(team);
    }
}
