package bd.daos;

import java.sql.*;
import bd.*;
import bd.core.*;
import bd.dbos.*;

public class Desenhos
{
    public static boolean cadastrado (String nome) throws Exception
    {
        boolean retorno = false;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM DESENHOS " +
                  "WHERE NOME = ?";

            BDOracle.COMANDO.prepareStatement (sql);

            BDOracle.COMANDO.setString (1, nome);

            MeuResultSet resultado = (MeuResultSet)BDOracle.COMANDO.executeQuery ();

            retorno = resultado.first(); 
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar livro");
        }

        return retorno;
    }

    public static void incluir (Desenho desenho) throws Exception
    {
        if (desenho == null)
            throw new Exception ("Desenho nao fornecido");

        try
        {
            String sql;

            sql = "INSERT INTO DESENHOS " +
                  "(CODIGO,NOME,DESENHO,CRIACAO,ATUALIZACAO) " +
                  "VALUES " +
                  "(?,?,?,?,?)";

            BDOracle.COMANDO.prepareStatement (sql);
            
            BDOracle.COMANDO.setInt			(1, desenho.getCodigo ());
            BDOracle.COMANDO.setString 		(2, desenho.getNome ());
            BDOracle.COMANDO.setString 		(3, desenho.getDesenho ());
            BDOracle.COMANDO.setString  	(4, desenho.getCriacao ());
            BDOracle.COMANDO.setString  	(5, desenho.getAtualizacao ());

            BDOracle.COMANDO.executeUpdate ();
            BDOracle.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
        	BDOracle.COMANDO.rollback();
            throw new Exception ("Erro ao inserir desenho");
        }
    }

    public static void excluir (String nome) throws Exception
    {
        if (!cadastrado (nome))
            throw new Exception ("Nao cadastrado");

        try
        {
            String sql;

            sql = "DELETE FROM DESENHOS " +
                  "WHERE CODIGO = ?";

            BDOracle.COMANDO.prepareStatement (sql);

            BDOracle.COMANDO.setString (1, nome);

            BDOracle.COMANDO.executeUpdate ();
            BDOracle.COMANDO.commit        ();        }
        catch (SQLException erro)
        {
        	BDOracle.COMANDO.rollback();
            throw new Exception ("Erro ao excluir livro");
        }
    }

    public static void alterar (Desenho desenho) throws Exception
    {
        if (desenho == null)
            throw new Exception ("Livro nao fornecido");

        if (!cadastrado (desenho.getNome()))
            throw new Exception ("Nao cadastrado");

        try
        {
            String sql;

            sql = "UPDATE DESENHOS " +
                  "SET DESENHO = ?, " +
                  "ATUALIZACAO = ? " +
                  "WHERE NOME = ?";

            BDOracle.COMANDO.prepareStatement (sql);

            BDOracle.COMANDO.setString 	(1, desenho.getDesenho ());
            BDOracle.COMANDO.setString 	(2, desenho.getAtualizacao ());
            BDOracle.COMANDO.setString 	(3, desenho.getNome ());

            BDOracle.COMANDO.executeUpdate ();
            BDOracle.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
        	BDOracle.COMANDO.rollback();
            throw new Exception ("Erro ao atualizar dados de livro");
        }
    }

    public static Desenho getDesenho (String nome) throws Exception
    {
        Desenho desenho = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM DESENHOS " +
                  "WHERE NOME = ?";

            BDOracle.COMANDO.prepareStatement (sql);

            BDOracle.COMANDO.setString (1, nome);

            MeuResultSet resultado = (MeuResultSet)BDOracle.COMANDO.executeQuery ();

            if (!resultado.first())
                throw new Exception ("Nao cadastrado");

            desenho = new Desenho 	(resultado.getInt("CODIGO"),
            						resultado.getString("NOME"),
            						resultado.getString("DESENHO"),
                               		resultado.getString("CRIACAO"),
                               		resultado.getString("ATUALIZACAO"));
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar livro");
        }

        return desenho;
    }

    public static MeuResultSet getDesenhos () throws Exception
    {
        MeuResultSet resultado = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM DESENHOS";

            BDOracle.COMANDO.prepareStatement (sql);

            resultado = (MeuResultSet)BDOracle.COMANDO.executeQuery ();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao recuperar desnhos");
        }

        return resultado;
    }
}
