// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.DriveConstants;

public class InputSubsystem extends SubsystemBase {

  private Joystick actuallyController;

  private int[] axes1 = {
        Constants.JOYSTICK_1X_PORTNUM,
        Constants.JOYSTICK_1Y_PORTNUM
  };
  private int[] axes2 = {
        Constants.JOYSTICK_2X_PORTNUM,
        Constants.JOYSTICK_2Y_PORTNUM
  };

  /** Creates a new ExampleSubsystem. */
  public InputSubsystem(Joystick actuallyController) {
      this.actuallyController = actuallyController;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  public void controlDrivetrain(DriveSubsystem drive) {
      double[] stick1 = this.getJoystickCoords(1);
      double[] stick2 = this.getJoystickCoords(2);
      ChassisSpeeds chassisSpeeds = this.convertToChassisSpeeds(stick1, stick2);
      drive.setChassisSpeeds(chassisSpeeds);
  }

  public double[] getJoystickCoords(int which) {
      int[] axes;
      switch (which) {
          case 1:
            axes = this.axes1;
            break;
          case 2:
            axes = this.axes2;
            break;
          default:
            System.out.println("WARNING!!!! UNKNOWN JOYSICK NUMBER (by default chose 1, hopefully thats okay)");
            axes = this.axes1;
            break;
      }
      int dim = 2;
      double[] coords = new double[dim];
      for (int i = 0; i < 2; i++) {
          coords[i] = this.actuallyController.getRawAxis(axes[i]);
     }
     return coords;
  }

  public ChassisSpeeds convertToChassisSpeeds(double[] stick1, double[] stick2) {
      // IN PROGRESS
      // System.out.println("stick1 <- " + stick1[0] + ", " + stick1[1]);
      // System.out.println("stick2 <- " + stick2[0] + ", " + stick2[1]);
      double xPart = stick1[0] * DriveConstants.CONTROL_SCALAR_X;
      double yPart = stick1[1] * DriveConstants.CONTROL_SCALAR_Y;
      double omegaPart = stick2[0] * DriveConstants.CONTROL_SCALAR_OMEGA;
      return new ChassisSpeeds(xPart, yPart, omegaPart);
  }
}
