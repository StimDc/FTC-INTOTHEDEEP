package org.firstinspires.ftc.teamcode.autonomie;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.autonomie.samplePipeline1;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;

public class Camera {
    private OpenCvWebcam webcam;
    private HardwareMap hardwareMap;
    private samplePipeline1 p1;
    /*
    private samplepipeline2 p2;
     */

    public Camera(HardwareMap hw) {
        p1 = new samplePipeline1();


        this.hardwareMap = hw;
        int cameraMonitorViewId =
                hardwareMap
                        .appContext
                        .getResources()
                        .getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam =
                OpenCvCameraFactory.getInstance()
                        .createWebcam(hardwareMap.get(WebcamName.class, "camera"), cameraMonitorViewId);
        webcam.setPipeline(p1);
        webcam.setMillisecondsPermissionTimeout(2500);

        webcam.openCameraDeviceAsync(
                new OpenCvCamera.AsyncCameraOpenListener() {
                    @Override
                    public void onOpened() {
                        webcam.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);
                    }

                    @Override
                    public void onError(int errorCode) {
                    }
                });
    }

    public void switchToFirstPipeline() {
        webcam.setPipeline(p1);
    }

    public String getPipeline1Output() {
        return p1.getOutput();
    }

    public double getPipeline1Distance(){
        return samplePipeline1.distance;
    }

    public void stop() {
        webcam.stopStreaming();
    }
}

