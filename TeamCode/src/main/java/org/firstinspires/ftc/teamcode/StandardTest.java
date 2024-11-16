package org.firstinspires.ftc.teamcode;
import static org.firstinspires.ftc.teamcode.DashConfig.wristPosDown;
import static org.firstinspires.ftc.teamcode.DashConfig.wristPosUp;
import static org.firstinspires.ftc.teamcode.DashConfig.wristPosIn;
import static org.firstinspires.ftc.teamcode.DashConfig.fourBarPosDown;
import static org.firstinspires.ftc.teamcode.DashConfig.fourBarPosUp;
import static org.firstinspires.ftc.teamcode.DashConfig.open;
import static org.firstinspires.ftc.teamcode.DashConfig.close;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "StandardTest")
public class StandardTest extends LinearOpMode {

    public DcMotor frontleft;
    public DcMotor frontright;
    public DcMotor backleft;
    public DcMotor backright;
    public DcMotor liftleft;
    public DcMotor liftright;
    public DcMotor vleft;
    public DcMotor vright;

    private Servo claw;
    private Servo wrist;
    private Servo fourBar;
    private CRServo leftintake;
    private CRServo rightintake;

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
        vleft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        vright.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        // Configure motor response when power is 0.0.
        frontleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        vleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        vright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Set the motor directions.
        frontleft.setDirection(DcMotor.Direction.REVERSE);
        frontright.setDirection(DcMotor.Direction.FORWARD);
        backleft.setDirection(DcMotor.Direction.REVERSE);
        backright.setDirection(DcMotor.Direction.FORWARD);
        liftleft.setDirection(DcMotor.Direction.REVERSE);
        liftright.setDirection(DcMotor.Direction.FORWARD);
        vleft.setDirection(DcMotor.Direction.FORWARD);
        vright.setDirection(DcMotor.Direction.REVERSE);


        // Inform the user that everything has been initialized.
        telemetry.addData("Status", "Initialized");
        telemetry.update();


        //intitialize positions
        vleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        vright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //wrist.setPosition(wristPosUp);
        //fourBar.setPosition(fourBarPosDown);
        claw.setPosition(close );

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            telemetry.addData("Status", "Running");
            // Create motion vector;
            f_x = gamepad1.left_stick_x;
            f_y = -gamepad1.left_stick_y;
            f_r = gamepad1.right_stick_x;

            //boolean speed_bot = gamepad1.y;
            //boolean slow_bot = gamepad1.a;
            //boolean reg_bot = gamepad1.b;
            //boolean talha = gamepad1.x;


            double x = -gamepad1.left_stick_x;
            double y = -gamepad1.left_stick_y;
            double rotX = -gamepad1.right_stick_x;


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
            f_frontright = y - x - rotX;
            f_frontleft = y + x + rotX;
            f_backright = y + x - rotX;
            f_backleft = y - x + rotX;


            /*if (slow_bot) {
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
*/
            f_frontleft = f_frontleft * speed;
            f_frontright = f_frontright * speed;
            f_backleft = f_backleft * speed;
            f_backright = f_backright * speed;


            frontleft.setPower(f_frontleft);
            frontright.setPower(f_frontright);
            backleft.setPower(f_backleft);
            backright.setPower(f_backright);

            /*if ((gamepad1.y) && (wrist.getPosition()==wristPosDown)){
                wrist.setPosition(wristPosUp);
            }
             if ((gamepad1.y) && (wrist.getPosition()==wristPosUp)){
                wrist.setPosition(wristPosDown);
            }
             */
            if (gamepad2.x){
                liftleft.setPower(-.5);
                liftright.setPower(-.5);
            }
            else if (gamepad2.a){
                liftleft.setPower(0.5);
                liftright.setPower(.5);
            }
            else{
                liftleft.setPower(0);
                liftright.setPower(0);
            }
            yloop:
            if (gamepad2.y){
                if (wrist.getPosition()==wristPosDown){
                    sleep(150);
                    wrist.setPosition(wristPosUp);
                    break yloop;
                }
                else if (wrist.getPosition()==wristPosUp){
                    sleep(150);
                    wrist.setPosition(wristPosDown);
                    break yloop;
                }
                else{
                    sleep(150);
                    wrist.setPosition(wristPosUp);
                    break yloop;
                }
            }
            /*aloop:
            if (gamepad1.a){
                if (fourBar.getPosition()==fourBarPosDown){
                    sleep(250);
                    fourBar.setPosition(fourBarPosUp);
                    break aloop;
                }
                else if (fourBar.getPosition()==fourBarPosUp){
                    sleep(250);
                    claw.setPosition(close);
                    fourBar.setPosition(fourBarPosDown);
                    sleep(600);
                    claw.setPosition(open);
                    break aloop;
                }
                else{
                    sleep(250);
                    fourBar.setPosition(fourBarPosDown);
                    break aloop;
                }
            }
             */
            bloop:
            if (gamepad2.b){
                if (claw.getPosition()==open){
                    sleep(250);
                    claw.setPosition(close);
                    break bloop;
                }
                else if (claw.getPosition()==close){
                    sleep(250);
                    claw.setPosition(open);
                    break bloop;
                }
                else{
                    sleep(250);
                    claw.setPosition(close);
                    break bloop;
                }
            }

