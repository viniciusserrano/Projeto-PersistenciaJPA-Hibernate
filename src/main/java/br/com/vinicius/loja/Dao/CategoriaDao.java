package br.com.vinicius.loja.Dao;

import br.com.vinicius.loja.modelo.Categoria;

import javax.persistence.EntityManager;

public class CategoriaDao {

    private EntityManager em;

    public CategoriaDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Categoria categoria) {
        this.em.persist(categoria);
    }

    public void atualizar(Categoria categoria) {
        this.em.merge(categoria);
    }

    //Precisa estar no estado Managed para remover (Verificar imagem no projeto)
    public void remover(Categoria categoria) {
        //categoria = em.merge(categoria); Deixando dessa forma atribuindo garanto que estou passando a entidade para Managed
        categoria = em.merge(categoria);
        this.em.remove(categoria);
    }

}
