import java.util.ArrayList;
import java.util.Scanner;

class Account{

  int PIN, SSN;
  double balance;

  void createAccount(){};
  boolean validate(){
    return true;
  };
  void deposit(){};
  void withdrawal(){};
  void transfer(){};
  static void query(){};

}

class checkingAccount extends Account{

  static int accountNumber = 1;
  static int location;
  static double lastDeposit = 0.0;

  static ArrayList<Integer>accounts = new ArrayList<Integer>();
  static ArrayList<Integer>pins = new ArrayList<Integer>();
  static ArrayList<Integer>ssns = new ArrayList<Integer>();
  static ArrayList<Double>balances = new ArrayList<Double>();

  static void createAccount(int PIN, int SSN, double balance){
    pins.add(PIN);
    ssns.add(SSN);
    balances.add(balance);
    accounts.add(checkingAccount.accountNumber);
    System.out.println("Your Checking Account Number is " + String.format("%05d", checkingAccount.accountNumber));
    checkingAccount.accountNumber++;
  }
  static void closeAccount(int accountNumber){
    for(int i = 0; i < checkingAccount.accounts.size() ; i++){
      if(accountNumber == checkingAccount.accounts.get(i)){
        checkingAccount.accounts.remove(i);
        checkingAccount.pins.remove(i);
        checkingAccount.ssns.remove(i);
        System.out.println("Your remaining Balance " + checkingAccount.balances.get(i) + " is withdrawn.");
        checkingAccount.balances.remove(i);
      }
    }
  }
  static boolean validate(int account, int PIN){
    int localPin = PIN;
    for (int i = 0; i < accounts.size(); i++){
      if(account == accounts.get(i)){
        System.out.println("Account found.");
        location = i;
        int tryPin = 1;
        int tryLeft = 2;
        while(tryPin <= 3){
          if(localPin == pins.get(i)){
            return true;
          }
          if(tryPin == 3){
            break;
          }
          System.out.println("Wrong Pin. You have " + tryLeft + " more tries.");
          Scanner input = new Scanner(System.in);
          localPin = input.nextInt();
          tryPin++;
          tryLeft--;
        }
      }
    }
    System.out.println("Account/PIN not match.");
    return false;
  }

  static void deposit(double amount){
    double newBalance = balances.get(location) + amount;
    balances.set(location, newBalance);
    lastDeposit = amount;
  }

  static void withdrawal(double amount){
    if(amount <= balances.get(location)){
      double newBalance = balances.get(location) - amount;
      balances.set(location, newBalance);
    }
    else{
      System.out.println("Amount exceed the current balance.");
    }  
  }

  static void query(){
    System.out.println("Your Balance = " + balances.get(location));
  }

  static void transfer(int account, double amount){
    if(amount <= balances.get(location)){
      for (int i = 0; i < accounts.size(); i++){
      if(accounts.get(i) == account){
          balances.set( i, (balances.get(i) + amount) );
          balances.set( location, (balances.get(location) - amount));
        }
      if(savingsAccount.accounts.get(i) == account){
          savingsAccount.balances.set( i, (savingsAccount.balances.get(i) + amount) );
          balances.set( location, (balances.get(location) - amount));
        }
      }
    }
    else{
      System.out.println("Insufficient fund.");
    }
  }

  static void lastDeposit(){
    System.out.println("Last Deposit: " + lastDeposit);
  }

  static void cancel(){
    System.out.println("Thanks for using our service and see you soon.");
  }

}
class savingsAccount extends Account{

  static int accountNumber = 10001;
  static int location;

  static ArrayList<Integer>accounts = new ArrayList<Integer>();
  static ArrayList<Integer>pins = new ArrayList<Integer>();
  static ArrayList<Integer>ssns = new ArrayList<Integer>();
  static ArrayList<Double>balances = new ArrayList<Double>();

  static void createAccount(int PIN, int SSN, double balance){
    pins.add(PIN);
    ssns.add(SSN);
    balances.add(balance);
    accounts.add(savingsAccount.accountNumber);
    System.out.println("Your Savings Account Number is " + savingsAccount.accountNumber);
    savingsAccount.accountNumber++;
  }

  static void closeAccount(int accountNumber){
    for(int i = 0; i < savingsAccount.accounts.size() ; i++){
      if(accountNumber == savingsAccount.accounts.get(i)){
        savingsAccount.accounts.remove(i);
        savingsAccount.pins.remove(i);
        savingsAccount.ssns.remove(i);
        System.out.println("Your remaining Balance " + savingsAccount.balances.get(i) + " is withdrawn.");
        savingsAccount.balances.remove(i);
      }
    }
  }
  static boolean validate(int account, int PIN){
    int localPin = PIN;
    for (int i = 0; i < accounts.size(); i++){
      if(account == accounts.get(i)){
        System.out.println("Account found.");
        location = i;
        int tryPin = 1;
        int tryLeft = 2;
        while(tryPin != 3){
          if(localPin == pins.get(i)){
            return true;
          }
          if(tryPin == 3){
            break;
          }
          System.out.println("Wrong Pin. You have " + tryLeft + " more tries.");
          Scanner input = new Scanner(System.in);
          localPin = input.nextInt();
          tryPin++;
          tryLeft--;
        }
      }
    }
    System.out.println("Account/PIN not match.");
    return false;
  }

  static void deposit(double amount){
    double newBalance = balances.get(location) + amount;
    balances.set(location, newBalance);
  }

  static void withdrawal(double amount){
    double newBalance = balances.get(location) - amount;
    balances.set(location, newBalance);
  }

  static void query(){
    System.out.println("Your Balance = " + balances.get(location));
  }

