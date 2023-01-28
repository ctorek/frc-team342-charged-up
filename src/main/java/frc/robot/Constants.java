// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {


  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }

  
  public static class DriveConstants{
    public static final int FRONT_LEFT_MOTOR = 1;
    public static final int FRONT_RIGHT_MOTOR = 2;
    public static final int BACK_LEFT_MOTOR = 3;
    public static final int BACK_RIGHT_MOTOR = 4;

    public static final double NORMAL_SPEED = 0.8;
    public static final double SLOW_SPEED = 0.4; 
  }

  /*
   * todo: add actual values for all of the constants, they are currently placeholders
   */
  public static class LimelightConstants{
    public static final double HEIGHT_TO_LOW = 0.0;
    public static final double HEIGHT_TO_MED = 0.0;
    public static final double HEIGHT_TO_HIGH = 0.0;
    public static final double MAX_VERT_OFFSET_FOR_LOW = 30.0;
    public static final double MAX_VERT_OFFSET_FOR_MED = 60.0;
    public static final double MAX_VERT_OFFSET_FOR_HIGH = 90.0;
  }

}
