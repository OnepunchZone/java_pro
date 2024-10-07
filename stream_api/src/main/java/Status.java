public enum Status {
    OPEN("Открыта"),
    IN_PROGRESS("В работе"),
    CLOSED("Закрыта");

    private String val;

    Status(String val) {
        this.val = val;
    }

    public String getVal() {
        return val;
    }
}
