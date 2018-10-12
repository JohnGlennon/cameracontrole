package be.kdg.simulator.generators;

import be.kdg.simulator.model.CameraMessage;

import java.util.Optional;

public interface MessageGenerator {
    Optional<CameraMessage> generate();
}
