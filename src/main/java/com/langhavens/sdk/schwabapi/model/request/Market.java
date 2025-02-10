package com.langhavens.sdk.schwabapi.model.request;

public enum Market {

    EQUITY, OPTION, BOND, FUTURE, FOREX;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }

}
