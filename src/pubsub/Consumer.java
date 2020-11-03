package pubsub;

import javafx.application.Platform;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;
import sample.Controller;
import sample.Main;

public class Consumer extends Thread {

    private Jedis JEDIS;
    private JedisPubSub PUB_SUB;
    private String CHANNELS;

    public Consumer(String CHANNELS){

        JEDIS = new Jedis("SG-SmartRedis-38687.servers.mongodirector.com");
        JEDIS.auth("s1TuRnDy7ERzuejDSq7V9ZEPYkVrdSyt");
        this.CHANNELS = CHANNELS;

        System.out.println("Consumer is running: " + JEDIS.ping());

        PUB_SUB = new JedisPubSub() {

            @Override
            public void onMessage(String channel, String message) {

                String[] ARR_OF_STR = message.split("@");
                if(ARR_OF_STR[0].equalsIgnoreCase(Main.CURRENT_USERNAME)) { return; }

                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {

                        Controller.ADD_MSG_TO_PANE_HAS_RECEIVER(message);
                    }

                });

            }

            @Override
            public void onSubscribe(String channel, int subscribedChannels) {

                System.out.println("Your client is Subscribed to channel : "+ channel);

            }

            @Override
            public void onUnsubscribe(String channel, int subscribedChannels) {

                System.out.println("Your client is Unsubscribed from channel : "+ channel);
            }

        };

    }

    private void CONSUME(JedisPubSub PUB_SUB, String CHANNELS){

         JEDIS.subscribe(PUB_SUB, CHANNELS);

    }

    @Override
    public void run() {
        try {

            CONSUME(PUB_SUB, CHANNELS);

        } catch (Exception e){

            e.printStackTrace();

        }
    }
}
