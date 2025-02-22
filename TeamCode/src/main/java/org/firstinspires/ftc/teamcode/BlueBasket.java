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
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@Autonomous

public class BlueBasket extends LinearOpMode {
    private Servo Extendo;
    private Servo diffyleft;
    private Servo diffyright;
    private Servo claw;
    private Servo outwrist;
    private DcMotor frontleftAsDcMotor;
    private DcMotor backleftAsDcMotor;
    private DcMotor frontrightAsDcMotor;
    private DcMotor backrightAsDcMotor;


    public BNO055IMU imu;

        // Get motors from hardwareMap.



    DriveClass d = new DriveClass();


    @Override
    public void runOpMode() {
        Extendo = hardwareMap.get(Servo.class, "Extendo");
        diffyleft = hardwareMap.get(Servo.class, "diffyleft");
        diffyright = hardwareMap.get(Servo.class, "diffyright");
        claw = hardwareMap.get(Servo.class, "claw");
        outwrist = hardwareMap.get(Servo.class, "outwrist");
        d.init(hardwareMap);

        Extendo.setPosition(ExtendoIn);
        claw.setPosition(ClawClosed);
        telemetry.addData("Status", "Initializing...");
        telemetry.update();

        waitForStart();

        if (opModeIsActive()) {
            d.Timely(-800,-800,-800,-800,0.8);
            d.Timely(1000, -1000, -1000, 1000, 0.8);
            d.liftNT(3000,0.5);
            outwrist.setPosition(Wristin);
            sleep(500);
            d.Timely(-100,-100,-100,-100,0.2);
            d.liftNT(-3000,0.5);
        }
    }
}