package insert;
import employee.Employee;
import function.Keyboard;
import function.Logout;



/*
 *  僕の修正が消えてしまっていたので統合しておきました
 *  Keyboard.InputInt()は張さんのものをそのまま全体用に反映しました
 *  不要になったScannerについては削除しています 2023-05-27-16:40 Shimanaka
 */

/*
 *  class名をInsertControlとしました。 2023-05-27-16:47 Shimanaka
 */

public class InsertControl {
    //private static Scanner scanner; //static参照しました 2023-05-26-20:30 Shimanaka

    public static void insertEmployee() throws Logout{ // static参照し、操作間の統一の為にメソッド名を変更しました。 2023-05-26-20:27 Shimanaka
        //scanner = new Scanner(System.in);

        System.out.print("追加したい社員の社員番号を入力してください> ");
        int employeeId = Keyboard.kbInputInt();; //Stringをintに変更しました 2023-05-26-20:17 Shimanaka

        System.out.print("苗字を入力してください> ");
        String lastName = Keyboard.kbInput();

        System.out.print("名前を入力してください> ");
        String firstName = Keyboard.kbInput();

        System.out.print("苗字（カナ）を入力してください> ");
        String lastNameKana = Keyboard.kbInput();

        System.out.print("名前（カナ）を入力してください> ");
        String firstNameKana = Keyboard.kbInput();

        System.out.print("パスワードを入力してください> ");
        String password = Keyboard.kbInput();

        System.out.print("性別を入力(1:男性 2:女性)してください> ");
        int gender = Keyboard.kbInputInt();

        System.out.println("以下、対応表を参考に部署コード（数値）を入力してください:");
        System.out.println("*　100	: 　人事部　*");
        System.out.println("*　200	: 　経理部　*");
        System.out.println("*　300	: 　営業部　*");
        System.out.println("*　400	: 　企画部　*");
        System.out.println("*　500	: 　開発部　*");
        System.out.println("*　600	:　 総務部　*");
        System.out.print("> ");
        int departmentCode = Keyboard.kbInputInt(); //Stringをintに変更しました 2023-05-26-20:21 Shimanaka
        InsertDAO insertDAO = new InsertDAO();
        String departmentName = insertDAO.getDepartmentName(departmentCode);
        // Employee統合の為に引数を編集しました。
        // 部署名は直接入力されないので仮に人事部として置いてあります。
        Employee employee = new Employee(employeeId, lastName, firstName, lastNameKana, firstNameKana, password, gender, departmentCode, departmentName);

        System.out.println("*******************************************************");
        System.out.println("下記の社員を追加しますか。(はい: y, いいえ: n , 一部修正を行う:r)");
        System.out.println(employee.getFormattedInfo() + " > ");

        String option = Keyboard.kbInput();
        if (option.equalsIgnoreCase("y")) {

            boolean success = insertDAO.insertEmployee(employee, password, gender);
            if (success) {
                System.out.println("【社員番号：" + employeeId + " の社員情報を追加しました。】");
            } else {
                System.out.println("追加に失敗しました。");
            }
        } else if (option.equalsIgnoreCase("r")) {
            modifyEmployeeInformation(employee);
        } else {
            System.out.println("追加をキャンセルしました。");
        }

        //scanner.close();
    }

    // 新しく送っていただいた方に差し替えました 2023-05-27-16:46 Shimanaka
    /*
    public static void modifyEmployeeInformation(Employee employee) { //static参照しました 2023-05-26-20:30 Shimanaka
        boolean modified = false;
        InsertDAO insertDAO = new InsertDAO();
        while (!modified) {
            System.out.println("*******************************************************");
            // ...

            System.out.println("修正前：" + employee.getDeptname());// Employeeを統合するためgetter名を変更しました。2023-05-26-18:15 Shimanaka
            System.out.print("修正内容を入力してください> ");
            int newDepartmentCode = Keyboard.kbInputInt(); // Intに変更しました
            employee.setDeptno(newDepartmentCode);// Employeeを統合するためsetter名を変更しました。2023-05-26-18:15 Shimanaka

            // ...

            System.out.println("*******************************************************");
            System.out.println("下記の社員を追加しますか? (はい: y, いいえ: n, 一部修正を行う: r)");
            System.out.println(employee.getFormattedInfo() + " > ");
            String option = Keyboard.kbInput();

            if (option.equalsIgnoreCase("y")) {
                boolean success = insertDAO.insertEmployee(employee, employee.getPassword(), employee.getGender());
                if (success) {
                    System.out.println("【社員番号：" + employee.getFormattedInfo()  + " の社員情報を追加しました。】");
                    modified = true;
                } else {
                    System.out.println("追加に失敗しました。");
                    modified = true;
                }
            } else if (option.equalsIgnoreCase("n")) {
                modified = true;
            }
        }
    }
    */

