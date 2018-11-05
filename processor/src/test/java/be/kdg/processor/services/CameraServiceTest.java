package be.kdg.processor.services;

import be.kdg.processor.camera.cameramodel.Camera;
import be.kdg.processor.message.JsonDeserializer;
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
public class CameraServiceTest {

    @Autowired
    private JsonDeserializer jsonDeserializer;

    @Test
    public void testCameraService() throws IOException {
        CameraServiceProxy cameraServiceProxy = new CameraServiceProxy();
        String cameraInfo = cameraServiceProxy.get(1);
        Camera camera = jsonDeserializer.toCamera(cameraInfo);
        Assert.assertNotNull(cameraInfo);
        Assert.assertEquals(camera.getCameraId(), 1);
    }
}
