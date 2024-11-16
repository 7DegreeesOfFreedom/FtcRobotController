package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "MotorTest")
        public class MotorTest extends LinearOpMode {

    public DcMotor motor;
    public DcMotor motor2;


    @Override
    public void runOpMode() {
        // Inform the user that the program is initializing.
        telemetry.addData("Status", "Initializing...");
        telemetry.update();

        // Get motors from hardwareMap.
        motor = hardwareMap.get(DcMotor.class, "liftleft");
        motor2 = hardwareMap.get(DcMotor.class, "liftright");

        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Configure motor response when power is 0.0.
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Set the motor directions.
        motor.setDirection(DcMotor.Direction.REVERSE);

        // Inform the user that everything has been initialized.
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            telemetry.addData("Status", "Running");
            double power = -gamepad1.right_trigger;
            double inverse = gamepad1.left_trigger;

            /*if (gamepad1.right_trigger > 0) {
                motor.setPower(power);
            } else if (gamepad1.left_trigger > 0) {
                motor.setPower(inverse);
            } else if ((gamepad1.left_trigger > 0) && (gamepad1.right_trigger > 0)) {
                motor.setPower(0);
            } else {
                motor.setPower(0);
            }
             */
            if (gamepad1.x){
                motor.setPower(.3);
                motor2.setPower(.3);
            }
            else if (gamepad1.a){
                motor.setPower(-0.3);
                motor2.setPower(-0.3);
            }
            else{
                motor.setPower(0);
                motor2.setPower(0);
            }
        }
    }
}
