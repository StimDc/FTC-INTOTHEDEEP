package org.firstinspires.ftc.teamcode.Control;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

import java.util.ArrayList;
import java.util.List;

@TeleOp(name= "Testare_ultima")
public class Testare_ultima extends OpMode {


    public DcMotor motor7, motor8;
    DcMotor motorbrat1, motorbrat2;
    public Servo servo1, servo2, servo3;
    DcMotor motor1, motor2, motor3, motor4;
    public Servo servo0;
    double putere_brat_sabilitate=-0.003;
    public DcMotor motor0eh, motor1eh, motor2eh;
    double ticks = 288.0;
    double newTarget;

    private VideoCapture camera;
    private Mat frame;
    double nimic=0;



    double unghi_incepere = 48.5;///trebuie data valoare manual
    double limita1 = 49,limita2 = 141;///trebuie data valoare manual/ vezi unde sta bratul fara sa cada si masoara unghiul
    double ticks_in_grade = 288/(360*0.25);///vezi gear ration dinti_roata1/dinti_roata2 complicat
    double lungime_slider_inchis = 40;
    double ultima_pozitie=0;
    double ultimul_unghi=48.5;
    double masa_slider=2;
    double putere_brat_stabilitate;
    double ticks_in_centimetri=12/288;
    boolean n = false;

    @Override
    public void init() {
        motorbrat1 = hardwareMap.get(DcMotor.class, "motorbrat1");
        motorbrat2 = hardwareMap.get(DcMotor.class, "motorbrat2");
        motorbrat1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorbrat1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorbrat2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorbrat2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);



        servo0 = hardwareMap.get(Servo.class, "Servo0");
        servo1 = hardwareMap.get(Servo.class, "Servo1");
        motor1 = hardwareMap.get(DcMotor.class, "motor1");

        motor1.setDirection(DcMotor.Direction.FORWARD);
        motor2 = hardwareMap.get(DcMotor.class, "motor2");
        motor2.setDirection(DcMotor.Direction.REVERSE);

        motor3 = hardwareMap.get(DcMotor.class, "motor3");
        motor3.setDirection(DcMotor.Direction.FORWARD);

        motor4 = hardwareMap.get(DcMotor.class, "motor4");
        motor4.setDirection(DcMotor.Direction.REVERSE);

