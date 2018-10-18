package be.kdg.processor.services;

import be.kdg.processor.exceptions.FineException;
import be.kdg.processor.model.Fine;
import be.kdg.processor.model.Offense;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FineServiceTest {

    @Autowired
    private FineService fineService;

    @Test
    public void testFineService() throws FineException {
        Fine fine = new Fine(new Offense(), 50);
        fineService.save(fine);
        Fine loadedFine = fineService.load(fine.getId());
        Assert.assertEquals(fine, loadedFine);
    }
}