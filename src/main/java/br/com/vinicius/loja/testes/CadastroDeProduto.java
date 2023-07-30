package br.com.vinicius.loja.testes;

import br.com.vinicius.loja.Dao.CategoriaDao;
import br.com.vinicius.loja.Dao.ProdutoDao;
import br.com.vinicius.loja.JPAUtil.JPAUtil;
import br.com.vinicius.loja.modelo.Categoria;
import br.com.vinicius.loja.modelo.Produto;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CadastroDeProduto {

    public static void main(String[] args) {
        cadastrarProduto();
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);

        Produto p = produtoDao.buscarPorId(1l);
        System.out.println(p.getPreco());

        List<Produto> todos = produtoDao.buscarPorNomeDaCategoria("CELULARES");
        todos.forEach(p2 -> System.out.println(p.getNome()));

        BigDecimal precoDoProduto = produtoDao.buscarPrecoDoProdutoComNome("Xiaomi Redmi");
        System.out.println("Preco do Produto: " + precoDoProduto);

    }
//        {
//        Usado esse bloco para metodo de atualização
//        em.persist(celulares);
//        celulares.setNome("XPTO");
//
//        em.flush();
//        em.clear();
//
//        celulares = em.merge(celulares);
//        celulares.setNome("1234");
//
//        //Está no estado Managed
//        em.flush();
//        em.remove(celulares);
//        em.flush();
//        }

    private static void cadastrarProduto() {
        Categoria celulares = new Categoria("CELULARES");
        Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares);

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);

        //Geralmente os Frameworks fazem toda parte de transaction, porém esse é o Jpa puro
        //Meu value é resouce_local entao tenho que usar getTransaction e usar begin para iniciar
        em.getTransaction().begin();

        categoriaDao.cadastrar(celulares);
        produtoDao.cadastrar(celular);
        //Agora preciso commitar isso no BD
        em.getTransaction().commit();
        em.close();
    }

}
