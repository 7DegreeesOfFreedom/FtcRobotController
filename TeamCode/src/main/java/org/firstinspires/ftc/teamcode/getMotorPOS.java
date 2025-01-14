package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.eventloop.opmode.OpMode.gamepad1;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
@TeleOp()
public class getMotorPOS extends LinearOpMode {
        // Declare motors as a private attribute to the
        // class (Edwin isn't sure why it throws an error
        // when they are scoped to runOpMode).


        public DcMotor liftleft;
        public DcMotor liftright;

        public DcMotor vleft;
        public DcMotor vright;

        public Servo wrist;
        public int liftPos;
        @Override
        public void runOpMode() {
            // Inform the user that the program is initializing.
            telemetry.addData("Status", "Initializing...");
            telemetry.update();

            // Get motors from hardwareMap.
            liftleft = hardwareMap.get(DcMotor.class, "liftleft");
            liftright = hardwareMap.get(DcMotor.class, "liftright");
            vleft = hardwareMap.get(DcMotor.class, "vleft");
            vright = hardwareMap.get(DcMotor.class, "vright");
            wrist = hardwareMap.get(Servo.class, "wrist");







            // Inform the user that everything has been initialized.
            telemetry.addData("Status", "Initialized");
            telemetry.update();

            // Wait for the game to start (driver presses PLAY)
            waitForStart();

            // run until the end of the match (driver presses STOP)
            liftright.setTargetPosition(0);
            liftleft.setTargetPosition(0);
            liftleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            liftright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            vleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            vright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            liftleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            liftright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            vleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            vright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

            liftleft.setDirection(DcMotorSimple.Direction.REVERSE);
            vright.setDirection(DcMotorSimple.Direction.REVERSE);
            while (opModeIsActive()) {

//1510 extendo pos

                waitForStart();

                if (isStopRequested()) return;

                while (opModeIsActive()) {


                    telemetry.addData("liftLeft",liftleft.getCurrentPosition());
                    telemetry.addData("liftRight",liftright.getCurrentPosition());
                    telemetry.addData("Vleft",vleft.getCurrentPosition());
                    telemetry.addData("Vright",vright.getCurrentPosition());
                    telemetry.update();

                    if(gamepad2.right_bumper){
                        liftleft.setTargetPosition(1500);
                        liftright.setTargetPosition(1500);
                        liftleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                        liftright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                        liftleft.setPower(.3);
                        liftright.setPower(.3);
                    }
                    else if(gamepad2.left_bumper){
                      //  wrist.setPosition(DashConfig.wristPosUp);

                        liftleft.setTargetPosition(0);
                        liftright.setTargetPosition(0);
                        liftleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                        liftright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                        liftleft.setPower(.3);
                        liftright.setPower(.3);
                    }
                    if (gamepad2.right_trigger>0.2){
//                        wrist.setPosition(DashConfig.wristPosUp);
                        vleft.setTargetPosition(3100);
                        vright.setTargetPosition(3100);
                        vleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                        vright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                        vleft.setPower(.3);
                        vright.setPower(.3);
                    }
                    else if (gamepad2.left_trigger>0.2){
                        vleft.setTargetPosition(3100);
                        vright.setTargetPosition(3100);
                        vleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                        vright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                        vleft.setPower(.3);
                        vright.setPower(.3);

                    }



                }
            }
        }

    }



