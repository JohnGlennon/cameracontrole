package be.kdg.processor.controllers.web;

import be.kdg.processor.dto.FineDTO;
import be.kdg.processor.model.Fine;
import be.kdg.processor.services.FineService;
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

    private final FineService fineService;
    private final ModelMapper modelMapper;

    public FineWebController(FineService fineService, ModelMapper modelMapper) {
        this.fineService = fineService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/offensefactors.do")
    public ModelAndView showOffenseFactors() {
        ModelAndView mav = new ModelAndView("factors");
        mav.addObject(fineService.getEmissionfactor());
        mav.addObject(fineService.getSpeedfactor());
        return mav;
    }

    @GetMapping("/fine.do")
    public ModelAndView showFineList(FineDTO fineDTO) {
        return new ModelAndView("finelist", "fineDTO", fineDTO);
    }

    @PostMapping("newfine.do")
    public ModelAndView createFine(@Valid @ModelAttribute FineDTO fineDTO) {
        Fine savedFine = fineService.save(modelMapper.map(fineDTO, Fine.class));
        return new ModelAndView("finesum", "fineDTO", modelMapper.map(savedFine, FineDTO.class));
    }
}
