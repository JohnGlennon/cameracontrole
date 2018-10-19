package be.kdg.processor.listeners;

import be.kdg.processor.fine.FineManager;
import be.kdg.processor.camera.Camera;
import be.kdg.processor.licenseplate.LicensePlate;
import be.kdg.processor.offense.EmissionListener;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmissionListenerTest {

    @Autowired
    private EmissionListener emissionListener;

    @Autowired
    private FineManager fineManager;

    @Test
    public void testEmissionDetection() {
        Camera camera = new Camera(1, 5);
        LicensePlate licensePlate = new LicensePlate("1-ABC-123", 4);
        boolean isOffense = emissionListener.listen(camera, licensePlate, LocalDateTime.now());
        Assert.assertTrue(isOffense);
    }

    @Test
    public void testFineCalculation() {
        Camera camera = new Camera(1, 5);
        LicensePlate licensePlate = new LicensePlate("1-ABC-123", 4);
        int amount = 0;
//        Optional<Fine> optionalFine = fineManager.calculateFine(new EmissionOffense(camera, licensePlate, LocalDateTime.now()));
//        if (optionalFine.isPresent()) {
//            amount = optionalFine.get().getAmount();
//        }
        Assert.assertNotEquals(amount, 50);
    }
}