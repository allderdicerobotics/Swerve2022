package frc.robot;

import edu.wpi.first.math.geometry.Translation2d;

public final class DriveConstants {

    public static final double MODULE_POS_COMPONENT = 0.381;
    public static final Translation2d MODULE_POS_F_LEFT = new Translation2d(MODULE_POS_COMPONENT, MODULE_POS_COMPONENT);
    public static final Translation2d MODULE_POS_F_RIGHT = new Translation2d(MODULE_POS_COMPONENT, -MODULE_POS_COMPONENT);
    public static final Translation2d MODULE_POS_B_LEFT = new Translation2d(-MODULE_POS_COMPONENT, MODULE_POS_COMPONENT);
    public static final Translation2d MODULE_POS_B_RIGHT = new Translation2d(-MODULE_POS_COMPONENT, -MODULE_POS_COMPONENT);

    public static final double CONTROL_SCALAR_X = 1.0;
    public static final double CONTROL_SCALAR_Y = 1.0;
    public static final double CONTROL_SCALAR_OMEGA = 1.0;
}
