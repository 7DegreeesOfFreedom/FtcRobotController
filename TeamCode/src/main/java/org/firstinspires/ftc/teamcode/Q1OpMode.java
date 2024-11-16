package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp()
public class Q1OpMode extends LinearOpMode {
    // Declare motors as a private attribute to the
    // class (Edwin isn't sure why it throws an error
    // when they are scoped to runOpMode).
    public DcMotor frontleft;
    public DcMotor frontright;
    public DcMotor backleft;
    public DcMotor backright;
    public DcMotor liftleft;
    public DcMotor liftright;
    public DcMotor vleft;
    public DcMotor vright;

    public Servo fourBar;
    public Servo wrist;
    public Servo claw;
    public CRServo leftintake;
    public CRServo rightintake;

    double f_x;
    double f_y;
    double f_r;
    double f_frontleft;
    double f_frontright;
    double f_backleft;
    double f_backright;


    @Override
    public void runOpMode() {
        // Inform the user that the program is initializing.
        telemetry.addData("Status", "Initializing...");
        telemetry.update();

        // Get motors from hardwareMap.
        frontleft = hardwareMap.get(DcMotor.class, "frontleft");
        frontright = hardwareMap.get(DcMotor.class, "frontright");
        backright = hardwareMap.get(DcMotor.class, "backright");
        backleft = hardwareMap.get(DcMotor.class, "backleft");
        liftleft = hardwareMap.get(DcMotor.class, "liftleft");
        liftright = hardwareMap.get(DcMotor.class, "liftright");
        vleft = hardwareMap.get(DcMotor.class, "vleft");
        vright = hardwareMap.get(DcMotor.class, "vright");

        claw = hardwareMap.get(Servo.class, "claw");
        wrist = hardwareMap.get(Servo.class, "wrist");
        fourBar = hardwareMap.get(Servo.class, "v4bar");
        leftintake = hardwareMap.get(CRServo.class, "leftintake");
        rightintake = hardwareMap.get(CRServo.class, "rightintake");


        double speed = 0.5;
        int mode = 2;
        //wave = hardwareMap.get(DcMotor.class, "wave");
        //imu = hardwareMap.get(BNO055IMU.class, "imu");
        //BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();

        // Configure motors to go to velocity/power/position.
        frontleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontright.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backright.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Configure motor response when power is 0.0.
        frontleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        liftleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        liftright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        vleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        vright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Set the motor directions.
        frontleft.setDirection(DcMotor.Direction.REVERSE);
        frontright.setDirection(DcMotor.Direction.FORWARD);
        backleft.setDirection(DcMotor.Direction.REVERSE);
        backright.setDirection(DcMotor.Direction.FORWARD);
        liftleft.setDirection(DcMotor.Direction.FORWARD);
        liftright.setDirection(DcMotor.Direction.FORWARD);
        vleft.setDirection(DcMotor.Direction.FORWARD);
        vright.setDirection(DcMotor.Direction.FORWARD);

        // Inform the user that everything has been initialized.
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            telemetry.addData("Status", "Running");
            // Create motion vector;
            f_x = gamepad1.left_stick_x;
            f_y = gamepad1.left_stick_y;
            f_r = gamepad1.right_stick_x;

            boolean speed_bot = gamepad1.y;
            boolean slow_bot = gamepad1.a;
            boolean reg_bot = gamepad1.b;
            boolean talha = gamepad1.x;


            double x = gamepad1.left_stick_x;
            double y = -gamepad1.left_stick_y;
            double rotX = -gamepad1.right_stick_x;

            double Horizontal = gamepad2.left_stick_x;
            double Vertical = gamepad2.right_stick_y;
            double in = gamepad2.left_trigger;
            double out = gamepad2.right_trigger;

            // Give controller joysticks a deadzone.
            if (-0.1 < f_x && f_x < 0.1) {
                f_x = 0;
            }
            if (-0.1 < f_y && f_y < 0.1) {
                f_y = 0;
            }
            if (-0.1 < f_r && f_r < 0.1) {
                f_r = 0;
            }

            // Apply weights to each component, such that when
            // f_x and f_y are 45* on a unit circle and f_r is
            // maxed out in a direction, each motor power adds
            // up to 1.0.
            f_x *= 1;
            f_y *= 1;
            f_r *= 1;

            // Set up motor powers for mechanum.
            f_frontright  = y - x - rotX;
            f_frontleft = y + x + rotX;
            f_backright   = y + x - rotX;
            f_backleft  = y - x + rotX;


            /*
            if (slow_bot){
                speed = 0.3;
                mode = 1;
            }
            if (reg_bot){
                speed = 0.5;
                mode = 2;
            }
            if (speed_bot){
                speed = 0.7;
                mode = 3;
            }
            if (talha){
                speed = 1.0;
                mode = 4;
            }
             */

            f_frontleft  = f_frontleft*speed;
            f_frontright = f_frontright*speed;
            f_backleft   = f_backleft*speed;
            f_backright  = f_backright*speed;


            frontleft.setPower(f_frontleft);
            frontright.setPower(f_frontright);
            backleft.setPower(f_backleft);
            backright.setPower(f_backright);

            if (gamepad2.left_stick_x < -0.2){
                liftleft.setPower(Horizontal);
                liftright.setPower(-Horizontal);
            }
            else if (gamepad2.left_stick_x > -0.2){
                liftleft.setPower(Horizontal);
                liftright.setPower(-Horizontal);
            }
            else{
                liftleft.setPower(0);
                liftright.setPower(0);
            }
            if (gamepad2.right_stick_y < 0.2){
                vleft.setPower(-Vertical);
                vright.setPower(Vertical);
                //fourBar.setPosition(1);
            }
            else if (gamepad2.right_stick_y > 0.2){
                vleft.setPower(-Vertical);
                vright.setPower(Vertical);
                fourBar.setPosition(0.18);
            }
            else {
                vleft.setPower(0);
                vright.setPower(0);
            }
            if (gamepad2.left_trigger > 0.1){
                wrist.setPosition(0.8);
                leftintake.setPower(-in);
                rightintake.setPower(in);
            }
            else if (gamepad2.right_trigger > 0.1){
                leftintake.setPower(out);
                rightintake.setPower(-out);
            }
            else if (gamepad2.y){
                leftintake.setPower(-in);
                rightintake.setPower(in);
                wrist.setPosition(0.1);
            }
            else {
                leftintake.setPower(0);
                rightintake.setPower(0);
                wrist.setPosition(0.4);
            }
            if (gamepad2.b){
                fourBar.setPosition(0.08);
            }
            else if (gamepad2.a){
                fourBar.setPosition(0.18);
                claw.setPosition(0.7);
            }
            else if (gamepad2.x){
                fourBar.setPosition(1);
                claw.setPosition(1);
            }
            if (gamepad2.dpad_down){
                claw.setPosition(0.7);
            }
            else if (gamepad2.dpad_up){
                claw.setPosition(1);
            }
        }
    }
}
