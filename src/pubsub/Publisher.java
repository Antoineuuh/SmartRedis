package pubsub;

import redis.clients.jedis.Jedis;

public class Publisher {

    private Jedis JEDIS;

    //s1TuRnDy7ERzuejDSq7V9ZEPYkVrdSyt

    public Publisher(){

        JEDIS = new Jedis("SG-SmartRedis-38687.servers.mongodirector.com");
        JEDIS.auth("s1TuRnDy7ERzuejDSq7V9ZEPYkVrdSyt");

        System.out.println("Publisher is running: " + JEDIS.ping());

    }

    public void PUBLISH(String CHANNEL, String DATA){

        JEDIS.publish(CHANNEL, DATA);

    }
}
