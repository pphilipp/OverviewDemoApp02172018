package philippbugayevskiy.example.com.presentation.debug;

import philippbugayevskiy.example.com.data.datasource.Constants;

public enum EnvironmentEndpoint {
    TEST("Test", Constants.ENDPOINT_TEST, 0),
    DEV("Development", Constants.ENDPOINT_DEV, 1);

    public final String name;
    public final String endpoint;
    public final int position;

    EnvironmentEndpoint(String name, String endpoint, int position) {
        this.name = name;
        this.endpoint = endpoint;
        this.position = position;
    }

    @Override
    public String toString() {
        return name;
    }

    public static EnvironmentEndpoint from(String endpoint) {
        for (EnvironmentEndpoint value : values()) {
            if (value.endpoint != null && value.endpoint.equals(endpoint)) {
                return value;
            }
        }
        return TEST;
    }

    public static EnvironmentEndpoint from(int position) {
        for (EnvironmentEndpoint value : values()) {
            if (value.position == position) {
                return value;
            }
        }
        return TEST;
    }

}
