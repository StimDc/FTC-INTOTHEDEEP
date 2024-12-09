package org.firstinspires.ftc.teamcode.Control;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name= "Test")
public class  test extends OpMode {

    //!!!trebuie tinut minte numele fiecarei componente
    //in functie de nume trebuie sa ii spunem codului ce este la fel ca mai jos
    DcMotor motor1,motor2,motor3,motor4 ;
    @Override
    public void init() {
        motor1 = hardwareMap.get(DcMotor.class, "motor1");
        motor2 = hardwareMap.get(DcMotor.class,"motor2");
        motor3 = hardwareMap.get(DcMotor.class, "motor3");
        motor4 = hardwareMap.get(DcMotor.class,"motor4");
        telemetry.addData("Hardwere:", "pornire");


    }

    @Override
    public void loop() {
        float x=gamepad1.left_stick_x;
        float y=gamepad1.left_stick_y;
        float i=gamepad1.right_stick_x;
        float j=gamepad1.right_stick_y;
        //1 ****** 2
        //**********   motoarele roților ordine
        //**********
        //3 ****** 4
        //motor.setPower i-a valori intre -1 si 1 unde cea mai mare este -1 cea mai mare viteza pe care o poate atinge motorul cu spatele invers la 1
        //deci avem două controlăre: gamepad1 și gamepad2
        if(Math.abs(gamepad1.left_stick_x)>0.1)
        {
            //robotul se mișcă spre dreapta si stanga
            motor2.setPower(-x);
            telemetry.addData("Daca este left stick x ", "robot se misca spre stanga si dreapta");
        }


        motor1.setPower(0);
        motor2.setPower(0);
        motor3.setPower(0);
        motor4.setPower(0);

    }
}