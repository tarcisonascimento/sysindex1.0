package br.com.rheacaoscannerpro.dal;

import java.sql.*;
/**
 *
 * @author Tarciso Nascimento
 */
    
    //antes de começar o modulo de conexao ou qualquer outro modulo deve-se importar as bibliotecas


public class ModuloConexao {

    /*metodo responsavel por estabelecer a conexao com o banco de dados
   --Connection é um framework ou conjunto de funcionalidades que vem do pacote java.sql
   --conector é o meu metodo de conexao
   --sempre que se cria um metodo sem a palavra void eu tenho que retornar algo.
     */
    public static Connection conector() {

        java.sql.Connection conexao = null; //usando o pacote java eu estou criando uma variavel chamada conexao e atribuo o valor nulo.
        //o metodo se chama conector mas a variavel se chama conexao
        //a linha abaixo chama o driver de conexao com o banco de dados, o driver importado para a biblioteca
        String driver = "com.mysql.jdbc.Driver"; //JDBC = abreviação Java Database Conector

        //variaveis para armazenar as informaçoes referentes ao banco de dados
        String url = "jdbc:mysql://localhost:3306/rheacaoestrutura"; //caminho do banco de dados
        String user = "root";//usuario do banco de dados
        String password = "";//senha do banco de dados

        try {
            Class.forName(driver); // essa linha executa o arquivo do driver
            conexao = DriverManager.getConnection(url, user, password); //obtem a conexao usando os parametros
            return conexao;

        } catch (Exception e) {
            // a linha abaixo se tirar o comentario mostra um erro caso a conexao com banco de dados falhe
            //JOptionPane.showMessageDialog(null, "Erro: " + e);
            //a linha abaixo serve de apoio para esclarecer o erro de conexao
            System.out.println(e);
            return null;

        }

    }

}
