package org.firstinspires.ftc.teamcode.Control;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="Gata")
public class Test_brat extends OpMode {
    DcMotor motorbrat1,motorbrat2;
    double unghi_incepere = 48.5;///trebuie data valoare manual
    double limita1 = 49,limita2 = 141;///trebuie data valoare manual/ vezi unde sta bratul fara sa cada si masoara unghiul
    double ticks_in_grade = 288/(360*0.25);///vezi gear ration dinti_roata1/dinti_roata2 complicat
    double lungime_slider_inchis = 40;
    double ultima_pozitie=0;
    double ultimul_unghi=48.5;
    double masa_slider=2;
    double ticks_in_centimetri=12/288;

    @Override
    public void init(){
        motorbrat1 = hardwareMap.get(DcMotor.class, "motorbrat1");
        motorbrat2 = hardwareMap.get(DcMotor.class, "motorbrat2");
        motorbrat1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorbrat2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    @Override
    public void loop(){
        float mb1 = gamepad2.left_stick_x;
        boolean n = false;
        float mb2 = gamepad2.right_stick_y;
        double pozitie_actuala_mb1 = motorbrat1.getCurrentPosition();
        double slider_pozitie_actuala = motorbrat2.getCurrentPosition();
        if (Math.abs(gamepad2.left_stick_x) > 0.1){
            if (pozitie_actuala_mb1/ticks_in_grade > limita1 || pozitie_actuala_mb1/ticks_in_grade < limita2 ) {
                if(brat_fix( slider_pozitie_actuala, ultima_pozitie, ultimul_unghi)>mb1) {
                    if (n == false) {
                        n = true;
                        ultima_pozitie = motorbrat1.getCurrentPosition();///intra in if doar la inceputul miscarii pentru a memora pozitia principala
                    }
                    motorbrat1.setPower(mb1);///mb1 este valoarea joystick-ului
                }
            }else{
                motorbrat1.setPower(brat_fix( slider_pozitie_actuala, ultima_pozitie, ultimul_unghi));///tine bratul in pozitie ficsa
                n = false;
            }
        }else{
            motorbrat1.setPower(brat_fix( slider_pozitie_actuala, ultima_pozitie, ultimul_unghi));///tine bratul in pozitie ficsa
            n = false;
        }

    }

    public double brat_fix( double slider_pozitie_actuala, double ultima_pozitie, double ultimul_unghi){
        double pozitie_modificata = motorbrat1.getCurrentPosition();
        double slider_pozitie_actuala_cm  =slider_pozitie_actuala*ticks_in_centimetri;
        double cateta = Math.sin(a(pozitie_modificata, ultima_pozitie))*slider_pozitie_actuala_cm;
        double forta_in_funcyie_de_cateta = (lungime_slider_inchis/cateta)*masa_slider;
        double suma_gaus_lungimi = (cateta*cateta+cateta)/2;
        double forta_exercitata_de_brat = forta_in_funcyie_de_cateta*suma_gaus_lungimi;
        return forta_exercitata_de_brat;

    }
    public double a(double pozitie_modificata, double ultima_pozitie){
        double b = (pozitie_modificata-ultima_pozitie)/ticks_in_grade;
        double a = ultimul_unghi+b;
        if (a>90){
            a=180-a;
        }
        ultimul_unghi=a;
        return a;
    }
}
