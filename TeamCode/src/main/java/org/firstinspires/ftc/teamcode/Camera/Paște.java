package org.firstinspires.ftc.teamcode.Camera;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.opencv.core.Core;
import org.opencv.videoio.VideoCapture;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;
import org.openftc.easyopencv.OpenCvWebcam;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org. openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;


@TeleOp(name="Camera_1", group = "Paste")
public class Paște extends LinearOpMode {
    private OpenCvWebcam webcam;//initializarea camerei
    @Override
    public void runOpMode() {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());//Pentru a vizualiza camera in Drive station
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam") , cameraMonitorViewId);//Creem obiectul webcam conectandu-l la camera definita in configuratia hardwerw "Webcam"
        webcam.setPipeline(new SamplePipeline());//legam camera la clasa SamplePipeline , care va procesa imaginea
        webcam.openCameraDevice();//deschide camera
        webcam.startStreaming(640, 480, OpenCvCameraRotation.UPRIGHT);//incepa transmisia cu rezolutia 640x480
        telemetry.addData("Status", "Camera initializata");//verificarea starea programului
        telemetry.update();
        waitForStart();//programul asteapta pana utilizatorul apasa start in Drive Station

        while (opModeIsActive()){
            telemetry.addData("Striming", "Camera activata");
            telemetry.update();
            sleep(100);
        }//cat timp OPModeisadctive codul afiseaza mesaje si mentine transmitera camarei
    }
    class SamplePipeline extends OpenCvPipeline{//clasa pentru procesarea imaginii
        @Override
        public Mat processFrame(Mat input)  {
            Imgproc.cvtColor(input, input, Imgproc.COLOR_RGB2GRAY);//convertire imaginea in alb-negru
            return input;
        }
    }
}
