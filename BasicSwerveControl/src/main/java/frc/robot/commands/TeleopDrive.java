// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.InputSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class TeleopDrive extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveSubsystem drive;
  private final InputSubsystem input;

  /**
   * Creates a new ExampleCommand.
   *
   * @param drive The drive subsystem used by this command.
   * @param input The input subsystem used by this command.
   */
  public TeleopDrive(DriveSubsystem drive, InputSubsystem input) {
    this.drive = drive;
    this.input = input;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drive, input);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() { System.out.println("TELEOPDRIVE:SCHEDULE!"); }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    input.controlDrivetrain(drive);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drive.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
