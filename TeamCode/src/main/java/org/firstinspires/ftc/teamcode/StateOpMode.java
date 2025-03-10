package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.DashConfig.ExtendoOut;
import static org.firstinspires.ftc.teamcode.DashConfig.ExtendoIn;
import static org.firstinspires.ftc.teamcode.DashConfig.PivotUp;
import static org.firstinspires.ftc.teamcode.DashConfig.PivotDown;
import static org.firstinspires.ftc.teamcode.DashConfig.WristUp;
import static org.firstinspires.ftc.teamcode.DashConfig.WristDown;
import static org.firstinspires.ftc.teamcode.DashConfig.LDin;
import static org.firstinspires.ftc.teamcode.DashConfig.LDout;
import static org.firstinspires.ftc.teamcode.DashConfig.RDin;
import static org.firstinspires.ftc.teamcode.DashConfig.RDout;
import static org.firstinspires.ftc.teamcode.DashConfig.Wristout;
import static org.firstinspires.ftc.teamcode.DashConfig.Wristin;
import static org.firstinspires.ftc.teamcode.DashConfig.ClawClosed;
import static org.firstinspires.ftc.teamcode.DashConfig.ClawOpen;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp()
public class StateOpMode extends LinearOpMode {
    // Declare motors as a private attribute to the
    // class (Edwin isn't sure why it throws an error
    // when they are scoped to runOpMode).
    public DcMotor frontleft;
    public DcMotor frontright;
    public DcMotor backleft;
    public DcMotor backright;
    public DcMotor lift1;
    public DcMotor lift2;

    public Servo Extendo;
    public Servo IntakePivot;
    public Servo IntakeWrist;
    public CRServo Intake;
    public Servo diffyleft;
    public Servo diffyright;
    public Servo claw;
    public Servo outwrist;

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
        lift1 = hardwareMap.get(DcMotor.class, "lift1");
        lift2 = hardwareMap.get(DcMotor.class, "lift2");

        Extendo = hardwareMap.get(Servo.class, "Extendo");
        IntakePivot = hardwareMap.get(Servo.class, "IntakePivot");
        IntakeWrist = hardwareMap.get(Servo.class, "IntakeWrist");
        Intake = hardwareMap.get(CRServo.class, "Intake");
        diffyleft = hardwareMap.get(Servo.class, "diffyleft");
        diffyright = hardwareMap.get(Servo.class, "diffyright");
        claw = hardwareMap.get(Servo.class, "claw");
        outwrist = hardwareMap.get(Servo.class, "outwrist");


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
        lift1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lift2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //wave.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Set the motor directions.
        frontleft.setDirection(DcMotor.Direction.REVERSE);
        frontright.setDirection(DcMotor.Direction.FORWARD);
        backleft.setDirection(DcMotor.Direction.REVERSE);
        backright.setDirection(DcMotor.Direction.FORWARD);

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
            double rotX = gamepad1.right_stick_x;


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

            // Set up motor powers for mecanum.
            f_frontright = y - x - rotX;
            f_frontleft = y + x + rotX;
            f_backright = y + x - rotX;
            f_backleft = y - x + rotX;


            if (slow_bot) {
                speed = 0.3;
                mode = 1;
            }
            if (reg_bot) {
                speed = 0.5;
                mode = 2;
            }
            if (speed_bot) {
                speed = 0.7;
                mode = 3;
            }
            if (talha) {
                speed = 1.0;
                mode = 4;
            }

            f_frontleft = f_frontleft * speed;
            f_frontright = f_frontright * speed;
            f_backleft = f_backleft * speed;
            f_backright = f_backright * speed;


            frontleft.setPower(f_frontleft);
            frontright.setPower(f_frontright);
            backleft.setPower(f_backleft);
            backright.setPower(f_backright);

            if (gamepad1.dpad_right) {
                IntakeWrist.setPosition(PivotDown);
            } else if (gamepad1.dpad_left) {
                IntakeWrist.setPosition(PivotUp);
            } else if (gamepad1.dpad_up) {
                IntakePivot.setPosition(WristUp);
            } else if (gamepad1.dpad_down) {
                IntakePivot.setPosition(WristDown);
            }
            if (gamepad2.right_bumper){
                //Intake.setPower(1);
                claw.setPosition(ClawClosed);
            }
            else if (gamepad2.left_bumper){
                //Intake.setPower(-1);
                claw.setPosition(ClawOpen);
            }
            if(gamepad1.right_bumper){
                Intake.setPower(1);
            }
            else if (gamepad1.left_bumper){
                Intake.setPower(-1);
            }
            else {
            Intake.setPower(0);
            }
            /*if (gamepad1.x){
                Extendo.setPosition(ExtendoIn);
            }
            else if (gamepad1.b){
                Extendo.setPosition(ExtendoOut);
            }
             */
            if (gamepad1.left_trigger > 0.1){
                //lift1&2 down
                lift1.setPower(1);
                lift2.setPower(1);
                //outwrist.setPosition(Wristout);
                //claw.setPosition(ClawOpen);
            }
            else if (gamepad1.right_trigger > 0.1){
                //lift1&2 up
                lift1.setPower(-1);
                lift2.setPower(-1);
                //outwrist.setPosition(Wristin);
                //claw.setPosition(ClawClosed);
            }
            else {
                lift1.setPower(0);
                lift2.setPower(0);

            }
            if (gamepad2.y){
                //19/1b0
                //outwrist.setPosition(Wristout);
                diffyleft.setPosition(LDout);
                //diffyright.setPosition(RDout);
            }
            else if (gamepad2.a){
                //0
                //outwrist.setPosition(Wristin);
                //diffyright.setPosition(RDin);
                diffyleft.setPosition(LDin);
            }
            else if (gamepad2.x){
                //diffyleft.setPosition(LDin);
                diffyright.setPosition(RDin);
            }
            else if (gamepad2.b){
                //diffyleft.setPosition(LDout);
                diffyright.setPosition(RDout);
            }
        }
    }
}
