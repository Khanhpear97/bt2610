import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("------Menu------");
        TransactionManager transactionManager = new TransactionManager();
        Scanner scanner = new Scanner(System.in);
        int choose;
        do {
            System.out.println("\n");
            System.out.println("1. Them giao dich");
            System.out.println("2. Xoa giao dich");
            System.out.println("3. Hien thi danh sach giao dich");
            System.out.println("4. Tim kiem giao dich");
            System.out.println("5. Sap xep danh sach giao dich");
            System.out.println("6. Sua thong tin giao dich");
            System.out.println("0. Thoat chuong trinh");
            choose = scanner.nextInt();
            switch (choose) {
                case 1:
                    transactionManager.addTransaction();
                    break;
                case 2:
                    transactionManager.removeTransaction();
                    break;
                case 3:
                    transactionManager.checkTransactionManager();
                    break;
                case 4:
                    ArrayList<Transaction> dataFilter = transactionManager.searchTransaction();
                    transactionManager.display(dataFilter);
                    break;
                case 5:
                    transactionManager.sortTransaction();
                    break;
                case 6:
                    transactionManager.editTransaction();
                    break;
                case 0:
                    return;
            }
        } while (true);
    }
}