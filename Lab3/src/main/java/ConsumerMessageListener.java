import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup",
                propertyValue = "jms/MyTopic"),
        @ActivationConfigProperty(propertyName = "destinationType",
                propertyValue = "javax.jms.Topic")
})
public class ConsumerMessageListener implements MessageListener {
    private LinkedList<String> set = new LinkedList<>();
    private int counter = 0;
    private String consumerName;
    @Resource
    private MessageDrivenContext mdc;

    public ConsumerMessageListener(String consumerName) {
        this.consumerName = consumerName;
    }

    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            System.out.println(consumerName + " received: " + textMessage.getText());
            if (set.contains(textMessage.getText())) {
                FileWriter nFile = new FileWriter("file.txt");
                nFile.write(set.indexOf(textMessage.getText())+1 + " " + textMessage.getText()+"\n");
                nFile.write(set.size()+1+" "+ textMessage.getText()+"\n");
                nFile.close();
            } else {
                set.add(textMessage.getText());
            }
        } catch (JMSException | IOException e) {
            e.printStackTrace();
            mdc.setRollbackOnly();
        }
    }

}