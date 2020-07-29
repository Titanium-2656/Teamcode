package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous(name="AutoControl", group="2656 Test")
public class AutoControl extends LinearOpMode  {

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor FrontLeftDrive = null;
    private DcMotor FrontRightDrive = null;
    private DcMotor BackLeftDrive = null;
    private DcMotor BackRightDrive = null;

    private DcMotor leftIntake = null;
    private  DcMotor rightIntake = null;

    private  DcMotor rightRise = null;

    private Servo leftStake = null;
    private Servo rightStake = null;

    private Servo frontRoot = null;
    private Servo backRoot  = null;


    @Override
    public void runOpMode() throws InterruptedException{

        FrontLeftDrive  = hardwareMap.get(DcMotor.class, "FrontLeftDrive");
        FrontRightDrive = hardwareMap.get(DcMotor.class, "FrontRightDrive");
        BackLeftDrive = hardwareMap.get(DcMotor.class, "BackLeftDrive");
        BackRightDrive = hardwareMap.get(DcMotor.class, "BackRightDrive");

        leftIntake = hardwareMap.get(DcMotor.class, "leftIntake");
        rightIntake = hardwareMap.get(DcMotor.class, "rightIntake");

        rightRise = hardwareMap.get(DcMotor.class, "rightRise");

        leftStake = hardwareMap.get(Servo.class, "leftStake");
        rightStake = hardwareMap.get(Servo.class, "rightStake");


        //Test unit variable
        frontRoot = hardwareMap.get(Servo.class, "FrontRoot");
        backRoot = hardwareMap.get(Servo.class, "BackRoot");

        FrontLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FrontRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BackLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BackRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftIntake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightIntake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        rightRise.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        leftIntake.setDirection(DcMotor.Direction.FORWARD);
        rightIntake.setDirection(DcMotor.Direction.REVERSE);

        rightRise.setDirection(DcMotor.Direction.REVERSE);

        //Set test variable turn direction
        backRoot.setDirection(Servo.Direction.REVERSE);

        leftIntake.setPower(0);
        rightIntake.setPower(0);

        rightRise.setPower(0);

        leftStake.setPosition(0.3);
        rightStake.setPosition(0.3);

        //Set test variable position.
        frontRoot.setPosition(0);
        backRoot.setPosition(0);

        telemetry.addData("状态", "初始化完毕");
        telemetry.update();


        waitForStart();



    }
}