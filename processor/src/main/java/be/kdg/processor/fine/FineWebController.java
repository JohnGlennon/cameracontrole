package be.kdg.processor.fine;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/fine")
public class FineWebController {

    private final FineService fineService;
    private final ModelMapper modelMapper;

    public FineWebController(FineService fineService, ModelMapper modelMapper) {
        this.fineService = fineService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/finefactors")
    public ModelAndView showOffenseFactors() {
        return new ModelAndView("factors", "ffDTO", new FineFactorDTO(fineService.getEmissionfactor(), fineService.getSpeedfactor()));
    }

//    @PutMapping("/fine.do")
//    public ModelAndView changeOffenseFactors() {
//
//    }

//    @GetMapping("/fine")
//    public ModelAndView showFineList(FineDTO fineDTO) {
//        return new ModelAndView("finelist", "fineDTO", fineDTO);
//    }
//
//    @PostMapping("newfine.do")
//    public ModelAndView createFine(@Valid @ModelAttribute FineDTO fineDTO) {
//        Fine savedFine = fineService.save(modelMapper.map(fineDTO, Fine.class));
//        return new ModelAndView("finesum", "fineDTO", modelMapper.map(savedFine, FineDTO.class));
//    }
}
