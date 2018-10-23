package be.kdg.processor.offense;

import be.kdg.processor.camera.Camera;
import be.kdg.processor.fine.FineManager;
import be.kdg.processor.licenseplate.Car;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class EmissionListener implements OffenseListener {

    private FineManager fineManager;

    public EmissionListener(FineManager fineManager) {
        this.fineManager = fineManager;
    }

    @Override
    public void listen(Camera camera, Car car, LocalDateTime timestamp) {
        fineManager.calculateEmissionFine(camera, car, timestamp);
    }
}
