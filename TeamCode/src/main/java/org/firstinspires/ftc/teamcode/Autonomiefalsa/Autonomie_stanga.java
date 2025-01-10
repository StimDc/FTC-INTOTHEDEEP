package org.firstinspires.ftc.teamcode.Autonomiefalsa;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Autonomie_stanga")
public class Autonomie_stanga extends LinearOpMode {
    DcMotor motorbrat1,motorbrat2;
    DcMotor motor1,motor2,motor3,motor4 ;
    public Servo servo0;
    static final double ticks = 288.0;
    double newTarget;
    private ElapsedTime runtime = new ElapsedTime();
    double a=0.2, b=0.31, c=0.33, d=0.9, e=5, f=1.8, g=1, j=0.28, k=0.3, l=0.18, m=0.3;
    @Override
    public void runOpMode(){
        motor1 = hardwareMap.get(DcMotor.class, "motor1");
        motor2 = hardwareMap.get(DcMotor.class, "motor2");
        motor3 = hardwareMap.get(DcMotor.class, "motor3");
        motor4 = hardwareMap.get(DcMotor.class, "motor4");
        motorbrat1 = hardwareMap.get(DcMotor.class, "motorbrat1");
        motorbrat2 = hardwareMap.get(DcMotor.class, "motorbrat2");
        servo0 = hardwareMap.get(Servo.class, "Servo0");
        motor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor3.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor4.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorbrat1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorbrat2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorbrat1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorbrat2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        waitForStart();

        if (opModeIsActive()){
                pas0(a); ///Ridica brat
                pas1(b); ///Merge inainte
                pas2(c); ///Merge la dreapta
                pas1(d); ///Merge oleaca inainte
                pas3(e); ///Coboara brat
                pas5(f); ///Merge oleaca in spate
                pas4();  ///Deschide gheara
                pas5(f); ///Merge oleaca in spate
                pas0(a); ///Ridica brat
                pas6(g); ///Merge in spate
                pas7(j); ///Se invarte la dreapta 90 grade
                pas8(k); ///Merge in spate
                pas9(l); ///Merge la stanga
                pas1(m); ///Merge in fata
                /*pas3(n); ///Coboara brat */
        }
    }
    public void pas0(double turnage) {
        if (turnage != 0) {
            newTarget = ticks / turnage;
            motorbrat1.setTargetPosition((int)-newTarget);
            motorbrat1.setPower(0.6);
            motorbrat1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            sleep(2000);
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
            sleep(1000);
        }
    }
    public void pas2(double turnage) {
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

    public void pas3(double turnage) {
        if (turnage != 0) {
            newTarget = ticks / turnage;
            motorbrat1.setTargetPosition((int)newTarget);
            motorbrat1.setPower(0.4);
            motorbrat1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            sleep(1000);
        }
    }

    public void pas4(){
        servo0.setPosition(0);
        sleep(3000);
    }

    public void pas5(double turnage) {
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
            motor3.setTargetPosition((int) newTarget);
            motor3.setPower(0.4);
            motor3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor4.setTargetPosition((int) newTarget);
            motor4.setPower(0.4);
            motor4.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            sleep(2000);
        }
    }


    public void pas6(double turnage) {
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
            motor3.setTargetPosition((int) newTarget);
            motor3.setPower(0.4);
            motor3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor4.setTargetPosition((int) newTarget);
            motor4.setPower(0.4);
            motor4.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            sleep(1000);
        }
    }

    public void pas7(double turnage) {
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
            sleep(1000);
        }
    }
    public void pas8(double turnage) {
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
            motor3.setTargetPosition((int) newTarget);
            motor3.setPower(0.4);
            motor3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor4.setTargetPosition((int) newTarget);
            motor4.setPower(0.4);
            motor4.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            sleep(1000);
        }
    }
    public void pas9(double turnage) {
        if (turnage != 0) {
            motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            newTarget = ticks / turnage;
            motor1.setTargetPosition((int) newTarget);
            motor1.setPower(0.4);
            motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor2.setTargetPosition((int) -newTarget);
            motor2.setPower(0.4);
            motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor3.setTargetPosition((int) newTarget);
            motor3.setPower(0.4);
            motor3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor4.setTargetPosition((int) -newTarget);
            motor4.setPower(0.4);
            motor4.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            sleep(1000);
        }
    }
}
