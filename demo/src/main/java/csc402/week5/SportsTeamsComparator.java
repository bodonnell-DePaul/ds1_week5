package csc402.week5;
import java.util.Comparator;

public class SportsTeamsComparator implements Comparator<ProfessionalSportsTeam> {
    @Override
    public int compare(ProfessionalSportsTeam team1, ProfessionalSportsTeam team2) {

        // double winPerc = (team1.getWins() / (team1.getLosses() + team1.getWins()));
        // double winPerc2 = (team2.getWins() / (team2.getLosses() + team2.getWins()));

        // if (winPerc < winPerc2) {
        //     return -1;
        // } else if (winPerc > winPerc2) {
        //     return 1;
        // } else {
        //     return 0;
        // }

        String teamSport1 = team1.getSport();
        String teamSport2 = team2.getSport();

        int compare = teamSport1.compareTo(teamSport2);

        if( compare < 1){
            return 1;
        }
        else if(compare > 1){
            return -1;
        }
        else {
            return 0;
        }
    }

}
