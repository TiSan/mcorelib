package de.timc.mcorelib.jsonchat;

import net.minecraft.server.v1_8_R2.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R2.PacketPlayOutChat;

import org.bukkit.craftbukkit.v1_8_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JSONChatMessage {
    private JSONObject chatObject;

    @SuppressWarnings("unchecked")
	public JSONChatMessage(String text, JSONChatColor color, JSONChatFormat... formats) {
        chatObject = new JSONObject();
        chatObject.put("text", text);
        if (color != null) {
            chatObject.put("color", color.getColorString());
        }
        if (formats != null) {
            for (JSONChatFormat format : formats) {
                chatObject.put(format.getFormatString(), true);
            }
        }
    }

    @SuppressWarnings("unchecked")
	public void addExtra(JSONChatExtra extraObject) {
        if (!chatObject.containsKey("extra")) {
            chatObject.put("extra", new JSONArray());
        }
        JSONArray extra = (JSONArray) chatObject.get("extra");
        extra.add(extraObject.toJSON());
        chatObject.put("extra", extra);
    }
    public void sendToPlayer(Player player) {
        //Bukkit.getLogger().info(chatObject.toJSONString());
        //Packet3Chat packet = new Packet3Chat(chatObject.toJSONString(), true);
        //((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    	
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(new PacketPlayOutChat(ChatSerializer.a(chatObject.toJSONString())));

    }

    public String toString() {
        return chatObject.toJSONString();
    }
}
