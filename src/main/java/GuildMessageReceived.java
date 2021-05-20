import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;


import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GuildMessageReceived extends ListenerAdapter {

    private JDA api;
    private String prefix = "!";

    private Pattern emotePattern = Pattern.compile(":\\w+:", Pattern.CASE_INSENSITIVE);
    private Pattern susPattern   = Pattern.compile("sus", Pattern.CASE_INSENSITIVE);
    private Pattern bonkPattern  = Pattern.compile(prefix + "bonk", Pattern.CASE_INSENSITIVE);

    private Message message;
    private MessageReceivedEvent event;

    private int count = 0;
    private String[] iHateThisEmotes = {"ℹ", "\uD83C\uDDED", "\uD83C\uDFB1", "\uD83C\uDDE9", "\uD83C\uDDEE", "\uD83C\uDDF8"};


    public GuildMessageReceived(JDA jda) {
        this.api = jda;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;


        this.event = event;
        message = event.getMessage();
        String content = message.getContentRaw();

        int upper = 8;
        if ( content.length() < upper) upper = content.length();
        Matcher bonkMatcher = bonkPattern.matcher(content.substring(0,upper));
        if ( bonkMatcher.find() )
            bonk();

        if ( event.getAuthor().getName().equals("AB55AL") && content.equals( prefix + "shutdown")) {
//            TextChannel textChannel = event.getGuild().getTextChannelsByName(event.getChannel().getName(),true).get(0);
//            textChannel.sendMessage("test").queue();
//            event.getMessage().delete();
            this.api.shutdownNow();
        }


        Matcher susMatcher = susPattern.matcher(content);
        if ( susMatcher.find() )
            sus();

        Matcher emoteMatcher = emotePattern.matcher(content);
        if ( event.getAuthor().getName().equals("Zennie") || event.getAuthor().getName().equals("KatKak") )
            if (emoteMatcher.find())
                emoteHate();


    }


    private void bonk() {
        List<Member> memberList = message.getMentionedMembers();
        TextChannel textChannel = event.getGuild().getTextChannelsByName(event.getChannel().getName(),true).get(0);
        String gif = "https://tenor.com/view/kendo-shinai-bonk-doge-horny-gif-20995284 ";

        if ( memberList.isEmpty() ) {
            textChannel.sendMessage(gif + event.getAuthor().getAsMention()).queue();
            return;
        }

        for ( Member m : memberList )
            textChannel.sendMessage(gif + m.getAsMention()).queue();

    }

    private void emoteHate() {

        message.addReaction(":AstraExtraSerious:791091180157927444").queue();
        count++;

        if (count % 5 == 0)
            message.addReaction(":AstraRage:818098186748559390").queue();

        if (count % 50 == 0)
            for (String s : iHateThisEmotes)
                message.addReaction(s).queue();

    }

    private void sus() {
        message.addReaction(":Sustraeus:822853144362483733").queue();
    }
}
