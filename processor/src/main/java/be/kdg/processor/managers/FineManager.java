package be.kdg.processor.managers;

import be.kdg.processor.model.EmissionOffense;
import be.kdg.processor.model.Fine;
import be.kdg.processor.services.FineService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Component
public class FineManager {

    private List<EmissionOffense> emissionOffenses;
    private FineService fineService;

    @Value("${timeframe}")
    private int timeframe;

    @Value("${emissionfactor}")
    private int emissionfactor;

    @Value("${speedfactor}")
    private int speedfactor;

    public FineManager(FineService fineService) {
        emissionOffenses = new ArrayList<>();
        this.fineService = fineService;
    }

    public void calculateFine(EmissionOffense emissionOffense) {
        boolean inList = false;
        Fine fine = new Fine(emissionOffense, emissionfactor);

        for (EmissionOffense offense : emissionOffenses) {
            if (offense.getLicensePlate().getPlateId().equals(emissionOffense.getLicensePlate().getPlateId())) {
                inList = true;
                Duration duration = Duration.between(offense.getTimestamp(), emissionOffense.getTimestamp());
                long hours = duration.getSeconds() / 3600;
                if (hours > timeframe) {
                    fineService.save(fine);
                }
            }
        }

        if (!inList) {
            fineService.save(fine);
        }

        emissionOffenses.add(emissionOffense);
    }
}
