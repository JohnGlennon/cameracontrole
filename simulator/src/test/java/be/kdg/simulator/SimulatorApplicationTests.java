package be.kdg.simulator;

import be.kdg.simulator.generators.MessageGenerator;
import be.kdg.simulator.model.CameraMessage;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SimulatorApplicationTests {

    @Autowired
    //Dit is Field Injection
    private MessageGenerator messageGenerator;

    @Test
    public void testMessageGenerator() {
        String regex = "^(\\d{1})-(\\w{3})-(\\d{3})$";
        CameraMessage cameraMessage = messageGenerator.generate().get();
        Assert.assertTrue(cameraMessage.getLicensePlate().matches(regex));
    }

}
