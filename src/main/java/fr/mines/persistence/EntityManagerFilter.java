package fr.mines.persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.*;
import java.io.IOException;

/**
 * Created by valentin on 23/10/15.
 */
public class EntityManagerFilter implements Filter
{
    private static final Logger LOGGER = LoggerFactory.getLogger(EntityManagerFilter.class);
    EntityManagerFactory emf;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
        destroy();
        emf = Persistence.createEntityManagerFactory("main-persistence");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
    {
        EntityManager em = null;
        try
        {
            em = emf.createEntityManager();
            JPAUtils.ENTITY_MANAGERS.set(em);
            filterChain.doFilter(servletRequest, servletResponse);
            JPAUtils.ENTITY_MANAGERS.remove();
        }
        finally
        {
            try
            {
                if (em != null) em.close();
            }
            catch(Throwable t)
            {
                LOGGER.error(t.getMessage());
            }
        }
    }

    @Override
    public void destroy()
    {
        if(emf != null) emf.close();
    }
}
