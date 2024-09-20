package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.DashConfig.wristPos;

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
    private Servo wrist;
    private CRServo leftintake;
    private CRServo rightintake;

    @Override
    public void runOpMode() throws InterruptedException {
        liftleft = hardwareMap.get(DcMotor.class, "liftleft");
        liftright = hardwareMap.get(DcMotor.class, "liftright");
        liftleft.setDirection(DcMotorSimple.Direction.REVERSE);
        liftleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        liftright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        liftleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        liftright.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        wrist = hardwareMap.get(Servo.class, "wrist");
        leftintake = hardwareMap.get(CRServo.class, "leftintake");
        rightintake = hardwareMap.get(CRServo.class, "rightintake");

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
            if(gamepad1.y){
                wrist.setPosition(0.45);
            }
            else if(gamepad1.a){
                wrist.setPosition(wristPos);
            }
            if (gamepad1.right_bumper){
                rightintake.setPower(1);
                leftintake.setPower(-1);
            }
            else if (gamepad1.left_bumper){
                rightintake.setPower(0);
                leftintake.setPower(0);
            }
        }
    }
}
