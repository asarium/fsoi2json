package net.hardlight;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class JSONMigratorTest
{
    public static final String[] INSTALLER_HOME_URLs = new String[]{
            "http://www.fsoinstaller.com/files/installer/java/",
            "http://scp.indiegames.us/fsoinstaller/"
    };

    @Test
    public void testMigrate() throws Exception
    {
        JSONMigrator migrator = new JSONMigrator();

        for (String url : INSTALLER_HOME_URLs)
        {
            // assemble URLs
            URL filenameURL;
            try
            {
                filenameURL = new URL(url + "filenames.txt");
            }
            catch (MalformedURLException murle)
            {
                continue;
            }
            System.out.printf("======> %s%n", url);

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(filenameURL.openStream())))
            {
                String line;

                while ((line = reader.readLine()) != null)
                {
                    System.out.printf("===> %s%n", line);

                    try (BufferedReader nodeReader = new BufferedReader(new InputStreamReader(new URL(
                            line).openStream())))
                    {
                        migrator.migrate(nodeReader);
                    }
                }
            }
        }

        System.out.println(migrator.toJSON());
    }
}
