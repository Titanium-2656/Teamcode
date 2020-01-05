package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "Red_Foundation", group = "")

public class Red_Foundation extends LinearOpMode{

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor FrontLeftDrive = null;
    private DcMotor FrontRightDrive = null;
    private DcMotor BackLeftDrive = null;
    private DcMotor BackRightDrive = null;
    private DcMotor leftIntake = null;
    private DcMotor rightIntake = null;
    private Servo grab = null;


    private int RUNTIME_INTERVAL = 100;

    @Override
    public void runOpMode(){
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

        //底盘电机
        FrontLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        FrontRightDrive.setDirection(DcMotor.Direction.FORWARD);
        BackLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        BackRightDrive.setDirection(DcMotor.Direction.FORWARD);

        grab.setPosition(1);

        leftIntake.setPower(0);
        rightIntake.setPower(0);

        //向控制台输出数据：车辆以初始化完毕
        telemetry.addData("状态", "初始化完毕");
        telemetry.update();

        //等待驾驶员按下Start按钮
        waitForStart();
        runtime.reset();

        while(opModeIsActive()){

            //初始化状态，确保机器人不滑动，持续10毫秒
            FrontLeftDrive.setPower(0);
            FrontRightDrive.setPower(0);
            BackLeftDrive.setPower(0);
            BackRightDrive.setPower(0);

            sleep(10);

            //所有电机全速旋转400毫秒
            FrontLeftDrive.setPower(1);
            FrontRightDrive.setPower(1);
            BackLeftDrive.setPower(1);
            BackRightDrive.setPower(1);

            sleep(400);

            //所有电机停转，持续100毫秒
            FrontLeftDrive.setPower(0);
            FrontRightDrive.setPower(0);
            BackLeftDrive.setPower(0);
            BackRightDrive.setPower(0);

            sleep(RUNTIME_INTERVAL);






        }

    }
}
