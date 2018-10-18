package be.kdg.processor.listeners;

import be.kdg.processor.managers.FineManager;
import be.kdg.processor.model.Camera;
import be.kdg.processor.model.EmissionOffense;
import be.kdg.processor.model.Fine;
import be.kdg.processor.model.LicensePlate;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmissionListenerTest {

    @Autowired
    private EmissionListener emissionListener;

    @Autowired
    private FineManager fineManager;

    @Test
    public void testEmissionDetection() {
        Camera camera = new Camera(5);
        LicensePlate licensePlate = new LicensePlate(4);
        boolean isOffense = emissionListener.listen(camera, licensePlate, LocalDateTime.now());
        Assert.assertTrue(isOffense);
    }

    @Test
    public void testFineCalculation() {
        Camera camera = new Camera(5);
        LicensePlate licensePlate = new LicensePlate(4);
        int amount = 0;
        Optional<Fine> optionalFine = fineManager.calculateFine(new EmissionOffense(camera, licensePlate, LocalDateTime.now()));
        if (optionalFine.isPresent()) {
            amount = optionalFine.get().getAmount();
        }
        Assert.assertNotEquals(amount, 0);
    }
}