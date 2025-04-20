package org.firstinspires.ftc.teamcode.Camera;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.opencv.core.Core;
import org.opencv.core.Scalar;
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
public class Recunostere_Culori extends LinearOpMode {
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
            Mat hsv = new Mat();
            Imgproc.cvtColor(input, hsv, Imgproc.COLOR_BGR2HSV);

            Scalar lowerRed = new Scalar(0, 100, 100);
            Scalar upperRed = new Scalar(10, 255, 255);

            Scalar lowerGreen = new Scalar(50, 100, 100);
            Scalar upperGreen = new Scalar(70, 255, 255);

            Scalar lowerBlue = new Scalar(110, 100, 100);
            Scalar upperBlue = new Scalar(130, 255, 255);

            Mat maskRed = new Mat();
            Mat maskGreen = new Mat();
            Mat maskBlue = new Mat();

            Core.inRange(hsv, lowerRed, upperRed, maskRed);
            Core.inRange(hsv, lowerGreen, upperGreen, maskGreen);
            Core.inRange(hsv, lowerBlue, upperBlue, maskBlue);

            int redPixels = Core.countNonZero(maskRed);
            int greenPixels = Core.countNonZero(maskGreen);
            int bluePixel = Core.countNonZero(maskBlue);

            String predominatntColor;
            if(redPixels > greenPixels && redPixels > bluePixel){
                predominatntColor = "rosu";
            }else if (greenPixels > redPixels && greenPixels > bluePixel){
                predominatntColor = "verde";
            }else{
                predominatntColor = "albastru";
            }

            telemetry.addData("Culoarte predominanta", predominatntColor);
            telemetry.update();

            Mat output = new Mat();
            Core.addWeighted(maskRed, 1.0, maskGreen, 1.0, 0.0, output);
            Core.addWeighted(output, 1.0, maskBlue, 1.0, 0.0, output);

            return  output;

        }
    }
}

