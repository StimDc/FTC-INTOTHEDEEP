package org.firstinspires.ftc.teamcode.Control;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name= "Controlbun")
public class Controlbun extends OpMode {
    //!!!trebuie tinut minte numele fiecarei componente
    //in functie de nume trebuie sa ii spunem codului ce este la fel ca mai jos
    DcMotor motor1,motor2,motor3,motor4 ;
    public CRServo servo0,servo1,servo2,servo3,servo4;
    public DcMotor motor0eh,motor1eh,motor2eh;
    double ticks=288.0;
    double newTarget;

    @Override
    public void init() {
      //
        motor0eh = hardwareMap.get(DcMotor.class, "motor0eh");
        motor1eh = hardwareMap.get(DcMotor.class, "motor1eh");
        motor2eh = hardwareMap.get(DcMotor.class, "motor2eh");
        servo0 = hardwareMap.get(CRServo.class , "Servo0");
        servo1 = hardwareMap.get(CRServo.class , "Servo1");
        servo2 = hardwareMap.get(CRServo.class , "Servo2");
        servo3 = hardwareMap.get(CRServo.class, "Servo3");
        servo4 = hardwareMap.get(CRServo.class, "Servo4");
        motor1 = hardwareMap.get(DcMotor.class, "motor1");

        motor1.setDirection(DcMotor.Direction.FORWARD);
        motor2 = hardwareMap.get(DcMotor.class,"motor2");
        motor2.setDirection(DcMotor.Direction.REVERSE);

        motor3 = hardwareMap.get(DcMotor.class, "motor3");
        motor3.setDirection(DcMotor.Direction.FORWARD);

        motor4 = hardwareMap.get(DcMotor.class,"motor4");
        motor4.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addData("Hardwere:", "pornire");
        motor0eh.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor1eh.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor2eh.setMode(DcMotor.RunMode.RUN_USING_ENCODER );


    }

    @Override
    public void loop() {
        float x=-gamepad1.left_stick_x;
        float y=-gamepad1.left_stick_y;
        float i=gamepad1.right_stick_x;
        float j=gamepad1.right_stick_y;
        merge(x,y,i);
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
