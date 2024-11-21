package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;


@TeleOp(name= "Saltare")
public class Motor extends OpMode {
    public DcMotor motor7, motor8;
    public CRServo servo1, servo2,servo3;
 
    @Override
    public void init() {
        telemetry.addData("Initialization: ", "started");
        motor7 = hardwareMap.get(DcMotor.class , "motor7");
        motor8 = hardwareMap.get(DcMotor.class , "motor8");
        servo1 = hardwareMap.get(CRServo.class , "Servo1");
        servo2 = hardwareMap.get(CRServo.class , "Servo2");
        servo3 = hardwareMap.get(CRServo.class , "Servo3");
    }
    @Override
    public void loop(){
        float Servo1x= gamepad2.left_stick_x;
        float Servo2x= gamepad2.right_stick_x;
        float Servo3y= gamepad2.right_stick_y;
        while(gamepad2.a){
            motor7.setPower(0.8);
            motor8.setPower(0.8);
        }
        while(gamepad2.b){
            motor7.setPower(-0.8);
            motor8.setPower(-0.8);
        }
        if (Math.abs(gamepad2.left_stick_x)>0.1){
            servo1.setPower(Servo1x);
        }
        if (Math.abs(gamepad2.right_stick_x)>0.1){
            servo2.setPower(Servo2x);
        }
        if (Math.abs(gamepad2.right_stick_y)>0.1){
            servo3.setPower(Servo3y);
        }
        motor7.setPower(0);
        motor8.setPower(0);
        servo3.setPower(0);
        servo2.setPower(0);
        servo1.setPower(0);
    }

}
