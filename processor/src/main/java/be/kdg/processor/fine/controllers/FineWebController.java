package be.kdg.processor.fine.controllers;

import be.kdg.processor.fine.dto.FineFactorDTO;
import be.kdg.processor.fine.dto.TimeframeDTO;
import be.kdg.processor.settings.Setting;
import be.kdg.processor.settings.SettingDTO;
import be.kdg.processor.settings.SettingService;
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

    private final SettingService settingService;
    private final ModelMapper modelMapper;

    public FineWebController(SettingService settingService, ModelMapper modelMapper) {
        this.settingService = settingService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/finefactors")
    public ModelAndView showOffenseFactors() {
        return new ModelAndView("factors", "ffDTO", new FineFactorDTO(settingService.getEmissionFactor(), settingService.getSpeedFactor()));
    }

    @PostMapping("/finefactor.do")
    public ModelAndView changeFinefactors(@Valid @ModelAttribute SettingDTO settingDTO) {
        Setting setting = modelMapper.map(settingDTO, Setting.class);
        settingService.save(setting);
        return new ModelAndView("factors", "ffDTO", new FineFactorDTO(settingService.getEmissionFactor(), settingService.getSpeedFactor()));
    }

    @GetMapping("/timeframes")
    public ModelAndView showTimeframes() {
        return new ModelAndView("timeframes", "tfDTO", new TimeframeDTO(settingService.getEmissionTimeframe(), settingService.getSpeedTimeframe()));
    }
}
