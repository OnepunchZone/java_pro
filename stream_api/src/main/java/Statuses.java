public enum Statuses {
    OPEN("Открыта"),
    IN_PROGRESS("В работе"),
    CLOSED("Закрыта");

    private String val;

    Statuses(String val) {
        this.val = val;
    }

    public String getVal() {
        return val;
    }
}
