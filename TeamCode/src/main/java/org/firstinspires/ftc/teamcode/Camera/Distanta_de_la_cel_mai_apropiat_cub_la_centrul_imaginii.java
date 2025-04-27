package org.firstinspires.ftc.teamcode.Camera;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.opencv.core.Core;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Rect;
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
import org.opencv.core.MatOfPoint;
import java.util.ArrayList;
import java.util.List;


@TeleOp(name="Camera_1", group = "Paste")
public class Distanta_de_la_cel_mai_apropiat_cub_la_centrul_imaginii extends LinearOpMode {
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
        final double KNOWN_WHIDTH = 5.0;//dimensiunuea reala a dreptunghiului(cm)
        final double FOCAL_LENGHT = 500.0;//lungimea focala a camerei (ajustabila)

        @Override
        public Mat processFrame(Mat input)  {
            Mat hsv = new Mat();//crearea unui obiect nou Mat numit hsv
            Imgproc.cvtColor(input, hsv, Imgproc.COLOR_BGR2HSV);//Convertim imaginea din formatul BGR in HSV, formatul HSV este cel mai bun in detectarea culorilor

            Scalar lowerRed = new Scalar(0, 100, 100);
            Scalar upperRed = new Scalar(10, 255, 255);//limitele pentru culoarea rosie

            Scalar lowerGreen = new Scalar(50, 100, 100);
            Scalar upperGreen = new Scalar(70, 255, 255);//limitele pentru culoarea verde

            Scalar lowerBlue = new Scalar(110, 100, 100);
            Scalar upperBlue = new Scalar(130, 255, 255);// limitele pentru culoarea albastru

            Mat maskRed = new Mat();
            Mat maskGreen = new Mat();
            Mat maskBlue = new Mat();// creearea mastilor pentru fiecare culoare. O masca contine doar culorile care se incadreaza inb intervalul unei anumitre culori

            Core.inRange(hsv, lowerRed, upperRed, maskRed);
            Core.inRange(hsv, lowerGreen, upperGreen, maskGreen);
            Core.inRange(hsv, lowerBlue, upperBlue, maskBlue);// filtyram culorile folosind Core.inRange(). Acesta contine doar pixelii care se potrivesc cu intevalele definite

            Mat combineMat = new Mat();
            Core.addWeighted(maskRed, 1.0, maskGreen, 1.0, 0.0, combineMat);
            Core.addWeighted(combineMat, 1.0, maskBlue, 1.0, 0.0, combineMat);//combina mastile pentru a obtine o singura imagine cu toate dreptunghiurile

            List<MatOfPoint> contours = new ArrayList<>();
            Mat hierarchy = new Mat();
            Imgproc.findContours(combineMat, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);//gasim contururile obiectelor colorate din imagine

            double closestDistance = Double.MAX_VALUE;//initializam variabila cu cea mai mare valoare posibila
            Rect closestRect = null;//variabila determina daca identifica vreun cub

            int imageCentreX=input.cols() / 2;//calculam mijlocul imaginii pe axa x

            for (MatOfPoint contour : contours){
                MatOfPoint2f contour2f = new MatOfPoint2f(contour.toArray());
                MatOfPoint2f approxCurve = new MatOfPoint2f();
                double epsilon = 0.002 * Imgproc.arcLength(contour2f, true);
                Imgproc.approxPolyDP(contour2f, approxCurve, epsilon, true);//convertim conturul intr-un format de tip MatOfPoint2f necesar pentru analiza formelor, simplificam forma, variabila epsilon contoleaza cat de exact va fi conturul aproximat

                if (approxCurve.total() == 4){
                    Rect rect = Imgproc.boundingRect(contour);
                    Imgproc.rectangle(input, rect, new Scalar(0,255,255), 2);//daca are patru puncte probabil este un dreptunghi sau unun cub creem un dreptunchi care incadreaza forma detectata

                    double distance = (KNOWN_WHIDTH * FOCAL_LENGHT) / rect.width;//formula de perspectiva aplicata pentru a calcula distanta

                    if(distance <closestDistance){
                        closestDistance = distance;//salvam cubul cel mai apropiat comparand distantele
                        closestRect = rect;
                    }
                }
            }

            if(closestRect != null){
                int centerXOfClosestRect = closestRect.x + (closestRect.width / 2);//calculam centrul dereptunghiului
                int distanceToCenter = Math.abs(centerXOfClosestRect - imageCentreX);//calculam distanta fata de mijlocul imaginii
                double pixelsPerCm = 10.0;//valoarea acestei variabile este in functie de calibrarea camerei
                double distanceToCenterCm = distanceToCenter / pixelsPerCm;//transforma distanta din pixeli in cm

                telemetry.addData("distanta centru centru", distanceToCenterCm);
                telemetry.addData("Distanta pana la cel mai apropiat cub", closestDistance + "cm");
                telemetry.update();
            }

            hierarchy.release(); //eliberam memoria memoria utilizata de hierarchy
            return  input;//returnam imaginea procesata ce contine cuburile evidentiate

        }
    }
}


