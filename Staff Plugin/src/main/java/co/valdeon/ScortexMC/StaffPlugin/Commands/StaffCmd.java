package co.valdeon.ScortexMC.StaffPlugin.Commands;

import co.valdeon.ScortexMC.StaffPlugin.StaffPlugin;
import com.avaje.ebeaninternal.server.cluster.mcast.Message;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.MessageFormat;
import java.util.Iterator;
import java.util.Set;

public class StaffCmd implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(args.length != 0)
            return false;

        if(!cmd.getName().equalsIgnoreCase("staff"))
            return false;

        if(StaffPlugin.staffList.size() == 0){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&" + StaffPlugin.messageColor + StaffPlugin.noStaffMessage));
            return true;
        }

        String finalStaffList = "";
        Set<Player> staff = StaffPlugin.staffList.keySet();

        Iterator i = staff.iterator();

        if(!StaffPlugin.lineBreaks) {
            while(i.hasNext()) {
                Player p = (Player) i.next();
                finalStaffList += StaffPlugin.staffList.get(p);
                finalStaffList += " " + StaffPlugin.usernameColor + p.getName();
                if (i.hasNext())
                    finalStaffList += "&" + StaffPlugin.messageColor + ", ";
            }

            if (staff.size() == 1)
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageFormat.format(StaffPlugin.staff1Message, StaffPlugin.messageColor, finalStaffList, StaffPlugin.messageColor2)));
            else
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageFormat.format(StaffPlugin.staff0OrMoreMessage, StaffPlugin.messageColor, staff.size(), finalStaffList, StaffPlugin.messageColor2)));
        } else {
            if (staff.size() == 1)
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageFormat.format(StaffPlugin.staff1Message, StaffPlugin.messageColor, "", StaffPlugin.messageColor2)));
            else
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageFormat.format(StaffPlugin.staff0OrMoreMessage, StaffPlugin.messageColor, staff.size(), "", StaffPlugin.messageColor2)));

            while(i.hasNext()) {
                Player p = (Player) i.next();
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', StaffPlugin.staffList.get(p) + " &" + StaffPlugin.usernameColor + p.getName()));
            }
        }

        return true;
    }

}
