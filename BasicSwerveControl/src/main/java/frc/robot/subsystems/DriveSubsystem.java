// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import java.util.function.Supplier;
import frc.robot.DriveConstants;

public class DriveSubsystem extends SubsystemBase {
  private static final SwerveDriveKinematics kinematics_obj = new SwerveDriveKinematics(
    DriveConstants.MODULE_POS_F_LEFT, DriveConstants.MODULE_POS_F_RIGHT,
    DriveConstants.MODULE_POS_B_LEFT, DriveConstants.MODULE_POS_B_RIGHT
  );

  private final Supplier<Boolean> focButton;

  private final Supplier<Rotation2d> magnetometer;

  /** Creates a new ExampleSubsystem. */
  public DriveSubsystem(Supplier<Boolean> focButton, Supplier<Rotation2d> magnetometer) {
    this.focButton = focButton;
    this.magnetometer = magnetometer;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  public void stop() {
    System.out.println("DRIVETRAIN STOPPING...");
  }

  public void setChassisSpeeds(ChassisSpeeds chassisSpeeds) {
    SwerveModuleState[] moduleStates = DriveSubsystem.kinematics_obj.toSwerveModuleStates(chassisSpeeds);
    this.setModuleStates(moduleStates);
  }

  public void setModuleStates(SwerveModuleState[] moduleStates) {
    if (this.focButton.get()) {
      this.setModuleStatesFOC(moduleStates);
    } else {
      this.setModuleStatesNormal(moduleStates);
    }
  }

  public void setModuleStatesFOC(SwerveModuleState[] moduleStates) {
    Rotation2d thetaOffset = this.magnetometer.get(); // TODO
    SwerveModuleState[] newStates = new SwerveModuleState[moduleStates.length];
    for (int i = 0; i < newStates.length; i++) {
      newStates[i] = new SwerveModuleState(
        moduleStates[i].speedMetersPerSecond,
        moduleStates[i].angle.minus(thetaOffset)
      );
    }
    this.setModuleStatesNormal(newStates);
  }

  public void setModuleStatesNormal(SwerveModuleState[] moduleStates) {
    for (int i = 0; i < 4; i++) {
      System.out.println(
        "SWERVE MODULE STATE " + i + ": " +
        moduleStates[i].speedMetersPerSecond + " @angle " + moduleStates[i].angle
      );
    }
  }
}
