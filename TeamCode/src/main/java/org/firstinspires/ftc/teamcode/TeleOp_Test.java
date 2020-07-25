package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


@TeleOp(name="TeleOp Test", group="2656 Test")
//@Disabled
public class TeleOp_Test extends LinearOpMode {

    //创建所有马达变量
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor FrontLeftDrive = null;
    private DcMotor FrontRightDrive = null;
    private DcMotor BackLeftDrive = null;
    private DcMotor BackRightDrive = null;

    private DcMotor leftIntake = null;
    private  DcMotor rightIntake = null;

    private  DcMotor leftRise = null;
    private  DcMotor rightRise = null;

    private Servo leftStake = null;
    private Servo rightStake = null;

    private boolean isPosition1 = false;
    private  boolean isRoot1 = false;


    //Test unit variable
    private Servo frontRoot = null;
    private Servo backRoot  = null;

    private Servo frontGrab = null;
    private Servo backGrab = null;



    @Override
    public void runOpMode() {

        FrontLeftDrive  = hardwareMap.get(DcMotor.class, "FrontLeftDrive");
        FrontRightDrive = hardwareMap.get(DcMotor.class, "FrontRightDrive");
        BackLeftDrive = hardwareMap.get(DcMotor.class, "BackLeftDrive");
        BackRightDrive = hardwareMap.get(DcMotor.class, "BackRightDrive");

        leftIntake = hardwareMap.get(DcMotor.class, "leftIntake");
        rightIntake = hardwareMap.get(DcMotor.class, "rightIntake");

        leftRise = hardwareMap.get(DcMotor.class, "leftRise");
        rightRise = hardwareMap.get(DcMotor.class, "rightRise");

        leftStake = hardwareMap.get(Servo.class, "leftStake");
        rightStake = hardwareMap.get(Servo.class, "rightStake");


        //Test unit variable
        frontRoot = hardwareMap.get(Servo.class, "FrontRoot");
        backRoot = hardwareMap.get(Servo.class, "BackRoot");

        frontGrab = hardwareMap.get(Servo.class, "FrontGrab");

        //设定电机由编码器管理
        FrontLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FrontRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BackLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BackRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftIntake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightIntake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        leftRise.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightRise.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        leftIntake.setDirection(DcMotor.Direction.FORWARD);
        rightIntake.setDirection(DcMotor.Direction.REVERSE);

        leftRise.setDirection(DcMotor.Direction.FORWARD);
        rightRise.setDirection(DcMotor.Direction.FORWARD);

        //Set test variable turn direction
        backRoot.setDirection(Servo.Direction.REVERSE);
        frontGrab.setDirection(Servo.Direction.REVERSE);

        leftIntake.setPower(0);
        rightIntake.setPower(0);

        leftRise.setPower(0);
        rightRise.setPower(0);

        leftStake.setPosition(0.3);
        rightStake.setPosition(0.3);

        //Set test variable position.
        //frontRoot.setPosition(0);
        //backRoot.setPosition(0);

        frontGrab.setPosition(0);

        //向控制台输出数据：车辆以初始化完毕
        telemetry.addData("状态", "初始化完毕");
        telemetry.update();

        //等待驾驶员按下Start按钮
        waitForStart();
        runtime.reset();

        //机器人开始按照预设系统运动
        while (opModeIsActive()) {

            calculateDrivePower();
            intake();
            rise();
            activateStake();
            activateRoot();

            // Show the elapsed game time and wheel power.
            telemetry.addData("状态", "运行时间: " + runtime.toString());
            telemetry.update();
        }
    }

    public void activateRoot(){
        if(gamepad1.dpad_right){
            if(isRoot1){
                backRoot.setPosition(0);
                frontRoot.setPosition(0);
                isRoot1 = false;
            }
            else{
                frontRoot.setPosition(0.5);
                backRoot.setPosition(0.4);
                frontGrab.setPosition(-0.1);
                isRoot1 = true;
            }
        }
    }

    public void activateStake(){
        if(gamepad1.dpad_left){
            if(isPosition1){
             leftStake.setPosition(0);
             rightStake.setPosition(0);
             isPosition1 = false;
            }
            else{
                leftStake.setPosition(0.3);
                rightStake.setPosition(0.3);
                isPosition1 = true;
            }
        }
    }
    public void rise(){
        if(gamepad1.dpad_up){
            leftRise.setPower(0.8);
            rightRise.setPower(0.8);
        }
        else{
            leftRise.setPower(0);
            rightRise.setPower(0);
        }

        if(gamepad1.dpad_down){
            leftRise.setPower(-0.4);
            rightRise.setPower(-0.4);
        }
        else{
            leftRise.setPower(0);
            rightRise.setPower(0);
        }
    }

    public void intake(){
        if(gamepad1.left_bumper == true){
            leftIntake.setPower(-1);
            rightIntake.setPower(-1);
        }
        else {
            leftIntake.setPower(0);
            rightIntake.setPower(0);
        }

        if(gamepad1.right_bumper){
            leftIntake.setPower(1);
            rightIntake.setPower(1);
        }
    }


    public void calculateDrivePower(){
        //--------New Code-------------

        //Scaling the joystick inputs for sensitivity
        float y_raw = gamepad1.left_stick_y * 0.8f;
        float x_raw = gamepad1.left_stick_x * 0.8f;
        float z_raw = gamepad1.right_stick_x * 0.8f;
        float xscale = (float) 0.75;
        float yscale = (float) 0.75;
        float zscale = (float) 0.65;
        float x = (xscale * (float) Math.pow(x_raw, 7.0) + (1- xscale) * x_raw);
        float y = -(yscale * (float) Math.pow(y_raw, 7.0) + (1 - yscale) * y_raw);
        float z = (zscale * (float) Math.pow(z_raw, 7.0) + (1 - zscale) * z_raw);

        //Mapping joystick values on motors
        float frontLeft = x + y + z;
        float frontRight = x - y + z;
        float backLeft = -x + y + z;
        float backRight = -x -y + z;

        //Initialize motor values

        float[] joystickValues = new float[]{frontRight, backLeft, frontLeft, backRight};
        float Max = Math.abs(frontRight);
        for(int index = 1; index < 4; index++){
            if(Math.abs(joystickValues[index]) > Max){
                Max = Math.abs(joystickValues[index]);
            }
        }

        if(Max > 1){
            frontRight /= Max;
            backLeft /= Max;
            frontLeft /= Max;
            backRight /= Max;
        }

        FrontLeftDrive.setPower(frontLeft);
        FrontRightDrive.setPower(frontRight);
        BackLeftDrive.setPower(backLeft);
        BackRightDrive.setPower(backRight);

    }
}