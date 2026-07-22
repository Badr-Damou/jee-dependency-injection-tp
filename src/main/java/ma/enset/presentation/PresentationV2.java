package ma.enset.presentation;

import ma.enset.dao.IDao;
import ma.enset.metier.IMetier;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;

public class PresentationV2 {
    public static void main(String[] args) throws ReflectiveOperationException, IOException {
        String daoClassName;
        String metierClassName;

        InputStream configStream = PresentationV2.class.getClassLoader()
                .getResourceAsStream("config.txt");

        if (configStream == null) {
            throw new IllegalStateException("Le fichier config.txt est introuvable dans le classpath");
        }

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(configStream, StandardCharsets.UTF_8))) {
            daoClassName = reader.readLine();
            metierClassName = reader.readLine();
        }

        if (daoClassName == null || metierClassName == null) {
            throw new IllegalStateException("Le fichier config.txt doit contenir les classes DAO et métier");
        }

        IDao dao = (IDao) Class.forName(daoClassName)
                .getDeclaredConstructor()
                .newInstance();

        IMetier metier = (IMetier) Class.forName(metierClassName)
                .getDeclaredConstructor()
                .newInstance();

        Method setDao = metier.getClass().getMethod("setDao", IDao.class);
        setDao.invoke(metier, dao);

        System.out.println("Résultat : " + metier.calcul());
    }
}
