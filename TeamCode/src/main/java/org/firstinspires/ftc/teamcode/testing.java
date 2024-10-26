package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;


@TeleOp(name= "testing")
public class testing extends OpMode {
    DcMotor motor1, motor2;
    double ticks=288;
    double newtarget;

    @Override
    public void init() {
        telemetry.addData("Initialization: ", "started");
        motor1 = hardwareMap.get(DcMotor.class , "motor1");
        motor2 = hardwareMap.get(DcMotor.class , "motor2");
        motor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }
    @Override
    public void loop(){
        if(gamepad1.a) {
            encoder(4);
        }
        if(gamepad1.b){
            tracker();
        }
        telemetry.addData("Motor ticks: ", motor1.getCurrentPosition());
        telemetry.addData("Motor ticks: ", motor2.getCurrentPosition());


    }

    public void encoder(int turnage) {
        motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        newtarget=ticks/turnage;
        motor1.setTargetPosition((int)newtarget);
        motor2.setTargetPosition((int)newtarget);
        motor1.setPower(0.3);
        motor2.setPower(0.3);
        motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while(motor1.isBusy() || motor2.isBusy());

    }

    public void tracker(){
        motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor1.setTargetPosition((int)newtarget);
        motor2.setTargetPosition((int)newtarget);
        motor1.setPower(-0.4);
        motor2.setPower(-0.4);
        motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while(motor1.isBusy() || motor2.isBusy());
        motor1.setPower(0);
        motor2.setPower(0);

    }

}

