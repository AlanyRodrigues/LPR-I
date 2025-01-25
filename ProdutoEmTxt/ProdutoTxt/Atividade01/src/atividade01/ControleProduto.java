/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atividade01;

/**
 *
 * @author 0077116
 * 
 */

import java.io.*;
import java.util.ArrayList;

public class ControleProduto {
    private final String arquivo = "produtos.txt";

    public ControleProduto() {
        carregarProdutos();
    }

    public void adicionarProduto(Produto produto) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo, true))) {
            bw.write(produto.getCodigo() + ";" + produto.getNome() + ";" + produto.getPreco() + ";" + produto.getUnidade() + ";" + produto.getEstoque());
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Erro ao salvar produto no arquivo: " + e.getMessage());
        }
    }

    public String getProdutos() {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length == 5) {
                    Produto produto = new Produto(
                            Integer.parseInt(dados[0]),
                            dados[1],
                            Double.parseDouble(dados[2]),
                            dados[3],
                            Integer.parseInt(dados[4])
                    );
                    sb.append(produto.descricaoProduto()).append("\n\n");
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler produtos do arquivo: " + e.getMessage());
        }
        return sb.toString();
    }
    
    private void carregarProdutos() {
        File file = new File(arquivo);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.err.println("Erro ao criar arquivo de produtos: " + e.getMessage());
        }
    }
}
