package org.firstinspires.ftc.teamcode.Autonomiefalsa;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Autonomie_High_Basket")
public class Autonomie_High_Basket extends LinearOpMode {
    DcMotor motorbrat1,motorbrat2;
    DcMotor motor1,motor2,motor3,motor4 ;
    public Servo servo0, servo1;
    static final double ticks = 288.0;
    double newTarget;
    private ElapsedTime runtime = new ElapsedTime();
    double a=0.17, b=0.35, c=0.18, f=0.65 ,d=0.5, e=0.238, g=0.15, j=0.24, k=0.1, l=0.83333, m=0.265, n=0.33, o=0.4;
    @Override
    public void runOpMode() {
        motor1 = hardwareMap.get(DcMotor.class, "motor1");
        motor2 = hardwareMap.get(DcMotor.class, "motor2");
        motor3 = hardwareMap.get(DcMotor.class, "motor3");
        motor4 = hardwareMap.get(DcMotor.class, "motor4");
        motorbrat1 = hardwareMap.get(DcMotor.class, "motorbrat1");
        motorbrat2 = hardwareMap.get(DcMotor.class, "motorbrat2");
        servo0 = hardwareMap.get(Servo.class, "Servo0");
        servo1 = hardwareMap.get(Servo.class,"Servo1");
        motor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor3.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor4.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorbrat1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorbrat2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorbrat1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorbrat2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        waitForStart();

        if (opModeIsActive()) {
            pas0(a);  ///Ridica brat
            pas1_5(d);///Merge in dreapta
            pas1(b);  ///Merge inainte
            pas2(c);  ///Extinde slider
            pas3(f);  ///Se invarte la stanga 38 grade
            pas3_5(g);///Coboara brat
            pas4();   ///Deschide gheara
            pas0(g);  ///Ridica brat
            pas5(c);  ///Revine slider
            pas6(e);  ///Se invarte la dreapta 105 grade
            pas1_5(k);///Merge in dreapta
            pas6(l);  ///Se invarte la dreapta 30 grade
            pas1(j);  ///Merge inainte
            pas6(m);  ///Se invarte 90 grade
            pas1(n);  ///Merge inainte
            pas2(o);  ///Extinde slider
            pas3_5(g);///Coboara brat
        }
    }
    public void pas0(double turnage) {
        if (turnage != 0) {
            newTarget = ticks / turnage;
            motorbrat1.setTargetPosition((int)-newTarget);
            motorbrat1.setPower(0.6);
            motorbrat1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            sleep(1000);
        }
    }

    public void pas1(double turnage) {
        if (turnage != 0) {
            motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            newTarget = ticks / turnage;

            motor1.setTargetPosition((int) newTarget);
            motor1.setPower(0.4);
            motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor2.setTargetPosition((int) newTarget);
            motor2.setPower(0.4);
            motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor3.setTargetPosition((int) -newTarget);
            motor3.setPower(0.4);
            motor3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor4.setTargetPosition((int) -newTarget);
            motor4.setPower(0.4);
            motor4.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            sleep(1500);
        }
    }

    public void pas1_5(double turnage) {
        if (turnage != 0) {
            motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            newTarget = ticks / turnage;
            motor1.setTargetPosition((int) -newTarget);
            motor1.setPower(0.4);
            motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor2.setTargetPosition((int) newTarget);
            motor2.setPower(0.4);
            motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor3.setTargetPosition((int) -newTarget);
            motor3.setPower(0.4);
            motor3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor4.setTargetPosition((int) newTarget);
            motor4.setPower(0.4);
            motor4.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            sleep(1000);
        }
    }

    public void pas2(double turnage){
        if(turnage !=0){
            newTarget = ticks / turnage;
            motorbrat2.setTargetPosition((int)-newTarget);
            motorbrat2.setPower(0.6);
            motorbrat2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            sleep(1000);
        }
    }
    public void pas3(double turnage) {
        if (turnage != 0) {
            motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            newTarget = ticks / turnage;
            motor1.setTargetPosition((int) -newTarget);
            motor1.setPower(0.4);
            motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor2.setTargetPosition((int) -newTarget);
            motor2.setPower(0.4);
            motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor3.setTargetPosition((int) -newTarget);
            motor3.setPower(-0.4);
            motor3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor4.setTargetPosition((int) -newTarget);
            motor4.setPower(-0.4);
            motor4.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            sleep(1000);
        }
    }

    public void pas3_5(double turnage){
        if (turnage != 0) {
            newTarget = ticks / turnage;
            motorbrat1.setTargetPosition((int) newTarget);
            motorbrat1.setPower(0.1);
            motorbrat1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            sleep(1000);
        }
    }

    public void pas4(){
       servo0.setPosition(0.6);
       servo1.setPosition(0.25);
       sleep(2000);
    }

    public void pas5(double turnage){
        if(turnage !=0){
            newTarget = ticks / turnage;
            motorbrat2.setTargetPosition((int)newTarget);
            motorbrat2.setPower(0.6);
            motorbrat2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            sleep(1000);
        }
    }

    public void pas6(double turnage) {
        if (turnage != 0) {
            motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            newTarget = ticks / turnage;
            motor1.setTargetPosition((int) newTarget);
            motor1.setPower(0.4);
            motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor2.setTargetPosition((int) newTarget);
            motor2.setPower(0.4);
            motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor3.setTargetPosition((int) newTarget);
            motor3.setPower(-0.4);
            motor3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor4.setTargetPosition((int) newTarget);
            motor4.setPower(-0.4);
            motor4.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            sleep(1500);
        }
    }


}


