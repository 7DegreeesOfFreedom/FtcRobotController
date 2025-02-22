package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.DashConfig.ClawClosed;
import static org.firstinspires.ftc.teamcode.DashConfig.ClawOpen;
import static org.firstinspires.ftc.teamcode.DashConfig.ExtendoIn;
import static org.firstinspires.ftc.teamcode.DashConfig.ExtendoOut;
import static org.firstinspires.ftc.teamcode.DashConfig.LDin;
import static org.firstinspires.ftc.teamcode.DashConfig.LDout;
import static org.firstinspires.ftc.teamcode.DashConfig.PivotDown;
import static org.firstinspires.ftc.teamcode.DashConfig.PivotUp;
import static org.firstinspires.ftc.teamcode.DashConfig.RDin;
import static org.firstinspires.ftc.teamcode.DashConfig.RDout;
import static org.firstinspires.ftc.teamcode.DashConfig.WristDown;
import static org.firstinspires.ftc.teamcode.DashConfig.WristUp;
import static org.firstinspires.ftc.teamcode.DashConfig.Wristin;
import static org.firstinspires.ftc.teamcode.DashConfig.Wristout;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp
public class StateCourselock extends LinearOpMode {
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

    public BNO055IMU imu;


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
        //wave.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
        //imu.initialize(parameters);


        // Configure motor response when power is 0.0.
        frontleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lift1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lift2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        // Set the motor directions.
        frontleft.setDirection(DcMotor.Direction.REVERSE);
        frontright.setDirection(DcMotor.Direction.FORWARD);
        backleft.setDirection(DcMotor.Direction.REVERSE);
        backright.setDirection(DcMotor.Direction.FORWARD);


        // Inform the user that everything has been initialized.
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        Extendo.setPosition(ExtendoIn);
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            IMU imu = hardwareMap.get(IMU.class, "imu");
            // Adjust the orientation parameters to match your robot
            IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                    RevHubOrientationOnRobot.LogoFacingDirection.RIGHT,
                    RevHubOrientationOnRobot.UsbFacingDirection.DOWN));
            // Without this, the REV Hub's orientation is assumed to be logo up / USB forward
            imu.initialize(parameters);

            waitForStart();

            if (isStopRequested()) return;

            while (opModeIsActive()) {
                boolean slow_bot;
                boolean reg_bot;
                boolean talha;
                boolean speed_bot;
                slow_bot = gamepad1.a;
                reg_bot = gamepad1.b;
                speed_bot = gamepad1.y;
                talha = gamepad1.x;

                double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
                double x = gamepad1.left_stick_x;
                double rx = gamepad1.right_stick_x;

                // This button choice was made so that it is hard to hit on accident,
                // it can be freely changed based on preference.
                // The equivalent button is start on Xbox-style controllers.
                if (gamepad1.options) {
                    imu.resetYaw();
                }

                double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

                // Rotate the movement direction counter to the bot's rotation
                double rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
                double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);

                rotX = rotX * 1.1;  // Counteract imperfect strafing

                // Denominator is the largest motor power (absolute value) or 1
                // This ensures all the powers maintain the same ratio,
                // but only if at least one is out of the range [-1, 1]
                double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1);
                double frontLeftPower = (rotY + rotX + rx) / denominator;
                double backLeftPower = (rotY - rotX + rx) / denominator;
                double frontRightPower = (rotY - rotX - rx) / denominator;
                double backRightPower = (rotY + rotX - rx) / denominator;

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
                    speed = 1;
                    mode = 4;
                }

                frontLeftPower = frontLeftPower * speed;
                frontRightPower = frontRightPower * speed;
                backLeftPower = backLeftPower * speed;
                backRightPower = backRightPower * speed;

                frontleft.setPower(frontLeftPower);
                backleft.setPower(backLeftPower);
                frontright.setPower(frontRightPower);
                backright.setPower(backRightPower);

                double f_wave = 0.0;

                if (gamepad2.left_stick_x > 0.1){
                    Extendo.setPosition(ExtendoIn);
                    IntakePivot.setPosition(WristUp);
                    IntakeWrist.setPosition(PivotUp);
                }
                else if (gamepad2.left_stick_x < -0.1){
                    Extendo.setPosition(ExtendoOut);
                    //wrist in
                    //outwrist.setPosition(Wristout);
                    //diffyleft.setPosition(LDin);
                    //diffyright.setPosition(RDin);
                }

                if (gamepad2.right_stick_y < -0.1){
                    //lift1&2 down
                    lift1.setPower(1);
                    lift2.setPower(1);
                }
                else if (gamepad2.right_stick_y > 0.1){
                    //lift1&2 up
                    lift1.setPower(-1);
                    lift2.setPower(-1);
                    Extendo.setPosition(ExtendoIn);
                }
                else {
                    lift1.setPower(0);
                    lift2.setPower(0);
                }

                if(gamepad2.right_trigger>0.1){
                    //grab
                    IntakePivot.setPosition(WristUp);
                    IntakeWrist.setPosition(PivotUp);
                    Intake.setPower(-1);
                }
                else if (gamepad1.left_trigger>0.1){
                    //release
                    Intake.setPower(1);
                }
                else {
                    Intake.setPower(0);
                    IntakePivot.setPosition(WristDown);
                    IntakeWrist.setPosition(PivotDown);
                }
                if (gamepad2.right_bumper){
                    claw.setPosition(ClawClosed);
                }
                else if (gamepad2.left_bumper){
                    claw.setPosition(ClawOpen);
                }
                if (gamepad2.b){
                    //claw out
                    outwrist.setPosition(Wristin);
                    diffyleft.setPosition(LDout);
                    diffyright.setPosition(RDout);
                }
                else if (gamepad2.x){
                    //claw in
                    outwrist.setPosition(Wristout);
                    diffyleft.setPosition(LDin);
                    diffyright.setPosition(RDin);
                }
            }
        }
    }

}

