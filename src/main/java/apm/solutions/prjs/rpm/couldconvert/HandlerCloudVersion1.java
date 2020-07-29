package apm.solutions.prjs.rpm.couldconvert;

import org.aioobe.cloudconvert.CloudConvertService;
import org.aioobe.cloudconvert.ConvertProcess;
import org.aioobe.cloudconvert.ProcessStatus;

import java.io.File;

public class HandlerCloudVersion1 {

    public HandlerCloudVersion1() {
        convertFiles();
    }

    public static void main(String[] args) {
        new HandlerCloudVersion1();
    }

    private void convertFiles() {

        CloudConvertService service = new CloudConvertService("");

// Create conversion process
        try {
            ConvertProcess process = service.startProcess("jpg", "png");

// Perform conversion
            process.startConversion(new File("C:/Users/Castulo/Downloads/Screenshot_20200724-144801.jpg"));

// Wait for result
            ProcessStatus status;
            waitLoop:
            while (true) {
                status = process.getStatus();

                switch (status.step) {
                    case FINISHED:
                        break waitLoop;
                    case ERROR:
                        throw new RuntimeException(status.message);
                }

                // Be gentle
                Thread.sleep(200);
            }

// Download result
            service.download(status.output.url, new File("C:/Users/Castulo/Downloads/output.png"));

// Clean up
            process.delete();
        } catch (Exception e) {


        }

    }
}
