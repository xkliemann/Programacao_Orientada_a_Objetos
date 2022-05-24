package br.com.kliemann.sistemabancario.classes;

import br.com.kliemann.sistemabancario.exception.InsufficientFoundsException;
import br.com.kliemann.sistemabancario.exception.NullDataException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ContaPoupanca {
    
    private double totalDaConta;
    private ArrayList<Transacao> transacao = new ArrayList<Transacao>();
    private ArrayList<Transacao> extrato = new ArrayList<Transacao>();
    
    public void withdraw(Double valor) {
        
        if(valor < 0 || valor > totalDaConta) {
            throw new InsufficientFoundsException();
        }
        this.transacao.add(new Transacao(LocalDate.now(), valor, "Saque"));
        totalDaConta -= valor;
        
    }
    
    public void deposit(Double valor) {
       
        if(valor < 0) {
            throw new InsufficientFoundsException();
        }
        this.transacao.add(new Transacao(LocalDate.now(), valor, "Deposito"));
        totalDaConta += valor;
        
    }
    
    public void extractByPeriod(LocalDate dataInicial, LocalDate dataFinal) {
        
        if(dataInicial == null || dataFinal == null) {
            throw new NullDataException();
        }
        for(Transacao transacao : this.transacao) {
            if(transacao.getData().isAfter(dataInicial) && transacao.getData().isBefore(dataFinal)) {
                extrato.add(transacao);
            }
        }
        
    }
    
    public void getBankBalanceByDate(LocalDate data) {
  
        if(data == null) {
            throw new NullDataException();
        }
        if(data.equals(data)) {
            for(Transacao transacao : this.transacao) {
                if(transacao.getData().equals(data)) {
                    extrato.add(transacao);
                }
            }
        }

    }

    @Override
    public String toString() {
        return "ContaPoupanca{" + "totalDaConta=" + totalDaConta + ", transacao=" + transacao + ", extrato=" + extrato + '}';
    }
    
}
