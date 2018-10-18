package be.kdg.processor.managers;

import be.kdg.processor.model.EmissionOffense;
import be.kdg.processor.model.Fine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Component
public class FineManager {

    private List<EmissionOffense> emissionOffenses;
    private List<Fine> fines;

    @Value("${timeframe}")
    private int timeframe;

    public FineManager() {
        emissionOffenses = new ArrayList<>();
        fines = new ArrayList<>();
    }

    public void calculateFine(EmissionOffense emissionOffense) {
        boolean inList = false;
        Fine fine = new Fine(emissionOffense, 50);

        for (EmissionOffense offense : emissionOffenses) {
            if (offense.getLicensePlate().getPlateId().equals(emissionOffense.getLicensePlate().getPlateId())) {
                inList = true;
                Duration duration = Duration.between(offense.getTimestamp(), emissionOffense.getTimestamp());
                long hours = duration.getSeconds() / 3600;
                if (hours > timeframe) {
                    fines.add(fine);
                }
            }
        }

        if (!inList) {
            fines.add(fine);
        }

        emissionOffenses.add(emissionOffense);
    }
}
