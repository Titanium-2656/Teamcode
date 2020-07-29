package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


@TeleOp(name="滑轨自检", group="2656 Test")
//@Disabled
public class LinearCraneTest extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();

    private CRServo lCraneServo = null;

    @Override
    public void runOpMode() {

        lCraneServo = hardwareMap.get(CRServo.class, "linearCraneServo");

        //向控制台输出数据：车辆以初始化完毕
        telemetry.addData("状态", "初始化完毕");
        telemetry.update();

        //等待驾驶员按下Start按钮
        waitForStart();
        runtime.reset();

        //机器人开始按照预设系统运动
        while (opModeIsActive()) {

            //测试如果LCrane能用
            if(gamepad1.dpad_right){
                lCraneServo.setPower(-0.8);
            }
            else{
                lCraneServo.setPower(0);
            }

            if(gamepad1.dpad_left){
                lCraneServo.setPower(0.8);
            }
            else{
                lCraneServo.setPower(0);
            }

            // Show the elapsed game time and wheel power.
            telemetry.addData("状态", "运行时间: " + runtime.toString());
            telemetry.update();
        }
    }
}