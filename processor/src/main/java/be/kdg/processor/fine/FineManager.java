package be.kdg.processor.fine;

import be.kdg.processor.camera.cameramodel.Camera;
import be.kdg.processor.licenseplate.Car;
import be.kdg.processor.message.CameraMessage;
import be.kdg.processor.offense.Offense;
import be.kdg.processor.offense.OffenseType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Component
public class FineManager {

    private final static Logger LOGGER = LoggerFactory.getLogger(FineManager.class);

    private FineService fineService;

    private List<CameraMessage> cameraMessages;
    private List<Camera> cameras;
    private List<Offense> emissionOffenses;

    @Value("${emissionTimeframe}")
    private int emissionTimeframe;

    @Value("${speedTimeframe}")
    private int speedTimeframe;

    public FineManager(FineService fineService) {
        this.fineService = fineService;
        cameraMessages = new ArrayList<>();
        cameras = new ArrayList<>();
        emissionOffenses = new ArrayList<>();
    }

//    public boolean checkForEmissionFine(CameraMessage cameraMessage, Camera camera, Car car) {
//        int euro
//    }

    public int calculateEmissionFine(CameraMessage cameraMessage, Camera camera, Car car) {
        int fineAmount = 0;
        int euroNorm = camera.getEuroNorm();
        int euroNumber = car.getEuroNumber();
        if (euroNumber < euroNorm) {
            LOGGER.info("Emission Offense! Your euro number is too low!");
            Offense newEmissionOffense = new Offense(car.getPlateId(), cameraMessage.getTimestamp(), OffenseType.EMISSION);

            boolean inList = false;
            Fine fine = new Fine(newEmissionOffense, fineService.getEmissionfactor());

            for (Offense oldEmissionOffense : emissionOffenses) {
                if (oldEmissionOffense.getLicensePlate().equals(newEmissionOffense.getLicensePlate())) {
                    inList = true;
                    Duration duration = Duration.between(oldEmissionOffense.getTimestamp(), newEmissionOffense.getTimestamp());
                    long hours = duration.getSeconds() / 3600;
                    if (hours > emissionTimeframe) {
                        fineAmount += fineService.getEmissionfactor();
                        fineService.save(fine);
                    }
                }
            }

            if (!inList) {
                fineService.save(fine);
                fineAmount += fineService.getEmissionfactor();
            }

            emissionOffenses.add(newEmissionOffense);
        }
        return fineAmount;
    }

    public void calculateSpeedFine(CameraMessage newCameraMessage, Camera camera, Car car) {
        if (camera.getSegment() != null) {
            cameras.add(camera);
        }

        double time;
        double speed = 0;
        int speedLimit = 0;

        for (CameraMessage oldCameraMessage : cameraMessages) {
            if (oldCameraMessage.getTimestamp().isBefore(LocalDateTime.now().minusMinutes(speedTimeframe))) {
                cameraMessages.remove(oldCameraMessage);
            }
        }

        for (CameraMessage oldCameraMessage : cameraMessages) {
            if (oldCameraMessage.getLicensePlate().equals(newCameraMessage.getLicensePlate())) {
                for (Camera oldCamera : cameras) {
                    if (oldCamera.getSegment().getConnectedCameraId() == camera.getCameraId()) {
                        int distanceInMeter = oldCamera.getSegment().getDistance();
                        double distanceInKilometer = (double) distanceInMeter / 1000;
                        speedLimit = oldCamera.getSegment().getSpeedLimit();

                        long delay = ChronoUnit.SECONDS.between(oldCameraMessage.getTimestamp(), newCameraMessage.getTimestamp());
                        time = (double) delay / 3600;
                        speed = distanceInKilometer / time;
                    }
                }

            }
        }

        if (speed > speedLimit) {
            LOGGER.info("Speed Offense! You were driving too fast!");
            Offense newSpeedOffense = new Offense(car.getPlateId(), newCameraMessage.getTimestamp(), OffenseType.SPEED);
            Fine fine = new Fine(newSpeedOffense, (speed - speedLimit) * fineService.getSpeedfactor());
            fineService.save(fine);
        }

        cameraMessages.add(newCameraMessage);
    }
}
