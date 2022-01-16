package frc.team3128.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team3128.subsystems.Hopper;

public class HopperPiston extends CommandBase {
    private final Hopper m_hopper;
    private final BooleanSupplier isShooting;
    private boolean isEjected;

    public HopperPiston(Hopper hopper, BooleanSupplier isShooting) {
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
        // also need to check if new ball and if so run hopper to get ball to front (certain amount of encoders)
            // that would not be within this if else is shooting though
        isEjected = m_hopper.getEjected();

        if (isShooting.getAsBoolean()) {
            if (isEjected) m_hopper.retractPiston();
            m_hopper.runHopper();
        } else {
            if (!isEjected) m_hopper.ejectPiston();
        }
    }

    @Override
    public void end(boolean interrupted) {
        m_hopper.turnPistonOff();
        m_hopper.stopHopper();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}