            /*xloop:
            if (gamepad1.x){
                if ((fourBar.getPosition()==fourBarPosDown) || (fourBar.getPosition()==fourBarPosUp)){
                    sleep(250);
                    fourBar.setPosition(0.25);
                    break xloop;
                }
                else if (claw.getPosition()==0.25){
                    sleep(250);
                    fourBar.setPosition(fourBarPosDown);
                    break xloop;
                }
                else{
                    sleep(250);
                    fourBar.setPosition(fourBarPosDown);
                    break xloop;
                }
            }
             */

            if (gamepad2.left_bumper){
                leftintake.setPower(1);
                rightintake.setPower(-1);
            }
            else if (gamepad2.right_bumper){
                leftintake.setPower(-1);
                rightintake.setPower(1);
            }
            else{
                leftintake.setPower(0);
                rightintake.setPower(0);
            }
            if (gamepad2.dpad_right) {
                claw.setPosition(close);
                fourBar.setPosition(fourBarPosDown);
            }
            else if (gamepad2.dpad_left) {
                claw.setPosition(close);
                fourBar.setPosition(fourBarPosUp);
            }
            else if (gamepad2.dpad_up) {
                fourBar.setPosition(0.25);
            }
            else if (gamepad2.dpad_down) {
                wrist.setPosition(wristPosIn);
            }
            /*if(gamepad1.dpad_left){
                wrist.setPosition(wristPosIn);
            }
            else if (gamepad1.dpad_right){
                wrist.setPosition(wristPosUp);
            }
             */

        double rtrigger = gamepad1.right_trigger;
        double ltrigger = gamepad1.left_trigger;
            // The maximum lift value is approximately 3330-3340
//            if (rtrigger > 0.05 && liftleft.getTargetPosition() <= 1500)
//            {
//                50---].setTargetPosition(liftleft.getTargetPosition() + 25);
//                liftright.setTargetPosition(liftright.getTargetPosition() + 25);
//                liftleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                liftright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                liftleft.setPower(0.2);
//                liftright.setPower(0.1);
//
//            }
//            if (ltrigger > 0.05 &&  liftright.getTargetPosition() >= 10)
//            {
//                liftleft.setTargetPosition(liftleft.getTargetPosition() - 25);
//                liftright.setTargetPosition(liftright.getTargetPosition() - 25);
//                liftleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                liftright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                liftleft.setPower(0.2);
//                liftright.setPower(0.1);
//            }

//            if(gamepad2.right_bumper){
//                liftleft.setTargetPosition(1500);
//                liftright.setTargetPosition(1500);
//                liftleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                liftright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                liftleft.setPower(.3);
//                liftright.setPower(.3);
//            }
//            else if(gamepad2.left_bumper){
//                wrist.setPosition(DashConfig.wristPosUp);
//
//                liftleft.setTargetPosition(0);
//                liftright.setTargetPosition(0);
//                liftleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                liftright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                liftleft.setPower(.3);
//                liftright.setPower(.3);
//            }
            if (gamepad2.right_trigger>0.2){
                vleft.setTargetPosition(3100);
                vright.setTargetPosition(3100);
                vleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                vright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                vleft.setPower(.3);
                vright.setPower(.3);
            }
            if (gamepad2.left_trigger>0.2){
                vleft.setTargetPosition(0);
                vright.setTargetPosition(0);
                vleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                vright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                vleft.setPower(.3);
                vright.setPower(.3);

            }
            /*if (gamepad2.x){
                liftleft.setTargetPosition(1510);
                liftright.setTargetPosition(1510);
                liftleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                liftright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                liftleft.setPower(1);
                liftright.setPower(1);
            }
            if (gamepad2.a){
                liftleft.setTargetPosition(1);
                //liftright.setTargetPosition(0);
                liftleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
               // liftright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                liftleft.setPower(-1);
                //liftright.setPower(-1);
            }
             */
        }
    }
}