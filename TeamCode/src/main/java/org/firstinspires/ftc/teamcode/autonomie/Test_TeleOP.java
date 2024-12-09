package org.firstinspires.ftc.teamcode.autonomie;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

    @TeleOp(name="Sample_TeleOP", group="robot")
    public class Test_TeleOP extends LinearOpMode {
        Camera camera=new Camera(hardwareMap);
        @Override
        public void runOpMode() throws InterruptedException {
            camera.switchToFirstPipeline();
            telemetry.addLine("Status Initialized");
            waitForStart();

            while (opModeIsActive()) {
                telemetry.addLine(camera.getPipeline1Output());
                telemetry.addData("Distance", camera.getPipeline1Distance());
                telemetry.update();
            }
        }
    }

