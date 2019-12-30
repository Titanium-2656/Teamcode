package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name="Autonomous_Skystone", group="2656 Test")

public class Autonomous_Skystone extends LinearOpMode{

    //初始化所有电机变量
    DcMotor FrontLeftDrive = null;
    DcMotor FrontRightDrive = null;
    DcMotor BackLeftDrive = null;
    DcMotor BackRightDrive = null;
    DcMotor leftIntake = null;
    DcMotor rightIntake = null;

    @Override
    public void runOpMode() throws InterruptedException{


        //Hardware map 初始化所有电机数据
        FrontLeftDrive  = hardwareMap.get(DcMotor.class, "FrontLeftDrive");
        FrontRightDrive = hardwareMap.get(DcMotor.class, "FrontRightDrive");
        BackLeftDrive = hardwareMap.get(DcMotor.class, "BackLeftDrive");
        BackRightDrive = hardwareMap.get(DcMotor.class, "BackRightDrive");
        leftIntake = hardwareMap.dcMotor.get("leftIntake");
        rightIntake = hardwareMap.dcMotor.get(("rightIntake"));

        //向控制台输出可以继续
        telemetry.addData("状态", "硬件初始化完成");
        telemetry.update();


        //等Driver点下开始按键
        waitForStart();

        //让机器人平移往前走
        //TODO: 调下这段数据，看机器人是向左平移还是向右平移
        FrontLeftDrive.setPower(-0.4);
        FrontRightDrive.setPower(0.4);
        BackLeftDrive.setPower(0.4);
        BackRightDrive.setPower(-0.4);

        sleep(1000);

        //开始识别方块






        //向控制台输出信息
        telemetry.addData("状态","自动程序执行完毕");
        telemetry.update();







    }

}

