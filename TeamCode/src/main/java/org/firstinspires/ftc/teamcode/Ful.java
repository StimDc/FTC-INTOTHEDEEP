package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.robotcore.internal.camera.delegating.DelegatingCaptureSequence;

@TeleOp(name= "Codful")
public class  Ful extends OpMode {

    //!!!trebuie tinut minte numele fiecarei componente
    //in functie de nume trebuie sa ii spunem codului ce este la fel ca mai jos
    DcMotor motor1,motor2,motor3,motor4 ;
    public CRServo servo0,servo1,servo2,servo3,servo4;
    public DcMotor motor0eh,motor1eh,motor2eh;

    @Override
    public void init() {
        motor0eh = hardwareMap.get(DcMotor.class, "motor0eh");
        motor1eh = hardwareMap.get(DcMotor.class, "motor1eh");
        motor2eh = hardwareMap.get(DcMotor.class, "motor2eh");
        servo0 = hardwareMap.get(CRServo.class , "Servo0");
        servo1 = hardwareMap.get(CRServo.class , "Servo1");
        servo2 = hardwareMap.get(CRServo.class , "Servo2");
        servo3 = hardwareMap.get(CRServo.class, "Servo3");
        servo4 = hardwareMap.get(CRServo.class, "Servo4");
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
            motor1.setPower(-x);
            motor2.setPower(x);
            motor3.setPower(-x);
            motor4.setPower(x);
            telemetry.addData("Daca este left stick x ", "robot se misca spre stanga si dreapta");
        }

        if (Math.abs(gamepad1.left_stick_y)>0.1)
        {//robotul merge inainte si inapoi
            motor1.setPower(-y);
            motor2.setPower(-y);
            motor3.setPower(y);
            motor4.setPower(y);
            telemetry.addData("Daca este left stick y ", "robot merge fata si spate");
        }

        if (Math.abs(gamepad1.right_stick_x)>0.1)
        {//robotul se invarte pe loc spre dreapta
            motor1.setPower(i);
            motor2.setPower(i);
            motor3.setPower(i);
            motor4.setPower(i);
            telemetry.addData("Daca este right stick x ", "robot se invarte stanga si dreapta");
        }

        motor1.setPower(0);
        motor2.setPower(0);
        motor3.setPower(0);
        motor4.setPower(0);

        //codul de sus pentru roti

        //codul de jos pentru brat

        float Servo0y= gamepad2.left_stick_y;
        float Servo1x= gamepad2.right_stick_y;
        float Motor0ehx=gamepad2.left_stick_x;
        float Motor1ehx=gamepad2.right_stick_x;
        float Servo3b=gamepad2.left_trigger;
        float Servo3a=gamepad2.right_trigger;
        float Servo4b=gamepad1.left_trigger;
        float Servo4a=gamepad1.right_trigger;
        boolean d=gamepad2.a;
        boolean e=gamepad2.b;

        if (Math.abs(gamepad2.left_stick_y)>0.1){
            motor0eh.setPower(gamepad2.left_stick_y);
        }
        if(Math.abs(gamepad2.right_stick_y)>0.1){
            motor1eh.setPower(gamepad2.right_stick_y);
            motor2eh.setPower(gamepad2.right_stick_y);
        }
        if (gamepad2.a)
            servo4.setPower(1);
        else
            servo4.setPower(0);
        /*if (gamepad2.b)
            servo4.setPower(-1);
        else
            servo4.setPower(0);*/
        if (gamepad2.x)
            servo3.setPower(1);
        else
            servo3.setPower(0);
        if (gamepad2.y)
            servo3.setPower(-1);
        else
            servo3.setPower(0);


        motor2eh.setPower(0);
        motor1eh.setPower(0);
        motor0eh.setPower(0);
        servo3.setPower(0);
        servo2.setPower(0);
        servo1.setPower(0);
        servo0.setPower(0);

    }
}