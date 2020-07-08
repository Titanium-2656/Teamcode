package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


@TeleOp(name="Basic: Linear OpMode", group="Linear Opmode")
@Disabled
public class Manual extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor rightBackMotor = null;
    private DcMotor rightFrontMotor = null;
    private DcMotor leftBackMotor = null;
    private DcMotor leftFrontMotor = null;

    double speedx;
    double speedy;
    double marginOfError;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        rightBackMotor  = hardwareMap.get(DcMotor.class, "rightBackMotor");
        rightFrontMotor = hardwareMap.get(DcMotor.class, "rightFrontMotor");
        leftBackMotor = hardwareMap.get(DcMotor.class, "leftBackMotor");
        leftFrontMotor = hardwareMap.get(DcMotor.class, "leftFrontMotor");

        rightBackMotor.setDirection(DcMotor.Direction.FORWARD);
        rightFrontMotor.setDirection(DcMotor.Direction.FORWARD);
        leftBackMotor.setDirection(DcMotor.Direction.REVERSE);
        leftFrontMotor.setDirection(DcMotor.Direction.REVERSE);

        rightBackMotor.setPower(0);
        rightFrontMotor.setPower(0);
        leftBackMotor.setPower(0);
        leftFrontMotor.setPower(0);

        rightFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBackMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBackMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            double front = -gamepad1.left_stick_y;
            double back = -gamepad1.right_stick_y;
            double left = gamepad1.left_stick_x;
            double right = gamepad1.right_stick_x;


            leftFrontMotor.setPower(front);
            rightFrontMotor.setPower(front);
            leftBackMotor.setPower(front);
            rightBackMotor.setPower(front);

            //This sets the left stick x variable to robot translate movement
            leftFrontMotor.setPower(left);
            rightFrontMotor.setPower(-left);
            leftBackMotor.setPower(-left);
            rightBackMotor.setPower(left);

            //This sets the right stick x variable to robot rotation movement.
            leftFrontMotor.setPower(right);
            rightFrontMotor.setPower(-right);
            leftBackMotor.setPower(right);
            rightBackMotor.setPower(-right);

//            double driverX = -(gamepad1.left_stick_x);
//            double driverY = gamepad1.left_stick_y;
//            double driverTurn = gamepad1.right_stick_x;
//            calculatePower(driverX, driverY, driverTurn);



            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();
        }
    }

//    public void calculatePower(double powerX, double powerY, double turn){
//        speedx = powerX;
//        speedy = powerY;
//        marginOfError = turn;
//
//        rightFrontMotor.setPower(Range.clip(speedy-speedx-marginOfError,-1,1));
//        rightBackMotor.setPower(Range.clip(speedy-speedx-marginOfError,-1,1));
//        leftBackMotor.setPower(Range.clip(speedy+speedx+marginOfError,-1,1));
//        leftFrontMotor.setPower(Range.clip(speedy-speedx+marginOfError,-1,1));
//
//
//    }
}
