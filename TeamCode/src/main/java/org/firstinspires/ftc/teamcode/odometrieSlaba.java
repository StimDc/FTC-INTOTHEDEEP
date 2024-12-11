package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "Odometry Autonomous", group = "Linear Opmode")
public class odometrieSlaba extends LinearOpMode {

    private DcMotor leftEncoder, rightEncoder, horizontalEncoder;

    // Constants
    private static final double TICKS_PER_REV = 8192; // Number of ticks per revolution for the encoders
    private static final double WHEEL_DIAMETER = 4; // Diameter of the tracking wheel
    private static final double TICKS_PER_INCH = TICKS_PER_REV / (Math.PI * WHEEL_DIAMETER);
    private static final double TRACK_WIDTH = 16; // Distance between left and right tracking wheels

    // Robot coordinates
    private double x = 0, y = 0, theta = 0;

    // Last encoder positions
    private double lastLeftPos = 0, lastRightPos = 0, lastHorizontalPos = 0;

    @Override
    public void runOpMode() {
        leftEncoder = hardwareMap.get(DcMotor.class, "leftEncoder");
        rightEncoder = hardwareMap.get(DcMotor.class, "rightEncoder");
        horizontalEncoder = hardwareMap.get(DcMotor.class, "horizontalEncoder");

        leftEncoder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightEncoder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        horizontalEncoder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftEncoder.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightEncoder.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        horizontalEncoder.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        waitForStart();

        while (opModeIsActive()) {
            updateOdometry();

            // Example movement: Move forward 24 inches
            moveToPosition(24, 0, 0.5);
            // Add more movements as needed...

            telemetry.addData("X", x);
            telemetry.addData("Y", y);
            telemetry.addData("Theta", theta);
            telemetry.update();
        }
    }

    private void updateOdometry() {
        double leftPos = leftEncoder.getCurrentPosition() / TICKS_PER_INCH;
        double rightPos = rightEncoder.getCurrentPosition() / TICKS_PER_INCH;
        double horizontalPos = horizontalEncoder.getCurrentPosition() / TICKS_PER_INCH;

        double deltaLeft = leftPos - lastLeftPos;
        double deltaRight = rightPos - lastRightPos;
        double deltaHorizontal = horizontalPos - lastHorizontalPos;

        // Update robot's position
        double deltaTheta = (deltaLeft - deltaRight) / (2 * TRACK_WIDTH);
        theta += deltaTheta;

        double deltaX = (deltaLeft + deltaRight) / 2.0;
        double deltaY = deltaHorizontal;

        x += deltaX * Math.cos(theta) - deltaY * Math.sin(theta);
        y += deltaX * Math.sin(theta) + deltaY * Math.cos(theta);

        lastLeftPos = leftPos;
        lastRightPos = rightPos;
        lastHorizontalPos = horizontalPos;
    }

    private void moveToPosition(double targetX, double targetY, double speed) {
        double distanceToTarget = Math.hypot(targetX - x, targetY - y);
        double targetTheta = Math.atan2(targetY - y, targetX - x);

        while (opModeIsActive() && distanceToTarget > 1) { // 1 inch tolerance
            double errorTheta = targetTheta - theta;

            double moveX = Math.cos(errorTheta) * speed;
            double moveY = Math.sin(errorTheta) * speed;

            // Set motor powers based on calculated movements
            // Assuming you have motors named 'leftMotor', 'rightMotor', etc.
            hardwareMap.get(DcMotor.class, "leftMotor").setPower(moveY + moveX);
            hardwareMap.get(DcMotor.class, "rightMotor").setPower(moveY - moveX);
            hardwareMap.get(DcMotor.class, "horizontalMotor").setPower(moveY);

            updateOdometry();
            distanceToTarget = Math.hypot(targetX - x, targetY - y);
            telemetry.addData("Distance to Target", distanceToTarget);
            telemetry.update();
        }

        // Stop the motors
        hardwareMap.get(DcMotor.class, "leftMotor").setPower(0);
        hardwareMap.get(DcMotor.class, "rightMotor").setPower(0);
        hardwareMap.get(DcMotor.class, "horizontalMotor").setPower(0);
    }
}
