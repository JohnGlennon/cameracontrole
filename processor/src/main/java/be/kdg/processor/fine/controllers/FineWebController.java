package be.kdg.processor.fine.controllers;

import be.kdg.processor.settings.Settings;
import be.kdg.processor.settings.SettingsDTO;
import be.kdg.processor.settings.SettingsService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/fine")
public class FineWebController {

    private final SettingsService settingsService;
    private final ModelMapper modelMapper;

    public FineWebController(SettingsService settingsService, ModelMapper modelMapper) {
        this.settingsService = settingsService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/settings")
    public ModelAndView showOffenseFactors() {
        SettingsDTO settingsDTO = new SettingsDTO();
        settingsDTO.addSetting("emissionFactor", settingsService.getEmissionFactor());
        settingsDTO.addSetting("speedFactor", settingsService.getSpeedFactor());
        settingsDTO.addSetting("emissionTimeframe", settingsService.getEmissionTimeframe());
        settingsDTO.addSetting("speedTimeframe", settingsService.getSpeedTimeframe());
        return new ModelAndView("settings", "settingsDTO", settingsDTO);
    }

    @PostMapping("/setting.do")
    public ModelAndView changeFinefactors(@Valid @ModelAttribute SettingsDTO settingsDTO) {
        Settings settings = modelMapper.map(settingsDTO, Settings.class);
        settingsService.save(settings);
        return new ModelAndView("settings", "settingsDTO", settingsDTO);
    }
}
