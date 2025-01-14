package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

//In the future import Timely from Driveclass, if you don't know what this means ask Sophia
@Autonomous(name = "Specimen", group = "Auto")
public class Specimen extends LinearOpMode {

    private DcMotor frontleft;
    private DcMotor frontright;
    private DcMotor backleft;
    private DcMotor backright;
    private Servo  claw;
    private Servo v4bar;
    private DcMotor vleft;
    private DcMotor vright;
    PIDController liftController;
    private Motor.Encoder liftEncoder;
    private final double LIFT_RAISE_MS = 500;

    @Override
    public void runOpMode() {
        liftController = new PIDController(0.004, 0, 0);
        liftEncoder = new Motor(hardwareMap, "vleft", Motor.GoBILDA.RPM_435).encoder;

        frontleft = hardwareMap.get(DcMotor.class, "frontleft");
        frontright = hardwareMap.get(DcMotor.class, "frontright");
        backright = hardwareMap.get(DcMotor.class, "backright");
        backleft = hardwareMap.get(DcMotor.class, "backleft");
        vleft = hardwareMap.get(DcMotor.class, "vleft");
        vright = hardwareMap.get(DcMotor.class, "vright");
        v4bar = hardwareMap.get(Servo.class, "v4bar");
        claw = hardwareMap.get(Servo.class, "claw");

        // Put initialization blocks here.
        frontleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        frontleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontright.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backright.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        frontleft.setDirection(DcMotor.Direction.REVERSE);
        backleft.setDirection(DcMotor.Direction.REVERSE);
        frontright.setDirection(DcMotor.Direction.FORWARD);
        backright.setDirection(DcMotor.Direction.FORWARD);

        frontleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        vleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        vright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();
        ElapsedTime liftTimer = new ElapsedTime();
        if (opModeIsActive()) {
          vleft.setPower(.5);
          vright.setPower(-.5);
          sleep(2700);
          vleft.setPower(.2);
          vright.setPower(-.2);
          v4bar.setPosition(1);
          sleep(2000);
            Timely(300,300,300,300,0.2);
          claw.setPosition(.5);
          v4bar.setPosition(.5);
          sleep(1000);
          claw.setPosition(1);
          sleep(500);
          v4bar.setPosition(.2);
          sleep(100);
            Timely(500,500,-500,-500,0.2);
        }
    }


    public void Timely(double frontLeftTarget,double backLeftTarget, double frontRightTarget, double backRightTarget, double speed){
        double red = 0;
        if (Math.abs(frontLeftTarget/speed) > red)
        {
            red = (Math.abs(frontLeftTarget)/speed);
        }

        if (Math.abs(backLeftTarget/speed) > red)
        {
            red = (Math.abs(backLeftTarget)/speed);
        }

        if (Math.abs(frontRightTarget/speed) > red)
        {
            red = (Math.abs(frontRightTarget)/speed);
        }

        if (Math.abs(backRightTarget/speed) > red)
        {
            red = (Math.abs(backRightTarget)/speed);
        }
        int frontLeftPos;
        int backLeftPos;
        int frontRightPos;
        int backRightPos;


        double tim = (0.4075 * red) + 420;
        frontleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeftPos = (int)frontLeftTarget;
        backLeftPos = (int)backLeftTarget;
        frontRightPos = (int)frontRightTarget;
        backRightPos = (int)backRightTarget;
        frontleft.setTargetPosition(frontLeftPos);
        backleft.setTargetPosition(backLeftPos);
        frontright.setTargetPosition(frontRightPos);
        backright.setTargetPosition(backRightPos);
        frontleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontleft.setPower(speed);
        backleft.setPower(speed);
        frontright.setPower(speed);
        backright.setPower(speed);
        try {
            // Block for 500 milliseconds
            Thread.sleep((long)tim);
        } catch(InterruptedException e) {
            // Tells the current thread (OpMode) to
            // end the execution as soon as possible
            Thread.currentThread().interrupt();
        }
    }
    public void CustomTimely(int frontLeftTarget, double speed1, int backLeftTarget, double speed2, int frontRightTarget, double speed3, int backRightTarget, double speed4) {

        double red = 0;
        if (Math.abs(frontLeftTarget/speed1) > red)
        {
            red = (Math.abs(frontLeftTarget)/speed1);
        }

        if (Math.abs(backLeftTarget/speed2) > red)
        {
            red = (Math.abs(backLeftTarget)/speed2);
        }

        if (Math.abs(frontRightTarget/speed3) > red)
        {
            red = (Math.abs(frontRightTarget)/speed3);
        }

        if (Math.abs(backRightTarget/speed4) > red)
        {
            red = (Math.abs(backRightTarget)/speed4);
        }

        double tim = (0.4075 * red) + 404;


        frontleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        int frontLeftPos = frontLeftTarget;
        int backLeftPos = backLeftTarget;
        int frontRightPos = frontRightTarget;
        int backRightPos = backRightTarget;
        frontleft.setTargetPosition(frontLeftPos);
        backleft.setTargetPosition(backLeftPos);
        frontright.setTargetPosition(frontRightPos);
        backright.setTargetPosition(backRightPos);
        frontleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontleft.setPower(speed1);
        backleft.setPower(speed2);
        frontright.setPower(speed3);
        backright.setPower(speed4);
        try {
            // Block for 500 milliseconds
            Thread.sleep((long)tim);
        } catch(InterruptedException e) {
            // Tells the current thread (OpMode) to
            // end the execution as soon as possible
            Thread.currentThread().interrupt();
        }
    }
    public void Check(double target){
        int dist = 0;
        try {
            // Block for 500 milliseconds
            Thread.sleep(50);
        } catch(InterruptedException e) {
            // Tells the current thread (OpMode) to
            // end the execution as soon as possible
            Thread.currentThread().interrupt();
        }
    }
}

