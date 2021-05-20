import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Emote;

import javax.security.auth.login.LoginException;


public class Main {


    public static void main(String[] args) throws LoginException {
        JDA api = JDABuilder.createDefault("ODQzODU0NDQ4MTc3NDQ2OTIy.YKJ6sg.fAiB4YM58gT3T1FW1xziOz1Bw10").build();
        GuildMessageReceived g = new GuildMessageReceived(api);
        api.addEventListener(g);
    }


}
