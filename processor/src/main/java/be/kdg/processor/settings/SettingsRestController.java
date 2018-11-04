package be.kdg.processor.settings;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SettingsRestController {

    private final SettingsService settingsService;
    private final ModelMapper modelMapper;

    public SettingsRestController(SettingsService settingsService, ModelMapper modelMapper) {
        this.settingsService = settingsService;
        this.modelMapper = modelMapper;
    }
}
