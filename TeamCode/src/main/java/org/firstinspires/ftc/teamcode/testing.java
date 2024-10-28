package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;


@TeleOp(name= "testing")
public class testing extends OpMode {
    DcMotor motor7, motor8;
    double ticks=288;
    double newtarget;

    @Override
    public void init() {
        telemetry.addData("Initialization: ", "started");
        motor7 = hardwareMap.get(DcMotor.class , "motor7");
        motor8 = hardwareMap.get(DcMotor.class , "motor8");
        motor7.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor8.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor7.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor8 .setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }
    @Override
    public void loop(){
        if(gamepad1.a) {
            encoder(4);
        }
        if(gamepad1.b){
            tracker();
        }
        telemetry.addData("Motor ticks: ", motor7.getCurrentPosition());
        telemetry.addData("Motor ticks: ", motor8.getCurrentPosition());


    }

    public void encoder(int turnage) {
        motor7.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor8.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        newtarget=ticks/turnage;
        motor7.setTargetPosition((int)newtarget);
        motor8.setTargetPosition((int)newtarget);
        motor7.setPower(-0.3);
        motor8.setPower(-0.3);
        motor7.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor8.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while(motor7.isBusy() || motor8.isBusy());

    }

    public void tracker(){
        motor7.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor8.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor7.setTargetPosition((int)newtarget);
        motor8.setTargetPosition((int)newtarget);
        motor7.setPower(0.4);
        motor8.setPower(0.4);
        motor7.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor8.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while(motor7.isBusy() || motor8.isBusy());


    }

}

