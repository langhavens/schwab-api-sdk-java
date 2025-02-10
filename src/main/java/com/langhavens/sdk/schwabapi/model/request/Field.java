package com.langhavens.sdk.schwabapi.model.request;

public enum Field {

    QUOTE, FUNDAMENTAL, EXTENDED, REFERENCE, REGULAR;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }

}
