package be.kdg.processor.managers;

import be.kdg.processor.model.Boete;
import be.kdg.processor.model.EmissieOvertreding;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Component
public class BoeteManager {

    private List<EmissieOvertreding> emissieOvertredingen;
    private List<Boete> boetes;

    @Value("${tijdsframe}")
    private int tijdsframe;

    public BoeteManager() {
        emissieOvertredingen = new ArrayList<>();
        boetes = new ArrayList<>();
    }

    public void berekenBoete(EmissieOvertreding emissieOvertreding) {
        boolean inLijst = false;
        Boete boete = new Boete(emissieOvertreding, 500);

        for (EmissieOvertreding overtreding : emissieOvertredingen) {
            if (overtreding.getLicensePlate().getPlateId().equals(emissieOvertreding.getLicensePlate().getPlateId())) {
                inLijst = true;
                Duration duration = Duration.between(overtreding.getTimestamp(), emissieOvertreding.getTimestamp());
                long aantalUren = duration.getSeconds() / 3600;
                if (aantalUren > tijdsframe) {
                    boetes.add(boete);
                }
            }
        }

        if (!inLijst) {
            boetes.add(boete);
        }

        emissieOvertredingen.add(emissieOvertreding);
    }
}
