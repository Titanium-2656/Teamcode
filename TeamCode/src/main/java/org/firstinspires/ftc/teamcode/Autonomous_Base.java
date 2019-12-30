package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;


@Autonomous(name="Test", group="2656 Test")
public class Autonomous_Base extends LinearOpMode  {

    //初始化所有电机变量
    DcMotor FrontLeftDrive = null;
    DcMotor FrontRightDrive = null;
    DcMotor BackLeftDrive = null;
    DcMotor BackRightDrive = null;
    DcMotor leftIntake = null;
    DcMotor rightIntake = null;
    @Override
    public void runOpMode() throws InterruptedException{


        //等Driver点下开始按键
        waitForStart();

        //Hardware map 初始化所有电机数据
        FrontLeftDrive  = hardwareMap.get(DcMotor.class, "FrontLeftDrive");
        FrontRightDrive = hardwareMap.get(DcMotor.class, "FrontRightDrive");
        BackLeftDrive = hardwareMap.get(DcMotor.class, "BackLeftDrive");
        BackRightDrive = hardwareMap.get(DcMotor.class, "BackRightDrive");
        leftIntake = hardwareMap.dcMotor.get("leftIntake");
        rightIntake = hardwareMap.dcMotor.get(("rightIntake"));


        //让机器人往前走一段距离
        FrontLeftDrive.setPower(0.4);
        FrontRightDrive.setPower(-0.4);
        BackLeftDrive.setPower(0.4);
        BackRightDrive.setPower(-0.4);

        sleep(700);

        //停车
        FrontLeftDrive.setPower(-0.4);
        FrontRightDrive.setPower(0.4);
        BackLeftDrive.setPower(-0.4);
        BackRightDrive.setPower(0.4);

        sleep(100);


        //原地旋转
        FrontLeftDrive.setPower(-0.4);
        FrontRightDrive.setPower(-0.4);
        BackLeftDrive.setPower(-0.4);
        BackRightDrive.setPower(-0.4);

        sleep(3200);







    }
}
