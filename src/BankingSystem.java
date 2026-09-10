import java.util.Scanner;

class Account {
    int accNo;
    String name;
    double balance;

    public Account(int accNo, String name, double balance) {
        this.accNo = accNo;
        this.name = name;
        this.balance = balance;
    }

    void deposit(double amount) {
        balance += amount;
    }

    boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }
}

public class BankingSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Account[] accounts = new Account[10];
        int count = 0;
        int choice;

        do {
            System.out.println("\n==== Online Banking System ====");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Amount");
            System.out.println("3. Withdraw Amount");
            System.out.println("4. Check Balance");
            System.out.println("5. View All Accounts");
            System.out.println("6. Exit");
            System.out.print("Enter your Choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    if (count >= accounts.length) {
                        System.out.println("Bank database is full! Cannot create more accounts.");
                        break;
                    }
                    System.out.print("Enter Account No: ");
                    int accNo = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    System.out.print("Enter your Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter balance: ");
                    double balance = sc.nextDouble();
                    accounts[count++] = new Account(accNo, name, balance);
                    System.out.println("Account Created Successfully.");
                    break;

                case 2:
                    System.out.print("Enter Account Number: ");
                    int depAcc = sc.nextInt();
                    System.out.print("Enter Deposit Amount: ");
                    double depAmt = sc.nextDouble();
                    boolean foundDep = false;
                    for (int i = 0; i < count; i++) {
                        if (accounts[i].accNo == depAcc) {
                            accounts[i].deposit(depAmt);
                            System.out.println("Amount Deposited Successfully.");
                            foundDep = true;
                            break;
                        }
                    }
                    if (!foundDep) System.out.println("Account not found.");
                    break;

                case 3:
                    System.out.print("Enter Account Number: ");
                    int wAcc = sc.nextInt();
                    System.out.print("Enter Withdraw Amount: ");
                    double wAmt = sc.nextDouble();
                    boolean foundWith = false;
                    for (int i = 0; i < count; i++) {
                        if (accounts[i].accNo == wAcc) {
                            foundWith = true;
                            if (accounts[i].withdraw(wAmt)) {
                                System.out.println("Withdrawal Successful.");
                                System.out.println("Remainig Balance: " + accounts[i].balance);
                            } else {
                                System.out.println("Insufficient Money.");
                            }
                            break;
                        }
                    }
                    if (!foundWith) System.out.println("Account not found.");
                    break;

                case 4:
                    System.out.print("Enter Account Number: ");
                    int checkAcc = sc.nextInt();
                    boolean foundCheck = false;
                    for (int i = 0; i < count; i++) {
                        if (accounts[i].accNo == checkAcc) {
                            System.out.println("Name: " + accounts[i].name);
                            System.out.println("Balance: " + accounts[i].balance);
                            foundCheck = true;
                            break;
                        }
                    }
                    if (!foundCheck) System.out.println("Account not found.");
                    break;

                case 5:
                    System.out.println("\n--- All Accounts ---");
                    if (count == 0) {
                        System.out.println("No accounts exist yet.");
                    } else {
                        for (int i = 0; i < count; i++) {
                            System.out.println("Acc Number: " + accounts[i].accNo +
                                    " | Name: " + accounts[i].name +
                                    " | Balance: " + accounts[i].balance);
                        }
                    }
                    break;

                case 6:
                    System.out.println("Thank you for banking with us.");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }
        } while (choice != 6);

        sc.close();
    }
}
