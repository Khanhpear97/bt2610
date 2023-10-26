import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class TransactionManager {
    private ArrayList<Transaction> transactionManager;

    public TransactionManager() {
        transactionManager = new ArrayList<Transaction>();
    }

    public void addTransaction() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap ten giao dich:");
        String name = scanner.nextLine();
        System.out.println("Nhap loai giao dich:");
        String kind = scanner.nextLine();
        boolean checkCode;
        String code;
        do {
            checkCode = true;
            System.out.println("Nhap ma giao dich:");
            code = scanner.nextLine();
            for (Transaction transaction: transactionManager) {
                if (transaction.getCode().equals(code)) {
                    checkCode = false;
                    break;
                }
            }
        } while (!checkCode);
        System.out.println("Nhap so tien:");
        int money = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Nhap thoi gian:");
        String time = scanner.nextLine();
        Transaction transaction = new Transaction(name, kind, code, money, time);
        transactionManager.add(transaction);
    }

    public void removeTransaction() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap ma giao dich can xoa");
        String removeCode = scanner.nextLine();
        for (Transaction transaction: transactionManager) {
            if (transaction.getCode().equals(removeCode)) {
                transactionManager.remove(transaction);
                break;
            }
        }
    }

    public void checkTransactionManager() {
        for (Transaction transaction: transactionManager) {
            System.out.println(transaction.getInfo());
        }
    }

    public void editTransaction() {
        Scanner scanner = new Scanner(System.in);
        int edit;
        System.out.println("Nhap ma giao dich can sua");
        String editCode = scanner.nextLine();
        for (Transaction transaction: transactionManager) {
            if (transaction.getCode().equals(editCode)) {
                do {
                    System.out.println("Nhap 1 de thay doi ten giao dich:");
                    System.out.println("Nhap 2 de thay doi loai giao dich:");
                    System.out.println("Nhap 3 de thay doi so tien:");
                    System.out.println("Nhap 4 de thay doi thoi gian:");
                    edit = scanner.nextInt();

                    switch (edit) {
                        case 1:
                            editTransactionName(transaction, scanner);
                            return;
                        case 2:
                            editKindOfTransaction(transaction, scanner);
                            return;
                        case 3:
                            editMoney(transaction, scanner);
                            return;
                        case 4:
                            editTime(transaction, scanner);
                            return;
                    }
                } while (true);
            } else {
                System.out.println("Khong tim thay giao dich");
            }
        }
    }

    private static void editTime(Transaction transaction, Scanner scanner) {
        scanner.nextLine();
        System.out.println("Nhap thoi gian moi:");
        String time = scanner.nextLine();
        transaction.setTime(time);
    }

    private static void editMoney(Transaction transaction, Scanner scanner) {
        System.out.println("Nhap so tien giao dich moi:");
        int money = scanner.nextInt();
        transaction.setMoney(money);
    }

    private static void editKindOfTransaction(Transaction transaction, Scanner scanner) {
        scanner.nextLine();
        System.out.println("Nhap loai giao dich moi:");
        String kind = scanner.nextLine();
        transaction.setKindOfTransaction(kind);
    }

    private static void editTransactionName(Transaction transaction, Scanner scanner) {
        scanner.nextLine();
        System.out.println("Nhap ten giao dich moi:");
        String name = scanner.nextLine();
        transaction.setNameTransaction(name);
    }

    public ArrayList<Transaction> searchTransaction() {
        Scanner scannerSearch = new Scanner(System.in);
        int search;
        do {
            ArrayList<Transaction> data = new ArrayList<Transaction>();
            System.out.println("Nhap 1 de tim giao dich theo ten:");
            System.out.println("Nhap 2 de tim giao dich theo loai:");
            System.out.println("Nhap 3 de tim giao dich theo thoi gian:");
            search = scannerSearch.nextInt();

            switch (search) {
                case 1:
                    searchByName(scannerSearch, data);
                    return data;
                case 2:
                    searchByKind(scannerSearch, data);
                    return data;
                case 3:
                    searchByDate(scannerSearch, data);
                    return data;
            }
        } while (true);
    }

    private void searchByDate(Scanner scanner, ArrayList<Transaction> data) {
        scanner.nextLine();
        System.out.println("Nhap khoang thoi gian tim kiem:");
        String date1 = scanner.nextLine();
        String date2 = scanner.nextLine();
        LocalDate firstDate = LocalDate.parse(date1);
        LocalDate secondDate = LocalDate.parse(date2);
        for (Transaction transaction: transactionManager) {
            LocalDate transactionDate = LocalDate.parse(transaction.getTime());
            int checkFirst = transactionDate.compareTo(firstDate);
            int checkLast = transactionDate.compareTo(secondDate);
            if (checkFirst >= 0 && checkLast <= 0) {
                data.add(transaction);
            }
        }
    }

    private void searchByKind(Scanner scanner, ArrayList<Transaction> data) {
        scanner.nextLine();
        System.out.println("Nhap loai giao dich can tim:");
        String kindSearch = scanner.nextLine();
        for (Transaction transaction: transactionManager) {
            if (transaction.getKindOfTransaction().equals(kindSearch)) {
                data.add(transaction);
            }
        }
    }

    private void searchByName(Scanner scanner, ArrayList<Transaction> data) {
        scanner.nextLine();
        System.out.println("Nhap ten giao dich can tim:");
        String nameSearch = scanner.nextLine();
        for (Transaction transaction: transactionManager) {
            if (transaction.getNameTransaction().equals(nameSearch)) {
                data.add(transaction);
            }
        }
    }

    public void sortTransaction() {
        Scanner scannerSearch = new Scanner(System.in);
        int sort;
        do {
            ArrayList<Transaction> data = new ArrayList<Transaction>();
            System.out.println("Nhap 1 de sap xep theo ngay cu nhat:");
            System.out.println("Nhap 2 de sap xep theo ngay moi nhat:");
            System.out.println("Nhap 3 sap xep theo so tien nho nhat:");
            System.out.println("Nhap 4 sap xep theo so tien lon nhat:");
            sort = scannerSearch.nextInt();

            switch (sort) {
                case 1:
                    sortTransactionByOldestDate();
                    return;
                case 2:
                    sortTransactionByNewestDate();
                    return;
                case 3:
                    sortTransactionByLowerMoney();
                    return;
                case 4:
                    sortTransactionByHigherMoney();
                    return;
            }
        } while (true);
    }

    private void sortTransactionByHigherMoney() {
        for (int i = 0; i < transactionManager.size(); i++) {
            int firstMoney = transactionManager.get(i).getMoney();
            for (int j = i + 1; j < transactionManager.size(); j++) {
                int secondMoney = transactionManager.get(j).getMoney();
                if (firstMoney < secondMoney) {
                    Collections.swap(transactionManager, i, j);
                }
            }
        }
    }

    private void sortTransactionByLowerMoney() {
        for (int i = 0; i < transactionManager.size(); i++) {
            int firstMoney = transactionManager.get(i).getMoney();
            for (int j = i + 1; j < transactionManager.size(); j++) {
                int secondMoney = transactionManager.get(j).getMoney();
                if (firstMoney > secondMoney) {
                    Collections.swap(transactionManager, i, j);
                }
            }
        }
    }

    private void sortTransactionByNewestDate() {
        for (int i = 0; i < transactionManager.size(); i++) {
            LocalDate firstDate = LocalDate.parse(transactionManager.get(i).getTime());
            for (int j = i + 1; j < transactionManager.size(); j++) {
                LocalDate secondDate = LocalDate.parse(transactionManager.get(j).getTime());
                if (firstDate.isBefore(secondDate)) {
                    Collections.swap(transactionManager, i, j);
                }
            }
        }
    }

    private void sortTransactionByOldestDate() {
        for (int i = 0; i < transactionManager.size(); i++) {
            LocalDate firstDate = LocalDate.parse(transactionManager.get(i).getTime());
            for (int j = i + 1; j < transactionManager.size(); j++) {
                LocalDate secondDate = LocalDate.parse(transactionManager.get(j).getTime());
                if (firstDate.isAfter(secondDate)) {
                    Collections.swap(transactionManager, i, j);
                }
            }
        }
    }

    public void display(ArrayList<Transaction> data) {
        for (Transaction d: data) {
            System.out.println("\n" + d.getInfo());
        }
    }
}
