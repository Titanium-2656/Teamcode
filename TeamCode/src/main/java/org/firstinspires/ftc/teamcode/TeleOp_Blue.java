package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


@TeleOp(name="Blue TeleOp", group="")
//@Disabled
public class TeleOp_Blue extends LinearOpMode {

    //创建所有马达变量
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor FrontLeftDrive = null;
    private DcMotor FrontRightDrive = null;
    private DcMotor BackLeftDrive = null;
    private DcMotor BackRightDrive = null;
    private DcMotor leftIntake = null;
    private DcMotor rightIntake = null;
    private Servo grab = null;


    //让前面的两个电机吸取方块
    public void intake(){
        if(gamepad1.left_bumper == true){
            leftIntake.setPower(1);
            rightIntake.setPower(-1);
        }
        else {
            leftIntake.setPower(0);
            rightIntake.setPower(0);
        }
    }

    //创建拖动地基的方法，该方法仅在自动方法中触发
    public void grab(){
        if(gamepad1.dpad_left == true){
            grab.setPosition(1);
        }

        if(gamepad1.dpad_right == true){
            grab.setPosition(-1);
        }
    }


    @Override
    public void runOpMode() {

        //底盘电机
        FrontLeftDrive  = hardwareMap.get(DcMotor.class, "FrontLeftDrive");
        FrontRightDrive = hardwareMap.get(DcMotor.class, "FrontRightDrive");
        BackLeftDrive = hardwareMap.get(DcMotor.class, "BackLeftDrive");
        BackRightDrive = hardwareMap.get(DcMotor.class, "BackRightDrive");

        //吸取方块电机
        leftIntake = hardwareMap.dcMotor.get("leftIntake");
        rightIntake = hardwareMap.dcMotor.get("rightIntake");

        //拖动地基舵机
        grab = hardwareMap.servo.get("grab");


        //设定电机由编码器管理
        FrontLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FrontRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BackLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BackRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //设定电机旋转方向

        //底盘电机
//        FrontLeftDrive.setDirection(DcMotor.Direction.REVERSE);
//        FrontRightDrive.setDirection(DcMotor.Direction.FORWARD);
//        BackLeftDrive.setDirection(DcMotor.Direction.REVERSE);
//        BackRightDrive.setDirection(DcMotor.Direction.FORWARD);

        //吸取方块电机
        leftIntake.setDirection(DcMotor.Direction.FORWARD);
        rightIntake.setDirection(DcMotor.Direction.REVERSE);

        //设定电机初始化速度和位置

        //拖动地基舵机
        grab.setPosition(1);

        //吸取方块电机
        leftIntake.setPower(0);
        rightIntake.setPower(0);

        //向控制台输出数据：车辆以初始化完毕
        telemetry.addData("状态", "初始化完毕");
        telemetry.update();

        //等待驾驶员按下Start按钮
        waitForStart();
        runtime.reset();

        //机器人开始按照预设系统运动
        while (opModeIsActive()) {



            //Scaling the joystick inputs for sensitivity
            float y_raw = gamepad1.left_stick_y * 0.85f;
            float x_raw = gamepad1.left_stick_x * 0.85f;
            float z_raw = gamepad1.right_stick_x * 0.7f;
            float xscale = (float) 0.85;
            float yscale = (float) 0.85;
            float zscale = (float) 0.75;
            float x = -(xscale * (float) Math.pow(x_raw, 7.0) + (1- xscale) * x_raw);
            float y = yscale * (float) Math.pow(y_raw, 7.0) + (1 - yscale) * y_raw;
            float z = -(zscale * (float) Math.pow(z_raw, 7.0) + (1 - zscale) * z_raw);

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


//
//
//
//            double leftDrive = -gamepad1.left_stick_y;
//            double rightDrive = -gamepad1.right_stick_y;
//            double leftTurn = gamepad1.left_stick_x;
//            double rightTurn = gamepad1.right_stick_x;
//
//            //This sets the left stick y variable to robot forward/backward movement
//            FrontLeftDrive.setPower(leftDrive);
//            FrontRightDrive.setPower(leftDrive);
//            BackLeftDrive.setPower(leftDrive);
//            BackRightDrive.setPower(leftDrive);
//
//            //This sets the left stick x variable to robot translate movement
//            FrontLeftDrive.setPower(leftTurn);
//            FrontRightDrive.setPower(-leftTurn);
//            BackLeftDrive.setPower(-leftTurn);
//            BackRightDrive.setPower(leftTurn);
//
//            //This sets the right stick x variable to robot rotation movement.
//            FrontLeftDrive.setPower(rightTurn);
//            FrontRightDrive.setPower(-rightTurn);
//            BackLeftDrive.setPower(rightTurn);
//            BackRightDrive.setPower(-rightTurn);

//            if(gamepad1.x) {
//                //intakePower = !intakePower;
//                intakePower = !intakePower;
//                counter++;
//            }
//
//            if(intakePower) {
//                leftIntake.setPower(-1);
//                rightIntake.setPower(-1);
//            }
//            else {
//                leftIntake.setPower(0);
//                rightIntake.setPower(0);
//            }


            //前后吸取代码
            intake();

            grab();





            // Show the elapsed game time and wheel power.
            telemetry.addData("状态", "运行时间: " + runtime.toString());
//            telemetry.addData("intakeStatus: ",intakePower+";  "+counter);
            telemetry.update();
            }
        }
    }