        if(nimic!=0) {
            camera = new VideoCapture(0);
            if (!camera.isOpened()) {
                telemetry.addData("Camera Error", "Cannot open camera");
            }
        }
    }

    @Override
    public void loop() {
        double pozitie_actuala_mb2 = motorbrat2.getCurrentPosition();
        telemetry.addData("Brat2", pozitie_actuala_mb2);
        double pozitie_actuala_mb1 = motorbrat1.getCurrentPosition();
        telemetry.addData("Brat1  ", pozitie_actuala_mb1);


        // Actualizarea controlului robotului
        float x = -gamepad1.left_stick_x;
        float y = -gamepad1.left_stick_y;
        float i = gamepad1.right_stick_x;
        merge(x, y, i);

        float lx2 = gamepad2.left_stick_y;
        if (Math.abs(lx2) > 0.1) {
            motorbrat1.setPower(lx2 > 0.1 ? 0.4 : -0.4);
        } else {
            motorbrat1.setPower(putere_brat_sabilitate);
            motorbrat1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }

        float ry2 = gamepad2.right_stick_y;
        telemetry.addData("Camera Error", ry2);
        if(Math.abs(ry2) > 0.1) {
            if(ry2 >0.1){
                if(ry2>0.3) {
                    motorbrat2.setPower(0.3);
                } else{
                    motorbrat2.setPower(ry2);
                }
                pozitie_actuala_mb2 = motorbrat2.getCurrentPosition();
            } else if( pozitie_actuala_mb2>-1400 || pozitie_actuala_mb1<   -1600 ) {
                if (ry2<-0.3) {
                    motorbrat2.setPower(-0.3);
                }else{
                    motorbrat2.setPower(ry2);
                }
                pozitie_actuala_mb2 = motorbrat2.getCurrentPosition();
            } else{
                motorbrat2.setPower(0);
            }

        } else {
            motorbrat2.setPower(0);
            motorbrat2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }

        if (gamepad2.a) {
            servo0.setPosition(0.40);
            servo1.setPosition(0.45);
        }
        if (gamepad2.b) {
            servo0.setPosition(0.6);
            servo1.setPosition(0.25);
        }

        if(nimic!=0) {
            if (camera.isOpened()) {
                frame = new Mat();
                camera.read(frame);
                if (!frame.empty()) {
                    Mat processedFrame = processFrame(frame);
                    // Afișează frame-ul procesat în telemetry (numai ca exemplu)
                    telemetry.addData("Frame", processedFrame.toString());
                }
            }
        }
    }
    public void brat(){

    }

    public void merge(float x, float y, float z) {
        double frontLeftPower = x + y + z;
        double frontRightPower = x - y - z;
        double backLeftPower = x - y + z;
        double backRightPower = x + y - z;
        double max = Math.max(Math.max(Math.max(frontLeftPower, backLeftPower), backRightPower), frontRightPower);
        if (max > 1.0) {
            frontLeftPower /= max;
            frontRightPower /= max;
            backLeftPower /= max;
            backRightPower /= max;
        }

        motor1.setPower(frontLeftPower);
        motor2.setPower(frontRightPower);
        motor3.setPower(backLeftPower);
        motor4.setPower(backRightPower);
    }

    public Mat processFrame(Mat frame) {
        Mat hsv = new Mat();
        Imgproc.cvtColor(frame, hsv, Imgproc.COLOR_BGR2HSV);

        // Recunoașterea dreptunghiurilor de culori specifice
        detectAndDrawRectangles(hsv, frame, new Scalar(100, 150, 0), new Scalar(140, 255, 255), new Scalar(255, 0, 0)); // Albastru
        detectAndDrawRectangles(hsv, frame, new Scalar(0, 150, 150), new Scalar(10, 255, 255), new Scalar(0, 0, 255));  // Roșu
        detectAndDrawRectangles(hsv, frame, new Scalar(20, 100, 100), new Scalar(30, 255, 255), new Scalar(0, 255, 255)); // Galben

        return frame;
    }

    public void detectAndDrawRectangles(Mat hsv, Mat frame, Scalar lowerBound, Scalar upperBound, Scalar color) {
        Mat mask = new Mat();
        Core.inRange(hsv, lowerBound, upperBound, mask);

        Mat edges = new Mat();
        Imgproc.Canny(mask, edges, 50, 150);

        Mat hierarchy = new Mat();
        List<MatOfPoint> contours = new ArrayList<>();
        Imgproc.findContours(edges, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

        for (MatOfPoint contour : contours) {
            MatOfPoint2f contour2f = new MatOfPoint2f(contour.toArray());
            RotatedRect rect = Imgproc.minAreaRect(contour2f);
            Point[] points = new Point[4];
            rect.points(points);

            for (int i = 0; i < 4; i++) {
                Imgproc.line(frame, points[i], points[(i + 1) % 4], color, 2);
            }

            // Calcularea distanței (Exemplu simplu)
            double distance = calculateDistance(rect);
            Imgproc.putText(frame, String.format("Distanta: %.2f", distance), rect.center, Imgproc.FONT_HERSHEY_SIMPLEX, 0.5, color, 2);
        }
    }

    public double calculateDistance(RotatedRect rect) {
        // Folosește dimensiunea dreptunghiului pentru a calcula distanța (exemplu simplu)
        double focalLength = 700; // Ajustează această valoare în funcție de setarea camerei tale
        double realWidth = 20; // Lățimea reală a obiectului (în cm)
        double widthInPixels = rect.size.width;
        return (focalLength * realWidth) / widthInPixels;
    }

    public double brat_stabil(){
        float mb1 = gamepad2.left_stick_x;
        float mb2 = gamepad2.right_stick_y;
        double pozitie_actala_mb1 = motorbrat1.getCurrentPosition();
        double slider_pozitie_actuala = motorbrat2.getCurrentPosition();
        if (Math.abs(gamepad2.left_stick_x) > 0.1){
            if (pozitie_actala_mb1/ticks_in_grade > limita1 || pozitie_actala_mb1/ticks_in_grade < limita2 ) {
                if(brat_fix( slider_pozitie_actuala, ultima_pozitie)>mb1) {
                    if (n == false) {
                        n = true;
                        ultima_pozitie = motorbrat1.getCurrentPosition();///intra in if doar la inceputul miscarii pentru a memora pozitia principala
                    }
                    putere_brat_stabilitate=brat_fix( slider_pozitie_actuala, ultima_pozitie);
                }
            }else{
                putere_brat_stabilitate=brat_fix( slider_pozitie_actuala, ultima_pozitie);///tine bratul in pozitie ficsa
                n = false;
            }
        }else{

            putere_brat_stabilitate=brat_fix( slider_pozitie_actuala, ultima_pozitie);///tine bratul in pozitie ficsa
            n = false;
        }
        return putere_brat_stabilitate;
    }

    public double brat_fix( double slider_pozitie_actuala, double ultima_pozitie){
        double pozitie_modificata = motorbrat1.getCurrentPosition();
        double slider_pozitie_actuala_cm  =slider_pozitie_actuala*ticks_in_centimetri;
        double cateta = Math.sin(a(pozitie_modificata, ultima_pozitie))*slider_pozitie_actuala_cm;
        double forta_in_funcyie_de_cateta = (lungime_slider_inchis/cateta)*masa_slider;
        double suma_gaus_lungimi = (cateta*cateta+cateta)/2;
        double forta_exercitata_de_brat = forta_in_funcyie_de_cateta*suma_gaus_lungimi;
        return forta_exercitata_de_brat;

    }
    public double a(double pozitie_modificata, double ultima_pozitie){
        double b = (pozitie_modificata-ultima_pozitie)/ticks_in_grade;
        double a = ultimul_unghi+b;
        if (a>90){
            a=180-a;
        }
        ultimul_unghi=a;
        return a;
    }
}

