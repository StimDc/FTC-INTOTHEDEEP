package org.firstinspires.ftc.teamcode.Control;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;

@TeleOp(name= "Test23")
public class  Test23 extends OpMode {

    //!!!trebuie tinut minte numele fiecarei componente
    //in functie de nume trebuie sa ii spunem codului ce este la fel ca mai jos
    DcMotor motor1,motor2,motor3,motor4 ;
    double ticks=288.0;
    double newTarget;
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
        motor0eh.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor1eh.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor2eh.setMode(DcMotor.RunMode.RUN_USING_ENCODER );


    }

    @Override
    public void loop() {
        float x=gamepad1.left_stick_x;
        float y=gamepad1.left_stick_y;
        float i=gamepad1.right_stick_x;
        float j=gamepad1.right_stick_y;
        if (gamepad1.a){
            encoder1(-3);
        }
        if (gamepad1.b){
            tracker1();
        }
        if(gamepad1.y){
            encoder2(-2);
        }
        if(gamepad1.x){
            tracker2();
        }
        if (Math.abs(y)>0.1){
            motor0eh.setPower(y);
        }
        else
            motor0eh.setPower(0);
    }

    public void encoder1(double turnage){
        newTarget=ticks/turnage;
        motor0eh.setTargetPosition((int)newTarget);
        motor0eh.setPower(0.6);
        motor0eh.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public void tracker1(){
        motor0eh.setTargetPosition(0);
        motor0eh.setPower(0.3);
        motor0eh.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public void encoder2(double turnage){
        newTarget=ticks/turnage;
        motor1eh.setTargetPosition((int)newTarget);
        motor1eh.setPower(0.6);
        motor1eh.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor2eh.setTargetPosition((int)newTarget);
        motor2eh.setPower(0.6);
        motor2eh.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public void tracker2(){
        motor1eh.setTargetPosition(0);
        motor1eh.setPower(0.3);
        motor1eh.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor2eh.setTargetPosition(0);
        motor2eh.setPower(0.3);
        motor2eh.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

}