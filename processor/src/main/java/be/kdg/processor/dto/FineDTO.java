package be.kdg.processor.dto;

import be.kdg.processor.model.Offense;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FineDTO {
    @NotEmpty
    private Offense offense;

    @NotEmpty
    private int amount;
}
