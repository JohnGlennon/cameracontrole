package be.kdg.processor.fine;

import be.kdg.processor.camera.Camera;
import be.kdg.processor.licenseplate.Car;
import be.kdg.processor.offense.EmissionOffense;
import be.kdg.processor.offense.SpeedOffense;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class FineManager {

    private final static Logger LOGGER = LoggerFactory.getLogger(FineManager.class);

    private List<EmissionOffense> emissionOffenses;
    private List<SpeedOffense> speedOffenses;
    private FineService fineService;

    @Value("${emissionTimeframe}")
    private int emissionTimeframe;

    @Value("${speedTimeframe}")
    private int speedTimeframe;

    public FineManager(FineService fineService) {
        emissionOffenses = new ArrayList<>();
        speedOffenses = new ArrayList<>();
        this.fineService = fineService;
    }

    public int calculateEmissionFine(Camera camera, Car car, LocalDateTime timestamp) {
        EmissionOffense newEmissionOffense = new EmissionOffense(car.getPlateId(), timestamp);
        int fineAmount = 0;
        int euroNorm = camera.getEuroNorm();
        int euroNumber = car.getEuroNumber();
        if (euroNumber < euroNorm) {
            LOGGER.info("Emission Offense! Your euro number is too low!");

            boolean inList = false;
            Fine fine = new Fine(newEmissionOffense, fineService.getEmissionfactor());

            for (EmissionOffense oldEmissionOffense : emissionOffenses) {
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

    public void calculateSpeedFine(Camera camera, Car car, LocalDateTime timestamp) {
        if (camera.getSegment() != null) {
            SpeedOffense newSpeedOffense = new SpeedOffense(car.getPlateId(), timestamp);
            int distance = camera.getSegment().getDistance();
            double time = 0;
            double speed;
            int speedLimit = camera.getSegment().getSpeedLimit();
            Fine fine = new Fine(newSpeedOffense, fineService.getSpeedfactor());

            for (SpeedOffense oldSpeedOffense : speedOffenses) {
                if (oldSpeedOffense.getTimestamp().isBefore(LocalDateTime.now().minusMinutes(speedTimeframe))) {
                    speedOffenses.remove(oldSpeedOffense);
                }
            }

            for (SpeedOffense oldSpeedOffense : speedOffenses) {
                if (oldSpeedOffense.getLicensePlate().equals(newSpeedOffense.getLicensePlate())) {
                    Duration duration = Duration.between(oldSpeedOffense.getTimestamp(), newSpeedOffense.getTimestamp());
                    time = (double) duration.getSeconds() / 3600;
                }
            }

            speed = distance / time;

            if (speed > speedLimit) {
                LOGGER.info("Speed Offense! You were driving too fast!");
                speedOffenses.add(newSpeedOffense);
                fineService.save(fine);
            }
        }
    }
}
