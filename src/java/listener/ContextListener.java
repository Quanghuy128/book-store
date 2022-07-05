package listener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author huy
 */
public class ContextListener implements Serializable, ServletContextListener{

    private List<String> loadListFromFile(String path) {
        FileReader fr = null;
        BufferedReader br = null;
        List<String> authList = new ArrayList<>();
        try {
            fr = new FileReader(new File(path));
            br = new BufferedReader(fr);
            String line = null;
            while ((line = br.readLine()) != null) {
                authList.add(line);
            }
            fr.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ContextListener.class.getName()).log(Level.SEVERE, "ContextListener_File_Not_Found", ex);
        } catch (IOException ex) {
            Logger.getLogger(ContextListener.class.getName()).log(Level.SEVERE, "ContextListener_IO_Error", ex);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(ContextListener.class.getName()).log(Level.SEVERE, "ContextListener_IO_Error", ex);
            }

        }
        return authList;
    }

    private Map<String, String> loadSiteMapFromFile(String path) {
        FileReader fr = null;
        BufferedReader br = null;
        Map<String, String> siteMap = new HashMap<>();
        try {
            fr = new FileReader(new File(path));
            br = new BufferedReader(fr);
            String line = null;
            while ((line = br.readLine()) != null) {
                int index = line.indexOf("=");
                String key = line.substring(0, index);
                String value = line.substring(index + 1);
                siteMap.put(key, value);
            }
            fr.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ContextListener.class.getName()).log(Level.SEVERE, "ContextListener_File_Not_Found", ex);
        } catch (IOException ex) {
            Logger.getLogger(ContextListener.class.getName()).log(Level.SEVERE, "ContextListener_IO_Error", ex);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }

                if (fr != null) {
                    fr.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(ContextListener.class.getName()).log(Level.SEVERE, "ContextListener_IO_Error", ex);
            }

        }
        return siteMap;
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext contex = sce.getServletContext();

        String path = sce.getServletContext().getRealPath("/WEB-INF/siteMap.txt");
        Map<String, String> siteMap = loadSiteMapFromFile(path);
        contex.setAttribute("SITE_MAP", siteMap);

        path = sce.getServletContext().getRealPath("/WEB-INF/authenticationList.txt");
        List<String> authList = loadListFromFile(path);
        contex.setAttribute("AUTH_LIST", authList);
        
        path = sce.getServletContext().getRealPath("/WEB-INF/checkoutSession.txt");
        List<String> checkoutSessionList = loadListFromFile(path);
        contex.setAttribute("CHECKOUT_SESSION_LIST", checkoutSessionList);

       
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
    
}
