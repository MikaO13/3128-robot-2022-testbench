package frc.team3128.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team3128.subsystems.Hopper;

public class HopperPhotoelectrics extends CommandBase {
    private final Hopper m_hopper;
    private final BooleanSupplier isShooting;
    private boolean isTop, isBottom;

    public HopperPhotoelectrics(Hopper hopper, BooleanSupplier isShooting) {
        m_hopper = hopper;
        this.isShooting = isShooting;

        addRequirements(m_hopper);
    }

    @Override
    public void initialize() {
        //
    }

    @Override
    public void execute() {
        isTop = m_hopper.getTop();
        isBottom = m_hopper.getBottom();

        if (isShooting.getAsBoolean()) {
            m_hopper.runHopper();
        } else if (isBottom && !isTop) {
            m_hopper.runHopper();
        } else {
            m_hopper.stopHopper();
        }
    }

    @Override
    public void end(boolean interrupted) {
        m_hopper.stopHopper();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}