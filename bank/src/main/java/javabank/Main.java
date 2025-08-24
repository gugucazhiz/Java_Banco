package javabank;

import java.util.Scanner;
import javabank.*;
import javabank.model.BankService;
import javabank.repository.AccountRepository;
import javabank.repository.InvestmentRepository;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static InvestmentRepository investmentRepository = new InvestmentRepository();
    static AccountRepository accountRepository = new AccountRepository();
    public static void main(String[] args) {
        System.out.println("Bem Vindo ao JavaBank!");

        while(true){
            System.out.println("Selecione Uma Opção:");
            System.out.println("1 - Criar Conta");
            System.out.println("2 - Criar Um Investimento");
            System.out.println("3 - Fazer Um Investimento");
            System.out.println("4 - Depositar ");
            System.out.println("5 - Sacar");
            System.out.println("6 - Transferir");
            System.out.println("7 - Sacar Investimento");
            System.out.println("8 - Atualizar Investimentos");
            System.out.println("9 - Ver Saldo Conta");
            System.out.println("10 - Ver Saldo Investimento");
            System.out.println();
            var option = scanner.nextInt();
            switch (option) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    createInvestment();
                    break;
                case 3:
                    depositInvestment();
                    break;
                case 4:
                    deposit();
                    break;
                case 5:
                    withDraw();
                    break;              
                case 6:
                    transfer();
                    break;
                case 7:
                    withDrawInvestment();
                    break;
                case 8:
                    investmentRepository.updateAmount(7);
                    System.out.println("Investimentos Atualizados");
                    sair();
                    break;
                case 9:
                    getBalance();
                    sair();
                    break;
                case 10:
                    getInvestmentBalance();
                    sair();
                    break;
                default:
                    System.out.println("Opção Inválida");
                    break;
            }
        }
    }
    private static void createAccount() {
        System.out.println("Digite Seu Nome:");
        var name = scanner.next();
        System.out.println("Digite Seu CPF:");
        var cpf = scanner.next();
        System.out.println("Digite Seu Email:");
        var email = scanner.next();
        System.out.println("Digite Seu Telefone:");
        var phone = scanner.next();
        System.out.println("Digite Seu Pix:");
        var pix = scanner.next();
        AccountRepository.create(pix,0);
        System.out.println("----------------Conta Criada-------------------");
        System.out.println(name+" Sua Conta Foi Criada Com Sucesso!");
        System.out.println("---------------------------------------------");
        System.out.println("Seu CPF é: "+ cpf);
        System.out.println("Seu Email é: "+ email);
        System.out.println("Seu Telefone é: "+ phone);
        System.out.println("Seu Pix é: "+ pix);
        System.out.println("---------------------------------------------");
        System.out.println("Aperte 1 Para Continuar");
        var enter = scanner.next();
    }

    private static void createInvestment() {
        System.out.println("Digite a Taxa do Investimento:");
        var tax = scanner.nextLong();
        System.out.println("Digite o Valor Inicial do Investimento:");
        var initialFunds = scanner.nextLong();
        System.out.println("Digite os Dias para Resgate:");
        var daysToRescue = scanner.nextLong();
        investmentRepository.create(tax, initialFunds, daysToRescue);
    }

    private static void depositInvestment() {
        System.out.println("Digite Seu Pix:");
        var pix = scanner.next();
        System.out.println("Digite o Valor do Depósito:");
        var amount = scanner.nextLong();
        System.out.println("----------------Depositado-------------------");
        System.out.println("Seu Depósito de "+ amount + " Foi Realizado Com Sucesso!");
        System.out.println("---------------------------------------------");
        investmentRepository.deposit(pix, amount);
    }

    private static void deposit() {
        System.out.println("Digite Seu Pix:");
        var pix = scanner.next();
        System.out.println("Digite o Valor do Depósito:");
        var amount = scanner.nextLong();
        System.out.println("----------------Depositado-------------------");
        System.out.println("Seu Depósito de "+ amount + " Foi Realizado Com Sucesso!");
        System.out.println("---------------------------------------------");
        accountRepository.deposit(pix, amount);
    }
    private static void withDraw() {
        System.out.println("Digite Seu Pix:");
        var pix = scanner.next();
        System.out.println("Digite o Valor do Saque:");
        var amount = scanner.nextLong();
        accountRepository.withDraw(pix, amount);
    }
    private static void transfer() {
        System.out.println("Digite Seu Pix:");
        var sourcepix = scanner.next();
        System.out.println("Digite o Pix do Destinatário:");
        var targetpix = scanner.next();
        System.out.println("Digite o Valor da Transferência:");
        var amount = scanner.nextLong();
        accountRepository.transferMoney(sourcepix, targetpix, amount);
    }
    private static void withDrawInvestment() {
        System.out.println("-----------------------------------");
        System.out.println("Digite Seu Pix:");
        var pix = scanner.next();
        System.out.println("Digite o Valor do Saque:");
        var amount = scanner.nextLong();
        investmentRepository.withDraw(pix, amount, "Saque de Investimento");
        System.out.println("-----------------------------------");
    }

    private static void getBalance(){
        System.out.println("-----------------------------------");
        System.out.println("Digite Seu Pix:");
        var pix = scanner.next();
        var balance = accountRepository.getBalance(pix);
        System.out.println("---------------SALDO---------------");
        System.out.println("Seu Saldo é: "+ balance);
        System.out.println("-----------------------------------");
    }
    public static void getInvestmentBalance(){
        System.out.println("-----------------------------------");
        System.out.println("Digite Seu Pix:");
        var pix = scanner.next();
        var balance = investmentRepository.getBalance(pix);
        System.out.println("---------------SALDO---------------");
        System.out.println("Seu Saldo é: "+ balance);
        System.out.println("-----------------------------------");
    }
    public static void sair(){
        System.out.println("Digite 1 Para Continuar ou 0 Para Sair");
            var exit = scanner.next();
            if(exit.equals("0")){
                System.out.println("Obrigado Por Usar o JavaBank!");
                System.exit(0);  
            }
    }
}