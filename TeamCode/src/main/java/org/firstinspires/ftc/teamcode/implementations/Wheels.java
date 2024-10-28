package org.firstinspires.ftc.teamcode.implementations;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Wheels {

    //Motoare de la roti
    private DcMotor frontLeft, frontRight, backLeft,backRight;
    private Telemetry telemetry;
    /**
     * Initialises the motors attached to wheels
     * @param hardwareMap used to attach virtual motors to physical ones
     */
    public Wheels(HardwareMap hardwareMap,Telemetry telemetry){
        frontLeft = hardwareMap.get(DcMotor.class, "motor1");
        frontRight = hardwareMap.get(DcMotor.class, "motor2");
        backLeft = hardwareMap.get(DcMotor.class, "motor3");
        backRight = hardwareMap.get(DcMotor.class, "motor4");

        this.telemetry = telemetry;
    }

    /**
     * Sets a runmode to the wheels
     * @param runMode
     */
    public void setRunMode(DcMotor.RunMode runMode){
        frontLeft.setMode(runMode);
        frontRight.setMode(runMode);
        backLeft.setMode(runMode);
        backRight.setMode(runMode);
    }

    /**
     * Sets the same power to all motors
     * @param power
     */
    public void setPower(float power){
        frontLeft.setPower(power);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(power);
    }

    /**
     * Sets different power to each motor
     * @param fronLeftPower
     * @param frontRightPower
     * @param backLeftPower
     * @param backRightPower
     */
    public void setPower(float fronLeftPower,float frontRightPower,float backLeftPower, float backRightPower){
        frontLeft.setPower(fronLeftPower);
        frontRight.setPower(frontRightPower);
        backLeft.setPower(backLeftPower);
        backRight.setPower(backRightPower);
    }

    /**
     * Sets the target position for every motor
     * @param targetPosition
     */
    public void setTargetPosition(int targetPosition){
        frontLeft.setTargetPosition(targetPosition);
        frontRight.setTargetPosition(targetPosition);
        backLeft.setTargetPosition(targetPosition);
        backRight.setTargetPosition(targetPosition);
    }


    public void displayDirection(){
        telemetry.addLine("FrontLeftDirection" + frontLeft.getDirection().toString());
        telemetry.addLine("FrontRightDirection" + frontRight.getDirection().toString());
        telemetry.addLine("BackLeftDirection" + backLeft.getDirection().toString());
        telemetry.addLine("BackRightDirection" + backRight.getDirection().toString());

    }
    /**
     * Stops the program until each motor is free
     */
    public void waitMotors(){
        while(true){
            if(frontLeft.isBusy() || frontRight.isBusy() ||
            backLeft.isBusy() || backRight.isBusy()){
                break;
            }
        }
    }
}
