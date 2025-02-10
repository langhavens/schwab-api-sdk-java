package com.langhavens.sdk.schwabapi.model.request;

public enum Projection {
    SYMBOL_SEARCH,
    SYMBOL_REGEX,
    DESC_SEARCH,
    DESC_REGEX,
    SEARCH,
    FUNDAMENTAL;

    @Override
    public String toString() {
        return this.name().toLowerCase().replace("_", "-");
    }
}
