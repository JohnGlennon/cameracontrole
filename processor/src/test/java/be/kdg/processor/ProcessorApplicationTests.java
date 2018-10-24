package be.kdg.processor;

import be.kdg.sa.services.CameraServiceProxy;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProcessorApplicationTests {

    @Test
    public void testCameraService() throws IOException {
        CameraServiceProxy cameraServiceProxy = new CameraServiceProxy();
        String cameraInfo = cameraServiceProxy.get(1);
        Assert.assertNotNull(cameraInfo);
    }
}
