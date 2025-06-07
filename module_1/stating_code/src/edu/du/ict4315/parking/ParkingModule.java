package edu.du.ict4315.parking;

import com.google.inject.AbstractModule;

public class ParkingModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(IPermitManager.class).to(PermitManager.class);
        bind(ITransactionManager.class).to(TransactionManager.class);
    }
}