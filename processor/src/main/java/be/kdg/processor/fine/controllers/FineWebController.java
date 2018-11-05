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
    public ModelAndView showSettings() {
        Settings settings = settingsService.getSettings();
        SettingsDTO settingsDTO = modelMapper.map(settings, SettingsDTO.class);
        return new ModelAndView("settings", "settingsDTO", settingsDTO);
    }

    @PostMapping("/setting.do")
    public ModelAndView changeSettings(@Valid @ModelAttribute SettingsDTO settingsDTO) {
        Settings newSettings = modelMapper.map(settingsDTO, Settings.class);
        settingsService.updateSettings(newSettings);
        return new ModelAndView("home");
    }
}
