package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
@TeleOp(name="Servo")
public class Servotest extends OpMode{

    public CRServo servo0,servo1,servo2;
    public DcMotor motor0eh,motor1eh,motor2eh;
    @Override
    public void init(){
        motor0eh = hardwareMap.get(DcMotor.class, "motor0eh");
        motor1eh = hardwareMap.get(DcMotor.class, "motor1eh");
        motor2eh = hardwareMap.get(DcMotor.class, "motor2eh");
        servo0 = hardwareMap.get(CRServo.class , "Servo0");
        servo1 = hardwareMap.get(CRServo.class , "Servo1");
        servo2 = hardwareMap.get(CRServo.class , "Servo2");
    }
    @Override
    public void loop(){
        float Servo0y= gamepad2.left_stick_y;
        float Servo1x= gamepad2.right_stick_y;
        float Motor0ehx=gamepad2.left_stick_x;
        float Motor1ehx=gamepad2.right_stick_x;
        if(Math.abs(gamepad2.left_stick_y)>0.1) {
            servo0.setPower(gamepad2.left_stick_y);
        }
        if (Math.abs(gamepad2.left_stick_x)>0.1){
            motor0eh.setPower(gamepad2.left_stick_x);
            telemetry.addData("Motorul:", "nebun");
        }
        if(Math.abs(gamepad2.right_stick_y)>0.1){
            servo1.setPower(gamepad2.right_stick_y);
            servo2.setPower(gamepad2.right_stick_y);
        }
        if(Math.abs(gamepad2.right_stick_x)>0.1){
            motor1eh.setPower(gamepad2.right_stick_x);
            motor2eh.setPower(gamepad2.right_stick_x);
        }
        motor2eh.setPower(0);
        motor1eh.setPower(0);
        motor0eh.setPower(0);
        servo2.setPower(0);
        servo1.setPower(0);
        servo0.setPower(0);
    }

}
