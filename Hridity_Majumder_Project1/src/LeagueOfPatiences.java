/**
 * LeagueOfPatiences
 */

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
public class LeagueOfPatiences {

    public void myFastestPlay(int S, int T, Date startTime, int[][] durations) {

        int[] times = new int[durations.length];  // storing all final shortest time

        Boolean[] processed = new Boolean[durations.length]; // processed[i] = T if vertex i's shortest time is finalized

        for (int i = 0; i < durations.length; i++) {
            times[i] = Integer.MAX_VALUE;            // put all time to max case
            processed[i] = false;                    // shortest time not initializes
        }
        times[S] = 0;  // start vertex
        for (int count = 0; count < durations.length; count++) {

            int u = findNextToProcess(times, processed);
            processed[u] = true;                        // Shortest path to U is finalized

            for (int v = 0; v < durations.length; v++) {
                if (!processed[v] && durations[u][v] != 0 && times[u] != Integer.MAX_VALUE) { // check condition...

                    int waitTime = 0;
                    int nextQuestTime = getNextQuestTime(startTime, u, v).getDate();  // next guest time
                    int questTime = times[u] + nextQuestTime;

                    if (nextQuestTime >= questTime) {
                        waitTime = nextQuestTime - questTime;
                    } else {
                        if ((questTime - nextQuestTime) == 0) {
                            waitTime = 0;
                        } else {
                            waitTime = durations[u][v] - (questTime - nextQuestTime);
                        }
                    }

                    if (times[u] + durations[u][v] + waitTime < times[v]) {
                        times[v] = times[u] + durations[u][v] + waitTime;
                    }
                }
                if (processed[T]) break;
            }
        }
        printShortestTimes(times);
    }

    public Date getNextQuestTime(Date askingTime, int u, int v) {
        int minutesUntilNext = (int) (Math.random() * ((30) + 1) + (v - u));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(askingTime);
        calendar.add(Calendar.MINUTE, minutesUntilNext);
        return calendar.getTime();
    }

    public int minutesBetween(Date time1, Date time2) {
        return (int) (time2.getTime() - time1.getTime()) / 1000;
    }

    public int findNextToProcess(int[] times, Boolean[] processed) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int i = 0; i < times.length; i++) {
            if (processed[i] == false && times[i] <= min) {
                min = times[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    public void printShortestTimes(int times[]) {
        System.out.println("Play time to advance to various locations");
        for (int i = 0; i < times.length; i++)
            System.out.println(i + ": " + times[i] + " minutes");
    }

    public void genericShortest(int graph[][], int source) {
        int numVertices = graph[0].length;
        // This is the array where we'll store all the final shortest times
        int[] times = new int[numVertices];
        // processed[i] will true if vertex i's shortest time is already finalized
        Boolean[] processed = new Boolean[numVertices];
        // Initialize all distances as INFINITE and processed[] as false
        for (int v = 0; v < numVertices; v++) {
            times[v] = Integer.MAX_VALUE;
            processed[v] = false;
        }
        // Distance of source vertex from itself is always 0
        times[source] = 0;
        // Find shortest path to all the vertices
        for (int count = 0; count < numVertices - 1; count++) {
            // Pick the minimum distance vertex from the set of vertices not yet processed.
            // u is always equal to source in first iteration.
            // Mark u as processed.
            int u = findNextToProcess(times, processed);
            processed[u] = true;
            // Update time value of all the adjacent vertices of the picked vertex.
            for (int v = 0; v < numVertices; v++) {
                // Update time[v] only if is not processed yet, there is an edge from u to v,
                // and total weight of path from source to v through u is smaller than current value of time[v]
                if (!processed[v] && graph[u][v] != 0 && times[u] != Integer.MAX_VALUE && times[u] + graph[u][v] < times[v]) {
                    times[v] = times[u] + graph[u][v];
                }
            }
        }
        printShortestTimes(times);
    }

    public static void main(String[] args) {
        int playTimeGraph[][] = {
                {0, 10, 21, 0, 0, 0},
                {0, 0, 21, 10, 0, 0},
                {0, 0, 0, 25, 0, 78},
                {0, 0, 16, 0, 11, 0},
                {0, 0, 22, 16, 0, 28},
                {0, 0, 0, 0, 0, 0},
        };
        LeagueOfPatiences t = new LeagueOfPatiences();
        try {
            Date date = new SimpleDateFormat("hh:mm").parse("14:45");
            System.out.println("Online wait time NOT accounted for: ");
            t.genericShortest(playTimeGraph, 0);
            System.out.println("Online wait time accounted for: ");
            t.myFastestPlay(0, 5, date, playTimeGraph);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
} 