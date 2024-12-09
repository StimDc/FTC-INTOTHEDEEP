package org.firstinspires.ftc.teamcode.autonomie;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Moments;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;
import org.openftc.easyopencv.OpenCvWebcam;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

@TeleOp(name="Camera")

public class samplePipeline1 extends OpenCvPipeline {

    private String output = "nu merege";
    private double cx = 0;
    private double cy = 0;
    private double width = 0;
    public static double distance = 0;
    public static final double objectWidthInRealWorldUnits = 3.75;
    public static final double focalLength = 728;

    public samplePipeline1() {

    }

    @Override
    public Mat processFrame(Mat input) {
        Mat hsv = new Mat();
        Imgproc.cvtColor(input, hsv, Imgproc.COLOR_BGR2HSV);
        Scalar lowerRed = new Scalar(0, 100, 100);
        Scalar upperRed = new Scalar(10, 255, 255);
        Mat mask = new Mat();
        Core.inRange(hsv, lowerRed, upperRed, mask);
        List<MatOfPoint> contours = new ArrayList<>();
        Mat hierarchy = new Mat();
        Imgproc.findContours(mask, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
        MatOfPoint largestContour = findLargestContour(contours);
        if (largestContour != null) {
            Moments moments = Imgproc.moments(largestContour);
            cx = moments.get_m10() / moments.get_m00();
            cy = moments.get_m01() / moments.get_m00();

            Imgproc.drawContours(input, contours, contours.indexOf(largestContour), new Scalar(0, 255, 0), 2);
            width = calculateWidth(largestContour);
            distance=getDistance(width);

            String widthLabel = "Width" + (int) width + "pixels";
            Imgproc.putText(input, widthLabel, new Point(cx + 10, cy + 20), Imgproc.FONT_HERSHEY_SCRIPT_SIMPLEX, 0.5, new Scalar(0, 255, 0), 2);

            String distanceLabel = "Distance: " + String.format("%.2f", getDistance(width)) + "inches";
            Imgproc.putText(input, distanceLabel, new Point(cx + 10, cy + 60), Imgproc.FONT_HERSHEY_SIMPLEX, 0.5, new Scalar(0, 255, 0), 2);

            String label = "(" + (int) cx + ", " + (int) cy + ")";
            Imgproc.putText(input, label, new Point(cx + 10, cy), Imgproc.FONT_HERSHEY_SIMPLEX, 0.5, new Scalar(0, 255, 0), 2);
            Imgproc.circle(input, new Point(cx, cy), 5, new Scalar(0, 255, 0), -1);
        }
        output = "Red color detected";
        return input;
    }

    private MatOfPoint findLargestContour(List<MatOfPoint> contours) {
        double maxArea = 0;
        MatOfPoint largestContour = null;
        for (MatOfPoint contour : contours) {
            double area = Imgproc.contourArea(contour);
            if (area > maxArea) {
                maxArea = area;
                largestContour = contour;
            }
        }
        return largestContour;
    }

    private double calculateWidth(MatOfPoint contour) {
        Rect boundingRect = Imgproc.boundingRect(contour);
        return boundingRect.width;
    }

    private double getDistance(double width) {
        return (objectWidthInRealWorldUnits * focalLength) / width;
    }


    public String getOutput() {
        return output;
    }
}




