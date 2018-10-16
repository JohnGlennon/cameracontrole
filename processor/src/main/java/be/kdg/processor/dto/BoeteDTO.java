package be.kdg.processor.dto;

import be.kdg.processor.model.Overtreding;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoeteDTO {
    @NotEmpty
    private Overtreding overtreding;

    @NotEmpty
    private int bedrag;
}
