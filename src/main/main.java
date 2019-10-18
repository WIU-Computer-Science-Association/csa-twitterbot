/*
    Huge shoutout to Mistapotta's video on the Twitter4j api in this video here with: https://youtu.be/kgj3mjclAsM ,
    I used it as a reference for the twitterer class with this project.
*/
package main;

import myBot.Twitterer;
import twitter4j.Status;
import twitter4j.TwitterException;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * This is a multi-role twitter bot that currently tweets out different tweets in randomized statements, or webscraped information.
 *
 * @author Evan
 */
public class main {
    private static PrintStream consolePrint;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws TwitterException, IOException, InterruptedException {
        Twitterer tweety = new Twitterer(consolePrint);
        boolean run = true;
        Scanner filenameSc = new Scanner(System.in);
        Status recentStatus = tweety.getRecent(); //TODO: Put this in a try catch
        int i = 0;
        tweety.eventsTweet();

        while (run == true) {
            try {
                recentStatus = tweety.getRecent();
                if (tweety.ageToHours(recentStatus) > 1) {
                    try {
                        tweety.tweetOut("Something"); //"Something" is temporary to make java not mad
                        i++;
                    } catch (IOException | TwitterException e) {
                        System.err.println("Error connecting to twitter. Will try again later. Error: " + e);
                    }
                }
            } catch (TwitterException e) {
                System.err.println("Error connecting to twitter. Will try again later. Error: " + e);
            }
            if (i > 12) {
                run = false;
            }
            Thread.sleep(TimeUnit.MINUTES.toMillis(30));
        }
    }
}
