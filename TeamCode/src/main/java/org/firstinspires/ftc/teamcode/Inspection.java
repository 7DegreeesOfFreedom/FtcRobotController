package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import java.util.List;
import org.firstinspires.ftc.robotcore.external.ClassFactory;

@Autonomous

public class Inspection extends LinearOpMode {
    private DcMotor frontleftAsDcMotor;
    private DcMotor backleftAsDcMotor;
    private DcMotor frontrightAsDcMotor;
    private DcMotor backrightAsDcMotor;

    DriveClass d = new DriveClass();

    @Override
    public void runOpMode() {
        d.init(hardwareMap);

        telemetry.addData("Status", "Initializing...");
        telemetry.update();

        waitForStart();

        if (opModeIsActive()) {
                d.Timely(500, 500, 500, 500, 0.4);
        }
    }
}