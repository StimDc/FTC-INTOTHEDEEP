package org.firstinspires.ftc.teamcode.Control;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior;


@TeleOp(name= "Saltare")
public class Motor extends OpMode {
    public DcMotor motor7, motor8;
    DcMotor motorbrat1,motorbrat2;
    public CRServo servo1, servo2,servo3;
 
    @Override
    public void init() {
        motorbrat1 = hardwareMap.get(DcMotor.class, "motorbrat1");
        motorbrat2 = hardwareMap.get(DcMotor.class, "motorbrat2");
        motorbrat1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorbrat2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    @Override
    public void loop() {

        float Servo1x = gamepad2.left_stick_y;
        float Servo2x = gamepad2.right_stick_x;
        float Servo3y = gamepad2.right_stick_y;
        if (Math.abs(gamepad2.left_stick_y)>0.1){
            motorbrat1.setPower(0.4);

        }
        else{
            motorbrat1.setPower(-0.009);
            motorbrat1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }


        if (Math.abs(gamepad2.right_stick_y)>0.1){
            motorbrat2.setPower(Servo3y);
        }else{
            motorbrat2.setPower(0);
        }



    }
}
