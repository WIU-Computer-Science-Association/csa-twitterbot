# csa-twitterbot
## Installing dependencies with Maven
In the csa-twitterbot directory, use the following command:

>mvn install

You can then package the build by using the following command in the csa-twitterbot directory:

>mvn package

I am new to Maven, so if you see any issues with any of the POMs, just let me know or put in a PR or issue.

## Project Details

This is a WIP CSA twitterbot in Java using Twitter4J and JSoup.

The goal of this bot is to listen to the CSA github and emails to tweet out when something is updated or events are posted through email.

Twitter4J and JSoup are used in this project, that might change though (specifically with JSoup if we don't need it). 

If you're interested in learning how the Twitter4J library works, here is a neat video that I used to help me start working on this twitter bot, and I used a little bit of the source code to make this twitter bot too: https://youtu.be/kgj3mjclAsM

This is based off of a twitterbot I have made before for tweeting events and random jokes. Some of the code reflects that, and that's one of the things that might need worked on is to make the code more lean. I've tried to keep a good documentation through the code but it might not be perfect either.

Anyone is welcome to suggest changes and work on the code to make it better. Feel free to fork this and make your own twitter bot, just make sure you apply for twitter dev. privileges here: https://developer.twitter.com/en/apply-for-access.html

If you do make your own twitter bot, just make sure you read the documentation and the rules on using the twitter API, there are limits on certain things like data collection and rate of tweets/messages that you should keep in mind. https://developer.twitter.com/en/docs
