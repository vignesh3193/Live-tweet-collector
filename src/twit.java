import twitter4j.*;
import java.io.*;
/**
 * <p>This is a code example of Twitter4J Streaming API - sample method support.<br>
 * Usage: java twitter4j.examples.PrintSampleStream<br>
 * </p>
 *
 * @author Yusuke Yamamoto - yusuke at mac.com
 */
public final class twit {
    /**
     * Main entry of this application.
     *
     * @param args arguments doesn't take effect with this example
     * @throws TwitterException when Twitter service or network is unavailable
     */
	static int count=0;
    public static void main(String[] args) throws TwitterException, IOException {
    	File file=new File("tweets.txt");
    	FileWriter writer=new FileWriter(file);
        TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
        StatusListener listener = new StatusListener() {
            @Override
            public void onStatus(Status status) {
           if(count<1000)
           {
               try {
				writer.write(count+". @" + status.getUser().getScreenName() + " - " + status.getText()+"\n\n\n");
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
               count++;
            }
           else
           {
        	   System.exit(0);
           }
            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
               // System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
            	//count--;
            }

            @Override
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
                System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
            }

            @Override
            public void onScrubGeo(long userId, long upToStatusId) {
                System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
            }

            @Override
            public void onStallWarning(StallWarning warning) {
                System.out.println("Got stall warning:" + warning);
            }

            @Override
            public void onException(Exception ex) {
                ex.printStackTrace();
            }
        };
        twitterStream.addListener(listener);
        twitterStream.sample();
    }
}