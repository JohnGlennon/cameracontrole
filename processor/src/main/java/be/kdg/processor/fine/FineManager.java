package be.kdg.processor.fine;

import be.kdg.processor.camera.Camera;
import be.kdg.processor.licenseplate.Car;
import be.kdg.processor.offense.Offense;
import be.kdg.processor.offense.OffenseType;
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

    private List<Offense> emissionOffenses;
    private List<Offense> speedOffenses;
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
        Offense newEmissionOffense = new Offense(car.getPlateId(), timestamp, OffenseType.EMISSION);
        int fineAmount = 0;
        int euroNorm = camera.getEuroNorm();
        int euroNumber = car.getEuroNumber();
        if (euroNumber < euroNorm) {
            LOGGER.info("Emission Offense! Your euro number is too low!");

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

    public void calculateSpeedFine(Camera camera, Car car, LocalDateTime timestamp) {
        if (camera.getSegment() != null) {
            Offense newSpeedOffense = new Offense(car.getPlateId(), timestamp, OffenseType.SPEED);
            int distance = camera.getSegment().getDistance();
            int speedLimit = camera.getSegment().getSpeedLimit();
            double time = 0;
            double speed;

            for (Offense oldSpeedOffense : speedOffenses) {
                if (oldSpeedOffense.getTimestamp().isBefore(LocalDateTime.now().minusMinutes(speedTimeframe))) {
                    speedOffenses.remove(oldSpeedOffense);
                }
            }

            for (Offense oldSpeedOffense : speedOffenses) {
                if (oldSpeedOffense.getLicensePlate().equals(newSpeedOffense.getLicensePlate())) {
                    Duration duration = Duration.between(oldSpeedOffense.getTimestamp(), newSpeedOffense.getTimestamp());
                    time = (double) duration.getSeconds() / 3600;
                }
            }

            speed = distance / time;

            Fine fine = new Fine(newSpeedOffense, (speed - speedLimit) * fineService.getSpeedfactor());

            if (speed > speedLimit) {
                LOGGER.info("Speed Offense! You were driving too fast!");
                fineService.save(fine);
            }

            speedOffenses.add(newSpeedOffense);
        }
    }
}
