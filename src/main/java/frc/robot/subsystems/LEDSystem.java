// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.LEDConstants.*;

import java.util.List;

public class LEDSystem extends SubsystemBase implements Testable {
  /** Creates a new AddressableLEDSubsystem. */

  public enum ColorType {
    YELLOW,
    PURPLE,
    RED;
  }

  private final AddressableLED LED;
  private final AddressableLEDBuffer LEDBuffer;

  public LEDSystem() {
    LED = new AddressableLED(PWM_PORT);
    LEDBuffer = new AddressableLEDBuffer(LENGTH);
    LED.setLength(LEDBuffer.getLength());
    LED.setData(LEDBuffer);
    LED.start();
  }

  /**
   * This method sets all the LED groups (Human Player & Driver) to off
   */
  public void allOff() {
    // Sets each LED to off
    for(int i = 0; i < LEDBuffer.getLength(); i++) {
      LEDBuffer.setHSV(i, 0, 0, 0);
    }
    LED.setData(LEDBuffer);
  }

  public void driverOff() {
    // driver panel is indices 0 to DRIVER_START_RANGE
    for (int i = 0; i < DRIVER_START_RANGE; i++) {
      LEDBuffer.setHSV(i, 0, 0, 0);
    }

    LED.setData(LEDBuffer);
  }

  public void humanPlayerOff() {
    // human player panel is indices DRIVER_START_RANGE to the end of the buffer
    for (int i = DRIVER_START_RANGE; i < LEDBuffer.getLength(); i++) {
      LEDBuffer.setHSV(i, 0, 0, 0);
    }

    LED.setData(LEDBuffer);
  }

  /**
   * This method sets the Human Player LED group to the Yellow Color or Purple Color
   */
  public void driverColorMethod(ColorType colortype) {
    //If the colortype requested is yellow, then it will set the Human Player group color to yellow
    //If the colortype requested is purple, then it will set the Human Player group color to purple
    if(ColorType.YELLOW == colortype)
    {
      //Sets each LED to Yellow
      for(int i = 0; i < DRIVER_START_RANGE; i++)
      {
        LEDBuffer.setHSV(i, YELLOW_H, YELLOW_S, YELLOW_V);
      }
      LED.setData(LEDBuffer);
    }
    else if(ColorType.PURPLE == colortype)
    {
      //Sets each LED to Purple
      for(int i = 0; i < DRIVER_START_RANGE; i++) {
        LEDBuffer.setHSV(i, PURPLE_H, PURPLE_S, PURPLE_V);
      }
      LED.setData(LEDBuffer);
    }
    else if(ColorType.RED == colortype)
    {
      for(int i = 0; i < DRIVER_START_RANGE; i++)
      {
        LEDBuffer.setHSV(i, RED_H, RED_S, 70);
      }
      LED.setData(LEDBuffer);
    }
  }

  /**
   * This method sets the Driver LED group to a specifed color
   */
  public void humanColorMethod(ColorType colorType) {
    if(ColorType.YELLOW == colorType)
    {
      //Sets each LED to Yellow
      for(int i = DRIVER_START_RANGE; i < LEDBuffer.getLength(); i++)
      {
        LEDBuffer.setHSV(i, YELLOW_H, YELLOW_S, YELLOW_V);
      }
      LED.setData(LEDBuffer);
    }
    else if(ColorType.PURPLE == colorType)
    {
      //Sets each LED to Purple
      for(int i = DRIVER_START_RANGE; i < LEDBuffer.getLength(); i++) {
        LEDBuffer.setHSV(i, PURPLE_H, PURPLE_S, PURPLE_V);
      }
      LED.setData(LEDBuffer);
    }
  }

  /**
   * show a color on the led panel on the front of the robot
   * @param colorType purple or yellow
   * @return
   */
  public CommandBase HumanColor(ColorType colorType) {
    // subsystem requirement is PURPOSEFULLY OMITTED
    return new FunctionalCommand(
      // start
      () -> {},
      // execute
      () -> {
        this.humanColorMethod(colorType);
      },
      // end
      (Boolean interrupted) -> {
        this.humanPlayerOff();
      },
      // is finished
      () -> { return false; }
    );
  }

  /**
   * show a color on the led panel on the back of the robot
   * @param colorType purple, yellow, or red
   * @return 
   */
  public CommandBase DriverColor(ColorType colorType) {
    // subsystem requirement is PURPOSEFULLY OMITTED
    return new FunctionalCommand(
      // start
      () -> {},
      // execute
      () -> {
        this.driverColorMethod(colorType);
      },
      // end
      (Boolean interrupted) -> {
        this.driverOff();
      },
      // is finished
      () -> { return false; }
    );
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public List<Connection> hardwareConnections() {
    // cannot check for leds bc they are connected over pwm
    return List.of();
  }

  @Override
  public CommandBase testRoutine() {
    return Commands.sequence(
      // show purple on both leds
      new RunCommand(
        () -> {
          driverColorMethod(ColorType.PURPLE);
          humanColorMethod(ColorType.PURPLE);
        }
      ).withTimeout(2.0),
      // show yellow on both leds
      new RunCommand(
        () -> {
          driverColorMethod(ColorType.YELLOW);
          humanColorMethod(ColorType.YELLOW);
        }
      ).withTimeout(2.0),
      // turn all leds off
      new InstantCommand(
        () -> allOff()
      )
    );
  }
}