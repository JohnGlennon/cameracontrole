package be.kdg.processor.managers;

import be.kdg.processor.camera.cameramodel.Camera;
import be.kdg.processor.camera.cameramodel.Segment;
import be.kdg.processor.fine.FineManager;
import be.kdg.processor.licenseplate.Car;
import be.kdg.processor.message.CameraMessage;
import be.kdg.processor.settings.SettingsService;
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
    private SettingsService settingsService;

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
        Assert.assertEquals(amount, settingsService.getEmissionFactor(), 0.01);
    }

    @Test
    public void testSpeedDetection() {
        CameraMessage firstCameraMessage = new CameraMessage(10, "1-AAA-999", LocalDateTime.now());
        CameraMessage secondCameraMessage = new CameraMessage(11, "1-AAA-999", LocalDateTime.now().plusMinutes(1));

        Camera firstCamera = new Camera(1, null, new Segment(2, 2000, 70), 5);
        Camera secondCamera = new Camera(2, null, null, 5);

        fineManager.checkForSpeedOffense(firstCameraMessage, firstCamera);
        boolean isDetected = fineManager.checkForSpeedOffense(secondCameraMessage, secondCamera);

        Assert.assertTrue(isDetected);
    }

    @Test
    public void testSpeedFineCalculation() {
        CameraMessage cameraMessage = new CameraMessage(10, "1-AAA-999", LocalDateTime.now());
        double speed = 90;
        int speedLimit = 70;

        double amount = fineManager.calculateSpeedFine(cameraMessage, speed, speedLimit);
        double calculatedAmount = (speed - speedLimit) * settingsService.getSpeedFactor();

        Assert.assertEquals(amount, calculatedAmount, 0.01);
    }
}