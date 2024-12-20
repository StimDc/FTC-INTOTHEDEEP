package org.firstinspires.ftc.teamcode.Autonomiefalsa;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous (name = "Af2", group = "Robot")
public class Af2 extends LinearOpMode {
    DcMotor motor1, motor2, motor3, motor4;
    DcMotor motor0eh, motor1eh, motor2eh;
    CRServo servo4;
    static final double ticks = 288.0;
    double newTarget;
    double a=0.1;

    @Override
    public void runOpMode() {
        motor0eh = hardwareMap.get(DcMotor.class, "motor0eh");
        motor1eh = hardwareMap.get(DcMotor.class, "motor1eh");
        motor2eh = hardwareMap.get(DcMotor.class, "motor2eh");
        motor1 = hardwareMap.get(DcMotor.class, "motor1");
        motor2 = hardwareMap.get(DcMotor.class, "motor2");
        motor3 = hardwareMap.get(DcMotor.class, "motor3");
        motor4 = hardwareMap.get(DcMotor.class, "motor4");
        servo4 = hardwareMap.get(CRServo.class, "Servo4");
        motor0eh.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor1eh.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor2eh.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor3.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor4.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        while (opModeIsActive()) {
                pas1(a);

        }
    }
    public void pas1(double turnage){
        motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        newTarget = ticks / turnage;
        motor1.setTargetPosition((int) newTarget);
        motor1.setPower(-0.8);
        motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor2.setTargetPosition((int) newTarget);
        motor2.setPower(0.8);
        motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor3.setTargetPosition((int) newTarget);
        motor3.setPower(-0.8);
        motor3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor4.setTargetPosition((int) newTarget);
        motor4.setPower(0.8);
        motor4.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        sleep(1000);

    }
}
