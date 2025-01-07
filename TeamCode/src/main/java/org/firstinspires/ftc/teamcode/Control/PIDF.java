package org.firstinspires.ftc.teamcode.Control;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "PIDF")
public class PIDF  extends OpMode {
    double p,i,d;/* trebuie date valori manual*/
    double f;/* trebuie date valori manual*/
    double target=0;/* pozitia la care doresti sa ajunga bratul*/
    int unghi_incepere;/*unghiul la care este bratul fata de verticala in pozitia zero*/
    int limita1=unghi_incepere,limita2=90+unghi_incepere;/*unghiul la care programul nu este folosit*/
    public static int buton_joystick;
    double ticks_in_grade=288/360;
    DcMotorEx motorbrat1;

    @Override
    public void init(){


        motorbrat1=hardwareMap.get(DcMotorEx.class,"mb1");
        ///motorbrat1.setDirection(DcMotorSimple.Direction.REVERSE);  /*vezi in ce parte este pozitivul daca este in jos foloseste comanda asta*/

    }
    @Override
    public void loop(){
        double  pozitie_actuala=motorbrat1.getCurrentPosition()/ticks_in_grade;
        double viteza;
        double ff=Math.sin(Math.toDegrees(target/ticks_in_grade))*f;
        double putere;
        if (buton_joystick==0) {
            target=target_set_joystick(pozitie_actuala);
            viteza=target-pozitie_actuala;
            putere=ff+viteza;
            if (pozitie_actuala > limita2 || pozitie_actuala < limita1) {
                if (pozitie_actuala < limita2 || pozitie_actuala > limita1) {
                    if (Math.abs(putere) >= 0.9) {
                        motorbrat1.setPower(0.9);
                    } else {
                        motorbrat1.setPower(putere);
                    }
                }
            } else {
                motorbrat1.setPower(0);
            }
        }else{
            target=0;
            viteza=target-pozitie_actuala;
            putere=viteza+ff;
            if (pozitie_actuala < limita2 || pozitie_actuala > limita1) {
                if (Math.abs(putere) >= 0.9) {
                    motorbrat1.setPower(0.9);
                } else {
                    motorbrat1.setPower(putere);
                }
            }
        }
    }

    public double target_set_joystick(double pozitie_actuala){
        double target=0;
        float x2=gamepad2.left_stick_x;
        float y2=gamepad2.left_stick_y;
        float i2=gamepad2.right_stick_x;
        float j2=gamepad2.right_stick_y;

        if (pozitie_actuala<limita2 || pozitie_actuala>limita1){
            if (gamepad2.left_stick_y>0.1){
                target=0.5;
            }
            if (gamepad2.left_stick_y<-0.1){
                target=0.5;
            }
        }
        return target;
    }


}