  static void transfer(int account, double amount){
    if(amount <= balances.get(location)){
      for (int i = 0; i < accounts.size(); i++){
      if(accounts.get(i) == account){
          balances.set( i, (balances.get(i) + amount) );
          balances.set( location, (balances.get(location) - amount));
        }
      if(checkingAccount.accounts.get(i) == account){
          checkingAccount.balances.set( i, (checkingAccount.balances.get(i) + amount) );
          balances.set( location, (balances.get(location) - amount));
        }
      }
    }
    else{
      System.out.println("Insufficient fund.");
    }
  }

  static void cancel(){
    System.out.println("Thanks for using our service and see you soon.");
  }

  static void timer(){
    System.out.println("Has it been a year?");
    Scanner input = new Scanner(System.in);
    boolean year = input.nextBoolean();
    if(year){
      balances.set( location, (balances.get(location) + (0.05 * balances.get(location))));
    }
  }

}
class Main {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    boolean redo = true;
    do{
    System.out.println("\tWelcome to HAWA BANK");
    System.out.println("1. Staff");
    System.out.println("2. Customer");
    System.out.println("Selection: ");
    Scanner in = new Scanner(System.in);
    int choice1 = in.nextInt();
    switch(choice1){
      case 1: System.out.println("1. Open Account");
              System.out.println("2. Close Account");
              int staff = in.nextInt();
              if(staff == 1){
                System.out.println("Customer PIN: ");
                int pin = in.nextInt();
                System.out.println("Customer Social Security Number:");
                int SSn = in.nextInt();
                System.out.println("Press 1 to open checking account or 2 to open savings account:");
                int accChoice = in.nextInt();
                if(accChoice == 1){
                  checkingAccount.createAccount(pin, SSn, 0.0);
                }
                if(accChoice == 2){
                  savingsAccount.createAccount(pin, SSn, 0.0);
                }
              }
              if(staff == 2){
                System.out.println("Press 1 to delete checking accont and 2 to delete savings account.");
                int accountType = in.nextInt();
                if(accountType == 1){
                  System.out.println("Customer Account Number: ");
                  int acc = in.nextInt();
                  checkingAccount.closeAccount(acc);
                }
                if(accountType == 2){
                  System.out.println("Customer Account Number: ");
                  int acc = in.nextInt();
                  savingsAccount.closeAccount(acc);
                }
              }
              break;
      case 2: do{ 
                  System.out.println("Press 1 for checking account or 2 for savings account.");
                  int userInput = input.nextInt();

                  if(userInput == 1){
                      System.out.println("Please enter your 5 digit Account Number: ");
                      int account = input.nextInt();
                      System.out.println("Enter your 4 digit pin.");
                      int pin = input.nextInt();
                      boolean approval = checkingAccount.validate(account, pin);
                      while(approval){
                        System.out.println("\tMAIN MENU: ");
                        System.out.println("1. Deposit");
                        System.out.println("2. Withdrawal");
                        System.out.println("3. Transfer");
                        System.out.println("4. Query");
                        System.out.println("5. Last Deposit");
                        System.out.println("6. Cancel");

                        System.out.println("Enter your choice: ");
                        int choice = input.nextInt();
                        switch(choice){
                          case 1: System.out.println("Amount? ");
                                  double tamount = input.nextDouble();
                                  checkingAccount.deposit(tamount);
                                  break;
                          case 2: System.out.println("Amount? ");
                                  double t1amount = input.nextDouble();
                                  checkingAccount.withdrawal(t1amount);
                                  break;
                          case 3: System.out.println("Amount? ");
                                  double t2amount = input.nextDouble();
                                  System.out.println("Account? ");
                                  int taccount = input.nextInt();
                                  checkingAccount.transfer(taccount, t2amount);
                                  break;
                          case 4: checkingAccount.query();
                                  break;
                          case 5: checkingAccount.lastDeposit();
                                  break;
                          case 6: checkingAccount.cancel();
                                  approval = false;
                                  break;
                        }
                      }
                    }

                  if(userInput == 2){
                      System.out.println("Please enter your 5 digit Account Number: ");
                      int account = input.nextInt();
                      System.out.println("Enter your 4 digit pin.");
                      int pin = input.nextInt();
                      boolean approval = savingsAccount.validate(account, pin);
                      while(approval){
                        System.out.println("\tMAIN MENU: ");
                        System.out.println("1. Deposit");
                        System.out.println("2. Withdrawal");
                        System.out.println("3. Transfer");
                        System.out.println("4. Query");
                        System.out.println("5. Cancel");

                        System.out.println("Enter your choice: ");
                        int choice = input.nextInt();
                        switch(choice){
                          case 1: System.out.println("Amount? ");
                                  double amount = input.nextDouble();
                                  savingsAccount.deposit(amount);
                                  break;
                          case 2: System.out.println("Amount? ");
                                  double t1amount = input.nextDouble();
                                  savingsAccount.withdrawal(t1amount);
                                  break;
                          case 3: System.out.println("Amount? ");
                                  double tamount = input.nextDouble();
                                  System.out.println("Account? ");
                                  int taccount = input.nextInt();
                                  savingsAccount.transfer(taccount, tamount);
                                  break;
                          case 4: savingsAccount.query();
                                  savingsAccount.timer();
                                  break;
                          case 5: savingsAccount.cancel();
                                  approval = false;
                                  break;
                        }
                      }
                    }
                  System.out.println("Want to continue?");
                  redo = input.nextBoolean();
                }while(redo);
                break;
      default: System.out.println("Thank you for your business.");
               break;
        
    }    
  }while(true);
  }
}