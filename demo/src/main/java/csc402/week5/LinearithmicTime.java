package csc402.week5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;


public class LinearithmicTime {
    public static void main(String[] args) {
        int[] array = {5, 3, 1, 4, 2};
        String[] arrayString = {"Brian", "Alice", "David", "Cashew", "Eve", "Walnut"};
        ProfessionalSportsTeam[] teams = {
                new ProfessionalSportsTeam("Bears", "Chicago", "Football"),
                new ProfessionalSportsTeam("Cubs", "Chicago", "Baseball"),
                new ProfessionalSportsTeam("White Sox", "Chicago", "Baseball"),
                new ProfessionalSportsTeam("Bulls", "Chicago", "Basketball"),
                new ProfessionalSportsTeam("Blackhawks", "Chicago", "Hockey"),
                new ProfessionalSportsTeam("Fire", "Chicago", "Soccer")
        };

        //Bears
        teams[0].setWins(5);
        teams[0].setLosses(12);
        //Cubs
        teams[1].setWins(83);
        teams[1].setLosses(79);
        //White Sox
        teams[2].setWins(41);
        teams[2].setLosses(121);
        //Bulls
        teams[3].setWins(39);
        teams[3].setLosses(43);
        //Blackhawks
        teams[4].setWins(23);
        teams[4].setLosses(53);
        //Fire
        teams[5].setWins(7);
        teams[5].setLosses(18);

        // Sort the array
        ArrayList easyWay = new ArrayList<ProfessionalSportsTeam>();
        easyWay.addAll(Arrays.asList(teams));
        Collections.sort(easyWay, new SportsTeamsComparator());

        mergeSort(teams, 0, teams.length - 1);
        System.out.println(Arrays.toString(teams));
    }

    public static void mergeSort(ProfessionalSportsTeam[] array, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            // Recursively sort the left and right halves
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);

            // Merge the sorted halves
            merge(array, left, mid, right);
        }
    }

    public static void merge(ProfessionalSportsTeam[] array, int left, int mid, int right) {
        // Find sizes of two subarrays to be merged
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Create temporary arrays
        ProfessionalSportsTeam[] leftArray = new ProfessionalSportsTeam[n1];
        ProfessionalSportsTeam[] rightArray = new ProfessionalSportsTeam[n2];

        // Copy data to temporary arrays
        for (int i = 0; i < n1; ++i) {
            leftArray[i] = array[left + i];
        }
        for (int j = 0; j < n2; ++j) {
            rightArray[j] = array[mid + 1 + j];
        }

        // Merge the temporary arrays

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarray array
        int k = left;
        while (i < n1 && j < n2) {

            double team1 = 0;
            double team2 = 0;
            team1 = leftArray[i].getWins() / (leftArray[i].getLosses() + leftArray[i].getWins());
            team2 = rightArray[j].getWins() / (rightArray[j].getLosses() + rightArray[j].getWins());
            //leftArray[i].compare(rightArray[j]);
            //int val = rightArray[i].compareTo(leftArray[j]);
            //if (leftArray[i] <= rightArray[j])

            if (team1 <= team2) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of leftArray[] if any
        while (i < n1) {
            array[k] = leftArray[i];
            i++;
            k++;
        }

        // Copy remaining elements of rightArray[] if any
        while (j < n2) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }
}