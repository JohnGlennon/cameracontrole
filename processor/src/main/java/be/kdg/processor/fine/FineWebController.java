package be.kdg.processor.fine;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/fine")
public class FineWebController {

    private final FineService fineService;

    public FineWebController(FineService fineService) {
        this.fineService = fineService;
    }

    @GetMapping("/finefactors")
    public ModelAndView showOffenseFactors() {
        return new ModelAndView("factors", "ffDTO", new FineFactorDTO(fineService.getEmissionfactor(), fineService.getSpeedfactor()));
    }
}
