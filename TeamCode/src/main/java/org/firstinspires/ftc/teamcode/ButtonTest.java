package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


@TeleOp(name="按键自检", group="2656 Test")
//@Disabled
public class ButtonTest extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    private String button = null;

    @Override
    public void runOpMode() {
        //向控制台输出数据：车辆以初始化完毕
        telemetry.addData("状态", "初始化完毕");
        telemetry.update();

        //等待驾驶员按下Start按钮
        waitForStart();
        runtime.reset();

        //机器人开始按照预设系统运动
        while (opModeIsActive()) {

            if(gamepad1.left_bumper){
                button = "left bumper";
            }
            else if(gamepad1.right_bumper){
                button = "right bumper";
            }

            if(gamepad1.dpad_left){
                button = "dpad left";
            }
            else if(gamepad1.dpad_right){
                button = "dpad right";
            }
            else if(gamepad1.dpad_down){
                button = "dpad down";
            }
            else if(gamepad1.dpad_up){
                button = "dpad up";
            }

            if(gamepad1.a){
                button = "a";
            }
            else if(gamepad1.b){
                button = "b";
            }
            else if(gamepad1.x){
                button = "x";
            }
            else if(gamepad1.y){
                button = "y";
            }

            telemetry.update();

            // Show the elapsed game time and wheel power.
            telemetry.addData("按钮测试：", button);
            telemetry.addData("状态", "运行时间: " + runtime.toString());
            telemetry.update();
        }
    }
}