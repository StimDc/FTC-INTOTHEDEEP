package org.firstinspires.ftc.teamcode.Control;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "PIDF")
public class PIDF  extends OpMode {
    double p,i,d;/* trebuie date valori manual*/
    double f;/* trebuie date valori manual*/
    int target=0;/* pozitia la care doresti sa ajunga bratul*/
    int unghi_incepere;/*unghiul la care este bratul fata de verticala in pozitia zero*/
    int limita1=unghi_incepere,limita2=90+unghi_incepere;/*unghiul la care programul nu este folosit*/

    double ticks_in_grade=288/360;
    DcMotorEx motorbrat1;

    @Override
    public void init(){


        motorbrat1=hardwareMap.get(DcMotorEx.class,"mb1");
        ///motorbrat1.setDirection(DcMotorSimple.Direction.REVERSE);  /*vezi in ce parte este pozitivul daca este in jos foloseste comanda asta*/

    }
    @Override
    public void loop(){
        int  pozitie_actuala=motorbrat1.getCurrentPosition();
        double viteza=Math.abs(target-pozitie_actuala);
        double ff=Math.sin(Math.toDegrees(target/ticks_in_grade))*f;
        double putere=viteza+ff;
        if (pozitie_actuala<limita2 || pozitie_actuala>limita1) {
            if (putere >= 0.9) {
                motorbrat1.setPower(0.9);
            } else {
                motorbrat1.setPower(putere);
            }
        }
    }
}
