package org.firstinspires.ftc.teamcode.Control;



import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name= "Saltare")
public class Codul_robotului extends OpMode {
    public DcMotor motor7, motor8;
    DcMotor motorbrat1,motorbrat2;
    public CRServo servo1, servo2,servo3;
    DcMotor motor1,motor2,motor3,motor4 ;
    public Servo servo0;
    public DcMotor motor0eh,motor1eh,motor2eh;
    double ticks=288.0;
    double newTarget;
 
    @Override
    public void init() {
        motorbrat1 = hardwareMap.get(DcMotor.class, "motorbrat1");
        motorbrat2 = hardwareMap.get(DcMotor.class, "motorbrat2");
        motorbrat1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorbrat2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        servo0 = hardwareMap.get(Servo.class , "Servo0");
        motor1 = hardwareMap.get(DcMotor.class, "motor1");

        motor1.setDirection(DcMotor.Direction.FORWARD);
        motor2 = hardwareMap.get(DcMotor.class,"motor2");
        motor2.setDirection(DcMotor.Direction.REVERSE);

        motor3 = hardwareMap.get(DcMotor.class, "motor3");
        motor3.setDirection(DcMotor.Direction.FORWARD);

        motor4 = hardwareMap.get(DcMotor.class,"motor4");
        motor4.setDirection(DcMotor.Direction.REVERSE);
    }
    @Override
    public void loop() {
        float x=-gamepad1.left_stick_x;
        float y=-gamepad1.left_stick_y;
        float i=gamepad1.right_stick_x;
        float j=gamepad1.right_stick_y;
        merge(x,y,i);


        float lx2 = gamepad2.left_stick_y;
        float rx2 = gamepad2.right_stick_x;
        float ry2 = gamepad2.right_stick_y;
        if (Math.abs(gamepad2.left_stick_y)>0.1){
            if (gamepad2.left_stick_y>0.1) {
                motorbrat1.setPower(0.4);
            }else{
                motorbrat1.setPower(-0.4);
            }

        }
        else{
            motorbrat1.setPower(-0.003);
            motorbrat1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }

        if (Math.abs(gamepad2.right_stick_y)>0.1){
            if(gamepad2.right_stick_y>0.1) {
                motorbrat2.setPower(0.3);
            }else{
                motorbrat2.setPower(-0.4);
            }
        }else{
            motorbrat2.setPower(0);
            motorbrat2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
        if (gamepad2.a) {
            servo0.setPosition(0.35);
        }
        if (gamepad2.b){
            servo0.setPosition(0);
        }


    }

    public void merge (float x, float y, float z){
        double frontLeftPower    =  x +y +z;
        double frontRightPower   =  x -y -z;
        double backLeftPower     =  x -y +z;
        double backRightPower    =  x +y -z;
        double max = Math.max(Math.max(Math.max(frontLeftPower,backLeftPower), backRightPower),frontRightPower);
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
}
