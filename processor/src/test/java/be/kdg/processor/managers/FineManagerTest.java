package be.kdg.processor.managers;

import be.kdg.processor.fine.FineManager;
import be.kdg.processor.camera.cameramodel.Camera;
import be.kdg.processor.fine.FineService;
import be.kdg.processor.licenseplate.Car;
import be.kdg.processor.message.CameraMessage;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FineManagerTest {

    @Autowired
    private FineManager fineManager;

    @Autowired
    private FineService fineService;

    @Test
    public void testEmissionDetection() {
        Camera camera = new Camera(1, null, null, 5);
        Car car = new Car("1-ABC-123", null, 4);
        CameraMessage cameraMessage = new CameraMessage(1, "1-ABC-123", null);
        boolean isDetected = fineManager.checkForEmissionOffense(cameraMessage, camera, car);
        Assert.assertTrue(isDetected);
    }

    @Test
    public void testEmissionFineCalculation() {
        CameraMessage cameraMessage = new CameraMessage(10, "1-AAA-999", LocalDateTime.now());
        double amount = fineManager.calculateEmissionFine(cameraMessage);
        Assert.assertEquals(amount, fineService.getEmissionfactor(), 0.01);
    }
}