package be.kdg.processor.message;

import be.kdg.processor.camera.CameraServiceAdapter;
import be.kdg.processor.camera.cameramodel.Camera;
import be.kdg.processor.fine.FineManager;
import be.kdg.processor.licenseplate.Car;
import be.kdg.processor.licenseplate.LicensePlateServiceAdapter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ObjectConverter {

    private final CameraServiceAdapter cameraServiceAdapter;
    private final LicensePlateServiceAdapter licensePlateServiceAdapter;
    private final FineManager fineManager;

    public ObjectConverter(CameraServiceAdapter cameraServiceAdapter, LicensePlateServiceAdapter licensePlateServiceAdapter, FineManager fineManager) {
        this.cameraServiceAdapter = cameraServiceAdapter;
        this.licensePlateServiceAdapter = licensePlateServiceAdapter;
        this.fineManager = fineManager;
    }

    public void convert(CameraMessage cameraMessage) {
        Optional<Camera> camera = cameraServiceAdapter.toCamera(cameraMessage.getId());
        Optional<Car> car = licensePlateServiceAdapter.toCar(cameraMessage.getLicensePlate());
        if (camera.isPresent() && car.isPresent()) {
            fineManager.calculateEmissionFine(cameraMessage, camera.get(), car.get());
            fineManager.calculateSpeedFine(cameraMessage, camera.get(), car.get());
        }
    }
}
