package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


@TeleOp(name="上升修复程序", group="2656 Test")
//@Disabled
public class RiserFix extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();

    private  DcMotor leftRise = null;
    private  DcMotor rightRise = null;

    @Override
    public void runOpMode() {
        leftRise = hardwareMap.get(DcMotor.class, "leftRise");
        rightRise = hardwareMap.get(DcMotor.class, "rightRise");

        leftRise.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightRise.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        leftRise.setDirection(DcMotor.Direction.FORWARD);
        rightRise.setDirection(DcMotor.Direction.FORWARD);

        leftRise.setPower(0);
        rightRise.setPower(0);

        //向控制台输出数据：车辆以初始化完毕
        telemetry.addData("状态", "初始化完毕");
        telemetry.update();

        //等待驾驶员按下Start按钮
        waitForStart();
        runtime.reset();

        //机器人开始按照预设系统运动
        while (opModeIsActive()) {
            rise();

            // Show the elapsed game time and wheel power.
            telemetry.addData("状态", "运行时间: " + runtime.toString());
            telemetry.update();
        }
    }

    public void rise(){
        if(gamepad1.dpad_up){
            rightRise.setPower(0.8);
        }

        if(gamepad1.dpad_down){
            rightRise.setPower(-0.4);
        }

        if(gamepad1.dpad_right) {
            leftRise.setPower(0.8);
        }
        else{
            leftRise.setPower(0);
        }

        if(gamepad1.dpad_left) {
            leftRise.setPower(-0.4);
        }
        else{
            rightRise.setPower(0);
        }
    }

    }