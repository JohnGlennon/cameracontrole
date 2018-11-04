package be.kdg.processor.fine.controllers;

import be.kdg.processor.fine.dto.FineFactorDTO;
import be.kdg.processor.fine.dto.TimeframeDTO;
import be.kdg.processor.settings.SettingService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/fine")
public class FineWebController {

    private final SettingService settingService;

    public FineWebController(SettingService settingService) {
        this.settingService = settingService;
    }

    @GetMapping("/finefactors")
    public ModelAndView showOffenseFactors() {
        return new ModelAndView("factors", "ffDTO", new FineFactorDTO(settingService.getEmissionFactor(), settingService.getSpeedFactor()));
    }

    @GetMapping("/timeframes")
    public ModelAndView showTimeframes() {
        return new ModelAndView("timeframes", "tfDTO", new TimeframeDTO(settingService.getEmissionTimeframe(), settingService.getSpeedTimeframe()));
    }
}
