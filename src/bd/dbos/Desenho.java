package bd.dbos;


public class Desenho implements Cloneable
{
    private int     codigo;
    private String 	nome;
    private String 	desenho;
    private String	criacao;
    private String	atualizacao;
    

    public void setCodigo (int codigo) throws Exception
    {
        if (codigo < 0)
            throw new Exception ("Codigo invalido");

        this.codigo = codigo;
    }  
    
    public void setNome (String nome) throws Exception
    {
    	if (nome == null || nome.equals(""))
            throw new Exception ("Desenho nao fornecido");

        this.nome = nome;;
    }
    
    public void setDesenho (String desenho) throws Exception
    {
    	if (desenho == null || desenho.equals(""))
            throw new Exception ("Desenho nao fornecido");

        this.desenho = desenho;
    }

    public void setCriacao (String criacao) throws Exception
    {
        if (criacao == null || criacao.equals(""))
            throw new Exception ("Cria��o invalida");

        this.criacao = criacao;
    }
    
    public void setAtualizacao (String atualizacao) throws Exception
    {
        if (atualizacao == null || atualizacao.equals(""))
            throw new Exception ("Atualiza��o invalida");

        this.atualizacao = atualizacao;
    }

    public int getCodigo ()
    {
        return this.codigo;
    }
    
    public String getNome ()
    {
        return this.nome;
    }

    public String getCriacao ()
    {
        return this.criacao;
    }
    
    public String getDesenho()
    {
    	return this.desenho;
    }
    
    public String getAtualizacao()
    {
        return this.atualizacao;
    }

    public Desenho (int codigo, String nome, String desenho, String criacao, String atualizacao)throws Exception
    {
        this.setCodigo 		(codigo);
        this.setDesenho 	(desenho);
        this.setNome		(nome);
        this.setCriacao 	(criacao);
        this.setAtualizacao (atualizacao);
    }
    

    public String toString ()
    {
        String ret="";

        ret+="Codigo: " + this.codigo +"\n";
        ret+="Nome..: " + this.nome  +"\n";
        ret+="Desenho: " + this.desenho +"\n";
        ret+="criacao.: " + this.criacao + "\n";
        ret+="atualizacao.: " + this.atualizacao;

        return ret;
    }

    public boolean equals (Object obj)
    {
        if (this==obj)
            return true;

        if (obj==null)
            return false;

        if (!(obj instanceof Desenho))
            return false;

        Desenho des = (Desenho)obj;

        if(this.codigo != des.codigo)
            return false;
        
        if(this.desenho.equals(des.desenho))
        	return false;

        if(this.nome.equals(des.nome))
            return false;

        if(this.criacao.equals(des.criacao))
            return false;
        
        if(this.atualizacao.equals(des.atualizacao))
            return false;

        return true;
    }

    public int hashCode ()
    {
        int ret=666;

        ret = 7*ret + this.nome.hashCode();
        ret = 7*ret + this.desenho.hashCode();
        ret = 7*ret + this.criacao.hashCode();
        ret = 7*ret + this.atualizacao.hashCode();

        return ret;
    }


    public Desenho (Desenho modelo) throws Exception
    {
        this.codigo 		= modelo.codigo;
        this.nome 			= modelo.nome;
        this.desenho		= modelo.desenho;
        this.criacao  		= modelo.criacao;
        this.atualizacao 	= modelo.atualizacao;
    }


	public Object clone ()
    {
        Desenho ret=null;
        try
        {
            ret = new Desenho (this);
        }
        catch (Exception erro)
        {}

        return ret;
    }

}