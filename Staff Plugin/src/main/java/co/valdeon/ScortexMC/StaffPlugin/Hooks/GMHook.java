package co.valdeon.ScortexMC.StaffPlugin.Hooks;

import co.valdeon.ScortexMC.StaffPlugin.StaffPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.anjocaido.groupmanager.GroupManager;
import org.anjocaido.groupmanager.permissions.AnjoPermissionsHandler;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class GMHook {

    private GroupManager gm = null;

    public GMHook (Plugin p) {
        if(p != null && p.isEnabled() && p instanceof GroupManager)
            this.gm = (GroupManager)p;
    }

    public GroupManager getGM() {
        return this.gm;
    }

    public AnjoPermissionsHandler getHandler(Player p) {
        AnjoPermissionsHandler h = gm.getWorldsHolder().getWorldPermissions(p);
        return h != null ? h : null;
    }

    public static String getProperTitle(Player p, AnjoPermissionsHandler h) {
        return StaffPlugin.onlyPrefixes ? ChatColor.translateAlternateColorCodes('&', h.getGroupPrefix(h.getGroup(p.getName()))) : ChatColor.translateAlternateColorCodes('&', h.getGroupPrefix(h.getGroup(p.getName())) + h.getGroup(p.getName()) + " |&f");
    }
}
