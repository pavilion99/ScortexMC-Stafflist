package co.valdeon.ScortexMC.StaffPlugin.Commands;

import co.valdeon.ScortexMC.StaffPlugin.StaffPlugin;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class StafflistAdminCmd implements CommandExecutor{

    private StaffPlugin instance;

    public StafflistAdminCmd(StaffPlugin p) { this.instance = p; }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(args.length != 1)
            return false;

        if(!args[0].equals("reload"))
            return false;

        switch(args[0]){
            case "reload":
                if(!sender.hasPermission(Bukkit.getPluginManager().getPermission("ScortexMC.staff.admin.reload")))
                    return false;
                instance.reload();
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&" + StaffPlugin.messageColor + "Stafflist plugin reloaded."));
                break;
            default:
                return false;
        }
        return true;
    }

}
