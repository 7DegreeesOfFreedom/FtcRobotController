package org.firstinspires.ftc.teamcode;

//import static org.firstinspires.ftc.teamcode.DashConfig.motorpower;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "ExtendoTest")
public class ExtendoTest extends LinearOpMode {

    private DcMotor liftleft;
    private DcMotor liftright;
    private DcMotor vleft;
    private DcMotor vright;

    @Override
    public void runOpMode() throws InterruptedException {
        liftleft = hardwareMap.get(DcMotor.class, "liftleft");
        liftright = hardwareMap.get(DcMotor.class, "liftright");
        vleft = hardwareMap.get(DcMotor.class, "vleft");
        vright = hardwareMap.get(DcMotor.class, "vright");

        liftleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        liftright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        vleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        vright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        liftleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        liftright.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        vleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        vright.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        liftleft.setDirection(DcMotorSimple.Direction.REVERSE);
        liftright.setDirection(DcMotorSimple.Direction.REVERSE);
        vleft.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();
        while (opModeIsActive()) {
            double power = gamepad1.right_trigger;
            double  inverse = -gamepad1.left_trigger;

            if (gamepad1.right_trigger>0){
                liftright.setPower(power);
                liftleft.setPower(power);
            }
            else if (gamepad1.left_trigger>0){
                liftright.setPower(inverse);
                liftleft.setPower(inverse);
            }
            else if ((gamepad1.left_trigger>0) && (gamepad1.right_trigger>0)){
                liftright.setPower(0);
                liftleft.setPower(0);
            }
            else {
                liftright.setPower(0);
                liftleft.setPower(0);
            }
            if (gamepad1.right_bumper){
                //vright.setPower(-motorpower);
                vleft.setPower(-0.5);
            }
            else if (gamepad1.left_bumper){
                //vright.setPower(motorpower);
                vleft.setPower(0.5);
            }
            else if ((gamepad1.left_bumper) && (gamepad1.right_bumper)){
                vright.setPower(0);
                vleft.setPower(0);
            }
            else {
                vright.setPower(0);
                vleft.setPower(0);
            }
        }
    }
}
