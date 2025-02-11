package csc402.week5;

public class ProfessionalSportsTeam {

    private String name;
    private String city;
    private String sport;
    private double wins;
    private double losses;

    public ProfessionalSportsTeam(String name, String city, String sport) {
        this.name = name;
        this.city = city;
        this.sport = sport;
        this.wins = 0;
        this.losses = 0;
    }

    public void setWins(double wins) {
        this.wins = wins;
    }

    public void setLosses(double losses) {
        this.losses = losses;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getSport() {
        return sport;
    }

    public double getWins() {
        return wins;
    }

    public double getLosses() {
        return losses;
    }
}
