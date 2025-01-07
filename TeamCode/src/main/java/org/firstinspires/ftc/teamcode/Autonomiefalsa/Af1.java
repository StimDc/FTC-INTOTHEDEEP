package org.firstinspires.ftc.teamcode.Autonomiefalsa;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Af1", group = "Robot")
public class Af1 extends LinearOpMode {
    DcMotor motor1, motor2, motor3, motor4;
    DcMotor motor0eh, motor1eh, motor2eh;
    CRServo servo4;
    static final double ticks = 288.0;
    double newTarget;
    private ElapsedTime runtime = new ElapsedTime();
    double a=0.1, b=1, c=1, d=1, e=1, f=1, g=1;

    @Override
    public void runOpMode() {
        motor0eh = hardwareMap.get(DcMotor.class, "motor0eh");
        motor1eh = hardwareMap.get(DcMotor.class, "motor1eh");
        motor2eh = hardwareMap.get(DcMotor.class, "motor2eh");
        motor1 = hardwareMap.get(DcMotor.class, "motor1");
        motor2 = hardwareMap.get(DcMotor.class, "motor2");
        motor3 = hardwareMap.get(DcMotor.class, "motor3");
        motor4 = hardwareMap.get(DcMotor.class, "motor4");
        servo4 = hardwareMap.get(CRServo.class, "Servo4");
        motor0eh.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor1eh.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor2eh.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor3.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor4.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.a) {
                pas1(a);
                pas2(b);
                pas3(c, 5);
                pas4(d);
                pas5(e);
                pas6(f);
                pas7(g);
                pas8(e);
            }
        }
    }

    public void pas1(double turnage) {
        if (turnage != 0) {
            motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            newTarget = ticks / turnage;
            motor1.setTargetPosition((int) newTarget);
            motor1.setPower(0.8);
            motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor2.setTargetPosition((int) newTarget);
            motor2.setPower(-0.8);
            motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);   
            motor3.setTargetPosition((int) newTarget);
            motor3.setPower(0.8);
            motor3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor4.setTargetPosition((int) newTarget);
            motor4.setPower(-0.8);
            motor4.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            sleep(4000);
        }
    }

    public void pas2(double turnage) {
        if (turnage != 0) {
            motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            newTarget = ticks / turnage;
            motor1.setTargetPosition((int) newTarget);
            motor1.setPower(-0.8);
            motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor2.setTargetPosition((int) newTarget);
            motor2.setPower(0.8);
            motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor3.setTargetPosition((int) newTarget);
            motor3.setPower(-0.8);
            motor3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor4.setTargetPosition((int) newTarget);
            motor4.setPower(0.8);
            motor4.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            sleep(1000);
        }
    }

    public void pas3(double turnage, double timpi) {
        if (turnage != 0) {
            newTarget = ticks / turnage;
            motor0eh.setTargetPosition((int) newTarget);
            motor0eh.setPower(0.6);
            motor0eh.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            runtime.reset();
            while (runtime.seconds() < timpi) {
                servo4.setPower(1);
            }
            servo4.setPower(0);
            motor0eh.setTargetPosition(0);
            motor0eh.setPower(0.4);
            motor0eh.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            sleep(4000);
        }
    }

    public void pas4(double turnage) {
        if (turnage != 0) {
            motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            newTarget = ticks / turnage;
            motor1.setTargetPosition((int) newTarget);
            motor1.setPower(0.8);
            motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor2.setTargetPosition((int) newTarget);
            motor2.setPower(0.8);
            motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor3.setTargetPosition((int) newTarget);
            motor3.setPower(0.8);
            motor3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor4.setTargetPosition((int) newTarget);
            motor4.setPower(0.8);
            motor4.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            sleep(4000);
        }
    }

    public void pas5(double turnage) {
        if (turnage != 0) {
            motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            newTarget = ticks / turnage;
            motor1.setTargetPosition((int) newTarget);
            motor1.setPower(0.8);
            motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor2.setTargetPosition((int) newTarget);
            motor2.setPower(0.8);
            motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor3.setTargetPosition((int) newTarget);
            motor3.setPower(-0.8);
            motor3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor4.setTargetPosition((int) newTarget);
            motor4.setPower(-0.8);
            motor4.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            sleep(4000);
        }
    }

    public void pas6(double turnage) {
        if (turnage != 0) {
            motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            newTarget = ticks / turnage;
            motor1eh.setTargetPosition((int) newTarget);
            motor1eh.setPower(0.6);
            motor1eh.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor2eh.setTargetPosition((int) newTarget);
            motor2eh.setPower(0.6);
            motor2eh.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            sleep(4000);
        }
    }

    public void pas7(double turnage) {
        if (turnage != 0) {
            motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            newTarget = ticks / turnage;
            motor1eh.setTargetPosition((int) newTarget);
            motor1eh.setPower(0.6);
            motor1eh.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor2eh.setTargetPosition((int) newTarget);
            motor2eh.setPower(0.6);
            motor2eh.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        sleep(4000);
    }

    public void pas8(double turnage) {
        if (turnage != 0) {
            motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            newTarget = ticks / turnage;
            motor1.setTargetPosition((int) newTarget);
            motor1.setPower(-0.8);
            motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor2.setTargetPosition((int) newTarget);
            motor2.setPower(-0.8);
            motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor3.setTargetPosition((int) newTarget);
            motor3.setPower(0.8);
            motor3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor4.setTargetPosition((int) newTarget);
            sleep(4000);
        }
    }
}