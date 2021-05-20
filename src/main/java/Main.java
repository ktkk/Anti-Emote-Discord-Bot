import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Emote;

import javax.security.auth.login.LoginException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class Main {


    public static void main(String[] args) throws LoginException, IOException {
        File file = new File( new File( System.getProperty("user.home") ).getAbsolutePath()+"/Bot Token"  );
        BufferedReader br = new BufferedReader( new FileReader(file.getAbsolutePath()) );
        String token = br.readLine();

        JDA api = JDABuilder.createDefault(token).build();
        GuildMessageReceived g = new GuildMessageReceived(api);
        api.addEventListener(g);
    }


}
