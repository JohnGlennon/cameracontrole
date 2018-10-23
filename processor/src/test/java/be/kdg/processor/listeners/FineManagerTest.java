package be.kdg.processor.listeners;

import be.kdg.processor.fine.FineManager;
import be.kdg.processor.camera.Camera;
import be.kdg.processor.fine.FineService;
import be.kdg.processor.licenseplate.Car;
import be.kdg.processor.offense.EmissionOffense;
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
        Camera camera = new Camera(1, 5);
        Car car = new Car("1-ABC-123", 4);
        int offenseAmount = fineManager.calculateEmissionFine(camera, car, LocalDateTime.now());
        Assert.assertEquals(offenseAmount, fineService.getEmissionfactor());
    }
}