    /*
     * UpdateControlクラスのことも考えてくださってこのような書き方をしてくださった事は承知していますが
     * 一旦結合の為にpublicをprivateに変更しました 2023-05-27-17:05 Shimanaka
     */
    private static void modifyEmployeeInformation(Employee employee) throws Logout{
        boolean modified = false;

        while (!modified) {
            System.out.println("*******************************************************");
            System.out.println("修正したい情報の変更キーを選択してください ");
            System.out.println("******************************");
            System.out.println("変更キー\t：項目");
            System.out.println("----------------------------");
            System.out.println("name\t\t氏名");
            System.out.println("password\tパスワード");
            System.out.println("sex\t\t性別");
            System.out.println("dept\t\t部署");
            System.out.println("******************************");
            System.out.print("> ");
            String key = Keyboard.kbInput();

            switch (key.toLowerCase()) {
                case "name":
                    System.out.println("氏名(変更前：" + employee.getFullName() + ")");
                    System.out.println("氏名を更新しますか？ (はい: y,いいえ  : n)");
                    System.out.print("> ");
                    String nameOption = Keyboard.kbInput();
                    if (nameOption.equalsIgnoreCase("y")) {
                        System.out.println("氏(変更前：" + employee.getLname() + ")> ");
                        String lastName = Keyboard.kbInput();
                        if (!lastName.isEmpty()) {
                            employee.setLname(lastName);
                        }

                        System.out.println("シ(変更前：" + employee.getLkana() + ")> ");
                        String lastNameKana = Keyboard.kbInput();
                        if (!lastNameKana.isEmpty()) {
                            employee.setLkana(lastNameKana);
                        }

                        System.out.println("名(変更前：" + employee.getFname() + ")> ");
                        String firstName = Keyboard.kbInput();
                        if (!firstName.isEmpty()) {
                            employee.setFname(firstName);
                        }

                        System.out.println("メイ(変更前：" + employee.getFkana() + ")> ");
                        String firstNameKana = Keyboard.kbInput();
                        if (!firstNameKana.isEmpty()) {
                            employee.setFkana(firstNameKana);
                        }
                    }
                    break;
                case "password":
                    System.out.println("修正前：" + employee.getPassword());
                    System.out.print("修正内容を入力してください> ");
                    String newPassword = Keyboard.kbInput();
                    employee.setPassword(newPassword);
                    break;
                case "sex":
                    System.out.println("修正前：" + employee.getGender());
                    System.out.print("修正内容を入力してください> ");
                    int newGender = Keyboard.kbInputInt();
                    employee.setGender(newGender);
                    break;
                case "dept":
                    System.out.println("修正前：" + employee.getDeptname());
                    System.out.println("以下、対応表を参考に部署コード（数値）を入力してください:");
                    System.out.println("*　100	: 　人事部　*");
                    System.out.println("*　200	: 　経理部　*");
                    System.out.println("*　300	: 　営業部　*");
                    System.out.println("*　400	: 　企画部　*");
                    System.out.println("*　500	: 　開発部　*");
                    System.out.println("*　600	:　 総務部　*");
                    System.out.print("> ");
                    // Stringからintに変更しました 2023-05-27-16:56 Shimanaka
                    int newDepartmentCode = Keyboard.kbInputInt();
                    InsertDAO insertDAO = new InsertDAO();
                    String newDepartmentName = insertDAO.getDepartmentName(newDepartmentCode);
                    if (newDepartmentName != null) {
                        employee.setDeptname(newDepartmentName);
                    } else {
                        System.out.println("無効な部署コードです。");
                    }
                    break;
                default:
                    System.out.println("無効な変更キーです。");
                    break;
            }

            System.out.println("下記の社員を追加しますか? (はい: y, いいえ: n, 一部修正を行う: r)");
            System.out.println(employee.getFormattedInfo() + " > ");
            String option = Keyboard.kbInput();
            if (option.equalsIgnoreCase("y")) {
            	InsertDAO insertDAO = new InsertDAO();
            boolean success = insertDAO.insertEmployee(employee, employee.getPassword(), employee.getGender());
            if (success) {
            	System.out.println("【社員番号：" + employee.getFormattedInfo()
            + " の社員情報を追加しました。】"); modified = true;
            }else { System.out.println("追加に失敗しました。"); modified = true;
             }
            } else if (option.equalsIgnoreCase("n")) { modified = true;
            }
        }
    }


    }
