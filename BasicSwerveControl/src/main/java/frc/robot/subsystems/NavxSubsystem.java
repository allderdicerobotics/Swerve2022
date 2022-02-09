// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class NavxSubsystem extends SubsystemBase {
  
  public AHRS ahrs;
  
  /** Creates a new NavxSubsystem. */
  public NavxSubsystem() {
    this.ahrs = new AHRS(Port.kUSB1);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  public Rotation2d getMagnetometer() {
      return ahrs.getRotation2d();
  }

  public Pose2d getPose2d() {
    return new Pose2d(
      ahrs.getDisplacementX(), ahrs.getDisplacementY(),
      ahrs.getRotation2d()
    );
  }
}
