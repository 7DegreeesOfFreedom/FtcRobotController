package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class Specimen {

    private DcMotor frontleft;
    private DcMotor frontright;
    private DcMotor backleft;
    private DcMotor backright;
    private Servo  claw;
    private Servo v4bar;
    private DcMotor vleft;
    private DcMotor vright;

    public void init(HardwareMap hwMap){

        frontleft = hwMap.get(DcMotor.class, "frontleft");
        frontright = hwMap.get(DcMotor.class, "frontright");
        backleft = hwMap.get(DcMotor.class, "backleft");
        backright = hwMap.get(DcMotor.class, "backright");

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


        frontleft.setPower(0);
        backleft.setPower(0);
        frontright.setPower(0);
        backright.setPower(0);

        public void Timely(double frontLeftTarget,double){
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
}
