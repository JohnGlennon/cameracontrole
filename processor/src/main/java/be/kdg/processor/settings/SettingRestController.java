package be.kdg.processor.settings;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SettingRestController {

    private final SettingService settingService;
    private final ModelMapper modelMapper;

    public SettingRestController(SettingService settingService, ModelMapper modelMapper) {
        this.settingService = settingService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/createsetting")
    public ResponseEntity<SettingDTO> createSetting(@RequestBody SettingDTO settingDTO) {
        Setting settingIn = modelMapper.map(settingDTO, Setting.class);
        Setting settingOut = settingService.save(settingIn);
        return new ResponseEntity<>(modelMapper.map(settingOut, SettingDTO.class), HttpStatus.CREATED);
    }

    @GetMapping("/readsettings")
    public SettingDTO[] readSettings() {
        List<Setting> settings = settingService.getSettings();
        return modelMapper.map(settings, SettingDTO[].class);
    }
}
