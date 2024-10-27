package org.firstinspires.ftc.teamcode.implementations;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Wheels {

    //Motoare de la roti
    private DcMotor frontLeft, frontRight, backLeft,backRight;

    /**
     * Initialises the motors attached to wheels
     * @param hardwareMap used to attach virtual motors to physical ones
     */
    public Wheels(HardwareMap hardwareMap){
        frontLeft = hardwareMap.get(DcMotor.class, "motor1");
        frontRight = hardwareMap.get(DcMotor.class, "motor2");
        backLeft = hardwareMap.get(DcMotor.class, "motor3");
        backRight = hardwareMap.get(DcMotor.class, "motor4");
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
