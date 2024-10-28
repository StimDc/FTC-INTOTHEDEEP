package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

public class testservo extends OpMode {
    public Servo servo;
    @Override
    public void init()
    {
        servo= hardwareMap.get(Servo.class, "servo");
    }
    @Override
    public void loop()
    {

    }

}
