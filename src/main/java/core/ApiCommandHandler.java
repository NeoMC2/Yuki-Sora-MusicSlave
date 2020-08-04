package core;

import commands.audioCore.AudioCommand;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.VoiceChannel;
import org.json.simple.JSONObject;

public class ApiCommandHandler {

    private AudioCommand audioCommand = new AudioCommand();

    public JSONObject handleApiCommand(JSONObject msg, Engine engine){
        JSONObject data = (JSONObject) msg.get("data");
        String inst = "";
        String guild = "";
        String member = "";
        String response = "";

        try {
            inst = (String) data.get("inst");
            guild = (String) data.get("guild");
            member = (String) data.get("member");
        } catch (Exception e){
        }

        String[] args = inst.split(" ");
        Guild g = engine.getDiscApplicationEngine().getBotJDA().getGuildById(guild);
        Member m = g.getMemberById(member);

        switch (args[0].toLowerCase()){
            case "add":
                response = audioCommand.add(args, m);
                break;

            case "play":
                response = audioCommand.play(args, m);
                break;

            case "stop":
                response = audioCommand.stop(m);
                break;

            case "skip":
                response = audioCommand.skip(args, m);
                break;

            case "shuffle":
                response = audioCommand.shuffle(m);
                break;

            case "info":
                response = audioCommand.info(m);
                break;

            case "queue":
                response = audioCommand.showQueue(args, m);
                break;
        }

        return engine.getFileUtils().convertStringToJson(response);
    }
}