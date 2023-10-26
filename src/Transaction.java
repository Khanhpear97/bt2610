public class Transaction {
    private String nameTransaction;
    private String kindOfTransaction;
    String code;
    private int money;
    private String time;

    public Transaction(String nameTransaction, String kindOfTransaction, String code, int money, String time) {
        this.nameTransaction = nameTransaction;
        this. kindOfTransaction = kindOfTransaction;
        this.code = code;
        this.money = money;
        this.time = time;
    }

    public String getNameTransaction() {
        return nameTransaction;
    }

    public String getKindOfTransaction() {
        return kindOfTransaction;
    }

    public int getMoney() {
        return money;
    }

    public String getTime() {
        return time;
    }

    public String getCode() {
        return code;
    }

    public void setNameTransaction(String nameTransaction) {
        this.nameTransaction = nameTransaction;
    }

    public void setKindOfTransaction(String kindOfTransaction) {
        this.kindOfTransaction = kindOfTransaction;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInfo() {
        return "Ten giao dich: " + getNameTransaction() + " Loai giao dich: " + getKindOfTransaction() +
               " Ma giao dich " + getCode() + " So tien: " + getMoney() + " Thoi gian: " + getTime();
    }
}
