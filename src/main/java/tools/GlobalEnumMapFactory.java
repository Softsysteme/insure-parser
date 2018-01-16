package tools;

public enum GlobalEnumMapFactory {
    INSTANCE;

    public GlobalEnumMap getEnumMap() {
        return new GlobalEnumMap();
    }

}
