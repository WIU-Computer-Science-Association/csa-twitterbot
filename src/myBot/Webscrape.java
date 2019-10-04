package myBot;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author Evan
 * <p>
 * This class is for webscraping utilities, all of the methods are tailored for specific websites with formating specific to that website.
 * This can be used to grab important public data off of a webpage.
 */
public class Webscrape {

    /**
     * Gets the weather.
     *
     * @return
     * @throws IOException
     */
    private static String weather() throws IOException {
        final Document document = Jsoup.connect("https://forecast.weather.gov/MapClick.php?x=68&y=83&site=ilx&zmx=&zmy=&map_x=67&map_y=83#.XFOy5KpKhPY").get();
        String currentCond = document.select("p.myforecast-current").text();
        String temp = document.select("p.myforecast-current-lrg").text();
        String tempC = document.select("p.myforecast-current-sm").text();
        String storeString = "Current Conditions " + currentCond + "\n" + "Temperature " + temp + " (" + tempC + ")";

        for (Element row : document.select("div#current_conditions_detail.pull-left tr")) {
            storeString = storeString + "\n" + row.text();
        }
        storeString = "Here are the current weather conditions in Macomb, IL: \n" + storeString;

        return storeString;
    }

    /**
     * This method gets data from the National Weather Service and returns information, in a String, for local weather data for Macomb, IL.
     *
     * @return String - Returns formatted weather data in a String.
     * @throws IOException Throws an IOException if connection to the NWS website cannot be made.
     */
    public static String getWeather() throws IOException {
        return weather();
    }

    /**
     * Creates a LinkedList storing all of the scheduled rocket launches/descriptions from spaceflightnow.com
     *
     * @return
     * @throws IOException
     */
    private static LinkedList<String> LaunchCal() throws IOException {
        Document spaceflightNow = Jsoup.connect("https://spaceflightnow.com/launch-schedule/").get();
        int node = 1;
        LinkedList<String> schedule = new LinkedList<>();

        for (Element launch : spaceflightNow.select("div.entry-content.clearfix div.datename, div.missiondata, div.missdescrip")) {
            String launchdate, mission, missiondata, missiondescrip;
            if (node == 1) {
                launchdate = launch.select("span.launchdate").text();
                mission = launch.select("span.mission").text();
                schedule.addLast(launchdate + "\n" + mission + "\n");
            }
            if (node == 2) {
                missiondata = launch.select("div.missiondata").text();
                schedule.addLast(missiondata + "\n");
            }
            if (node == 3) {
                missiondescrip = launch.select("div.missdescrip").text();
                schedule.addLast(missiondescrip + "\n\n");
            }
            node++;
            if (node > 3)
                node = 1;
        }

        return schedule;
    }

    /**
     * This method gets information from https://spaceflightnow.com/launch-schedule/ about upcoming space launches, times, dates, missions, and returns that information in a LinkedList.
     *
     * @return LinkedList - Returns information about upcoming spaces launches including: time, date, mission, and launch vehicle.
     * @throws IOException Throws an IOException if connection to https://spaceflightnow.com/launch-schedule/ cannot be made.
     */
    public static LinkedList<String> getLaunchCal() throws IOException {
        return LaunchCal();
    }

    private static LinkedList<String> wiuEvents() throws IOException {
        final Document wiu = Jsoup.connect("http://www.wiu.edu/wiucalendar/").get();
        LinkedList<String> eventList = new LinkedList<>();

        for (Element event : wiu.select("div#main h3.date, tr")) {
            String curEvent = event.text();
            eventList.add(curEvent);
        }

        return eventList;
    }

    /**
     * This method gets information from http://www.wiu.edu/wiucalendar/ about events happen around campus. The first node is always a day, potentially followed by an event.
     *
     * @return LinkedList - Returns information about events in the form of a LinkedList that holds strings.
     * @throws IOException Throws an IOException if connection to http://www.wiu.edu/wiucalendar/ cannot be made.
     */
    public static LinkedList<String> getWiuEvents() throws IOException {
        LinkedList<String> allEvents = wiuEvents();
        Scanner sc;
        LinkedList<String> curEvents = new LinkedList<>();

        while (!allEvents.isEmpty()) {
            curEvents.add(allEvents.remove());
            sc = new Scanner(allEvents.getFirst());
            String temp = sc.next();

            if (temp.equalsIgnoreCase("Monday,") || temp.equalsIgnoreCase("Tuesday,") || temp.equalsIgnoreCase("Wednesday,") || temp.equalsIgnoreCase("Thursday,") || temp.equalsIgnoreCase("Friday,") || temp.equalsIgnoreCase("Saturday,") || temp.equalsIgnoreCase("Sunday,")) {
                break;
            }
        }

        return curEvents;
    }
}
