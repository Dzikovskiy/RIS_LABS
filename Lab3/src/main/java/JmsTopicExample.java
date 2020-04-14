import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerFactory;
import org.apache.activemq.broker.BrokerService;

import javax.jms.*;
import java.net.URI;

public class JmsTopicExample {
    public static void main(String[] args) throws Exception {
        BrokerService broker = BrokerFactory.createBroker(new URI(
                "broker:(tcp://localhost:61616)"));
        broker.start();
        Connection connection = null;
        try {
            // Producer
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                    "tcp://localhost:61616");
            connection = connectionFactory.createConnection();
            Session session = connection.createSession(false,
                    Session.AUTO_ACKNOWLEDGE);
            Topic topic = session.createTopic("Topic");

            MessageConsumer consumer1 = session.createConsumer(topic);
            consumer1.setMessageListener(new ConsumerMessageListener("Consumer1"));
            MessageConsumer consumer2 = session.createConsumer(topic);
            consumer2.setMessageListener(new ConsumerMessageListener("Consumer2"));

            connection.start();

            // Publish first messages
            String payload = "Important Task";
            Message msg = session.createTextMessage(payload);
            MessageProducer producer = session.createProducer(topic);
            System.out.println("Sending text '" + payload + "'");
            producer.send(msg);
            producer.send(msg);// equals messages

            payload = "Important Task 2";
            msg = session.createTextMessage(payload);
            System.out.println("Sending text: '" + payload + "'");
            producer.send(msg);

            Thread.sleep(3000);
            session.close();
        } finally {
            if (connection != null) {
                connection.close();
            }
            broker.stop();
        }
    }
}
